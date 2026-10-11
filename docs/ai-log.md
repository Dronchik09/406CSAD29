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
| 1 / P-001 | [Verbatim prompt](#p-001--strict-audit-journal-and-documentation-request) | Inspected the current workspace, noted the existing README, and regenerated this journal with the historical and current prompts plus diagnostic notes. Rewrote the architecture document and manifest to spell out orchestration gates, preservation rules, and `target/` handling. Progress updates disclosed the missing files, README preservation, and validation scope. | `docs/ai-log.md`; `docs/architecture.md`; `.github/agents/build-engineer.agent.md` | Passed — requested files and content reviewed after creation. Build/test/CI were not run because no Maven project is present. | Initial reads confirmed README content `123`; the three requested paths were absent. `git status --short` returned no entries. Final `git status --short` listed only the requested `.github/` and `docs/` directories as untracked. Preserved README unchanged; did not run build commands. |
| 2 / P-000 | [Verbatim prompt](#p-000--earlier-file-generation-request) | Created an initial interaction log, architecture overview, and `build-engineer` manifest; reviewed the files and reported that build/test/CI could not run because no Maven project existed. | `docs/ai-log.md`; `docs/architecture.md`; `.github/agents/build-engineer.agent.md` | Passed — files were created and reviewed in that earlier interaction. | Earlier inspection found only `.git/`; `git log -1` reported that `main` had no commits. Build/test/CI were not applicable because there was no Java/Maven project. At the start of this regeneration, the three files were absent; their disappearance is not attributed to a cause. |
| 3 / P-002 | [Verbatim prompt](#p-002--three-agent-skill-specifications) | Inspected customization skill conventions and the workspace; created complete `project-scaffold`, `build-and-test`, and `github-actions` skill specifications; appended the prompt and activity record to this journal. | `.github/skills/project-scaffold/SKILL.md`; `.github/skills/build-and-test/SKILL.md`; `.github/skills/github-actions/SKILL.md`; `docs/ai-log.md` | Passed — all three skill files and this journal entry were reviewed; no Java/Maven tests or workflow execution were applicable or run. | Initial inspection found no pre-existing files under `.github/skills/`; no conflicting skill files required resolution. Maven build, tests, workflow execution, and external YAML/Actions validation were not performed because this workspace has no Maven project/workflow or validator invocation. |

## Prompt register

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

## Verification summary

- **Documentation and manifest review:** Passed for the current generated files.
- **Agent YAML frontmatter:** Reviewed for the required metadata and valid
  delimiter structure; no external schema validator was run.
- **Java/Maven build, JUnit tests, and GitHub Actions:** Not run. The current
  workspace does not contain a Maven project or workflow to execute.
- **Existing README:** Preserved; no change was requested or made to it.
