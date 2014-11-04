/**
A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.

Find the largest palindrome made from the product of two 3-digit numbers.

**/

def isPalindromic (num: Int) = num.toString.reverse == num.toString

val maxN=(100 to 999).flatMap(num => (100 to 999).map(num2 => num*num2)).filter(isPalindromic).max

println(maxN)