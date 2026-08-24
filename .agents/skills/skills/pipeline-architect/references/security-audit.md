# Explicit CI/CD security audit mode

Use this module only when the user explicitly requests a CI/CD security audit, security review, threat model,
vulnerability assessment, or equivalent systematic review.

## Operating boundary

Start read-only. Do not edit files, trigger pipelines, execute untrusted pipeline code, publish, deploy, retrieve or
rotate secrets, or change platform, repository, organization, runner, environment, registry, or cloud settings. Complete
and present the audit before proposing any write.

End with an explicit remediation gate. Silence, an ambiguous reply, general repository access, or the audit request
itself is not approval. After an affirmative response, modify only repository files needed for confirmed Critical and
High findings from the presented audit. Medium and Low findings require a separate scope expansion. Deployments,
publishing, secret operations, remote execution, and external-setting changes always require their own explicit
authorization.

## Discover and model the attack surface

Identify the CI/CD system and inspect all repository-visible workflows, templates, local actions, reusable components,
includes, plugins, invoked scripts, task targets, container/build configuration, and deployment entry points. Follow
local references transitively. Mark remote components that cannot be inspected as unknown and never assume they are
safe.

Assume an external attacker can control a fork or proposed branch, change repository content and metadata, select
filenames and refs, open a pull or merge request, and influence other low-trust event inputs. Determine whether that
control can:

- expose or poison credentials, caches, artifacts, workspaces, reports, images, generated configuration, dependencies,
  tools, or executable build outputs;
- reach privileged triggers, chained workflows, comments, dispatches, schedules, deployment or publication jobs;
- exploit excessive token permissions, unsafe interpolation, mutable third-party components, weak environment gates, or
  persistent/self-hosted runners;
- move an object from a lower-trust producer to a higher-trust consumer without verified provenance and integrity.

Never print, decode, retrieve, or test real secret values.

## Audit systematically

For every pipeline and job, record its trigger and trust level, revision source, runner and persistence, token
permissions, secret availability, environment or approval gates, concurrency, upstream/downstream relationships, and
privileged capabilities. Label behavior dependent on unobservable remote settings or current service semantics as
unknown.

For every explicit or implicit cache, artifact, workspace, report, image layer, or remote cache, record paths, namespace
and key, fallback keys, retention and scope, readers and writers by trust level, attacker control, executable content,
credential exposure, and provenance/integrity checks. Include package-manager, setup-tool, compiler, Docker/BuildKit,
and custom caches.

For each credential or capability, using names only, trace where it is introduced, the minimum job or step that can
access it, attacker-controlled code or inputs that can reach it, paths or logs it may enter, cleanup order relative to
upload/cache steps, lifetime and scope, OIDC constraints, environment approval, and persisted checkout credentials.

Pay particular attention to privileged pull/merge-request variants; parent, completion, downstream, and chained
pipelines; chatops or agentic inputs; publication and deployment without protected gates; mutable third-party actions,
images, plugins, or includes; and untrusted work on persistent runners. Checkout defaults, masking, read-only tokens,
protected variables, and nominal cache isolation are not sufficient without tracing the complete path.

## Report evidence

Use this structure:

1. **Executive verdict:** `SAFE AGAINST THIS THREAT MODEL`, `CONDITIONALLY SAFE`, or `UNSAFE`, explained in no more than
   five sentences.
2. **Pipeline and trust inventory:** each pipeline/job, trigger, trust, runner, revision source, permissions, secrets,
   and privileged capabilities.
3. **Findings:** severity (Critical, High, Medium, Low), exact file and line, platform/trigger/trust boundary, storage
   path, credential or capability at risk, attacker-controlled input, exploit path, effectiveness of existing
   protections, minimal remediation, and evidence status (`confirmed` or `unknown`). Do not turn generic best practices
   or unknowns into confirmed vulnerabilities.
4. **Cache and artifact access matrix:** columns for pipeline/job, trigger, trust, object/key, read/write access,
   executable content, credential exposure, integrity/provenance, and safety.
5. **Credential-to-storage trace:** columns for credential/capability, introduction point, availability, written path,
   cache/upload status, lifetime, and risk. Use names only.
6. **Required patches:** minimal proposed patches for confirmed findings, without applying them.
7. **Validation plan:** non-secret static checks, canary credentials, low-trust inability to read/write trusted state,
   rejection of unverified artifacts, resistance to poisoned caches, pre-approval credential absence, and external
   settings requiring manual verification.
8. **Approval gate:** state that no files were changed and ask: “Vuoi che implementi ora esclusivamente le remediation
   confermate Critical e High?”

## Remediation after approval

Reconfirm the approved findings and exact files. Apply the smallest repository-only changes for confirmed Critical and
High findings, preserving behavior where security allows. Validate modified configuration with non-executing syntax or
static tools, inspect the final diff for secrets and scope creep, and report unavoidable behavior changes plus the
complete validation result. Leave remote and external state unchanged without separate authorization.
