---
name: check
description: "Perform non-destructive inspection of Java project structure and POM, then run Maven tests without editing source."
argument-hint: "Optional project root (defaults to the current workspace)"
agent: build-engineer
---

Perform a non-destructive verification of the Java/Maven project. Do not edit,
format, generate, stage, or commit files.

1. Resolve the project root and capture `git status --short` before checks.
   Preserve the exact initial status so pre-existing changes are not mistaken
   for check-generated files.
2. Inspect (read-only) the standard layout:
   `src/main/java/com/example` and `src/test/java/com/example`. Report missing
   paths without creating them.
3. Inspect `pom.xml` without modifying it. Verify it is well-formed XML and
   targets Java 17 with `maven-compiler-plugin`, JUnit 5
   (`org.junit.jupiter:junit-jupiter`), and `maven-surefire-plugin`. Report
   missing or invalid configuration precisely.
4. Confirm Java and Maven are available. If prerequisites and the POM permit,
   run `mvn test` from the project root. Do not change existing code or
   configuration to make tests pass. Maven may write normal build artifacts
   under ignored `target/`; do not delete or stage them.
5. Capture command output, exit status, and test summary. Distinguish test
   failures from missing tools, dependency/network failures, malformed POM,
   and missing source/test paths.
6. Capture Git status after checks and compare it with the initial status.
   Report any status changes; do not clean them up.
7. Return a concise pass/fail/not-run result for each check, supporting
   diagnostics, and explicitly state that no source/configuration files were
   modified by this inspection.

If a check would require editing a file, stop that check and report the
prerequisite instead. Never use destructive Git operations, expose secrets, or
represent unavailable checks as passing.
