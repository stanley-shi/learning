/**

https://projecteuler.net/problem=15
Starting in the top left corner of a 2×2 grid, and only being able to move to the right and down, there are exactly 6 routes to the bottom right corner.

How many such routes are there through a 20×20 grid?

**/

// the answer is: choose 20 numbers from total 40 numbers, C(20,40)

def comb(c: Int, t: Int): Long = {
  //value should be: t!/(c!*(t-c)!)
  var result=1L
  for ( m <- 1 to c){
    result=result*(t+1-m)/m
  }
  result
}

println(comb(20,40))