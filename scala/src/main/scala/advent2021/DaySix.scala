package advent2021

import scala.annotation.tailrec

object DaySix {
  def partOne(input: Seq[String]) = {
    var school = parseInput(input)
    fishAge(80, school)
  }

  def partTwo(input: Seq[String]) = {
    var school = parseInput(input)
    fishAge(256, school)
  }

  def oldfishAge(days: Int, fish: Seq[LanternFish]): Seq[LanternFish] = {
    if (days == 0)
      fish
    else {
      val newfish = fish.map(_.tick)
      val babies = (1 to fish.count(_.days == 0)).map(x => LanternFish(8))
      oldfishAge(days - 1, newfish.appendedAll(babies))
    }
  }

  def parseInput(input: Seq[String]) = {
    SchoolOfFish(
      input(0)
        .split(",")
        .map(_.toInt)
        .groupBy(x => x)
        .view
        .mapValues(x => BigInt(x.length))
        .toMap
        .withDefault(x => 0)
    )
  }

  def fishAge(days: Int, school: SchoolOfFish): BigInt = {
    if (days == 0) school.counts.values.sum
    else
      fishAge(days - 1, school.rotate)
  }
}

case class LanternFish(days: Int) {
  def tick = if (days == 0) this.reproduce else this.copy(days - 1)
  def reproduce = this.copy(6)
}

case class SchoolOfFish(counts: Map[Int, BigInt]) {
  def rotate = {
    this.copy(
      Map[Int, BigInt](
        (8 -> counts(0)),
        (7 -> counts(8)),
        (6 -> (counts(7) + counts(0))),
        (5 -> counts(6)),
        (4 -> counts(5)),
        (3 -> counts(4)),
        (2 -> counts(3)),
        (1 -> counts(2)),
        (0 -> counts(1))
      )
    )
  }
}
