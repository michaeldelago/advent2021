package advent2021
import scala.io.Source
import java.util.Calendar

object Tasks extends App {
  val day =
    if (args.length > 0) args(0)
    else Calendar.getInstance.get(Calendar.DAY_OF_MONTH)
  val input =
    if (new java.io.File(s"inputs/$day").exists)
      Source.fromFile(s"inputs/$day").getLines.toList
    else List()
  day match {
    case "1" =>
      println(
        s"Day $day, part 1: ${dayOne.partOne(input)}\n" +
          s"Day $day, part 2: ${dayOne.partTwo(input)}"
      )
    case _ => println("Day not implemented!")
  }
}
