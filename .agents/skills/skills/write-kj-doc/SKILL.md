---
name: write-kj-doc
description: Write, complete, normalize, or review KDoc and Javadoc in a specific personal English style, with role-aware blueprints for ordinary declarations, UI components, screens, ViewModels, service clients, requesters, and helpers. Use when Codex is asked to document Kotlin (.kt or .kts) or Java (.java) declarations, add documentation to a type and every member, convert existing API comments to this style, or check that documentation matches signatures and behavior. This skill is strictly comments-only and must never change source code, imports, annotations, signatures, bodies, build files, or formatting outside documentation blocks.
---

# Write KDoc and Javadoc

Document Kotlin and Java code with concise, semantic comments based on the user's personal templates. Preserve the
recognizable wording and layout while correcting factual or copy-and-paste mistakes.

## Enforce the comments-only boundary

- Insert or edit only KDoc and Javadoc blocks delimited by `/**` and `*/`.
- Treat every pre-existing character outside those documentation blocks as immutable, including package declarations,
  imports, annotations, modifiers, identifiers, types, signatures, default values, property initializers, method bodies,
  string literals, ordinary `//` or `/* ... */` comments, and whitespace or line wrapping.
- Never add, remove, reorder, or rewrite imports or annotations to support a documentation link. Use a resolvable fully
  qualified link or non-linked code text inside the documentation instead.
- Never fix compiler errors, bugs, unsafe calls, nullability, naming, spelling in identifiers, deprecations, style
  issues, or formatting discovered while documenting. Report them separately without editing them.
- Never run an autoformatter, import optimizer, code generator, or other command that may rewrite target source files.
- If the user requests documentation and code changes together, keep this skill's execution comments-only and separate
  the code-change request from this workflow. Do not treat the documentation request as authorization to edit code.
- If accurate documentation exposes a conflict or ambiguity in the implementation, document only facts that are provable
  and report the conflict. Never change the implementation to match the intended documentation.

## Load the style reference

Read [references/style-guide.md](references/style-guide.md) completely before drafting or reviewing comments. Apply the
language-specific templates and tag order from that file.

Load only the role references that match the target code:

- Read [references/ui-blueprints.md](references/ui-blueprints.md) for UI components, composable functions, screens,
  presenters, ViewModels, state holders, or UI sections.
- Read [references/service-blueprints.md](references/service-blueprints.md) for service clients, requesters, gateways,
  remote operations, payload or query builders, endpoint assemblers, and general helpers.
- Read both when one file genuinely contains both roles. Use only the base style guide when neither role fits.

## Keep every blueprint independent

- Infer roles from the target declaration, annotations, inheritance, implementation, and call sites; never classify code
  solely because its name resembles a sample.
- Treat blueprint text as a sentence family to fill from the target contract, not as domain content to copy.
- Never introduce product names, package names, base classes, annotations, services, state models, endpoints, resources,
  or workflows taken from examples.
- Never require a particular architecture. A screen does not automatically need a ViewModel, and a ViewModel does not
  automatically perform networking or own session state.
- Fall back to the base declaration template when no specialized role matches cleanly.

## Follow this workflow

1. Read each target file completely. Read directly related declarations, base types, callers, or callees when needed to
   understand the real contract and declaration role. Before editing, record the current target-file diff or preserve a
   read-only baseline so pre-existing user changes remain distinguishable.
2. Confirm the requested scope from the prompt. When the user asks for a class and every method, cover the type, fields
   or properties, constructors, and every explicit method or function in scope, including simple wrappers and non-public
   members.
3. Resolve optional metadata before editing:
    - Preserve an existing `@author` value unless the user requests a change.
    - Use an author supplied in the current request exactly as written.
    - Add `@author` to type declarations by default, not to fields, properties, constructors, methods, functions, or
      top-level UI components. Preserve a broader existing convention or follow an explicit user request when one
      applies.
    - When adding type-level documentation and no author value is available, ask once for the exact `@author` value or
      permission to omit it. Never reuse identities from examples or neighboring projects.
    - Preserve an existing `@since` value. Add a new one only from an explicit release, revision, or user answer; never
      infer a version from the current date or repository history.
   - Apply a new `@since` tag at the narrowest non-redundant scope:
       - When an entire new type and its initial members are added together, add `@since` only to the new enclosing
         type;
         do not repeat the same tag on each initial property, constructor, or method.
       - When a new property, constructor, or method is added to an already-existing type, add `@since` to each newly
         added declaration.
       - Preserve all pre-existing `@since` tags even when they are more repetitive than this rule.
    - If interaction is unavailable or the user asks to proceed without questions, omit unresolved optional tags instead
      of emitting placeholders.
4. Classify each declaration by its actual role, select the closest applicable blueprint, and derive every sentence from
   the implementation and contract. Treat samples as style evidence only; do not copy their domain facts.
5. Apply narrow patches that insert or replace documentation blocks only. Do not include surrounding source lines in a
   replacement when they do not need to change.
6. Compare the edited files with the recorded baseline and reject any newly introduced difference outside KDoc or
   Javadoc blocks. Correct only accidental changes made by this workflow and preserve all pre-existing user changes.
7. Validate the result against the checklist below. Run an existing focused KDoc, Dokka, Javadoc, or doclint task only
   when it cannot rewrite target sources and is readily available and proportionate; do not alter the build merely to
   validate comments.

## Validate every comment

- Inspect the final diff against the pre-edit baseline. Every newly added, removed, or replaced character must belong to
  a `/** ... */` documentation block.
- Fail the comments-only check if any source token, ordinary comment, blank line, indentation, or line wrapping outside
  a documentation block changed.
- Do not claim that code was preserved when the baseline comparison is unavailable or ambiguous; stop and report the
  verification problem instead.
- Use valid `/** ... */` source comments with one leading `*` per content line.
- Keep descriptions in English, concise, and without a final period, matching the reference style.
- Document parameters in signature order and with their exact names.
- Add `@return` only for an actual non-`void` or non-`Unit` result. Resolve inferred Kotlin return types from the
  implementation or callee before deciding.
- Document type parameters, receivers, and thrown exceptions only when they are part of the real contract.
- Make every Java `{@link ...}` and KDoc `[...]` target resolvable in context.
- Keep `@see` entries relevant and avoid repeating links already obvious from the summary.
- Never invent an author, version, revision, exception, side effect, nullability guarantee, or return type.
- Never leave a sample-specific identifier or architectural assumption in generated documentation.
- Remove stale statements and copied descriptions that contradict names, types, annotations, or behavior.
- Keep tag groups and blank lines exactly as defined in the language template, while preserving a stricter established
  convention when the target file already has one.

## Report the result

State which files or declarations were documented, which focused validation ran, and whether any optional metadata was
intentionally omitted. State that no non-comment code changed only after the baseline comparison passes. Do not claim
semantic correctness when related code could not be inspected.
