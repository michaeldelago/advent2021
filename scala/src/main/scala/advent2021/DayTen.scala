package advent2021

object DayTen {
  val bracketScores = Map(
    ")" -> 3,
    "]" -> 57,
    "}" -> 1197,
    ">" -> 25137
  )

  val p2BracketScores = Map(
    ")" -> 1,
    "]" -> 2,
    "}" -> 3,
    ">" -> 4
  )

  def partOne(input: Seq[String]) = {
    input.map(getBadBracket(_)).filterNot(_ == "").map(bracketScores.getOrElse(_, 0)).sum
  }

  def partTwo(input: Seq[String]) = {
    val scores = input.filter(getBadBracket(_) == "").map(getBracketCloser(_)).map(_.foldLeft(0L) {
      case (acc, char) =>
        (5 * acc) + p2BracketScores.getOrElse(char, 1)
    }).sorted
    println(scores)
    scores(scores.length / 2)
  }
  def getBadBracket(bracketSet: String): String = {
    var stack: List[Char] = List()
    for (char <- bracketSet) {
      char match {
        case '(' | '[' | '{' | '<' =>
          stack = stack :+ char
        case ')' | ']' | '}' | '>' =>
          if (stack.isEmpty)
            return char.toString()
          if (!isCounterpart(char, stack.last))
            return char.toString()
          stack = stack.dropRight(1)
      }
    }
    return ""
  }

  def getBracketCloser(bracketSet: String) = {
    var stack: List[Char] = List()
    for (char <- bracketSet) {
      char match {
        case '(' | '[' | '{' | '<' =>
          stack = stack :+ char
        case ')' | ']' | '}' | '>' =>
          stack = stack.dropRight(1)
      }
    }

    stack.reverse.map(getCounterpart(_).toString())
  }

  private def isCounterpart(bracketClose: Char, bracketOpen: Char): Boolean = {
    bracketOpen match {
      case '(' =>
        bracketClose == ')'
      case '{' =>
        bracketClose == '}'
      case '[' =>
        bracketClose == ']'
      case '<' =>
        bracketClose == '>'
    }
  }

  private def getCounterpart(bracket: Char) = {
    bracket match {
      case '(' =>
        ')'
      case '{' =>
        '}'
      case '[' =>
        ']'
      case '<' =>
        '>'
    }
  }
}
