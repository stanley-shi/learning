/**
By starting at the top of the triangle below and moving to adjacent numbers on the row below, the maximum total from top to bottom is 23.

3
7 4
2 4 6
8 5 9 3

That is, 3 + 7 + 4 + 9 = 23.

Find the maximum total from top to bottom in Problem67_triangle.txt , a 15K text file containing a triangle with one-hundred rows.

NOTE: This is a much more difficult version of Problem 18. It is not possible to try every route to solve this problem, as there are 299 altogether! If you could check one trillion (1012) routes every second it would take over twenty billion years to check them all. There is an efficient algorithm to solve it. ;o)
**/
val input_file = "Problem67_triangle.txt"
val nums_input=scala.io.Source.fromFile(input_file).getLines.map(_.split(" ").map(_.toInt)).toArray.reverse

val result_tmp = (0 to nums_input.length).map(n => 0).toArray

def getResult(nums: Array[Array[Int]], result: Array[Int]): Array[Int] ={
  if(nums.length==0) result
  else {
    val res1 = nums.head.zip(result.tail).map(n => n._1+n._2)
    val res2 = nums.head.zip(result.init).map(n => n._1+n._2)
    val newResult = res1.zip(res2).map(n => if (n._1>n._2) n._1 else n._2)
    getResult(nums.tail, newResult)
  }
}

val finalResult = getResult(nums_input, result_tmp)
println(finalResult(0))
