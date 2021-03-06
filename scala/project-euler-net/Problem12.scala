/**
The sequence of triangle numbers is generated by adding the natural numbers. So the 7th triangle number would be 1 + 2 + 3 + 4 + 5 + 6 + 7 = 28. The first ten terms would be:

1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...

Let us list the factors of the first seven triangle numbers:

 1: 1
 3: 1,3
 6: 1,2,3,6
10: 1,2,5,10
15: 1,3,5,15
21: 1,3,7,21
28: 1,2,4,7,14,28
We can see that 28 is the first triangle number to have over five divisors.

What is the value of the first triangle number to have over five hundred divisors?
**/

/**
Thoughts:
for each triangle number:
  calculate number of divisors;
  if num_div >500, exit 
  else continue;

calculate divisors for num:
  if a*b==num && gcd(a,b)==1 then
    return num_div(a)*num_div(b)

for any num, how to find a*b==num && gcd(a,b)==1:
  primes.takewhile.find; 
  if found, get the most out of it; 
  if not, this is a prime; num_divisors == 2

*/

def isPrime(num: Int, primes: scala.collection.mutable.MutableList[Long]):
Boolean = {
  primes.takeWhile( i =>  i * i <= num).forall( num % _ > 0)
}

def firstNPrimes(nth: Int) = {
  val primes=scala.collection.mutable.MutableList(2L,3L)
  var nextInt=4
  while(primes.length < nth){
    if (isPrime(nextInt, primes)) {
      primes+=nextInt
    }
    nextInt+=1
  }
  primes
}
val head1kPrimes=firstNPrimes(1000)
def numDivisors (num: Long, length: Int =1 ): Int = {
  if(num ==1 ) length
  else {
    val div = head1kPrimes.find(num%_==0)
    if(div.isEmpty){
      //nothing is found, not possible;
      length
    } else {
      var ind=0;
      val divisor=div.get
      var remainder=num
      while(remainder % divisor==0){
        remainder = remainder / divisor
        ind+=1
      }
      numDivisors(remainder,(ind+1)*length)
    }
  }
}
val limit=(head1kPrimes.last*head1kPrimes.last).toInt
val res = (1 to limit).find (num => numDivisors(num*(num+1)/2, 1) > 500).get
println(res*(res+1)/2)