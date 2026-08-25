---
name: wikitome
description: Create a short, clear, self-contained wiki entry in chat about a specific API, technical capability, or functionality to implement, for example "how to implement remember." Before producing the entry, always analyze the available project context in read-only mode. Use this skill when the user wants to quickly understand what an API or concept does, how to design it independently of languages and frameworks, its use cases, when to use or avoid it, better alternatives, and a small conceptual example. Also accept a user-provided scenario to identify the APIs or capabilities to document; when a scenario contains multiple candidates, list them first and expand the user-selected ones step by step. When explicitly requested, evaluate the scenario or a snippet against the API being discussed.
---

# WikiToMe

## Objective

Produce a compact technical wiki entry that can be understood at a glance. Remain agnostic about languages, frameworks,
vendors, and libraries unless the user explicitly requests otherwise.

## Procedure

1. Always analyze the available project context in read-only mode before identifying or describing any API. Examine only
   the files, documentation, and elements relevant to the request; do not modify the project.
2. Identify the requested API, capability, or behavior and the problem it solves.
3. When the user provides a scenario without naming a specific API, use it to identify candidate APIs or capabilities to
   document. Derive candidates only from the scenario and the analyzed context, without inventing missing requirements,
   APIs, or behaviors.
4. If the scenario contains multiple candidates, return a compact list of the APIs or capabilities and the role each one
   plays. Do not expand all entries automatically. Let the user choose which candidates to explore, then document the
   selected ones one at a time, step by step.
5. If the name is ambiguous, state the adopted interpretation in one line. Do not invent signatures, endpoints, or
   proprietary behaviors.
6. Describe the conceptual contract: input, output, state, persistence, errors, and relevant constraints. Omit items
   that do not apply.
7. Explain the implementation through components and data flow, using abstract interfaces and language-neutral
   pseudocode.
8. Highlight use cases, when to choose the solution, when to avoid it, and any better-suited alternatives. Base
   alternatives on the analyzed context and clearly state why they may be preferable.
9. Conclude with a minimal example showing the main path and, when important, an error or edge case.
10. Add a contextual evaluation only when the user explicitly asks to evaluate a snippet or scenario they shared.

## Response format

Use this order, adapting or merging sections when needed. Prefer definitions, short lists, and compact tables over
continuous prose.

### Candidate APIs or capabilities (scenario only)

When a scenario yields multiple candidates, list each candidate with its purpose and relevance to the scenario. Stop
after the list and let the user choose which candidate to explore first.

### `<API or functionality name>`

> Definition in 1–2 sentences and the problem it solves.

#### Contract

Show the conceptual signature, input, result, state, and main errors. Use a table only when it clarifies multiple
fields.

#### How it works

List 3–6 conceptual steps, including only the necessary components.

#### Usage

Cover in separate points: use cases, when to use it, when to avoid it, and better-suited alternatives with their
relevant tradeoffs.

#### Example

Show a minimal, complete example of the main path.

#### Snippet or scenario evaluation (optional)

Include this section only when explicitly requested and base it on the material provided by the user. Evaluate the usage
against the contract, lifecycle, and use cases of the described API, not as a general code review.

- State immediately whether the approach is suitable, partially suitable, or unsuitable, and explain why.
- Identify at most three relevant aspects: correctness, lifecycle, keys or dependencies, state, errors, performance, or
  a more appropriate alternative.
- Propose a minimal correction when needed; preserve the language, framework, and style of the original snippet.
- Use a natural, direct tone. Do not impose the wiki structure on this section when a short prose explanation is
  clearer.
- State any assumptions when the scenario lacks sufficient context. Do not invent missing requirements.

## Quality rules

- Target 150–300 words unless otherwise requested.
- Use a wiki style: neutral, declarative, information-dense, and without conversational introductions or conclusions.
- Avoid long paragraphs, repetition, narrative transitions, and phrases such as "in other words."
- Explain observable behavior before internal details.
- Use abstract names such as `Store`, `Clock`, `Serializer`, or `Policy` instead of specific products.
- Enclose API, function, parameter, type, key, and literal value names in inline backticks.
- Place every multiline example in a fenced Markdown block and always specify the correct language, such as `json`,
  `http`, `sql`, `python`, or `javascript`.
- Use the `text` tag only for genuinely language-independent pseudocode. Do not declare a language different from the
  content merely to obtain syntax highlighting.
- Separate the public contract from possible implementation strategies.
- Call out important decisions about data lifetime, invalidation, concurrency, security, privacy, idempotency, and error
  handling, but only when relevant.
- Do not turn the response into a complete tutorial, framework comparison, or exhaustive documentation.
- Do not present pseudocode as production-ready code.
- If the user names a concrete API or provides documentation, preserve its terminology and distinguish documented facts
  from design recommendations.
- Always suggest better-suited alternatives when the API should not be used or when the analyzed context supports a more
  appropriate option. Do not invent unsupported alternatives.
- Do not automatically add an evaluation or ask for a snippet when the user only wants the API entry.

## Style example

For a request such as "How can I implement remember?", interpret `remember` as a capability that retains a value across
requests only when the context does not indicate a specific API. Summarize the contract as
`remember(key, producer, policy) -> value`, describe lookup, expiration, computation, storage, and invalidation, then
show concise pseudocode in a `text` block and state when a cache or session is inappropriate.
