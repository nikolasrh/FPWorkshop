```kotlin
fun <a, b> map(f: ((a) -> b), rx: Result<a>): Result<b> = apply(rtrn(f), rx)
```