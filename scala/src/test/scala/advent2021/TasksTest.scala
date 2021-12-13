package advent2021
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.time.Days
// import matchers.should._

/** @version 1.1.0 */
class AdventTest extends AnyFunSuite {

  test("Day One") {
    val input = Tasks.loadInput(1, true)
    assert(DayOne.partOne(input) == 7)
    assert(DayOne.partTwo(input) == 5)
  }

  test("Day Two") {
    val input = Tasks.loadInput(2, true)
    assert(DayTwo.partOne(input) == 150)
    assert(DayTwo.partTwo(input) == 900)
  }

  test("Day Three") {
    val input = Tasks.loadInput(3, true)
    assert(DayThree.partOne(input) == 198)
    assert(DayThree.partTwo(input) == 230)
  }

  test("Day Four") {
    val input = Tasks.loadInput(4, true)
    assert(DayFour.partOne(input) == 4512)
    assert(DayFour.partTwo(input) == 1924)
  }

  test("Day Five") {
    val input = Tasks.loadInput(5, true)
    assert(DayFive.partOne(input) == 5)
    assert(DayFive.partTwo(input) == 12)
  }

  test("Day Six") {
    val input = Tasks.loadInput(6, true)
    assert(DaySix.partOne(input) == 5934)
    assert(DaySix.partTwo(input) == BigInt("26984457539"))
  }

  test("Day Seven") {
    val input = Tasks.loadInput(7, true)
    assert(DaySeven.partOne(input) == 37)
    assert(DaySeven.partTwo(input) == 168)
  }

  test("Day Eight") {
    val input = Tasks.loadInput(8, true)
    assert(DayEight.partOne(input) == 26)
    assert(DayEight.partTwo(input) == 61229)
  }

  test("Day Nine") {
    val input = Tasks.loadInput(9, true)
    assert(DayNine.partOne(input) == 15)
    assert(DayNine.partTwo(input) == 1134)
  }

  test("Day Ten") {
    val input = Tasks.loadInput(10, true)
    assert(DayTen.partOne(input) == 26397)
    assert(DayTen.partTwo(input) == 288957)
  }

  test("Day Eleven") {
    val input = Tasks.loadInput(11, true)
    assert(DayEleven.partOne(input) == 1656)
    assert(DayEleven.partTwo(input) == 195)
  }

  test("Day Twelve") {
    val input = Tasks.loadInput(12, true)
    assert(DayTwelve.partOne(input) == 10)
    assert(DayTwelve.partTwo(input) == 36)
  }

}
