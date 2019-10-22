package monad

import org.funktionale.currying.curried

fun safeDiv(divisor: Double, dividend: Double): Result<Double> = when (divisor) {
    0.0 -> Error("Can't divide by zero")
    else -> Ok(dividend / divisor)
}

val safeDivCurried = ::safeDiv.curried()
val divideByTwo = safeDivCurried(2.0)
val divideByFive = safeDivCurried(5.0)
val divideByZero = safeDivCurried(0.0)