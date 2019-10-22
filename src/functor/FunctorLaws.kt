package functor

import kotlin.test.assertEquals
import kotlin.test.fail

/*
    https://en.wikibooks.org/wiki/Haskell/The_Functor_class#The_functor_laws

    fmap id = id

    fmap (g . f) = fmap g . fmap f
 */

fun main() {
    testFirstLaw()
    testSecondLaw()
}

// fmap id = id
fun testFirstLaw() {
    val x = 1
    val rx = rtrn(x)
    val id: (Int) -> Int = ::id

    val left = x
    val right = id(x)

    val rleft = rx
    val rright = map(id, rx)

    assertEquals(left, right)
    resultAssertEquals(rleft, rright)
}

// fmap (g . f) = fmap g . fmap f
fun testSecondLaw() {
    val x = 1
    val rx = rtrn(x)

    val add3 = { x: Int -> add1(add2(x)) }

    val left = map(add3, rx)
    val right = map(add1, map(add2, rx))

    resultAssertEquals(left, right)
}

fun <T> resultAssertEquals(left: Result<T>, right: Result<T>) = when {
    left is Ok && right is Ok -> assertEquals(left.value, right.value)
    left is Error && right is Error -> assertEquals(left.error, right.error)
    else -> fail()
}