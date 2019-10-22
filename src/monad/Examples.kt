package monad

fun main() {
    // Bruker kleisli-komposisjon for å sy sammen nye funksjoner
    val divideByFiveAfterTwo = divideByFive `›=›` divideByTwo
    val divideByZeroAfterFiveAfterTwo = divideByZero `›=›` divideByFiveAfterTwo

    divideByFiveAfterTwo(20.0) // Ok(2)
    divideByZeroAfterFiveAfterTwo(20.0) // Error(...)

    // Bruker bind til å "pipe" en verdi igjennom flere funksjoner
    rtrn(20.0) `››=` divideByTwo `››=` divideByFive // Ok(5)
}