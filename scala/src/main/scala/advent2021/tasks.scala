package advent2021
import scala.io.Source
import java.util.Calendar

object Tasks extends App {
  val day =
    if (args.length > 0) args(0)
    else Calendar.getInstance.get(Calendar.DAY_OF_MONTH)
  val input =
    if (new java.io.File(s"../inputs/$day").exists)
      Source.fromFile(s"../inputs/$day").getLines.toList
    else List()
  day match {
    case "1" =>
      println(
        s"Day $day, part 1: ${DayOne.partOne(input)}\n" +
          s"Day $day, part 2: ${DayOne.partTwo(input)}"
      )
    case "2" =>
      println(
        s"Day $day, part 1: ${DayTwo.partOne(input)}\n" +
          s"Day $day, part 2: ${DayTwo.partTwo(input)}"
      )
    case "3" =>
      println(
        s"Day $day, part 1: ${DayThree.partOne(input)}\n" +
          s"Day $day, part 2: ${DayThree.partTwo(input)}"
      )
    case "4" =>
      println(
        s"Day $day, part 1: ${DayFour.partOne(input)}\n" +
          s"Day $day, part 2: ${DayFour.partTwo(input)}"
      )
    case "5" =>
      println(
        s"Day $day, part 1: ${DayFive.partOne(input)}\n" +
          s"Day $day, part 2: ${DayFive.partTwo(input)}"
      )
    case _ => println("Day not implemented!")
  }
}
