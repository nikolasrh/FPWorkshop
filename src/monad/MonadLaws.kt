package monad

import kotlin.test.assertEquals
import kotlin.test.fail


/*
    https://wiki.haskell.org/Monad_laws

    Left identity: return a >>= f ≡ f a

    Right identity: m >>= return ≡ m

    Associativity: (m >>= f) >>= g ≡ m >>= (\x -> f x >>= g)
*/

fun main() {
    testLeftIdentity()
    testRightIdentity()
    testAssociativity()
}

fun testLeftIdentity() {
    val x = 10.0
    val f = divideByTwo

    val left = rtrn(x) `››=` f
    val right = f(x)

    resultAssertEquals(left, right)
}

fun testRightIdentity() {
    val rx = rtrn(1)

    val left = rx
    val right = rx `››=` ::rtrn

    resultAssertEquals(left, right)
}

fun testAssociativity() {
    val rx = rtrn(10.0)

    val left = (rx `››=` divideByTwo) `››=` divideByFive
    val right = rx `››=` (divideByTwo `›=›` divideByFive)

    resultAssertEquals(left, right)
}

fun <T> resultAssertEquals(left: Result<T>, right: Result<T>) = when {
    left is Ok && right is Ok -> assertEquals(left.value, right.value)
    left is Error && right is Error -> assertEquals(left.error, right.error)
    else -> fail()
}