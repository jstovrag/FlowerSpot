# FlowerSpot - Android-Kotlin (Compose)

### Technology Stack

    Modularization
    Gradle Dependency management
    Gradle written in Kotlin DSL
    Custom Plugin(dependencies with no duplication)
    Static Code Analytics (KTLint)
    Hilt (dependency injection)
    StateFlows - Coroutines


### Dependencies

* Latest Android Studio
* Homebrew (for setup script - required for installation of ktlint)
* Ruby + overcommit gem

```
scripts/setup
```
This script will:
 * Setup overcommit hooks
 * Install ktlint (required for hooks)

Android lint is recommended to be run only on CI, since it can be slow for larger projects, while ktlint can be run on every commit.


### Kotlin styleguides

This project is following **official Kotlin codestyle**, which is also enforced by [ktlint](https://github.com/shyiko/ktlint). It follows both codestyle from [kotlinlang.org](https://kotlinlang.org/docs/reference/coding-conventions.html) and [Android Kotlin styleguide](https://developer.android.com/kotlin/style-guide).

### Android file naming conventions

This project is following this [Android naming conventions](https://github.com/ribot/android-guidelines/blob/master/project_and_code_guidelines.md)

### New feature

Use [GitFlow](https://www.atlassian.com/git/tutorials/comparing-workflows/gitflow-workflow) development workflow with tests included.

## Tools

### Networking with OkHttp + Retrofit

Networking is implemented via [Retrofit](https://square.github.io/retrofit/), with Http client provided by [OkHttp](http://square.github.io/okhttp/) that provides easy API communication and response parsing using [Gson](https://github.com/google/gson).

### Testing

Unit, instrumentation tests

## Maintainers

- [Jasmin Stovrag](https://github.com/jstovrag)
