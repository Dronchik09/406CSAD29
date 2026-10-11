---
name: git-init
description: "Inspect Git status and ensure Java/Maven build output is ignored without disturbing existing repository state."
argument-hint: "Optional project root (defaults to the current workspace)"
agent: build-engineer
---

Safely inspect Git and ignore rules for the current Java/Maven project. This
prompt is idempotent: repeated runs must not duplicate ignore patterns or alter
unrelated repository data.

1. Resolve the project root from the optional argument, or use the current
   workspace. Confirm the path exists.
2. Inspect whether the root is already a Git repository and run `git status
   --short --branch` when Git is available. Report the actual state; do not
   initialize a repository that already exists.
3. Read the root `.gitignore` if present. Ensure it ignores at least:

   ```gitignore
   target/
   *.class
   ```

   Preserve all existing content. If either required rule is missing, add only
   the missing rule(s), without duplicate entries. Do not overwrite an
   unchecked `.gitignore`.
4. Do not stage, commit, clean, reset, or remove files. Do not alter unrelated
   Git configuration, tracked files, or user changes.
5. Verify the ignore rules textually and, when the directory is a Git
   repository, run `git check-ignore target/` (or test a representative path
   under `target/`). Capture the exit status of `git status` and
   `git check-ignore`.
6. Report the project root, repository status, whether `.gitignore` changed,
   verification results, and any errors. If Git is unavailable or the
   directory is not a repository, say which Git checks could not be performed.

Do not run `git init` unless the user explicitly asks for repository
initialization. Never use destructive Git rewrites, expose secrets, or claim
that an ignore check passed unless its result was observed.
