---
name: bench-test-gen
description: Generate concrete, language-agnostic, repository-native behavioral tests and all test-scoped infrastructure required to discover, compile, and run them; generate performance benchmarks and all benchmark-scoped infrastructure only when benchmark implementation is explicitly requested. Use when Codex must create or expand executable tests, regression suites, cross-target coverage, test setup, or authorized latency, throughput, and allocation benchmarks without changing production behavior, production dependencies, published artifacts, or anything outside the requested scope.
---

# Bench Test Gen

## Core Contract

Inspect the repository in read-only mode first, identify the library's principal consumer-facing features, and then
implement the requested test artifacts together with every strictly test-scoped change required to make them
discoverable, compilable, and runnable through the repository's native workflow.

When benchmark implementation is explicitly authorized, implement the benchmark artifacts together with every strictly
benchmark-scoped change required to make them discoverable, compilable, and runnable.

A test or benchmark source file without its required dependencies, source-set registration, runner, harness, task,
manifest, adapter, or target configuration is incomplete. Do not stop at source generation when repository-local setup
can make the artifact runnable.

Treat repository-local setup as a mandatory part of generation, not as an optional recommendation or follow-up for the
user. Do not ask the user to add an in-scope dependency, harness, runner, task, source set, target opt-in, or equivalent
setup that this skill can add without affecting production or leaving the requested scope.

Allowed writes:

- unit, integration, compatibility, property-based, and regression test sources;
- performance benchmark sources;
- fixtures, fakes, test data, and helpers stored inside existing test or benchmark areas;
- new test or benchmark directories when their location follows repository conventions;
- test-scoped and authorized benchmark-scoped dependency declarations;
- minimal lockfile updates caused only by those scoped dependencies;
- source-set, suite, target, compilation, discovery, runner, harness, adapter, and task configuration used only by tests
  or authorized benchmarks;
- test or benchmark manifests, resources, launchers, and environment-independent execution settings;
- minimal edits inside shared build or project files when the edited declarations affect only test or authorized
  benchmark workflows.

Forbidden writes:

- production sources;
- public or internal library implementation;
- production dependency declarations or changes to the production dependency graph;
- production compiler options, supported targets, runtime resources, packaging, publication, deployment, or generated
  production artifacts;
- broad dependency upgrades, lockfile refreshes, or configuration rewrites unrelated to the required test or benchmark
  setup;
- CI workflows, coverage configuration, or automation unless the user explicitly includes them in scope;
- documentation, examples, generated production assets, or unrelated files;
- existing tests outside the requested scope unless the user explicitly asks to expand them.

Never run tests, benchmarks, coverage, builds, compilers, linters, formatters, profilers, or generated code. Generate
and configure the requested artifacts and leave their execution to the user.

Use the repository's native dependency mechanism to declare and, when required to complete repository-local setup,
materialize only test-scoped or explicitly authorized benchmark-scoped dependencies. Preserve unrelated dependency
versions and lockfile entries.

If a required setup change cannot be isolated from production behavior, production dependencies, production artifacts,
or the requested scope, do not make that change. Generate every valid in-scope artifact possible and report the exact
remaining prerequisite.

## Mandatory Generated Deliverable

Always create or update concrete source files for every requested generation track. Analysis is preparation, not the
deliverable.

A successful use of this skill must produce:

- at least one real coverage test file when coverage generation is requested;
- at least one real benchmark source file when benchmark implementation is explicitly requested;
- both kinds of files when both generations are explicitly requested;
- every repository-local dependency and configuration change required to discover, compile, and run each generated
  track.

Do not stop after:

- identifying principal features;
- listing scenarios or branches;
- proposing filenames;
- showing pseudocode or illustrative snippets;
- recommending a framework;
- explaining what tests should exist.

Write complete repository-native source code with the correct language, file extension, package/module declaration,
imports, framework annotations or registration, fixtures, setup, assertions, and benchmark methods. Do not leave `TODO`,
placeholder bodies, ellipses, pseudocode, or examples that still need to be converted into files.

Do not claim completion until the files exist in the repository. If no valid test or benchmark file can be generated
even after every allowed repository-local setup change has been implemented, mark the generation as blocked and name the
remaining prerequisite. Never substitute a test plan for the required source files.

Do not claim completion merely because source files exist. Confirm statically that the repository exposes a native
command, task, target, suite, or runner that will execute them. External prerequisites such as a physical device,
simulator, browser, service, credential, or operating system may remain user-provided, but all repository-local setup
must be implemented.

## Keep Coverage and Performance Separate

Treat the two outputs as independent suites.

- **Coverage suite**: generate tests that exercise the public behaviors, meaningful inputs, failures, state transitions,
  side effects, and consumer-reachable executable branches of the principal features.
- **Performance suite**: generate benchmark code that can measure latency, throughput, and allocations reliably when the
  user runs it.

Never present coverage as a performance signal. Never present benchmark design as behavioral coverage. Keep files,
dependencies, fixtures, setup, tasks, commands, tables, and conclusions distinct even when both suites target the same
feature.

Because this skill never executes generated code:

- do not claim that tests pass or fail;
- do not claim actual coverage percentages;
- do not report measured performance values;
- describe expected coverage and intended benchmark metrics only;
- provide exact commands for the user to run without running them.

## Require Explicit Benchmark Implementation

Do not create or modify benchmark files unless the user explicitly asks to implement, create, add, or generate
benchmarks.

Treat requests such as these as explicit authorization:

- “implementa i benchmark”;
- “genera la suite benchmark”;
- “aggiungi benchmark per queste feature”;
- “crea i file per misurare latenza e throughput”.

Do not treat these as implementation authorization:

- “analizza le performance”;
- “quali benchmark servirebbero?”;
- “individua le operazioni costose”;
- “come misureresti questa libreria?”;
- a generic request to generate tests that does not explicitly mention benchmark implementation.

Without explicit authorization, perform only read-only benchmark analysis when requested: identify candidate features,
input sizes, metrics, harness support, and missing prerequisites. Do not write benchmark sources and state that
benchmark implementation was not requested.

When the request is ambiguous, do not infer permission from the skill name or from a mention of performance. Keep
benchmark files unchanged and ask whether the user wants to proceed with their implementation.

Once benchmark implementation is explicitly authorized, generate complete repository-native benchmark source files and
every benchmark-scoped dependency, harness, source-set, task, runner, adapter, manifest, or minimal shared configuration
edit required to run them. The authorization never permits production changes, unrelated dependency changes, coverage or
CI changes outside the request, or any other out-of-scope mutation.

## 1. Detect the Ecosystem Without Assuming a Language

Infer the repository's language and conventions from its files. Do not prefer a language, framework, or build system in
advance.

Read:

1. source layout and package/module exports;
2. build and dependency files before making any scoped setup edit;
3. existing test and benchmark directories;
4. existing test imports, lifecycle hooks, assertions, fixtures, and naming;
5. documentation and examples that describe supported consumer workflows;
6. public declarations and their implementations;
7. platform-specific source sets, targets, runtimes, and compatibility layers;
8. coverage and benchmark configuration when already present.

Select the existing framework and style whenever the repository establishes one. Examples may include native language
test frameworks, third-party unit frameworks, property-based tools, or benchmark harnesses, but never assume any
specific one.

If multiple ecosystems coexist:

- generate tests in the source set that owns the behavior;
- use shared/common tests for genuinely shared behavior;
- add platform-specific tests only for platform-specific contracts;
- avoid duplicating identical scenarios across targets without a compatibility reason.
- wire the suite into every supported target that owns or implements the tested behavior;
- treat a target as covered only when its native test workflow discovers and can execute the generated tests.

If no test framework is established, add the smallest repository-native test-scoped framework, dependency, source-set
registration, and runner configuration that supports the repository's declared targets. Reuse a built-in facility when
it provides native discovery, assertions, reporting, and execution. Do not invent a standalone runner when the ecosystem
has a conventional test integration.

If a supported target requires repository-local opt-in or registration, add it. If execution additionally requires an
external runtime such as a device, simulator, browser, service, credential, or operating system, complete the
repository-local setup and report only that external prerequisite.

## 2. Identify the Principal Features

Build a feature inventory before writing files. Prioritize features that:

- appear in the public API, README, examples, or package exports;
- represent the main consumer workflows;
- create, read, update, transform, persist, parse, encode, decode, or validate data;
- control lifecycle, caching, synchronization, resource ownership, or configuration;
- have important failure, compatibility, concurrency, or performance behavior;
- are widely called or central to the library's purpose.

Deprioritize:

- trivial getters and setters without distinct behavior;
- generated code and simple data holders;
- private helpers that cannot be reached through a supported public interface;
- deprecated shims unless compatibility is part of the request;
- exhaustive testing of every declaration when a smaller feature-oriented suite captures the public contract.

For each selected feature, record:

- public entry points;
- documented or implementation-derived behavior;
- inputs, outputs, errors, state, and side effects;
- dependencies and nondeterministic boundaries;
- platform or runtime variants;
- existing test gaps;
- whether coverage tests should be generated;
- whether benchmarks would be useful and, separately, whether their implementation was explicitly authorized.

## 3. Derive Coverage Scenarios

Derive scenarios from actual behavior and consumer contracts, not a generic matrix.

Consider:

### Normal Behavior

- minimal valid and representative inputs;
- all materially different public result shapes;
- interaction between the feature's main methods;
- complete consumer workflows rather than isolated private steps.

### Boundaries and Invalid Input

- empty, null, missing, default, malformed, and unsupported values when the API admits them;
- zero, negative, maximum, overflow, precision, size, encoding, locale, and time boundaries;
- collection ordering, duplicates, equality, and identity behavior;
- documented error values or exception types.

### State and Side Effects

- fresh, populated, cleared, closed, disposed, and invalid lifecycle states;
- repeated calls, idempotency, caching, invalidation, and instance isolation;
- persistence and serialization round trips;
- emitted, stored, written, or externally visible effects;
- absence of mutation or external calls after failure;
- cleanup, rollback, and partial failure behavior.

### Compatibility and Concurrency

- supported platforms, runtimes, versions, encodings, and overloads;
- cancellation, reentrancy, thread-safety, and simultaneous calls only when relevant to the public contract;
- equivalent results across common and platform-specific implementations.

Classify each scenario as:

- current behavior to document;
- desired regression behavior expected to fail until production is fixed;
- consumer-reachable branch;
- unreachable or defensive branch;
- out of scope with a reason.

Generate regression tests for desired behavior when the user asks for a bug fix or new contract, but never implement the
production fix.

## 4. Generate Coverage Tests

Create or expand actual test source files through public library interfaces. Avoid private access, reflection, exported
test hooks, or internal call assertions unless the repository already treats them as supported test boundaries.

Implement the complete coverage harness:

- declare required test-scoped dependencies;
- register all required test source sets, suites, targets, and compilations;
- configure framework discovery, runners, adapters, manifests, and test tasks;
- connect shared tests to every supported target that owns the behavior;
- add deterministic test-only resources, fixtures, and environment setup;
- expose the exact repository-native commands that execute the suite.

Follow existing conventions for:

- file placement and naming;
- imports and package/module declarations;
- setup, teardown, and resource lifecycle;
- assertion style;
- synchronous, asynchronous, and concurrent tests;
- fixtures, fakes, mocks, and temporary resources.

Prefer real local collaborators when deterministic and inexpensive. Use existing fakes or controlled test doubles at
filesystem, network, database, clock, randomness, and other nondeterministic boundaries.

Make every test readable as a feature specification. Assert as applicable:

- return value and public result structure;
- documented error or exception;
- state before and after the call;
- durable side effect or its absence;
- ordering only when guaranteed;
- equality and round-trip fidelity;
- cancellation and cleanup;
- instance, thread, platform, or storage isolation.

Avoid:

- tautological expected values copied from the implementation algorithm;
- arbitrary sleeps;
- accidental dependence on iteration order;
- oversized fixtures that obscure the behavior;
- tests created only to touch a line without proving a consumer-visible outcome;
- snapshots for stable scalar behavior better expressed with explicit assertions.

Use property-based tests when they materially improve coverage of invariants or input spaces. Reuse existing or built-in
support first; when property-based tests are selected for the requested suite and no support exists, add the smallest
test-scoped dependency and registration required to run them. Do not add property-testing infrastructure speculatively.

Before considering coverage generation complete, confirm statically that every generated test is included in a runnable
suite. A source file ignored by the build, runner, or target graph is not a generated deliverable.

## 5. Generate Performance Benchmarks

Enter this generation step only after explicit benchmark implementation authorization. Otherwise stop after read-only
benchmark analysis and leave all benchmark files unchanged.

After authorization, create actual benchmark source files separately from coverage tests. Use the repository's existing
benchmark framework and directory layout. If the repository lacks a benchmark harness, add the smallest
repository-native benchmark-scoped dependency and configuration that supports the requested measurements without
affecting production artifacts.

Implement the complete performance harness:

- declare required benchmark-scoped dependencies;
- register benchmark source sets, suites, targets, compilations, and tasks;
- configure warmup, measurement, reporting, and dead-code-elimination support through the native harness;
- add benchmark-only fixtures and parameter sources;
- expose the exact repository-native commands that execute the benchmarks.

Design benchmarks for:

- **Latency**: time per operation with warmup and repeated samples;
- **Throughput**: operations, items, or bytes processed per unit of time with an explicit work unit;
- **Allocations**: bytes/op, objects/op, allocation count, allocation rate, or GC pressure when the existing platform
  harness supports it.

For every benchmark:

1. Reuse a correctness-tested public operation.
2. Keep setup outside the measured section unless setup is intentionally part of the workflow.
3. Include representative and boundary-relevant input sizes.
4. Prevent dead-code elimination using the framework's supported mechanism.
5. Include warmup for JIT runtimes when the framework supports it.
6. Configure multiple iterations or samples according to existing conventions.
7. Avoid I/O, clocks, randomness, logging, and unrelated setup inside microbenchmarks unless they are the target.
8. Keep environment-sensitive thresholds out of ordinary tests.
9. Prefer baseline comparison hooks already supported by the repository.

Do not invent an allocation metric the harness cannot collect. When allocation measurement is explicitly requested or
part of the authorized benchmark suite, add a compatible benchmark-scoped profiler, plugin, adapter, or dependency when
one exists and can be isolated from production. Mark allocation measurement as unsupported only when no compatible
in-scope capability can be added without affecting production or leaving the requested scope.

Never place performance assertions in the coverage suite. Never generate fixed nanosecond or throughput thresholds
without an existing calibrated baseline or explicit repository policy.

## 6. Preserve the Repository

Before writing, inspect the working tree and existing files. Preserve user changes and avoid overwriting unrelated work.

When creating artifacts:

- write source artifacts only inside test or benchmark locations;
- edit shared build, dependency, manifest, workspace, or lock files only through minimal test-scoped or authorized
  benchmark-scoped changes;
- reuse existing helpers before adding new test-local helpers;
- keep fixtures minimal and scoped;
- avoid changing existing snapshots through execution;
- do not create production-facing abstractions for test convenience;
- do not change visibility or expose internals;
- do not modify a method body even when a generated regression test is expected to fail;
- do not alter production dependency resolution, compiler behavior, supported targets, packaging, publication, runtime
  resources, or generated artifacts;
- do not reformat or rewrite unrelated parts of a shared configuration file.

If the requested scope cannot be completed without a forbidden write, stop at the boundary and report it.

## 7. Review Generated Artifacts Without Executing Them

After generation, perform a read-only review:

- confirm that every authorized generation track produced at least one concrete source file;
- confirm every changed source file is a test, benchmark, or scoped fixture/helper;
- confirm every changed shared configuration, dependency, manifest, workspace, or lock file contains only the minimal
  setup required by tests or authorized benchmarks;
- confirm imports and APIs follow nearby repository examples;
- confirm no generated file contains pseudocode, placeholders, `TODO`, or incomplete bodies;
- confirm no production source, production dependency graph, production compiler behavior, published artifact,
  packaging, runtime behavior, or out-of-scope file changed;
- confirm every generated test and authorized benchmark is registered with its native discovery and execution workflow;
- confirm every supported target that owns the behavior has a runnable suite or only an explicitly named external
  prerequisite;
- confirm exact repository-native execution commands exist;
- compare the generated scenario inventory with the principal features;
- identify expected failing regression tests;
- identify code that could not be covered through public interfaces;
- identify benchmark metrics unsupported by the current harness.

Do not invoke a compiler, test discovery, dry run, linter, formatter, coverage collector, benchmark runner, or profiler
as a substitute for this review.

## 8. Report Generation Results

Report only what was generated and what it is designed to prove.

Include:

- exact paths of generated or expanded test files;
- exact paths and summaries of test-scoped or benchmark-scoped setup changes;
- principal features covered;
- test inventory grouped by feature and public entry point;
- inputs, expected outputs/errors, state, and side effects asserted;
- expected failing regression tests;
- unreachable, defensive, or omitted behavior with reasons;
- benchmark files and intended latency, throughput, and allocation metrics;
- prerequisites the repository lacks;
- exact commands the user can run;
- whether benchmark implementation was explicitly authorized.

Use separate summaries:

```text
Coverage artifacts
Feature | Test file | Generated scenarios | Expected branch/behavior coverage | Known gaps

Performance artifacts
Feature | Benchmark file | Input sizes | Latency | Throughput | Allocations | Missing support
```

Use `planned` or `generated` for coverage status, never an unmeasured percentage. Use `not executed` for benchmark
results, never fabricated timings or allocation values.

Use `generated` only when the corresponding source file was actually written. Never use `planned` as the final status
for a successful request; reserve it for explicitly blocked scenarios that could not be materialized.

When benchmark implementation was not explicitly requested, report:

```text
Benchmark implementation requested: no
Benchmark files modified: no
```

State explicitly:

```text
Production code modified: no
Production behavior, dependencies, and artifacts modified: no
Test infrastructure modified: yes/no
Benchmark infrastructure modified: yes/no
Tests executed: no
Benchmarks executed: no
```

Do not claim that the suite is green, that a coverage target was reached, or that performance improved until the user
executes the appropriate commands outside this skill.
