/**

By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.

What is the 10 001st prime number?

**/

def isPrime(num: Int, primes: scala.collection.mutable.MutableList[Long]): Boolean = {
  primes.takeWhile( i =>  i * i <= num).forall( num % _ > 0)
}

def nthPrime(nth: Int): Long = {
	val primes=scala.collection.mutable.MutableList(2L,3L)
  var nextInt=4
	while(primes.length<nth){
    if (isPrime(nextInt, primes)) {
      primes+=nextInt
    }
    nextInt+=1
	}
  primes.last
}
println(nthPrime(10001))