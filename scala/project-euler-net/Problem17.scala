/**

If the numbers 1 to 5 are written out in words: one, two, three, four, five, then there are 3 + 3 + 5 + 4 + 4 = 19 letters used in total.

If all the numbers from 1 to 1000 (one thousand) inclusive were written out in words, how many letters would be used?


NOTE: Do not count spaces or hyphens. For example, 342 (three hundred and forty-two) contains 23 letters and 115 (one hundred and fifteen) contains 20 letters. The use of "and" when writing out numbers is in compliance with British usage.

**/

val dict = collection.mutable.Map (
  (1, "one"),(2, "two"),(3,"three"),(4,"four"),(5,"five"),(6,"six"), (7,"seven"),
  (8,"eight"),(9,"nine"),(10,"ten"),(11,"eleven"),(12,"twelve"),(13, "thirteen"),
  (14,"fourteen"),(15,"fifteen"),(16,"sixteen"),(17,"seventeen"),(18,"eighteen"),
  (19,"nineteen"),(20,"twenty"),(30,"thirty"),(40,"forty"),(50,"fifty"),(60,"sixty"),
  (70, "seventy"),(80,"eighty"),(90,"ninety"),(100,"hundred"),(1000,"thousand")
)

def numChars(num: Int):String = {
  if(num == 1000) dict(1) + dict(1000)
  else {
    if (num >=100) {
      // a1 hundred and b1-ty-c1
        val hun=num/100
        if(num%100==0) dict(hun)+dict(100)
        else {
          dict(hun)+ dict(100) + "and" + numChars(num%100)
        }
      } else {
        val tens=num/10
        if (tens <= 1) dict(num) else (dict(tens*10) + (if(num%10!=0) dict(num%10) else ""))
      }
  }
}

// val chars = numChars(150)
// println(chars)
// println(chars.length)

val result = (1 to 1000).map(numChars(_).length).reduce(_+_)
println(result)

