package advent2021
import scala.io.Source
import java.util.Calendar

object Tasks extends App {
  val day =
    if (args != null && args.length > 0) Integer.parseInt(args(0))
    else Calendar.getInstance.get(Calendar.DAY_OF_MONTH)

  val input = loadInput(day, false)

  day match {
    case 1 =>
      println(
        s"Day $day, part 1: ${DayOne.partOne(input)}\n" +
          s"Day $day, part 2: ${DayOne.partTwo(input)}"
      )
    case 2 =>
      println(
        s"Day $day, part 1: ${DayTwo.partOne(input)}\n" +
          s"Day $day, part 2: ${DayTwo.partTwo(input)}"
      )
    case 3 =>
      println(
        s"Day $day, part 1: ${DayThree.partOne(input)}\n" +
          s"Day $day, part 2: ${DayThree.partTwo(input)}"
      )
    case 4 =>
      println(
        s"Day $day, part 1: ${DayFour.partOne(input)}\n" +
          s"Day $day, part 2: ${DayFour.partTwo(input)}"
      )
    case 5 =>
      println(
        s"Day $day, part 1: ${DayFive.partOne(input)}\n" +
          s"Day $day, part 2: ${DayFive.partTwo(input)}"
      )
    case 6 =>
      println(
        s"Day $day, part 1: ${DaySix.partOne(input)}\n" +
          s"Day $day, part 2: ${DaySix.partTwo(input)}"
      )
    case 7 =>
      println(
        s"Day $day, part 1: ${DaySeven.partOne(input)}\n" +
          s"Day $day, part 2: ${DaySeven.partTwo(input)}"
      )
    case 8 =>
      println(
        s"Day $day, part 1: ${DayEight.partOne(input)}\n" +
          s"Day $day, part 2: ${DayEight.partTwo(input)}"
      )
    case 9 =>
      println(
        s"Day $day, part 1: ${DayNine.partOne(input)}\n" +
          s"Day $day, part 2: ${DayNine.partTwo(input)}"
      )
    case 10 =>
      println(
        s"Day $day, part 1: ${DayTen.partOne(input)}\n" +
          s"Day $day, part 2: ${DayTen.partTwo(input)}"
      )
    case 11 =>
      println(
        s"Day $day, part 1: ${DayEleven.partOne(input)}\n" +
          s"Day $day, part 2: ${DayEleven.partTwo(input)}"
      )
    case 12 =>
      println(
        s"Day $day, part 1: ${DayTwelve.partOne(input)}\n" +
          s"Day $day, part 2: ${DayTwelve.partTwo(input)}"
      )
    case 13 =>
      println(
        s"Day $day, part 1: ${DayThirteen.partOne(input)}\n" +
          s"Day $day, part 2: ${DayThirteen.partTwo(input)}"
      )
    case 14 =>
      println(
        s"Day $day, part 1: ${DayFourteen.partOne(input)}\n" +
          s"Day $day, part 2: ${DayFourteen.partTwo(input)}"
      )
    case _ => println("Day not implemented!")
  }

  def loadInput(day: Int, test: Boolean) = {
    val extenstion = if (test) ".test" else ""
    if (new java.io.File(s"../inputs/$day$extenstion").exists)
      Source.fromFile(s"../inputs/$day$extenstion").getLines().toList
    else List()
  }
}
