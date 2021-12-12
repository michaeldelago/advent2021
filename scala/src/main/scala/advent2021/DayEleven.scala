package advent2021

import scala.annotation.tailrec

object DayEleven {
  def partOne(input: Seq[String]) = {
    val octopussy = input
      .filterNot(_ == "")
      .map(_.map(_.toInt - '0').toVector.map(Octopus))
      .toVector
    val school = SchoolOfOctopus(octopussy)
    (1 to 100)
      .foldLeft(school) { case (school, round) =>
        school.step.flash()
      }
      .flashes
  }

  def partTwo(input: Seq[String]) = {
    val octopussy = input
      .filterNot(_ == "")
      .map(_.map(_.toInt - '0').toVector.map(Octopus))
      .toVector
    val school = SchoolOfOctopus(octopussy)
    getAllFlash(school)
  }

  case class Octopus(energy: Int) {
    def step = this.copy(energy + 1)
    def reset = this.copy(0)
  }

  @tailrec
  def getAllFlash(school: SchoolOfOctopus, round: Int = 0): Int = {
    if (school.allFlash)
      round
    else
      getAllFlash(school.step.flash(), round + 1)
  }

  case class SchoolOfOctopus(matrix: Seq[Seq[Octopus]], flashes: Int = 0) {
    def step = this.copy(matrix.map(_.map(_.step)))

    def allFlash = matrix.forall(_.forall(_.energy == 0))

    def flash(flashed: Seq[(Int, Int)] = Seq.empty): SchoolOfOctopus = {
      val flashPoints = (0 until matrix.length).flatMap(y =>
        (0 until matrix(y).length).map(x => (x, y)).filter { case (x, y) =>
          matrix(y)(x).energy >= 10
        }.filterNot(flashed.contains(_))
      )
      if (flashPoints.length == 0) {
        val newMatrix =
          matrix.map(_.map(octo => if (octo.energy > 9) octo.reset else octo))
        this.copy(newMatrix, flashes)
      } else {
        val surroundingPoints = flashPoints.flatMap { case (x, y) =>
          surrounding(x, y)
        }
        val newMatrix = surroundingPoints.foldLeft(matrix) {
          case (matrix, (x, y)) =>
            matrix.updated(y, matrix(y).updated(x, matrix(y)(x).step))
        }
        
        this.copy(newMatrix, flashes + flashPoints.length).flash(flashPoints.appendedAll(flashed))
      }
    }

    private def surrounding(x: Int, y: Int) = {
      (-1 to 1).flatMap(a => (-1 to 1).map(b => (x + b, y + a))).filter {
        case (x, y) =>
          x >= 0 && y >= 0 && y < matrix.length && x < matrix(y).length
      }
    }
  }
}
