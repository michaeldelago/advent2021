package advent2021

object DayThirteen extends AdventDay {

  type Matrix = Array[Array[Char]]

  override def partOne(input: Seq[String]): Int =
    val initPoints = input.takeWhile(_ != "").map(_.split(",").map(Integer.parseInt(_)))
    val folds = input.dropWhile(!_.matches("fold along .*")).map(_.split(" ").last)
      .map(_.split("=").toArray)
      .map { case Array(axis, pos) => (axis.head, Integer.parseInt(pos))}
    val (maxX, maxY) = (initPoints.map(_(0)).max + 1, initPoints.map(_(1)).max + 1)
    val initMatrix = Array.fill(maxY)(Array.fill(maxX)('.'))
    val matrix = initPoints.foldLeft(initMatrix) { case (matrix, Array(x, y)) =>
      matrix.updated(y, matrix(y).updated(x, '#'))
    }

    val (axis, pos) = folds.head
    val (a, b) = splitMatrix(matrix, axis, pos)
    mergeMatrices(a, flipMatrix(b, axis)).map(_.count(_ == '#')).sum

  override def partTwo(input: Seq[String]): Int =
    val initPoints = input.takeWhile(_ != "").map(_.split(",").map(Integer.parseInt(_)))
    val folds = input.dropWhile(!_.matches("fold along .*")).map(_.split(" ").last)
      .map(_.split("=").toArray)
      .map { case Array(axis, pos) => (axis.head, Integer.parseInt(pos))}
    val (maxX, maxY) = (initPoints.map(_(0)).max + 1, initPoints.map(_(1)).max + 1)
    val initMatrix = Array.fill(maxY)(Array.fill(maxX)('.'))
    val matrix = initPoints.foldLeft(initMatrix) { case (matrix, Array(x, y)) =>
      matrix.updated(y, matrix(y).updated(x, '#'))
    }
    val result = folds.foldLeft(matrix) {case (matrix, (axis, pos)) =>
      val (a, b) = splitMatrix(matrix, axis, pos)
      mergeMatrices(a, flipMatrix(b, axis))
    }

    printMatrix(result)
    result.map(_.count(_ == '#')).sum

  def splitMatrix(matrix: Matrix, axis: Char, pos: Int): (Matrix, Matrix) =
    axis match {
      case 'x' =>
        val length = matrix.head.length
        val (a, b) = (
          matrix.map(_.slice(0, pos)),
          matrix.map(_.slice(length - pos, length))
        )
        (a, b)

      case 'y' =>
        val height = matrix.length
        (matrix.slice(0, pos), matrix.slice(height - pos, height))
    }

  def flipMatrix(matrix: Matrix, axis: Char): Matrix =
    axis match {
      case 'x' =>
        matrix.map(_.reverse)
      case 'y' =>
        matrix.reverse
    }

  def mergeMatrices(matrixA: Matrix, matrixB: Matrix): Matrix =
    val points = for
      x <- (0 to matrixA.head.length - 1)
      y <- (0 to matrixA.length - 1)
    yield (x, y)

    points.foldLeft(matrixB) { case (matrix, (x, y)) =>
      if (matrixA(y)(x) == '#' || matrixB(y)(x) == '#')
        matrix(y)(x) = '#'
      matrix
    }

  def printMatrix(matrix: Matrix) =
    matrix.map(x => println(x.mkString("")))
    println(" ")
}
