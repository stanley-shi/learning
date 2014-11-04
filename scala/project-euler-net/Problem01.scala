/**
If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.

Find the sum of all the multiples of 3 or 5 below 1000.

**/

object problem01 {
	def main(args: Array[String]){
		println((1 to 999).filter( n =>  n%3 == 0 || n%5 ==0 ).sum)
	}
}