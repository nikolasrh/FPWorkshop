package applicative

sealed class Result<T>
class Ok<T>(val value: T) : Result<T>()
class Error<T>(val error: String) : Result<T>()

fun <a> id(x: a): a = x

fun <a> rtrn(x: a): Result<a> = Ok(x)

fun <a, b> map(f: ((a) -> b), rx: Result<a>): Result<b> = when (rx) {
    is Ok -> Ok(f(rx.value))
    is Error -> Error(rx.error)
}

fun <a, b, c> map2(f: ((a) -> (b) -> c), ra: Result<a>, rb: Result<b>): Result<c> =
    throw NotImplementedError()

fun <a, b, c, d> map3(f: ((a) -> (b) -> (c) -> d), ra: Result<a>, rb: Result<b>, rc: Result<c>): Result<d> =
    throw NotImplementedError()

fun <a, b> apply(rf: Result<((a)->b)>, rx: Result<a>) : Result<b> =
    throw NotImplementedError()

// Obs! Det er ikke vanlige "less than" og "greater than" i infix-operatoren
infix fun <a, b> Result<((a)->b)>.`‹*›`(rx: Result<a>) : Result<b> = apply(this, rx)