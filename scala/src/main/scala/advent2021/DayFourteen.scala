package advent2021

object DayFourteen extends advent2021.AdventDay {
  override def partOne(input: Seq[String]) =
    val (initString, rules) = normalizeInput(input)
    val initState = rules.keys.map(key => (key -> 0L)).toMap
    val initScore = rules.values.map(value => value -> 0L).toMap
    val state = initString.sliding(2).foldLeft(initState) { case (state, subString) =>
      state.updated(subString, state(subString) + 1)
    }

    val score = initString.foldLeft(initScore.withDefaultValue(0L)) { case (state, char) =>
      val asString = char.toString
      state.updated(asString, state(asString) + 1)
    }

    val result = process(10, rules, score, state)
    (result.values.max - result.values.min)


  override def partTwo(input: Seq[String]) =
    val (initString, rules) = normalizeInput(input)
    val initState = rules.keys.map(key => (key -> 0L)).toMap
    val initScore = rules.values.map(value => value -> 0L).toMap
    val state = initString.sliding(2).foldLeft(initState) { case (state, subString) =>
      state.updated(subString, state(subString) + 1)
    }

    val score = initString.foldLeft(initScore.withDefaultValue(0L)) { case (state, char) =>
      val asString = char.toString
      state.updated(asString, state(asString) + 1)
    }

    val result = process(40, rules, score, state)
    (result.values.max - result.values.min)

  def normalizeInput(input: Seq[String]) =
    val initString = input(0)
    val rulePattern = "([A-z]{2}) -> ([A-Z])".r
    val rules = input
      .dropWhile(!rulePattern.matches(_))
      .map(rulePattern.findAllIn(_).subgroups)
      .map { reMatch => (reMatch(0) -> reMatch(1))}.toMap
    (initString, rules)

  def process(n: Int, rules: Map[String, String], score: Map[String, Long], state: Map[String, Long]): Map[String, Long] =
    var (stateCopy, scoreCopy) = (state, score)
    for (_ <- (0 to n - 1))
      var new_state = rules.keys.map((_ -> 0L)).toMap
      for ((k -> v) <- stateCopy) {
        val x = rules(k)
        val prefix = s"${k(0).toString}$x"
        val suffix = s"$x${k(1).toString}"
        new_state = new_state.updated(prefix, new_state(prefix) + v)
        new_state = new_state.updated(suffix, new_state(suffix) + v)
        scoreCopy = scoreCopy.updated(x, scoreCopy(x) + v)
      }
      stateCopy = new_state

    scoreCopy
}
