object Test {
  def isPrime(num: Int, primes: scala.collection.mutable.MutableList[Long]): Boolean = {
    primes.takeWhile( i =>  i * i <= num).forall( num % _ > 0)
  }


  def primeListUnder(upper: Int) = { 
    val primes=scala.collection.mutable.MutableList(2L,3L); 
    var num=4
    while( num < upper) {
      if(isPrime(num,primes))
      { 
        primes += num
      }
      num += 1
    }
    primes
  }

  def main(args: Array[String]) {
      val pls=primeListUnder(args(0).toInt)
      val sum=pls.sum
      println(sum)
  }

}
