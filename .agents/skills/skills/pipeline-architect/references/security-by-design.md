# Security by design

Apply this baseline in architect mode while preserving the requested pipeline behavior. Use repository evidence and keep
the depth proportional to the risk; this is not a formal audit.

## Establish trust before wiring jobs

For each trigger and job, identify who controls the checked-out revision and inputs, whether secrets or write-capable
tokens are available, the runner type, and which artifacts or caches cross into later jobs. Treat forked or
proposed-change content and metadata as low trust. Treat caches, artifacts, workspaces, generated scripts, and build
outputs as data-transfer mechanisms rather than security boundaries.

Keep low-trust validation separate from privileged publication or deployment. Never let a privileged pull/merge-request
variant, comment, dispatch, completion event, or chained workflow execute contributor-controlled code with trusted
credentials. Do not place untrusted workloads on persistent or self-hosted runners unless the user explicitly accepts
and mitigates the isolation risk after discussion.

## Minimize authority and supply-chain risk

- Give each job the minimum token permissions and secret access it needs; prefer short-lived, narrowly constrained
  identity such as OIDC where supported.
- Introduce credentials only in the smallest trusted job or step that needs them. Do not persist them into checkout
  helpers, logs, process arguments, caches, artifacts, workspaces, images, or generated files.
- Pin third-party actions, plugins, images, reusable workflows, and remote components to immutable, verified revisions
  or digests when the platform supports it. Surface mutable or uninspectable dependencies as a risk rather than silently
  trusting them.
- Keep cache namespaces and restore paths separated by trust level. Do not allow trusted jobs to execute or publish
  content restored from a cache or artifact writable by lower-trust jobs without producer, provenance, and integrity
  verification.
- Prefer building and signing once, recording a digest, and promoting the same immutable artifact. Protect publication
  and deployment with trusted refs, environments, and approvals appropriate to the impact.

## Escalate decisions, not routine checks

Return to the critical/invasive decision gate in `SKILL.md` before choosing a design that grants secrets or write
access, crosses a low-to-high trust boundary, uses persistent runners for untrusted work, accepts mutable third-party
execution, weakens provenance, or changes live external state. Offer a safer alternative and explain the behavior or
operational cost it introduces.

If repository evidence reveals a likely vulnerability while designing, do not exploit it or expose credentials. Contain
it in the proposed architecture, cite the exact evidence, and ask whether the user wants the explicit audit mode when a
systematic assessment would be useful.

## Verify the implemented boundary

Review the final configuration for unsafe event contexts, excessive permissions, secret reachability, credential
persistence, cache or artifact trust inversion, mutable dependencies, runner exposure, and missing publication or
deployment gates. Use syntax and static validation where possible. Do not execute untrusted code or claim remote
security settings were verified when they were not observable.
