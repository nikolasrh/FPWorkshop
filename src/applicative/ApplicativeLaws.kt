package applicative

import kotlin.test.assertEquals
import kotlin.test.fail

/*
    https://en.wikibooks.org/wiki/Haskell/Applicative_functors#Applicative_functor_laws

    pure id <*> v = v                            -- Identity
    pure f <*> pure x = pure (f x)               -- Homomorphism
    u <*> pure y = pure ($ y) <*> u              -- Interchange
    pure (.) <*> u <*> v <*> w = u <*> (v <*> w) -- Composition
*/

fun main() {
    testFirstLaw()
    testSecondLaw()
    testThirdLaw()
    testFourthLaw()
}

// pure id <*> v = v
fun testFirstLaw() {
    val rx = rtrn(1)
    val id: (Int) -> Int = ::id

    val left = rtrn(id) `‹*›` rx
    val right = rx

    resultAssertEquals(left, right)
}

// pure f <*> pure x = pure (f x)
fun testSecondLaw() {
    val x = 1;
    val f: (Int) -> Int = ::id

    val left = rtrn(f) `‹*›` rtrn(x)
    val right = rtrn(f(x))

    resultAssertEquals(left, right)
}

// u <*> pure y = pure ($ y) <*> u
fun testThirdLaw() {
    val x = 1
    val rx = rtrn(x)
    val f: (Int) -> Int = ::id
    val rf = rtrn(f)
    val higherOrderFun = { f: (Int) -> Int -> f(x) }

    val left = rf `‹*›` rx
    val right = rtrn(higherOrderFun) `‹*›` rf

    resultAssertEquals(left, right)
}

// pure (.) <*> u <*> v <*> w = u <*> (v <*> w)
fun testFourthLaw() {
    // Får ikke til å kalle rtrn med en compose-funksjon som bruker generics
    val compose = { f: (Int) -> Int -> { g: (Int) -> Int -> { x: Int -> f(g(x)) }}}
    val radd1 = rtrn(add1)
    val radd2 = rtrn(add2)
    val rx = rtrn(1)

    val left = rtrn(compose) `‹*›` radd1 `‹*›` radd2 `‹*›` rx
    val right = radd1 `‹*›` (radd2 `‹*›` rx)

    resultAssertEquals(left, right)
}

fun <T> resultAssertEquals(left: Result<T>, right: Result<T>) = when {
    left is Ok && right is Ok -> assertEquals(left.value, right.value)
    left is Error && right is Error -> assertEquals(left.error, right.error)
    else -> fail()
}