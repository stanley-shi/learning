/**
2^15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.

What is the sum of the digits of the number 2^1000?
**/

def sumOfDigits(num: BigInt): Int = {
  num.toString.toCharArray.map(_.toInt - 48).reduce(_+_)
}

val value=(1 to 1000).map(n => BigInt(2)).reduce(_*_)
val sum=sumOfDigits(value)
println(sum)