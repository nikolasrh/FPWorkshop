package functor

sealed class Result<T>
class Ok<T>(val value: T) : Result<T>()
class Error<T>(val error: String) : Result<T>()

fun <T> id(x: T): T = throw NotImplementedError()

// return
// pure
fun <a> rtrn(x: a): Result<a> = throw NotImplementedError()

// fmap
// lift
// Select
fun <a, b> map(f: ((a) -> b), rx: Result<a>): Result<b> = throw NotImplementedError()