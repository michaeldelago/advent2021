package advent2021

object DayThirteen extends AdventDay {

  type Matrix = Array[Array[Char]]

  override def partOne(input: Seq[String]): Int =
    val initPoints = input.takeWhile(_ != "").map(_.split(",").map(Integer.parseInt(_))).map(x => (x(0), x(1))).toSet
    val folds = input.dropWhile(!_.matches("fold along .*")).map(_.split(" ").last)
      .map(_.split("=").toArray)
      .map { case Array(axis, pos) => (axis.head, Integer.parseInt(pos))}

    val res = folds.scanLeft(initPoints) { case (points, (axis, pos)) =>
        axis match {
            case 'x' =>
                initPoints.map { case (x, y) => (pos - math.abs(pos - x), y)}
            case 'y' =>
                initPoints.map { case (x, y) => (x, pos - math.abs(pos - y))}
        }
    }

    // println(res.head.tail)
    res.tail.head.size

  override def partTwo(input: Seq[String]): Int =
    val initPoints = input.takeWhile(_ != "").map(_.split(",").map(Integer.parseInt(_))).map(x => (x(0), x(1))).toSet
    val folds = input.dropWhile(!_.matches("fold along .*")).map(_.split(" ").last)
      .map(_.split("=").toArray)
      .map { case Array(axis, pos) => (axis.head, Integer.parseInt(pos))}

    val res = folds.scanLeft(initPoints)(fold)

    printMatrix(res.last)
    res.last.size

  def fold(points: Set[(Int, Int)], foldSpec: (Char, Int)) =
    foldSpec match
      case ('x', pos) => points.map((x, y) => (pos - (pos - x).abs, y))
      case ('y', pos) => points.map((x, y) => (x, pos - (pos - y).abs))

  def printMatrix(matrix: Set[(Int, Int)]) =
    val maxX = matrix.map(_._1).max + 1
    val maxY = matrix.map(_._2).max + 1
    val initMatrix = Array.fill(maxY)(Array.fill(maxX)('.'))
    matrix.foldLeft(initMatrix) { case (matrix, (x, y)) =>
      matrix(y)(x) = '#'
      matrix
    }.map(x => println(x.mkString))
    println(" ")

}
