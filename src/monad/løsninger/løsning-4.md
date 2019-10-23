```kotlin
fun <a, b> map(f: ((a) -> b), rx: Result<a>): Result<b> = bind(rx, compose(::rtrn, f))

fun <a, b> apply(rf: Result<((a)->b)>, rx: Result<a>) : Result<b> = bind(rf) { f -> map(f, rx) }
```