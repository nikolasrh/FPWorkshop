```kotlin
fun <a> flatten(rrx: Result<Result<a>>): Result<a> = when (rrx) {
    is Ok -> rrx.value
    is Error -> Error(rrx.error)
    else -> Error("")
}

fun <a, b> bind(rx: Result<a>, f: (a) -> Result<b>) : Result<b> = flatten(map(rx, f))
```
