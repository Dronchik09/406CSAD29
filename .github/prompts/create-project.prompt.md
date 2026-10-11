---
name: create-project
description: "Create the standard Java 17 Maven directory layout and Hello World application through the project-scaffold skill."
argument-hint: "Optional package name (default com.example) and project root"
agent: build-engineer
---

Use the `project-scaffold` skill to create or verify the Java project skeleton.

1. Resolve the project root and optional package name from the user's input.
   Use `com.example` and `App` when not specified.
2. Inspect Git status and every expected output path before writing. Preserve
   existing files; do not overwrite a file unless its contents were inspected
   and replacement was explicitly authorized.
3. Ensure the matching Maven directories exist:
   `src/main/java/<package-path>` and `src/test/java/<package-path>`.
4. Ensure the package's `App.java` has an executable `main` method that prints
   exactly `Hello, World!`. Merge safely or report a conflict; do not discard
   user-authored behavior.
5. Ensure `.gitignore` contains `target/`, `.idea/`, `*.class`, and OS metadata
   rules (at minimum `.DS_Store`, `Thumbs.db`, and `Desktop.ini`). Preserve
   existing rules and add only missing entries.
6. Do not create or modify `pom.xml` or `AppTest.java`; those belong to
   `build-and-test`.
7. Verify paths, package declaration, output text, and ignore rules. If Git is
   available, verify `target/` with `git check-ignore`. Run
   `mvn -q -DskipTests package` only if a POM and Maven are available; otherwise
   mark compilation as not run and state why.
8. Report created, changed, and preserved files; list commands with observed
   results and unresolved conflicts.

This operation must be safe to repeat: leave already-correct output unchanged
and never duplicate `.gitignore` rules. Do not stage/commit files, remove build
outputs, expose credentials, or perform destructive Git operations.
