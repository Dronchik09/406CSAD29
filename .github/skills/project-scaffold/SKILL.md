---
name: project-scaffold
description: "Create or inspect a standard Java 17 Maven project skeleton, Hello World App.java, and safe .gitignore rules. Use when initializing or scaffolding the project."
---

# Project Scaffold

Create the standard Maven source/test directory layout and a minimal Java
application for the current repository. Inspect first and preserve existing
work; this skill establishes layout and source, while `build-and-test` owns the
Maven build file and initial JUnit test.

## Inputs

- `project_root` (required): repository or project directory.
- `package_name` (optional): Java package; defaults to `com.example`.
- `application_class` (optional): main class name; defaults to `App`.
- `overwrite_existing` (optional, default `false`): whether an existing target
  file may be replaced. Never interpret this as permission to discard user
  changes; compare and report differences first.

Validate that the package name is a legal Java package and that the selected
class name is a legal Java identifier. If either is invalid or ambiguous, stop
and ask for correction rather than silently substituting a value.

## Expected output files

For the default inputs:

- `src/main/java/com/example/App.java`
- `src/test/java/com/example/` directory, ready for `AppTest.java` from
  `build-and-test`
- `.gitignore`, containing rules for:

  ```gitignore
  target/
  .idea/
  *.class
  .DS_Store
  Thumbs.db
  Desktop.ini
  ```

For a non-default `package_name`, convert dots to directory separators under
both `src/main/java/` and `src/test/java/`; keep the matching Java `package`
declaration in source. Add only the required ignore rules when `.gitignore`
already exists, preserving its contents.

`App.java` must declare the selected package and class and provide an executable
`main` method that prints exactly:

```text
Hello, World!
```

Do not create a `pom.xml` or a test class in this stage; they are owned by
`build-and-test`.

## Procedure

1. Resolve `project_root` and inspect the existing tree, `.gitignore`, Git
   status, and all target paths before writing.
2. Create missing Maven directories:
   `src/main/java/<package-path>` and `src/test/java/<package-path>`.
3. For each expected file, create it if absent. If present, inspect its content
   and preserve it. If it conflicts with the requested result, do not overwrite
   it unless replacement was explicitly approved; report the conflicting path
   and request direction.
4. Create or merge the required `.gitignore` patterns without deleting or
   reordering unrelated existing rules. Confirm that `target/` is ignored.
5. Review the resulting source and file list. Report created files, preserved
   files, conflicts, and any directories created.

## Verification

- Confirm the expected source file and both package directories exist.
- Confirm `App.java` has the correct package declaration, an executable
  `main(String[] args)`, and the exact `Hello, World!` output.
- Confirm `.gitignore` covers `target/`, `.idea/`, `*.class`, and OS metadata
  such as `.DS_Store`, `Thumbs.db`, and `Desktop.ini`.
- Use `git check-ignore target/` when Git is available; treat no matching ignore
  rule as a verification failure.
- Maven compilation is not a required check at this stage because the
  `pom.xml` is produced by `build-and-test`. Once that file exists and Java and
  Maven are available, run `mvn -q -DskipTests package` and report its actual
  exit status.

## Error handling

- If the project root cannot be read or written, stop and report the path and
  operating-system error; do not claim partial success.
- If a target file exists with incompatible content, leave it untouched,
  report the difference, and request approval or a compatible alternative.
- If directory creation or a write fails, report the exact operation and
  affected paths. Do not continue as if all outputs were generated.
- If Git, Java, or Maven is unavailable, mark only the dependent verification
  as unavailable; continue safe content review and explain the limitation.
- Never remove build output, stage files, rewrite Git history, or expose
  credentials while scaffolding.

