package monad

fun <a, b, c> compose(f: ((b)->c), g: ((a)->b)): (a) -> c = { x: a -> f(g(x)) }

infix fun <a, b, c> ((b)->c).o(g: ((a)->b)): (a) -> c = { x: a -> this(g(x)) }