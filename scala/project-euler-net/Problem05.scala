/**

2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.

What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?

**/

def posNum (limit: Int) = {
	def calc(num: Int): Int = if ( (1 to limit).forall(num%_==0) ) num else calc (num+1)
	calc(1)
}
println(posNum(20))