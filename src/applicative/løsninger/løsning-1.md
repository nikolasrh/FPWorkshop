```kotlin
fun <a, b> apply(rf: Result<((a)->b)>, rx: Result<a>) : Result<b> = when {
    rf is Ok && rx is Ok -> Ok(rf.value(rx.value))
    rf is Error && rx is Error -> Error(rf.error + ", " + rx.error)
    rf is Error -> Error(rf.error)
    rx is Error -> Error(rx.error)
    else -> Error("") // Not reachable
}
```