package advent2021

object DayEight {
  type Digit = Set[Char]
  case class Display(input: Seq[Digit], output: Seq[Digit])

  def partOne(input: Seq[String]) = {
    val outputs = input.map(_.split(" \\| ")(1))
    val lengths = outputs
      .flatMap(_.split(" "))
      .groupBy(_.length())
      .transform((_, v) => v.length)
    lengths.getOrElse(2, 0) + lengths.getOrElse(3, 0) + lengths.getOrElse(
      4,
      0
    ) + lengths.getOrElse(7, 0)
  }
  def partTwo(input: Seq[String]) = {
    def parse(displayPart: String) = displayPart.split(" ").map(_.toSet).toList
    val displays = input.map(_.split(" \\| ")).map(x => Display(parse(x(0)), parse(x(1))))
    displays.map(decode(_)).sum
    
  }

  def decode(display: Display) = {
    val one = display.input.filter(_.size == 2).head
    val seven = display.input.filter(_.size == 3).head
    val four = display.input.filter(_.size == 4).head
    val eight = display.input.filter(_.size == 7).head
    val three = display.input.filter(three => three.size == 5 && three.subsetOf(eight) && one.subsetOf(three) && seven.subsetOf(three)).head
    val nine = display.input.filter(nine => nine.size == 6 && three.subsetOf(nine)).head
    val five = display.input.filter(five => five.size == 5 && five.subsetOf(nine) && five != three).head
    val two = display.input.filter(two => two.size == 5 && ! two.subsetOf(nine) && two != three).head
    val six = display.input.filter(six => six.size == 6 && five.subsetOf(six) && six != nine).head
    val zero = display.input.filter(zero => zero.size == 6 && ! five.subsetOf(zero)).head
    val numbers = Map(0 -> zero, 1 -> one, 2 -> two, 3 -> three, 4 -> four, 5 -> five, 6 -> six, 7 -> seven, 8 -> eight, 9 -> nine)
    display.output.flatMap(x => numbers.find(_._2 == x).map(_._1)).mkString.toInt
      
  }
}
