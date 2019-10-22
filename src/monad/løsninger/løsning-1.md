```kotlin
fun <a, b> bind(rx: Result<a>, f: (a) -> Result<b>) : Result<b> = when (rx) {
    is Ok -> f(rx.value)
    is Error -> Error(rx.error)
    else -> Error("")
}

fun <a, b, c> kleisliCompose(f: (b) -> Result<c>, g: (a) -> Result<b>): (a) -> Result<c> = { x: a -> bind(g(x), f) }

fun <a> flatten(rrx: Result<Result<a>>): Result<a> = when (rrx) {
    is Ok -> rrx.value
    is Error -> Error(rrx.error)
    else -> Error("")
}
```