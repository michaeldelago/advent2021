package advent2021

object DayThree {
  def partOne(input: Seq[String]) = {
    val line_length = input(0).toString.length()
    val tests = (0 to line_length - 1).map(math.pow(2, _).toInt).reverse
    val numbers = input
      .filterNot(_ == "")
      .map(Integer.parseInt(_, 2))
      .map(x => tests.map(x & _))

    val gamma = (0 to line_length - 1)
      .map(n => mostCommonValue(numbers, n))
    val gamma_rate = Integer.parseInt(gamma.mkString, 2)

    val epsilon = (0 to line_length - 1)
      .map(n => leastCommonValue(numbers, n))
    val epsilon_rate = Integer.parseInt(epsilon.mkString, 2)

    gamma_rate * epsilon_rate
  }

  def partTwo(input: Seq[String]) = {
    val line_length = input(0).toString.length()
    val tests = (0 to line_length - 1).map(math.pow(2, _).toInt).reverse
    val numbers = input
      .filterNot(_ == "")
      .map(Integer.parseInt(_, 2))
      .map(x => tests.map(x & _))

    val o2 = Integer.parseInt(o2Reading(numbers, 0),2 )
    val co2 = Integer.parseInt(co2Reading(numbers, 0),2 )
    o2 * co2
  }

  private def mostCommonValue(numbers: Seq[IndexedSeq[Int]], field: Int) = {
    val sizes = numbers
      .map(_(field))
      .groupBy(_ != 0).view
      .mapValues(x => x.size)

    // println(sizes.getOrElse(true, 0), field)
    if (sizes.getOrElse(true, 0) >= sizes.getOrElse(false, 0)) 1
    else 0
  }

  private def leastCommonValue(input: Seq[IndexedSeq[Int]], field: Int) = {
    mostCommonValue(input, field) ^ 1
  }

  private def o2Reading(tested_numbers: Seq[IndexedSeq[Int]], field: Int): String = {
    val mostCommon = mostCommonValue(tested_numbers, field)
    val newNumbers = tested_numbers
      .map(_.map(x => if (x != 0) 1 else 0))
      .filter(_(field) == mostCommon)

    if (newNumbers.size == 1)
      newNumbers.head.mkString
    else
      o2Reading(newNumbers, field + 1)
  }

  private def co2Reading(tested_numbers: Seq[IndexedSeq[Int]], field: Int): String = {
    val leastCommon = leastCommonValue(tested_numbers, field)
    val newNumbers = tested_numbers
      .map(_.map(x => if (x > 0) 1 else 0))
      .filter(_(field) == leastCommon)
    if (newNumbers.size == 1)
      newNumbers.head.mkString
    else
      co2Reading(newNumbers, field + 1)
  }
}
