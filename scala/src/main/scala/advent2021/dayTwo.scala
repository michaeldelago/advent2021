package advent2021
// (depth, horiz, aim)
object DayTwo {
  def partOne(input: Seq[String]) = {
    val opcodes = input.map(_.split(" ")).map { case Array(command, n) =>
      (command, n.toInt)
    }
    var (depth, horiz) = opcodes
      .map { opcode =>
        opcode match {
          case ("forward", n) => (0, n)
          case ("down", n)    => (n, 0)
          case ("up", n)      => (-n, 0)
        }
      }
      .reduce(((x, y) => (x._1 + y._1, x._2 + y._2)))

    depth * horiz
  }

  def partTwo(input: Seq[String]) = {
    val opcodes = input.map(_.split(" ")).map { case Array(command, n) =>
      (command, n.toInt)
    }
    val (depth, horiz, _) = opcodes
      .map { opcode =>
        opcode match {
          case ("forward", n) => (n, n, 0)
          case ("down", n)    => (0, 0, n)
          case ("up", n)      => (0, 0, -n)
        }
      }
      .prepended((0, 0, 0))
      .reduce(((x, y) => (x._1 + (x._3 * y._1), x._2 + y._2, x._3 + y._3)))
    depth * horiz
  }
}
