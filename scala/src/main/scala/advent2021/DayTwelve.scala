package advent2021

object DayTwelve {
  type Path = Set[String]

  def partOne(input: Seq[String]) = {
    val caveLinks = input
      .map(_.split("-"))
      .foldLeft(Map[String, Set[String]]().empty.withDefaultValue(Set.empty)) {
        case (caveMap, caves) =>
          val Array(a, b) = caves
          caveMap
            .updated(a, caveMap(a) + b)
            .updated(b, caveMap(b) + a)
      }

    findPaths("start", caveLinks, Set("start"), true)
  }

  def partTwo(input: Seq[String]) = {
    val caveLinks = input
      .map(_.split("-"))
      .foldLeft(Map[String, Set[String]]().empty.withDefaultValue(Set.empty)) {
        case (caveMap, caves) =>
          val Array(a, b) = caves
          caveMap
            .updated(a, caveMap(a) + b)
            .updated(b, caveMap(b) + a)
      }

    findPaths("start", caveLinks, Set("start"), false)
  }

  def findPaths(current: String, caveLinks: Map[String, Set[String]], visitedPaths: Path, alternate: Boolean = false): Int = {
    val potentialPaths = if (alternate) caveLinks(current) -- visitedPaths else caveLinks(current) - "start"
    if (current == "end")
      1
    else if (potentialPaths.isEmpty)
      0
    else {
      val newVisited = if (current.head.isLower) visitedPaths + current else visitedPaths
      potentialPaths.foldLeft(0) { (caves, path) =>
        val duplicateSmall = alternate || (path.head.isLower && visitedPaths.contains(path))
        caves + findPaths(path, caveLinks, newVisited, duplicateSmall)
      }
    }
  }
}
