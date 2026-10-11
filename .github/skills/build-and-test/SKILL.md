---
name: build-and-test
description: "Configure a clean Java 17 Maven build with JUnit 5, generate AppTest.java, and run Maven tests. Use when establishing or verifying the Java build."
---

# Build and Test

Create a reproducible Maven build for the Java 17 project and add baseline
JUnit 5 tests for the scaffolded application. Inspect existing configuration
before editing; coordinate with `project-scaffold` for application and source
layout and with `github-actions` for CI runner configuration.

## Preconditions

- The project root is identified and readable.
- `src/main/java/` contains the application source, normally
  `com/example/App.java`, created by `project-scaffold`.
- Java 17 and Maven are available for executing the verification command.
  Configuration may still be generated if a tool is unavailable, but execution
  must be reported as unavailable.

If the expected application source or package is missing, do not invent a
different API. Report the missing prerequisite or coordinate completion of
`project-scaffold`.

## Inputs

- `project_root` (required): Maven project directory.
- `java_release` (optional, default `17`): target Java release; this lab
  requires 17.
- `package_name` (optional, default `com.example`): package containing `App`
  and `AppTest`.
- `application_class` (optional, default `App`): class under test.
- `test_command` (optional, default `mvn test`): local verification command.
- `overwrite_existing` (optional, default `false`): existing files must be
  read and compared before any replacement; user changes are never discarded.

## Expected output files

- `pom.xml` with valid Maven coordinates and:
  - Java compiler release 17 configured through
    `org.apache.maven.plugins:maven-compiler-plugin`.
  - `org.junit.jupiter:junit-jupiter` with test scope.
  - `org.apache.maven.plugins:maven-surefire-plugin` configured at a version
    compatible with JUnit Platform/JUnit 5.
- `src/test/java/com/example/AppTest.java` (or the equivalent package path)
  containing JUnit Jupiter tests.

Keep plugin versions explicit and compatible. Do not add unnecessary
dependencies, configure a Java version other than 17, or replace existing
project coordinates without preserving their intent. Use current project
conventions where compatible with these requirements.

## Procedure

1. Inspect `pom.xml`, application source, test source tree, `.gitignore`, Git
   status, and available Java/Maven versions before editing.
2. If `pom.xml` exists, understand its coordinates, dependencies, profiles, and
   plugins. Preserve compatible settings. If configuration conflicts with
   Java 17 or JUnit 5, make a focused change; if the conflict cannot be safely
   reconciled, stop and report it.
3. Create or update the Maven project metadata. Set compiler release to 17,
   add `org.junit.jupiter:junit-jupiter` with `test` scope, and configure
   `maven-surefire-plugin` to discover and execute Jupiter tests.
4. Inspect the application API. Generate `AppTest.java` with standard
   independent test cases for observable application behavior. At minimum,
   include a JUnit 5 test that verifies the required `Hello, World!` output
   when that is the scaffolded application behavior. Prefer testing a
   testable application method if one exists; do not change production
   behavior merely to fit a speculative test.
5. Review the resulting POM and test source for consistent coordinates, package
   names, test scope, plugin versions, and test discovery.
6. Run `mvn test` from `project_root`; capture the exact command, exit status,
   summary, and any actionable failure output.

## Verification

Required command:

```sh
mvn test
```

The command passes only when Maven exits successfully and executes the
expected JUnit tests. Record the number of tests and their pass/fail/error
counts from Maven output. Also inspect the effective project configuration if
the test provider is not discovered or the build behavior is unexpected.

## Error handling and recovery

- If Maven or Java is missing, do not claim test success. Record the unavailable
  executable and version requirement; leave the generated files available for
  later execution where safe.
- If dependency resolution fails, record Maven's relevant error and
  distinguish network/repository access failures from invalid dependency
  coordinates. Retry only after identifying a correctable cause; do not
  silently remove JUnit 5 or substitute another framework.
- If compilation fails, report the source file and compiler diagnostics. Make
  only a minimal correction supported by the diagnosed error, then rerun
  `mvn test`.
- If no JUnit tests are discovered, inspect package paths, test naming,
  Surefire version, and JUnit Platform configuration. Correct the root cause
  and rerun; never report an empty successful build as validated tests.
- If a test fails, preserve the failure details and do not weaken or delete the
  test merely to get a green result. Determine whether the cause is a
  production defect, a test defect, or an environment issue.
- Do not overwrite unchecked files, expose credentials, stage `target/`, or use
  destructive Git operations during recovery.

