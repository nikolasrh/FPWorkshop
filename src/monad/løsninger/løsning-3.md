```kotlin
fun <a, b> map(rx: Result<a>, f: ((a) -> b)): Result<b> = bind(rx) { x -> rtrn(f(x)) }

fun <a, b> apply(rf: Result<((a)->b)>, rx: Result<a>) : Result<b> = bind(rf) { f -> map(rx, f) }
```

```kotlin
fun <a, b> bind(rx: Result<a>, f: (a) -> Result<b>) : Result<b> = flatten(map(rx, f))
```