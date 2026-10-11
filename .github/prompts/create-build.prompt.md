---
name: create-build
description: "Configure the Java 17 Maven build and JUnit 5 tests using the build-and-test skill."
argument-hint: "Optional project root, Java release, or package name (lab defaults: current workspace, 17, com.example)"
agent: build-engineer
---

Use the `build-and-test` skill to configure and verify this Java project's
Maven build.

1. Resolve the project root, package, and Java release. Defaults are the
   current workspace, `com.example`, and Java 17. This lab requires Java 17;
   reject or clarify any incompatible requested release rather than silently
   changing it.
2. Confirm the scaffolded application exists. Inspect `pom.xml`, application
   source, test tree, `.gitignore`, and Git status before making changes.
3. Create or safely update `pom.xml` with explicit Maven coordinates,
   `maven-compiler-plugin` configured for Java release 17,
   `org.junit.jupiter:junit-jupiter` in test scope, and a compatible explicit
   `maven-surefire-plugin` version that discovers JUnit 5 tests. Preserve
   existing coordinates and compatible configuration. Do not replace a
   conflicting POM without inspecting and reconciling it.
4. Create or safely update `src/test/java/<package-path>/AppTest.java` with
   independent JUnit Jupiter tests for observable application behavior,
   including the expected Hello World behavior when that is the scaffold's
   behavior. Preserve existing tests and do not weaken tests to get a passing
   result.
5. Ensure `target/` remains ignored and do not stage or commit generated build
   output.
6. Run `mvn test` from the project root. Capture the exact command, exit code,
   test count, pass/fail/error counts, and relevant diagnostics.
7. Report files created/changed, configuration choices, test result, and
   blockers. If Java/Maven or dependency access is unavailable, report the
   verification as not run/blocked; do not claim success.

The operation is idempotent: inspect all existing files, preserve compatible
configuration and tests, and avoid duplicate dependencies/plugins. Never
expose credentials or use destructive Git operations.
