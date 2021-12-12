package advent2021

import scala.annotation.tailrec

object DaySeven {
  def partOne(input: Seq[String]) = {
    val inputAsInts = input(0).split(",").map(_.toInt)
    val crabs = inputAsInts.map(CrabSub(_))
    inputAsInts.map(n => crabs.map(_.moveto(n)).sum).min
  }

  def partTwo(input: Seq[String]) = {
    val inputAsInts = input(0).split(",").map(_.toInt)
    val length = inputAsInts.max
    val crabs = inputAsInts.map(ExpensiveCrabSub(_))
    (0 to length).map(n => crabs.map(_.moveto(n)).sum).min
  }

  case class CrabSub(pos: Int) {
    def moveto(n: Int) = math.abs(pos - n)
  }

  case class ExpensiveCrabSub(pos: Int) {
    def moveto(n: Int) = {
      val desired = math.abs(pos - n)
      stepcost(desired, 0)
    }

    @tailrec
    private def stepcost(distance: Int, current: Int): Int = {
      if (distance == 0)
        current
      else {
        stepcost(distance - 1, current + distance)
      }
    }
  }

}
