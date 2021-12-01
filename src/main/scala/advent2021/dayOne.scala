package advent2021

object dayOne {
    def partOne(input: Seq[String]) = {
        val as_ints = input.map(_.toInt)
        val shifted = as_ints.slice(1, as_ints.length)
        as_ints.zip(shifted).map { case (a, b) => a < b }.count(_ == true)
    }

    def partTwo(input: Seq[String]) = {
        val as_ints = input.map(_.toInt)
        val normalized = as_ints.sliding(3).map(_.sum).toList
        val shifted = normalized.slice(1, normalized.length)
        normalized.zip(shifted).map { case (a, b) => a < b }.count(_ == true)
    }
}