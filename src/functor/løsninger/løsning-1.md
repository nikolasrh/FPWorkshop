```kotlin
fun <a> id(x: a): a = x

fun <a> rtrn(x: a): Result<a> = Ok(x)

fun <a, b> map(f: ((a) -> b), rx: Result<a>): Result<b> = when (rx) {
    is Ok -> Ok(f(rx.value))
    is Error -> Error(rx.error)
    else -> Error("") // Not reachable
}
```