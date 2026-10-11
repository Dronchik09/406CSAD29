---
name: create-actions
description: "Configure cross-platform Java 17 Maven CI using github-actions skill and decoupled ci.sh/ci.bat runners."
argument-hint: "Optional workflow path and Maven command (default .github/workflows/ci.yml and mvn -B clean verify)"
agent: build-engineer
---

Use the `github-actions` skill to configure cross-platform CI with the
repository's verified Java 17 Maven command.

1. Inspect Git status, `pom.xml`, `.gitignore`, existing workflows, and any
   `ci.sh`/`ci.bat` scripts before editing. Preserve existing workflow
   behavior and user changes.
2. Use the supplied Maven command or default to `mvn -B clean verify`. Keep the
   same Maven goals/options in both runner scripts.
3. Create/update `ci.sh` as a POSIX runner that fails fast and propagates
   Maven's exit status. Create/update `ci.bat` as the Windows equivalent,
   propagating the Maven exit code (for example, with `exit /b %ERRORLEVEL%`).
4. Create/update the selected workflow (default `.github/workflows/ci.yml`) to
   trigger on both `push` and `pull_request` for `develop` and `master`, and
   use a matrix containing exactly `ubuntu-latest`, `windows-latest`, and
   `macos-latest`. Set up Java 17 and invoke `ci.bat` on Windows and `bash
   ./ci.sh` on Linux/macOS.
5. Do not put Maven build/test commands inline in workflow steps; the workflow
   must delegate to the runner scripts. Do not add secrets or credentials.
6. Validate workflow structure with an available YAML/Actions validator;
   validate `ci.sh` with `sh -n` when available; inspect `ci.bat` for correct
   Maven invocation and exit-code propagation. Run platform scripts only where
   their platform/tools are available. A local check is not a remote Actions
   run.
7. Report all changed files, validators/checks actually run, outcomes, and
   checks unavailable or not run. Do not claim a workflow passed without
   observing the actual result.

This prompt must be safe to repeat: read existing files first, make minimal
changes, avoid duplicate jobs/triggers, and do not remove unrelated automation.
Do not stage/commit changes, discard user data, or use destructive Git
operations.
