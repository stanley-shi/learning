/**
The prime factors of 13195 are 5, 7, 13 and 29.

What is the largest prime factor of the number 600851475143 ?

**/

def isPrime(num: Int, primes: scala.collection.mutable.MutableList[Int]): Boolean = {
  primes.takeWhile( i =>  i * i <= num).forall( num % _ > 0)
}

def primeListUnder(upper: Int) = { 
  val primes=scala.collection.mutable.MutableList(2,3); 
  var num=4
  while(num<upper) {
    if(isPrime(num,primes))
    { 
      primes+=num
    }
    num+=1
  }
  primes
}

val num = 600851475143L
val nl = Math.sqrt(num)

val value = primeListUnder(nl.toInt).reverse.find(num % _ ==0)
println(value.get)
