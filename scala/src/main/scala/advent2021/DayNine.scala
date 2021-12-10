package advent2021

object DayNine {
  var visited: Seq[(Int, Int)] = Seq.empty
  def partOne(input: Seq[String]) = {
    val step = input.head.length()
    val height = input.length
    val heightmap = input.map(_.map(_.toInt - '0').toList)
    (0 to height - 1)
      .flatMap(x => (0 to step - 1).map(checkNeighbors(heightmap, _, x)))
      .filterNot(_ == (-1, -1))
      .map(point => heightmap(point._2)(point._1))
      .map(getRisk)
      .sum

  }

  def partTwo(input: Seq[String]) = {
    val step = input.head.length()
    val height = input.length
    val heightmap = input.map(_.map(_.toInt - '0').toList)
    val lowPoints = (0 to height - 1)
      .flatMap(x => (0 to step - 1).map(checkNeighbors(heightmap, _, x)))
      .filterNot(_ == (-1, -1))

    lowPoints.map(findBasin(heightmap, _)).sorted.reverse.take(3).product
  }

  def checkNeighbors(matrix: Seq[Seq[Int]], x: Int, y: Int) = {
    val thisPoint = matrix(y)(x)
    val up = if (y - 1 < 0) true else matrix(y - 1)(x) > thisPoint
    val down =
      if (y + 1 >= matrix.length) true else matrix(y + 1)(x) > thisPoint
    val left = if (x - 1 < 0) true else matrix(y)(x - 1) > thisPoint
    val right =
      if (x + 1 >= matrix(y).length) true else matrix(y)(x + 1) > thisPoint
    if (up && down && left && right)
      (x, y)
    else
      (-1, -1)
  }

  def getRisk(n: Int) = 1 + n

  def findBasin(matrix: Seq[Seq[Int]], point: (Int, Int)): Int = {
    val (x, y) = point
    if (x < 0 || y < 0 || x >= matrix.head.length || y >= matrix.length || matrix(y)(x) == 9 || visited.contains(point))
      0
    else {
      visited = visited.appended(point)
      val up = findBasin(matrix, (x, y + 1))
      val down = findBasin(matrix, (x, y - 1))
      val right = findBasin(matrix, (x + 1, y))
      val left = findBasin(matrix, (x - 1, y)) 
      1 + up + down + right + left
    }
  }
}
