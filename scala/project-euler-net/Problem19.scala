/**
You are given the following information, but you may prefer to do some research for yourself.

1 Jan 1900 was a Monday.
Thirty days has September, April, June and November.
All the rest have thirty-one, Saving February alone,
Which has twenty-eight, rain or shine.
And on leap years, twenty-nine.
A leap year occurs on any year evenly divisible by 4, but not on a century unless it is divisible by 400.
How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?
**/

/**
  Thought: let 1 Jan 1900 be day 0, we can construct a list of numbers;
  for each number, we calculate which month/year/day it is and then do a filtering
**/

class DateInfo (dw: Int, dm: Int, my: Int, yr: Int) {
  // all the following numbers are starting from 1;
  val dayOfWeek: Int = dw
  val dayOfMonth: Int = dm
  val monthOfYear: Int = my
  val year: Int = yr
}

def nextDay(today: DateInfo): DateInfo {

}

val days = new Array[DataInfo](37000)
days(0)=DateInfo(1, 1, 1, 1900)


