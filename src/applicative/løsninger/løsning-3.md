```kotlin
fun <a, b, c> map2(f: ((a) -> (b) -> c), ra: Result<a>, rb: Result<b>): Result<c> =
    apply(map(ra, f), rb)

fun <a, b, c, d> map3(f: ((a) -> (b) -> (c) -> d), ra: Result<a>, rb: Result<b>, rc: Result<c>): Result<d> =
    apply(apply(map(ra, f), rb), rc)
```