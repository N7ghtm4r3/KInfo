# Personal KDoc and Javadoc Style

Treat every value inside angle brackets as an instruction to replace, never as literal output.

## Contents

- [Shared voice and layout](#shared-voice-and-layout)
- [Javadoc templates](#javadoc-templates)
- [KDoc templates](#kdoc-templates)
- [Semantic special cases](#semantic-special-cases)

## Shared voice and layout

- Write documentation in English.
- Start type summaries with `The ...`; start constructor summaries with `Constructor to init ...`; start method
  summaries with `Method used to ...` or, for a named operation, `Method to perform ...`.
- Prefer the user's recognizable vocabulary, but use grammatically correct and semantically truthful descriptions.
- Do not add a period at the end of summaries, tag descriptions, or prose paragraphs.
- Start `@param` descriptions with a capital letter, commonly `The ...`.
- Start `@return` descriptions with lowercase `the ...` or `whether ...`.
- End typed return descriptions with the exact shape `as <type link>`; do not insert `a` or `an` between `as` and the
  linked type.
- Keep tags of the same kind adjacent. Insert one blank documentation line between the description and tags, between
  parameters and returns, and before `@since`.
- Wrap long prose naturally at the project's existing line width. Do not reflow unrelated comments.
- Describe observable purpose and contract rather than narrating individual implementation statements.

Use these summary patterns according to the declaration:

```text
The <Type> class is useful to <purpose>
The <Type> object allows to <capability>
The <Type> interface defines the contract to <purpose>
The <Type> enum is useful to represent <concept>
The <Type> annotation is useful to mark <target or behavior>
Constructor to init the <Type> class
Method used to <action>
Method to perform <named operation>
Method used to get the <property> instance
Method used to set the <property> instance
Method used to check whether <condition>
```

## Javadoc templates

Use `{@code ...}` for the documented type name, field name, primitive, literal, or short code fragment. Use
`{@link Type}`, `{@link #member}`, or `{@link Type#member}` for navigable declarations.

### Type

```java
/**
 * The {@code TypeName} class is useful to <purpose>
 *
 * <optional detail paragraph>
 *
 * @author <exact author value>
 * @see RelatedType
 * @see AnotherRelatedType
 *
 * @since <explicit version or revision>
 */
```

Omit unused optional paragraphs and tags together with their surrounding blank lines. Keep Java `@author` and `@see`
lines adjacent, as in the supplied Java style. Put `@since` last when present.

For other type kinds, replace `class` with `interface`, `enum`, `record`, or `annotation` and choose the matching shared
summary pattern.

### Field

```java
/**
 * {@code fieldName} the <meaning or role of the field>
 */
```

### Constructor

```java
/**
 * Constructor to init the {@link TypeName} class
 *
 * @param first  The <meaning of first>
 * @param second The <meaning of second>
 */
```

Align Java parameter descriptions within the same tag block when this does not conflict with the repository formatter.

For a framework-required empty constructor, add only when true:

```java
 * @apiNote empty constructor required
```

### Method

```java
/**
 * Method used to <action and relevant context>
 *
 * @param input The <meaning of input>
 *
 * @return the <result> as {@link ResultType}
 */
```

Use these return forms where appropriate:

```java
 * @return whether <condition> as {@code boolean}
 * @return the {@link #fieldName} instance as {@link List} of {@link ItemType}
```

Use `@param <T>` for Java type parameters and `@throws ExceptionType` only for exceptions that belong to the declared or
documented contract. Omit `@return` for constructors and `void` methods.

## KDoc templates

Use backticks for the documented type name, literals, acronyms, and conceptual code terms such as `JVM`, `DEK`, or
`Base64`. Use `[Type]`, `[member]`, or `[Type.member]` for declarations that should be linked.

### Type or object

```kotlin
/**
 * The `TypeName` object allows to <capability>
 *
 * <optional detail paragraph using [RelatedType] links>
 *
 * @param input The <meaning of a primary-constructor parameter>
 * @property state The <meaning of a primary-constructor property>
 *
 * @author <exact author value>
 *
 * @see RelatedType
 * @see AnotherRelatedType
 *
 * @since <explicit version or revision>
 */
```

Use the corresponding `class`, `interface`, `enum`, or `annotation` summary for other Kotlin declaration kinds. Omit the
primary-constructor tag group when it is not applicable. Keep constructor tags, `@author`, `@see`, and `@since` as
separate groups divided by blank documentation lines.

### Property

```kotlin
/**
 * `propertyName` the <meaning or role of the property>
 */
```

For primary-constructor properties, prefer `@property propertyName ...` in the type KDoc when a standalone property
comment cannot be attached naturally. Use `@param parameterName ...` for constructor parameters that are not properties.

### Secondary constructor

```kotlin
/**
 * Constructor to init the [TypeName] class
 *
 * @param input The <meaning of input>
 */
```

### Function

```kotlin
/**
 * Method used to <action and relevant context>
 *
 * @param input The <meaning of input>
 *
 * @return the <result> as [ResultType]
 */
```

Use these return forms where appropriate:

```kotlin
 * @return whether <condition> as [Boolean]
 * @return the [propertyName] instance as [List] of [ItemType]
```

Use one space after each KDoc parameter name; do not vertically align KDoc parameter descriptions. Use `@param T` for
type parameters and `@receiver` for an extension receiver when its role is not already clear. Omit `@return` for `Unit`
functions. Add `@since` last, separated from the preceding tag group by one blank documentation line.

## Semantic special cases

- Describe getters as retrieving the backing property only when they actually expose that value; describe
  transformations explicitly.
- Describe predicates with `whether ... as {@code boolean}` or `whether ... as [Boolean]`.
- Describe wrappers and delegates by their caller-visible action, not merely by saying they call another method.
- Mention encryption, persistence, network, blocking, suspension, mutation, caching, or validation only when confirmed
  by code or contract.
- Preserve meaningful existing detail paragraphs and tags after correcting their syntax and facts.
- Do not turn grammar problems or copied parameter descriptions from style samples into reusable rules.
