object Problem14 {
  def recCollatz(num: Long, length: Int): Int = num match {
    case 1 => length
    case n if (n%2==0) => recCollatz(num/2, length+1)
    case n => recCollatz(3*n+1, length+1)
  }

  def recCollatz2(num: Long, length: Int): Int = {
    if (num==1) length
    else if (num%2==0) recCollatz2(num/2,length+1)
    else recCollatz2(3*num+1,length+1)
  }

  def recCollatzBad(num: Long): Int = num match {
    case 1 => 1
    case n if (n%2==0) => recCollatzBad(num/2)+1
    case n => recCollatzBad(3*n+1)+1
  }

  def maxCollatzLen (limit: Int): (Int, Int) = {
    var maxLength=1
    var maxN=1
    var n=1
    while ( n < limit ){
      val l = recCollatz(n,1)
      if (l > maxLength ) {
        maxLength=l
        maxN=n
      }
      n=n+1
    }
    (maxN, maxLength)
  }

  def main(args: Array[String]){
    println(maxCollatzLen(args(0).toInt))
  }
}
