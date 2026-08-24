# Service Client and Helper Documentation Blueprints

Use these blueprints for remote clients, requesters, service gateways, payload or query builders, endpoint assemblers,
and utility helpers. Derive every detail from the target code and use the base style guide for language-specific syntax.

## Contents

- [Classify the service role](#classify-the-service-role)
- [Clients and requesters](#clients-and-requesters)
- [Remote operations](#remote-operations)
- [Builders and helpers](#builders-and-helpers)
- [Service validation](#service-validation)

## Classify the service role

- Recognize a client or gateway from its remote-operation methods, transport abstraction, request annotations, or
  delegated service calls.
- Recognize a payload or query builder from the value it constructs, not merely from a method name.
- Recognize an assembler from its composition of paths, identifiers, configuration, or request parts.
- Recognize a general helper or extension from its reusable transformation or validation contract.
- Do not assume a protocol, response envelope, serialization format, authentication model, endpoint structure, or base
  framework that the target does not establish.

## Clients and requesters

For a client-like class, use:

```text
The `TypeName` class is useful to communicate with <remote system or service>
```

Document primary-constructor configuration before metadata tags. Describe only parameters actually present, for example
host or base address, credentials, identifiers, timeouts, and debug options. Do not copy a sample's configuration policy
or security choices.

Use a property summary for persistent client state:

```kotlin
/**
 * `stateName` the <configuration, headers, cache, or state> used to <purpose>
 */
```

Use `@see` only for actual superclasses, implemented interfaces, or central contracts. Resolve `@author` and `@since`
through the main workflow.

## Remote operations

For a low-level method that directly represents a remote operation, prefer:

```text
Request to <retrieve, create, update, delete, or execute a resource action>
```

For an orchestration method that asks another layer to perform the operation, prefer `Method used to request <action>`.
Do not mention the HTTP verb or path unless it is useful to the public contract.

Document request inputs in signature order. Use precise generic patterns such as:

```text
@param page The page to request
@param pageSize The number of items to include in the page
@param identifier The identifier of the resource
@param filter The filter to apply to the request
@param value The value to send
```

For nullable inputs, state that they are optional only when `null` has that meaning. Describe defaults when they change
caller-visible behavior.

Describe the real result type:

```text
@return the result of the request as [ResultType]
@return the requested resource as [ResourceType]
@return the paginated resources as [PageType] of [ResourceType]
```

Never force a raw response object when the method returns a typed model, and never add `@return` to `Unit` methods.

## Builders and helpers

Use these sentence families when the implementation matches:

```text
Method used to create the payload for the <operation> request
Method used to create the query for the <operation> request
Method used to assemble the <endpoint, path, or request part>
Method used to load the [stateName] with <value>
Method used to clear the current <client state, cache, or session data>
Method used to format <input> as <output>
Method used to validate whether <condition>
```

Describe builder results with the concrete constructed type:

```text
@return the payload for the request as [PayloadType]
@return the query for the request as [QueryType]
@return the assembled path as [String]
```

For overrides, describe the specialized behavior and preserve the inherited contract. For overloads, document the
distinction in inputs or convenience behavior rather than duplicating an inaccurate generic sentence.

Do not expose internal payload keys, serialization calls, transport wrappers, or header assembly unless callers must
know them. Do not infer that a helper belongs to networking solely because it shares a package with a client.

## Service validation

- Match every `@param` to one and only one real parameter; catch duplicates, omissions, stale names, and copied
  descriptions.
- Verify the request action, resource, result type, optional inputs, and side effects against the body or delegated
  contract.
- Verify that overloaded methods describe their actual difference.
- Verify that reset or clear methods name only the state they truly clear.
- When annotations, signatures, delegation, and bodies disagree, inspect the effective contract or report the ambiguity
  instead of selecting the most convenient claim.
- Document declaration-level state, not temporary local payload, query, or response variables.
- Remove sample-specific products, resources, endpoints, identifiers, base classes, annotations, and authentication
  assumptions.
