# AI Interaction Journal

**Project:** Laboratory Work #1 — Designing an AI Agent for Development and
CI/CD Automation  
**Variant:** 5 — Java 17, Maven, JUnit 5  
**Purpose:** Maintain an auditable record of prompts, agent responses, file
changes, diagnostics, verification, and resolutions. Prompt text is transcribed
verbatim in the prompt register below; output summaries and tool diagnostics are
recorded in the journal table and activity record.

## Interaction journal

| Step / Prompt ID | Exact Prompt Sent | Summary of Copilot Output / Actions | Files Generated or Modified | Verification Status (Passed/Failed) | Encountered Errors & Resolution Notes |
|---|---|---|---|---|---|
| 7 / P-006 | [Verbatim prompt](#p-006--fix-linux-ci-crlf-and-matrix-fail-fast) | Updated the CI matrix to keep other OS jobs running after a failure; changed the Linux/macOS step to strip CRLF, set executable mode, and explicitly run the POSIX script with Bash. Rewrote `ci.sh` using strict POSIX shell syntax. | `.github/workflows/ci.yml`; `ci.sh`; `docs/ai-log.md` | Passed — static workflow requirement checks passed; `ci.sh` has no CRLF/bare CR bytes and `sh -n ci.sh` passed. No YAML validator was available; Maven and remote Actions execution were not run. | Error: "CI Build failed on ubuntu-latest within 3 seconds due to CRLF carriage return in ci.sh and fail-fast termination." Resolution: "Configured 'fail-fast: false', added sed stripping of CRLF characters, and explicitly invoked bash in ci.yml." |
| 6 / P-005 | [Verbatim prompt](#p-005--create-cross-platform-ci-workflow) | Inspected existing `ci.sh` and `ci.bat` and confirmed `.github/workflows/` was absent. Created the requested Java 17 cross-platform GitHub Actions workflow for Linux, Windows, and macOS without inline Maven commands. | `.github/workflows/ci.yml`; `docs/ai-log.md` | Passed — workflow content and target file existence reviewed. Maven execution and external workflow/YAML validation were not run; Maven is unavailable in the workspace. | No existing workflow required merging. Existing runner scripts were inspected and preserved. Workflow was statically reviewed; no remote Actions run was triggered. |
| 1 / P-001 | [Verbatim prompt](#p-001--strict-audit-journal-and-documentation-request) | Inspected the current workspace, noted the existing README, and regenerated this journal with the historical and current prompts plus diagnostic notes. Rewrote the architecture document and manifest to spell out orchestration gates, preservation rules, and `target/` handling. Progress updates disclosed the missing files, README preservation, and validation scope. | `docs/ai-log.md`; `docs/architecture.md`; `.github/agents/build-engineer.agent.md` | Passed — requested files and content reviewed after creation. Build/test/CI were not run because no Maven project is present. | Initial reads confirmed README content `123`; the three requested paths were absent. `git status --short` returned no entries. Final `git status --short` listed only the requested `.github/` and `docs/` directories as untracked. Preserved README unchanged; did not run build commands. |
| 2 / P-000 | [Verbatim prompt](#p-000--earlier-file-generation-request) | Created an initial interaction log, architecture overview, and `build-engineer` manifest; reviewed the files and reported that build/test/CI could not run because no Maven project existed. | `docs/ai-log.md`; `docs/architecture.md`; `.github/agents/build-engineer.agent.md` | Passed — files were created and reviewed in that earlier interaction. | Earlier inspection found only `.git/`; `git log -1` reported that `main` had no commits. Build/test/CI were not applicable because there was no Java/Maven project. At the start of this regeneration, the three files were absent; their disappearance is not attributed to a cause. |
| 3 / P-002 | [Verbatim prompt](#p-002--three-agent-skill-specifications) | Inspected customization skill conventions and the workspace; created complete `project-scaffold`, `build-and-test`, and `github-actions` skill specifications; appended the prompt and activity record to this journal. | `.github/skills/project-scaffold/SKILL.md`; `.github/skills/build-and-test/SKILL.md`; `.github/skills/github-actions/SKILL.md`; `docs/ai-log.md` | Passed — all three skill files and this journal entry were reviewed; no Java/Maven tests or workflow execution were applicable or run. | Initial inspection found no pre-existing files under `.github/skills/`; no conflicting skill files required resolution. Maven build, tests, workflow execution, and external YAML/Actions validation were not performed because this workspace has no Maven project/workflow or validator invocation. |
| 4 / P-003 | [Verbatim prompt](#p-003--six-build-engineer-command-prompts) | Read the prompt-file format, inspected the workspace and Git status, generated six idempotent command prompts, and recorded the request and diagnostics in this journal. | `.github/prompts/git-init.prompt.md`; `.github/prompts/create-project.prompt.md`; `.github/prompts/create-build.prompt.md`; `.github/prompts/create-actions.prompt.md`; `.github/prompts/check.prompt.md`; `.github/prompts/init.prompt.md`; `docs/ai-log.md` | Passed — all six prompt files and the journal record were reviewed. Maven tests and workflow validation were not run; this workspace contains no Maven project or workflow. | No existing prompt files or prompt-name collisions were found. Git status was clean before file generation. |
| 5 / P-004 | [Verbatim prompt](#p-004--execute-init-and-generate-variant-5-project) | Read and followed `init.prompt.md` in order. `git-init` added Java/Maven ignore rules; `create-project` created the source scaffold; `create-build` generated Java 17/JUnit 5 Maven configuration and tests. The required `mvn test` command could not run, so the orchestrator halted before `create-actions` and `check`. | `.gitignore`; `src/main/java/com/example/App.java`; `src/test/java/com/example/AppTest.java`; `pom.xml`; `docs/ai-log.md`. Not generated because execution halted: `ci.sh`, `ci.bat`, `.github/workflows/ci.yml`, `docs/usage.md`. | Failed / blocked — `git-init` and source-level scaffold checks passed. `mvn test` failed to start (PowerShell exit code 1); Maven test result unavailable. `create-actions` and `check` were not run per fail-fast instructions. | `mvn` was not recognized and no Maven installation was found on PATH or checked standard locations; Java reports version 25 rather than the requested Java 17. Action: stopped the workflow at `create-build` after recording the blocker; install/configure Maven and Java 17, then rerun `init`. |

### P-004 — Execute init and generate Variant 5 project

<pre>
Act strictly as the AI build-engineer agent. Read .github/prompts/init.prompt.md and execute the orchestration workflow end-to-end for our Variant 5 Java project:

1. Generate the complete source code and configuration files:
   - .gitignore
   - pom.xml (configured for Java 17, maven-compiler-plugin, maven-surefire-plugin, JUnit 5)
   - src/main/java/com/example/App.java (executable Hello World)
   - src/test/java/com/example/AppTest.java (unit test verifying App functionality)
   - ci.sh (POSIX shell script running mvn clean test package with executable permissions)
   - ci.bat (Windows batch script running mvn clean test package)
   - .github/workflows/ci.yml (multi-OS matrix workflow calling ci.sh on linux/macOS and ci.bat on windows)
2. Generate docs/usage.md explaining how to trigger prompts, run local tests, and build artifacts.
3. Provide the full log entry to append to docs/ai-log.md documenting the generated files and execution status.
</pre>

## Prompt register

### P-006 — Fix Linux CI CRLF and matrix fail-fast

<pre>
Our GitHub Actions pipeline failed on ubuntu-latest and cancelled other OS runners with an early execution error caused by CRLF line endings in ci.sh and fail-fast matrix behavior.

Please apply the following fixes:

1. Update .github/workflows/ci.yml:
   - In the build matrix strategy, add: `fail-fast: false`.
   - In the step for Linux/macOS runners (runner.os != 'Windows'), sanitize CRLF line endings before executing ci.sh:
     run: |
       sed -i -e 's/\r$//' ci.sh
       chmod +x ci.sh
       bash ci.sh
   - Keep Windows runner executing `ci.bat` via cmd.

2. Ensure ci.sh has strict POSIX syntax with LF formatting.

3. Provide the exact Markdown table row to append to docs/ai-log.md documenting:
   - Error: "CI Build failed on ubuntu-latest within 3 seconds due to CRLF carriage return in ci.sh and fail-fast termination."
   - Resolution: "Configured 'fail-fast: false', added sed stripping of CRLF characters, and explicitly invoked bash in ci.yml."
</pre>

### P-005 — Create cross-platform CI workflow

<pre>
Create the folder .github/workflows/ and generate the complete file .github/workflows/ci.yml for Laboratory Work #1 (Variant 5: Java 17, Maven, JUnit 5).

Requirements for ci.yml:
1. Workflow Name: "CI Build and Test"
2. Trigger events: on 'push' and 'pull_request' to branches: develop, master, main.
3. Multi-OS Matrix: Single job 'build' running across matrix strategy:
   - ubuntu-latest
   - windows-latest
   - macos-latest
4. Steps:
   - Checkout code using actions/checkout@v4.
   - Setup JDK using actions/setup-java@v4 (Java version 17, distribution 'temurin', cache: 'maven').
   - On Linux and macOS runners (runner.os != 'Windows'): make 'ci.sh' executable (chmod +x) and execute './ci.sh'.
   - On Windows runner (runner.os == 'Windows'): execute 'ci.bat' using shell: cmd.
5. Do not inline Maven commands in the workflow; strictly delegate to ci.sh and ci.bat as required by the lab guidelines.

Also provide the update line for docs/ai-log.md to document the generation of this workflow.
</pre>

### P-001 — Initial request for this journal

<pre>
We are working on Laboratory Work #1: "Designing an AI Agent for Development and CI/CD Automation" for Variant 5 (Java 17, Maven, JUnit 5).

Strict Requirement: All interaction prompts, responses, file modifications, and diagnostic steps MUST be meticulously logged for the final laboratory report.

Please generate the following 3 files:
1. docs/ai-log.md - A structured interaction journal. It must start with a logged entry for this initial request and include a clear Markdown schema/table with columns:
   - Step / Prompt ID
   - Exact Prompt Sent
   - Summary of Copilot Output / Actions
   - Files Generated or Modified
   - Verification Status (Passed/Failed)
   - Encountered Errors & Resolution Notes
2. docs/architecture.md - Technical architecture document detailing:
   - The primary role and responsibilities of the build engineer AI agent.
   - The required skills (project-scaffold, build-and-test, github-actions).
   - The available user commands (git-init, create-project, create-build, create-actions, check, init).
   - Inter-stage data flow and strict error-handling strategy for the orchestrator.
3. .github/agents/build-engineer.agent.md - The agent manifest specifying:
   - Role: Specialized Java/Maven/JUnit Automation & Build Engineer.
   - Operational boundaries, file-safety constraints (no overwrites without checks, keep target/ untracked).
   - Prohibited actions (no destructive Git rewrites, no credential leaks).
   - Definition of Done / Completion criteria.
</pre>

### P-000 — Earlier file-generation request

<pre>
We are building Laboratory Work #1: "Designing an AI Agent for Development and CI/CD Automation" for Variant 5 (Java 17, Maven, JUnit 5).

Please generate the following 3 files:
1. docs/ai-log.md - A structured markdown interaction log containing a table with columns: Step, Prompt Sent, Copilot Output Summary, Files Generated/Modified, Verification Status, and Errors Encountered.
2. docs/architecture.md - A technical overview describing the role of our AI agent, the required skills (project-scaffold, build-and-test, github-actions), commands (git-init, create-project, create-build, create-actions, check, init), data flow between steps, and error-handling strategies.
3. .github/agents/build-engineer.agent.md - The agent manifest defining the "build-engineer" role, responsibilities for Java/Maven/JUnit projects, file-handling rules, prohibited dangerous operations (no hard resets, no secret exposure), and completion criteria.
</pre>

### P-002 — Three agent skill specifications

<pre>
Generate the full specifications for all three required agent skills under .github/skills/ for our Java/Maven agent:

1. .github/skills/project-scaffold/SKILL.md:
   - Instructions to initialize the standard Maven folder layout (src/main/java/com/example, src/test/java/com/example).
   - Scaffolds a basic App.java class outputting "Hello, World!".
   - Provides .gitignore rules ignoring target/, .idea/, *.class, and OS metadata.
   - Declares input parameters, expected output files, verification commands, and error handling.

2. .github/skills/build-and-test/SKILL.md:
   - Instructions to generate a clean pom.xml targeting Java 17 with maven-compiler-plugin, maven-surefire-plugin, and JUnit 5 (org.junit.jupiter:junit-jupiter).
   - Generates AppTest.java with standard unit test cases.
   - Declares preconditions, inputs, outputs, verification command (mvn test), and error recovery steps.

3. .github/skills/github-actions/SKILL.md:
   - Instructions to configure cross-platform CI matrix testing on ubuntu-latest, windows-latest, and macos-latest triggered on push/PR to develop and master.
   - Enforces using decoupled runner scripts (ci.sh, ci.bat) rather than monolithic inline workflow shell commands.
   - Declares validation steps and failure reporting.

Also, provide the exact row to append to docs/ai-log.md to document this step.
</pre>

### P-003 — Six build engineer command prompts

<pre>
Generate all 6 command prompts in .github/prompts/ for our Java build engineer agent:

1. git-init.prompt.md - Verifies Git status and ensures .gitignore covers Java/Maven build output.
2. create-project.prompt.md - Executes project-scaffold skill to generate directory structures and App.java.
3. create-build.prompt.md - Executes build-and-test skill to create pom.xml and AppTest.java.
4. create-actions.prompt.md - Executes github-actions skill to produce ci.yml, ci.sh, and ci.bat.
5. check.prompt.md - Non-destructive inspection command that checks folder hierarchy, pom.xml integrity, and executes `mvn test` without modifying existing code.
6. init.prompt.md - The orchestrator command executing sequentially: git-init -> create-project -> create-build -> create-actions -> check. It must immediately halt upon any step failure and provide actionable diagnostics.

Ensure each prompt is idempotent (safe to re-run). Also, provide the corresponding log entry for docs/ai-log.md.
</pre>

## Activity and diagnostic record

| Activity ID | Action / Diagnostic | Observed Result | Resolution / Impact |
|---|---|---|---|
| D-001 | Inspected the repository during the initial interaction. | Directory listing showed `.git` only; `git log -1 --oneline` reported that branch `main` had no commits. | Treated the project as an empty starting repository; did not invent a Maven build or claim tests passed. |
| A-001 | Added the first versions of the three requested documents and inspected each file. | All three documents existed and their content was readable; `git status --short` showed `.github/` and `docs/` as untracked directories. | Reported successful document creation/review, not application verification. |
| D-002 | Inspected current `README.md` and attempted to read the three requested files before regeneration. | README contains exactly `123`; the three requested paths were reported absent. `git status --short` produced no entries. | Left README untouched. Recorded the workspace discrepancy without attributing its cause. |
| A-002 | Recreated and reviewed the three requested files. | Journal contains both user prompts verbatim and diagnostic history; architecture and manifest include the requested workflow and boundaries. | Documentation review passed. Maven build, tests, and CI remain unverified because no `pom.xml` or application source was present. |
| D-003 | Reviewed all three generated files and ran `git status --short; Get-ChildItem -Recurse -File docs,.github\agents \| Select-Object FullName,Length`. | All three requested files were present: `docs/ai-log.md` (6679 bytes), `docs/architecture.md` (7696 bytes), and `.github/agents/build-engineer.agent.md` (4374 bytes). Git status showed only `.github/` and `docs/` untracked. | Confirmed output paths and scope. This was a file/content review, not a build, test, CI run, or external YAML schema validation. |
| D-004 | Rechecked with `git status --short; Get-ChildItem -File docs\ai-log.md,docs\architecture.md,.github\agents\build-engineer.agent.md \| Select-Object FullName,Length`. | All three files remained present; latest observed sizes were `docs/ai-log.md` 7386 bytes, `docs/architecture.md` 7696 bytes, and `.github/agents/build-engineer.agent.md` 4374 bytes. Git status showed only `.github/` and `docs/` untracked. | Final path/status check passed. Subsequent edits to this journal reordered its prompt register so the current request is its first entry; no project build/tests were run. |
| D-005 | Inspected `.github/skills/` and read the agent-skills format reference before creating the skill files. | No existing skill files were present under `.github/skills/`; the reference specifies `.github/skills/<name>/SKILL.md` and required `name`/`description` frontmatter. | Followed the required directory/name convention; no existing content needed to be merged. |
| A-003 | Created the three skill specifications and appended the P-002 journal entry. | Specifications document workflow, inputs, outputs, validations, error handling, and safe file operations. | Files created: `.github/skills/project-scaffold/SKILL.md`, `.github/skills/build-and-test/SKILL.md`, `.github/skills/github-actions/SKILL.md`; journal modified. |
| D-006 | Final content and path review of all three skill files and `docs/ai-log.md`; checked repository status. | All requested skill files and journal record were present and readable. | Documentation review passed. No Maven build, test, Actions run, or external YAML/Actions schema validation was run. |
| D-007 | Inspected the prompt-file format reference, enumerated repository and `.github/` files, and ran `git status --short`. | Prompt files use `.github/prompts/*.prompt.md`, support `agent` frontmatter, and the repository had no existing prompt files; Git status was clean before generation. | Selected workspace prompts targeting the `build-engineer` agent and proceeded without overwriting existing prompt files. |
| A-004 | Created six command prompt files and appended the P-003 entry and verbatim request to this journal. | Prompts cover Git ignore/status checks, scaffold, Maven/JUnit build, CI scripts/workflow, read-only check, and fail-fast orchestration; each documents repeat-safe behavior. | Six `.github/prompts/*.prompt.md` files and `docs/ai-log.md` changed. |
| D-008 | Reviewed all six prompt files and the updated journal; ran `git status --short` and listed the generated paths. | Six requested prompt files were present and readable; Git status showed only `.github/` and `docs/` content as untracked/changed. | File/content verification passed. Maven tests and workflow validators were not run because no Maven project or CI workflow exists. |
| D-009 | Read `.github/prompts/init.prompt.md` and its five step prompts; inspected Git status, repository files, Java/Maven availability, and project output paths before writing. | Initial branch was `feature/java-agent-project` with clean status; no Maven project, source tree, `.gitignore`, CI workflow, or scripts existed. Java 25 was available; `mvn` was not found on PATH or the checked standard locations. | Established initial state; preserved existing repository files and selected the prompt-specified `com.example` / Java 17 defaults. |
| A-005 | Executed `git-init` and `create-project` sequentially. | Added `.gitignore`; `git check-ignore target/` exited 0. Created `App.java` and the test package directory; `java src/main/java/com/example/App.java` printed `Hello, World!` and exited 0. | `.gitignore`; `src/main/java/com/example/App.java`; created `src/test/java/com/example/` directory. |
| A-006 | Executed `create-build`: created Java 17 Maven configuration with compiler/Surefire plugins and JUnit Jupiter dependency, plus two output/greeting tests; invoked required `mvn test`. | POM and tests were generated. PowerShell reported `mvn` was not recognized; command exit code was 1, so test count/result could not be produced. | `pom.xml`; `src/test/java/com/example/AppTest.java`. |
| D-010 | Applied `init.prompt.md` fail-fast rule following the failed Maven test command. | `create-build` verification is blocked; downstream `create-actions` and `check` were not executed. | Halted before creating `ci.sh`, `ci.bat`, `.github/workflows/ci.yml`, and `docs/usage.md`. Actionable resolution: install/configure Maven and Java 17, then rerun `/init`. |
| D-011 | Inspected `ci.sh`, `ci.bat`, `.github/workflows/`, and repository status before workflow generation. | Runner scripts already invoke `mvn clean test package`; workflow directory did not exist. Maven remains unavailable. | Preserved both scripts and created the requested workflow directory/file. |
| A-007 | Added `.github/workflows/ci.yml` and journaled this prompt. | Workflow configures `CI Build and Test`, push/PR filters for `develop`, `master`, and `main`, the three requested OS matrix values, checkout/setup-java v4, conditional script execution, and no inline Maven invocation. | `.github/workflows/ci.yml`; `docs/ai-log.md`. |
| D-012 | Reviewed workflow text, script integration, generated file path, and Git status. | Workflow exists at the requested path; existing `ci.sh` and `ci.bat` invoke `mvn clean test package`. | Static content review completed. No YAML/Actions validator, local Maven command, or remote workflow run was performed. |
| D-013 | Inspected the CI workflow, `ci.sh` bytes, and working-tree state after the reported failure. | Confirmed the requested `ci.sh` contained a CRLF line ending and the workflow had no `fail-fast: false`; Windows step used `shell: cmd`. | Identified the two requested corrections without changing the Windows runner. |
| A-008 | Updated the matrix and Linux/macOS run step; rewrote `ci.sh` with `/bin/sh`, `set -eu`, and `exec` while retaining the same Maven goals. | The workflow now sanitizes CRLF before chmod and `bash ci.sh`; matrix failures no longer cancel peer jobs. | Modified `.github/workflows/ci.yml` and `ci.sh`; appended P-006 record here. |
| D-014 | Checked `ci.sh` bytes, required workflow directives, POSIX syntax with `C:\Program Files\Git\bin\sh.exe -n ci.sh`, YAML validator availability, and Git status. | `ci.sh` is 46 bytes with zero CRLF pairs or bare CR bytes; `sh -n ci.sh` exited 0. Static checks confirmed `fail-fast: false`, requested CRLF sanitation and Bash invocation, Windows `cmd` step, and no inline Maven command. No `actionlint` or `yamllint` command was available. | Shell and static workflow checks passed. Maven execution and remote Actions run were not performed; workflow YAML was not independently parsed by a dedicated validator. |

## Verification summary

- **Documentation, skill, and prompt review:** Passed for the current generated files.
- **Agent YAML frontmatter:** Reviewed for the required metadata and valid
  delimiter structure; no external schema validator was run.
- **Earlier Java/Maven build, JUnit tests, and GitHub Actions:** Not run during
  the documentation-only generations because no Maven project or workflow
  existed at that time.
- **Existing README:** Preserved; no change was requested or made to it.
- **Latest `/init` execution:** Scaffold and Maven files were generated, but
  `mvn test` could not start because Maven is unavailable and the installed
  Java runtime is version 25. Per the fail-fast orchestrator, downstream
  `create-actions` and `check` were not run during that invocation.
- **Workflow generated afterward:** `.github/workflows/ci.yml` now exists and
  was statically reviewed. Maven tests, workflow validation, and remote Actions
  execution remain unverified. Install/configure Maven and Java 17 before
  claiming a passing build.
