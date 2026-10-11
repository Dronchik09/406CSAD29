---
name: init
description: "Initialize the Java build engineer workflow by running git-init, create-project, create-build, create-actions, and check in order."
argument-hint: "Optional project root and package name (default current workspace and com.example)"
agent: build-engineer
---

Orchestrate first-time setup by executing these user commands in exactly this
order:

1. `/git-init`
2. `/create-project`
3. `/create-build`
4. `/create-actions`
5. `/check`

Pass the same project root and package name through every applicable step.
Respect each command's idempotence and safety instructions. Before starting,
inspect repository status and existing files; do not assume the project is
empty.

## Orchestration rules

- Run one step at a time and inspect its completion result before starting the
  next step.
- Immediately halt when a step fails, is blocked, or reports an unresolved
  conflict. Do not run later dependent steps.
- Treat an unavailable required tool/check as a blocker when it prevents the
  stage from verifying its required output. Report the exact step, command,
  exit code or unavailable prerequisite, and actionable next step.
- Do not convert warnings or partial output into success. Resume only when the
  user requests it or the reported blocker is resolved; safe completed steps
  may be rerun because they are idempotent.
- Never overwrite unchecked files, discard user changes, stage or commit
  changes, expose credentials, or perform destructive Git rewrites.
- Keep `target/` ignored and untracked.

After each successful step, summarize its changed/preserved files and observed
verification. On full completion, report all five step outcomes and the final
Git status. The workflow is complete only if all five commands succeed and
`mvn test` reports passing tests; never claim remote GitHub Actions execution
unless a workflow run was actually observed.
