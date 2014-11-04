/**
A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,

a2 + b2 = c2
For example, 32 + 42 = 9 + 16 = 25 = 52.

There exists exactly one Pythagorean triplet for which a + b + c = 1000.
Find the product abc.
**/

val nums = (100 to 500).flatMap(a => (a to 900).map(b => (a,b))).filter(n => n._1*n._1 + n._2 *n._2 == (1000-n._1-n._2)*(1000-n._1-n._2))
println(nums)