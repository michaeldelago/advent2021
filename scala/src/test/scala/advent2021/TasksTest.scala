package advent2021
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.time.Days

/** @version 1.1.0 */
class AdventTest extends AnyFunSuite {

  test("Day One, part 1") {
    val input = Tasks.loadInput(1, true)
    assert(DayOne.partOne(input) == 7)
  }

  test("Day One, part 2") {
    val input = Tasks.loadInput(1, true)
    assert(DayOne.partTwo(input) == 5)
  }

  test("Day Two, part 1") {
    val input = Tasks.loadInput(2, true)
    assert(DayTwo.partOne(input) == 150)
  }

  test("Day Two, part 2") {
    val input = Tasks.loadInput(2, true)
    assert(DayTwo.partTwo(input) == 900)
  }

  test("Day Three, part 1") {
    val input = Tasks.loadInput(3, true)
    assert(DayThree.partOne(input) == 198)
  }

  test("Day Three, part 2") {
    val input = Tasks.loadInput(3, true)
    assert(DayThree.partTwo(input) == 230)
  }

  test("Day Four, part 1") {
    val input = Tasks.loadInput(4, true)
    assert(DayFour.partOne(input) == 4512)
  }

  test("Day Four, part 2") {
    val input = Tasks.loadInput(4, true)
    assert(DayFour.partTwo(input) == 1924)
  }

  test("Day Five, part 1") {
    val input = Tasks.loadInput(5, true)
    assert(DayFive.partOne(input) == 5)
  }

  test("Day Five, part 2") {
    val input = Tasks.loadInput(5, true)
    assert(DayFive.partTwo(input) == 12)
  }

  test("Day Six, part 1") {
    val input = Tasks.loadInput(6, true)
    assert(DaySix.partOne(input) == 5934)
  }

  test("Day Six, part 2") {
    val input = Tasks.loadInput(6, true)
    assert(DaySix.partTwo(input) == BigInt("26984457539"))
  }

  test("Day Seven, part 1") {
    val input = Tasks.loadInput(7, true)
    assert(DaySeven.partOne(input) == 37)
  }

  test("Day Seven, part 2") {
    val input = Tasks.loadInput(7, true)
    assert(DaySeven.partTwo(input) == 168)
  }

  test("Day Eight, part 1") {
    val input = Tasks.loadInput(8, true)
    assert(DayEight.partOne(input) == 26)
  }

  test("Day Eight, part 2") {
    val input = Tasks.loadInput(8, true)
    assert(DayEight.partTwo(input) == 61229)
  }

  test("Day Nine, part 1") {
    val input = Tasks.loadInput(9, true)
    assert(DayNine.partOne(input) == 15)
  }

  test("Day Nine, part 2") {
    val input = Tasks.loadInput(9, true)
    assert(DayNine.partTwo(input) == 1134)
  }
  test("Day Ten, part 1") {
    val input = Tasks.loadInput(10, true)
    assert(DayTen.partOne(input) == 26397)
  }

  test("Day Ten, part 2") {
    val input = Tasks.loadInput(10, true)
    assert(DayTen.partTwo(input) == 288957)
  }

  test("Day Eleven, part 1") {
    val input = Tasks.loadInput(11, true)
    assert(DayEleven.partOne(input) == 1656)
  }

  test("Day Eleven, part 2") {
    val input = Tasks.loadInput(11, true)
    assert(DayEleven.partTwo(input) == 195)
  }

  test("Day Twelve, part 1") {
    val input = Tasks.loadInput(12, true)
    assert(DayTwelve.partOne(input) == 10)
  }

  test("Day Twelve, part 2") {
    val input = Tasks.loadInput(12, true)
    assert(DayTwelve.partTwo(input) == 36)
  }

  test("Day Thirteen, Part 1") {
    val input = Tasks.loadInput(13, true)
    assert(DayThirteen.partOne(input) == 17)
  }

  test("Day Thirteen, Part 2") {
    val input = Tasks.loadInput(13, true)
    assert(DayThirteen.partTwo(input) == 16)
  }

  test("Day Fourteen, Part 1") {
    val input = Tasks.loadInput(14, true)
    assert(DayFourteen.partOne(input) == 1588L)
  }

  test("Day Fourteen, Part 2") {
    val input = Tasks.loadInput(14, true)
    assert(DayFourteen.partTwo(input) == 2188189693529L)
  }

}
