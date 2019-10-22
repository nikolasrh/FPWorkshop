Når et `Result` inneholder en funksjon så bruker man `apply` til å kalle funksjonen med ett argument. Funksjonen må ta inn et argument og returnere en ny funksjon som tar inn neste argument. Dette gjøres til man sitter igjen med return-verdien.

Siden `apply` tar inn 2 `Result`, som begge kan være av typen `Error`, så må 4 forskjellige kombinasjoner håndteres.

Implementer `apply` i ApplicativeResult slik at flere feil blir komma-separert, f.eks:

```kotlin
Result<(Int) -> Int)> rf = Error("Feil #1")
Result<Int> rx = Error("Feil #2")
apply(rf, rx) // Error("Feil #1, Feil #2")
```

Kjør ApplicativeLaws uten feil.

Lag noen eksempler som sender inn flere `Èrror` til en lang `apply`-rekke og se at tekstene blir slått sammen.