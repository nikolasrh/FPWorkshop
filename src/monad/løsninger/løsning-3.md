```kotlin
fun <a> flatten(rrx: Result<Result<a>>): Result<a> = bind(rrx, ::id)
```