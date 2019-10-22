```kotlin
fun <a, b> map(f: ((a) -> b), rx: Result<a>): Result<b> = bind(rx, compose(::rtrn, f))

fun <a, b> apply(rf: Result<((a)->b)>, rx: Result<a>) : Result<b> = bind(rf) { f -> map(rx, f) }
```

```kotlin
fun <a, b> bind(rx: Result<a>, f: (a) -> Result<b>) : Result<b> = flatten(map(rx, f))
```