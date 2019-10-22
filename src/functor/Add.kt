package functor

import org.funktionale.currying.curried

fun add(x: Int, y: Int) = x + y

val addCurried = ::add.curried()
val add1 = addCurried(1)
val add2 = addCurried(2)