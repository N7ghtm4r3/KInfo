# UI Documentation Blueprints

Use these blueprints as role-aware sentence patterns. Derive all nouns, actions, links, and guarantees from the target
code. The examples use KDoc syntax; preserve their semantics but switch to the Javadoc syntax from the base style guide
for Java targets.

## Contents

- [Classify the UI role](#classify-the-ui-role)
- [Composable components](#composable-components)
- [Screens and sections](#screens-and-sections)
- [ViewModels and state holders](#viewmodels-and-state-holders)
- [UI validation](#ui-validation)

## Classify the UI role

- Recognize a component from its UI annotation, emitted widgets, receiver, parameters, and call sites.
- Recognize a screen from its ownership of full-screen content or navigation-level presentation, whether implemented as
  a function or class.
- Recognize a section or layout helper from the subset of content it renders. Do not promote every private composable to
  a screen.
- Recognize a ViewModel or state holder from its base contract, exposed state, event handlers, and lifecycle
  responsibilities. A name suffix is supporting evidence only.
- Apply only the closest role. Do not add framework types, architecture layers, or dependencies that are absent from the
  code.

## Composable components

Use a short nominal summary instead of forcing `Method used to ...` when the function primarily emits UI:

```text
Custom [Widget] used to <display or allow a purpose>
Component used to <display or collect content>
Representative icon based on the [ModelType]
Badge used as marker for <condition>
Custom [Divider] used to separate each [ItemType]
```

Link a base widget or model only when the link resolves and materially explains the component. For private components,
keep the summary short and omit automatic `@author` or `@see` tags.

Describe common parameters with the role proven by the signature and body:

```text
@param modifier The modifier to apply to the component
@param state The state displayed by the component
@param item The item to display
@param isSelected Whether the item is currently selected
@param onAction The callback to invoke when <event>
```

Use `The support viewmodel used by the component` only when a ViewModel parameter actually exists. Prefer state and
event descriptions when the component is state-hoisted. Omit `@return` for a composable returning `Unit`.

## Screens and sections

For a screen class, use:

```text
The [TypeName] displays <main content> and allows <primary interactions>
```

For a screen function, use `Screen used to display <content>` or a more specific nominal summary. Add `@author` through
the main metadata workflow and add `@see` only for real base types or contracts.

Use these patterns for members when their behavior matches:

```text
The custom content displayed in the <screen or container>
The <subject> details displayed in the screen
The <name> section used to <purpose>
Section used to display <content>
Method used to format the [Component] component as [Row]
Method used to format the [Component] component as [Column]
Method used to collect or instantiate the states of the screen
```

Mention compact, expanded, portrait, landscape, or another layout condition only when the implementation or annotation
establishes it. Describe procedural layout helpers with `Method used to ...`; describe content-producing sections with a
nominal phrase.

## ViewModels and state holders

For a ViewModel class, use this role-specific summary:

```text
The **TypeName** class is the support class used to <manage screen state and actions>
```

For a new ViewModel comment, keep the ViewModel name in bold exactly as shown instead of changing it to backticks or a
self-link. Preserve a different established convention only when the target file already uses one. Replace the
responsibility with the real domain-neutral contract of the target. Do not assume networking, persistence, pagination,
authentication, or session ownership.

Use these patterns when verified:

```kotlin
/**
 * `stateName` the state used to <handle or expose responsibility>
 */
```

```text
Method used to load and retrieve <items> to update the [stateName]
Method used to request <user-visible action>
Method used to clear <owned state or session>
```

Document callbacks according to their actual invocation point:

```text
@param onAction The callback to invoke when <event>
@param onSuccess The callback to invoke after <confirmed successful outcome>
@param onComplete The optional callback to invoke when <operation completes>
```

Do not mention coroutine scopes, dispatchers, clients, or internal wrappers unless they are part of the caller-visible
contract. Do not claim success when a callback can run on failure or before completion.

## UI validation

- Cover every requested component, section, state property, and event method, including private helpers when the user
  asks for every member.
- Verify that summaries describe rendered content or caller-visible effects rather than incidental widget choices.
- Verify each callback's timing, nullability, and success conditions from the body.
- Verify that layout links and model links resolve in the target file.
- Remove copied screen names, domain entities, architecture layers, and framework assumptions that are not present in
  the target.
