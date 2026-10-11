---
name: github-actions
description: "Configure cross-platform GitHub Actions CI for Java 17 Maven projects, using decoupled ci.sh and ci.bat runner scripts. Use when adding or maintaining project CI."
---

# GitHub Actions CI

Configure GitHub Actions to run the project's verified Java 17 Maven checks on
Linux, Windows, and macOS. Keep Maven invocation logic in checked-in runner
scripts (`ci.sh` and `ci.bat`); do not put monolithic build/test shell commands
inline in the workflow.

## Preconditions

- The repository root and target workflow directory have been identified.
- A Java 17 Maven project exists, with a `pom.xml`.
- The Maven command to run has been selected; use `mvn -B clean verify` for the
  CI runner scripts unless the project has a documented compatible command.
- Existing workflows and scripts have been inspected so that established
  behavior is preserved.

If local Maven verification has not been performed, record that explicitly.
CI configuration may be prepared, but do not describe it as validated or
passing.

## Inputs

- `project_root` (required): checked-out repository root.
- `workflow_file` (optional, default `.github/workflows/ci.yml`): workflow
  destination.
- `branches` (optional, fixed for this lab): `develop` and `master`.
- `java_distribution` (optional, default `temurin`): JDK distribution for
  `actions/setup-java`.
- `maven_command` (optional, default `mvn -B clean verify`): command to
  centralize in both runner scripts.

## Expected output files

- `.github/workflows/ci.yml` containing:
  - Triggers for `push` and `pull_request`, each filtered to branches `develop`
    and `master`.
  - A matrix with exactly the requested operating systems:
    `ubuntu-latest`, `windows-latest`, and `macos-latest`.
  - Checkout and Java 17 setup steps, with Maven dependency caching where
    supported.
  - A runner-script step: invoke `ci.bat` using Windows command execution for
    `windows-latest`, and invoke `bash ./ci.sh` on Linux and macOS.
  - No inline Maven build/test command in the workflow.
- `ci.sh`: POSIX shell script that fails fast and executes the selected Maven
  command from the repository root.
- `ci.bat`: Windows batch equivalent that propagates Maven's nonzero exit code.

Both scripts must invoke the same Maven goals/options. Scripts must not contain
credentials or depend on local absolute paths. Respect existing script
conventions and line endings where practical.

## Procedure

1. Inspect the existing `.github/workflows/` files, runner scripts, `pom.xml`,
   `.gitignore`, and Git status. Read any workflow or script that would be
   modified before changing it.
2. Confirm the selected Maven command is appropriate for the project. Prefer
   the command already verified by `build-and-test`; if it differs from the
   default, use the same command in both scripts and document the reason.
3. Create or update `ci.sh` and `ci.bat`. Make the POSIX script use strict
   failure handling and pass Maven's exit status through. Make the batch script
   use `exit /b %ERRORLEVEL%` (or equivalent correct propagation) after Maven
   completes.
4. Create or update the workflow with push and pull-request branch filters for
   both `develop` and `master`; define the three-OS matrix; install Java 17;
   and call the platform-specific script. Keep workflow steps declarative and
   build logic in the scripts.
5. Preserve unrelated jobs, triggers, permissions, and environment settings in
   existing workflows. Resolve duplicate/conflicting workflows deliberately;
   do not silently remove or disable existing automation.
6. Validate the YAML and runner scripts using available local tools. Inspect
   the final diff to ensure that every matrix OS runs the script and no inline
   monolithic Maven command has been introduced.

## Validation

- Parse `.github/workflows/ci.yml` with an installed YAML/GitHub Actions
  validator, if available. Report the validator and actual result; do not claim
  validation if no validator was run.
- Check that `ci.sh` parses with `sh -n ci.sh` where POSIX shell is available.
- Check that `ci.bat` contains the Maven invocation and propagates its exit
  status. Windows execution is required to validate actual batch behavior.
- Run both scripts locally on their respective platforms when possible. A
  platform matrix in YAML is not evidence that remote CI has run.
- Review trigger branches and matrix values against the exact required names
  and operating-system labels.
- Do not run a remote workflow or push changes unless explicitly requested.

## Failure reporting and recovery

- Report YAML parser errors, invalid workflow structure, unavailable
  validators, missing scripts, and platform execution failures separately.
- If Maven fails within a script, preserve the Maven output and nonzero status;
  do not mask it with a succeeding shell command or workflow step.
- If a platform-specific runner cannot be tested locally, label that validation
  not run and rely only on checks that were actually completed.
- If existing workflows conflict, retain them and report the conflict rather
  than silently deleting or disabling CI.
- Correct only diagnosed issues and repeat the relevant validation. Do not
  claim a GitHub Actions run passed without observing the run result.
- Never add credentials to workflow files, scripts, logs, or reports. Do not
  stage or commit `target/`, overwrite unchecked files, or use destructive Git
  operations.

