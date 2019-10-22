package monad

interface Result<T>
class Ok<T>(val value: T) : Result<T>
class Error<T>(val error: String) : Result<T>

fun <T> id(x: T): T = x

fun <T> rtrn(x: T): Result<T> = Ok(x)

// flatMap
// SelectMany
fun <a, b> bind(rx: Result<a>, f: (a) -> Result<b>) : Result<b> =
    throw NotImplementedError()

infix fun <a, b> Result<a>.`››=`(f: (a) -> Result<b>) : Result<b> = bind(this, f)

fun <a, b, c> kleisliCompose(f: (b) -> Result<c>, g: (a) -> Result<b>): (a) -> Result<c> =
    throw NotImplementedError()

infix fun <a, b, c> ((b) -> Result<c>).`›=›`(g: (a) -> Result<b>): (a) -> Result<c> = kleisliCompose(this, g)

// join
fun <a> flatten(rrx: Result<Result<a>>): Result<a> =
    throw NotImplementedError()

fun <a, b> apply(rf: Result<((a)->b)>, rx: Result<a>) : Result<b> = when {
    rf is Ok && rx is Ok -> Ok(rf.value(rx.value))
    rf is Error && rx is Error -> Error(rf.error + ", " + rx.error)
    rf is Error -> Error(rf.error)
    rx is Error -> Error(rx.error)
    else -> Error("") // Not reachable
}

infix fun <a, b> Result<((a)->b)>.`‹*›`(rx: Result<a>) : Result<b> = apply(this, rx)

fun <a, b> map(f: ((a) -> b), rx: Result<a>): Result<b> = when (rx) {
    is Ok -> Ok(f(rx.value))
    is Error -> Error(rx.error)
    else -> Error("") // Not reachable
}

infix fun <a, b> ((a) -> b).`‹!›`(rx: Result<a>): Result<b> = map(this, rx)