package advent2021

object DayFive {
  def partOne(input: Seq[String]) = {
    val coords =
      unfuckInput(input).map(x => LineSegment(x(0), x(1))).filterNot(_.isDiag)
    val maxX = coords.flatMap(line => List(line.start._1, line.end._1)).max + 1
    val maxY = coords.flatMap(line => List(line.start._2, line.end._2)).max + 1
    val matrix = Matrix(maxX, maxY, Array.ofDim[Int](maxX, maxY))
    val pointsAdded = coords.foldLeft(matrix) { case (acc, line) =>
      acc.addLine(line)
    }
    pointsAdded.matrix.map(_.count(_ >= 2)).sum
  }

  def partTwo(input: Seq[String]) = {
    val coords = unfuckInput(input).map(x => LineSegment(x(0), x(1)))
    val maxX = coords.flatMap(line => List(line.start._1, line.end._1)).max + 1
    val maxY = coords.flatMap(line => List(line.start._2, line.end._2)).max + 1
    val emptyMatrix = Matrix(maxX, maxY, Array.ofDim[Int](maxX, maxY))
    val pointsAdded = coords.foldLeft(emptyMatrix) { case (acc, line) =>
      acc.addLine(line)
    }

    pointsAdded.matrix.map(_.count(_ >= 2)).sum
  }

  def unfuckInput(input: Seq[String]) = {
    input
      .filterNot(_ == "")
      .map(
        _.split("\\s->\\s")
          .map(_.split(",").map(_.toInt))
          .map(arr => (arr(0), arr(1)))
      )

  }

  case class LineSegment(start: (Int, Int), end: (Int, Int)) {
    val isDiag = start._1 != end._1 && start._2 != end._2
    val slope =
      if (isDiag) (end._2 - start._2).toDouble / (end._1 - start._1).toDouble
      else 0.0
    val distance = math.sqrt(
      math.abs(math.pow(end._1 - start._1, 2) + math.pow(end._2 - start._2, 2))
    )
    val isVertical = end._1 == start._1 && !isDiag
    val isHorizontal = end._2 == start._2 && !isDiag
    val yIntercept = end._2 - (slope * end._1)

    def slopeForm(x: Int) = {
      ((slope * x) + yIntercept).toInt
    }
  }

  case class Matrix(x: Int, y: Int, matrix: Array[Array[Int]] = Array.empty) {
    def addLine(line: LineSegment) = {
      val new_matrix = matrix
      new_matrix(line.end._2)(line.end._1) += 1
      new_matrix(line.start._2)(line.start._1) += 1

      if (line.isDiag) {
        val start = List(line.start._1, line.end._1).min
        val end = List(line.start._1, line.end._1).max
        (start + 1 to end - 1).foreach(x => new_matrix(line.slopeForm(x))(x) += 1)
      } else if (line.isHorizontal) {
        val start = List(line.start._1, line.end._1).min
        val end = List(line.start._1, line.end._1).max
        (start + 1 to end - 1).foreach(new_matrix(line.start._2)(_) += 1)
      } else if (line.isVertical) {
        val start = List(line.start._2, line.end._2).min
        val end = List(line.start._2, line.end._2).max
        (start + 1 to end - 1).foreach(new_matrix(_)(line.start._1) += 1)
      }
      this.copy(x, y, new_matrix)
    }
  }

}