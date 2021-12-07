package advent2021

import scala.collection.mutable.ListBuffer
import scala.annotation.tailrec

object DayFour {
  def partOne(input: Seq[String]) = {
    val (numbers, boards) = unfuckInput(input)
    val numbersAsInts = numbers.map(_.toInt).toList
    var boardsAsInts = boards.map(Board(_, List.empty))
    val winningBoard = findWin(0, numbersAsInts, boardsAsInts)
    winningBoard.sumUnmatched
  }

  def partTwo(input: Seq[String]) = {
    val (numbers, boards) = unfuckInput(input)
    val numbersAsInts = numbers.map(_.toInt).toList
    var boardsAsInts = boards.map(Board(_, List.empty))
    val winningBoard = findAllWins(0, numbersAsInts, boardsAsInts, List.empty)
    winningBoard.last.sumUnmatched
  }

  def unfuckInput(input: Seq[String]) = {
    val numbers = input(0).split(",")
    val boards =
      input.slice(2, input.length).filterNot(_ == "").grouped(5).toList
    (numbers, boards)
  }

  def findWin(n: Int, numbers: Seq[Int], boards: Seq[Board]): Board = {
    val checks = boards.filter(_.checkWin)
    if (checks.length > 0) {
      checks.head
    }
    else {
      val newBoards = boards.map(_.addNumber(numbers(n)))
      findWin(n + 1, numbers, newBoards)
    }
  }

  @tailrec
  def findAllWins(n: Int, numbers: Seq[Int], boards: Seq[Board], winners: Seq[Board]): Seq[Board] = {
    val checks = boards.filter(_.checkWin)
    if (numbers(n) == numbers.last) {
      winners
    }
    else {
      val newBoards = boards.filterNot(_.checkWin).map(_.addNumber(numbers(n)))
      val newWinners = winners.appendedAll(checks)
      findAllWins(n + 1, numbers, newBoards, newWinners)
    }
  }
}

case class Board(asText: Seq[String], matches: List[(Int, Int)], lastNumber: Int = -1) {
  val boardAsInts = asText
    .map(_.split("\\s+").filterNot(_ == "").map(_.toInt).toVector)
    .toVector

  def addNumber(n: Int) = {
    val coords = boardAsInts.filter(line => line.contains(n)).map(line =>
      (line.indexOf(n), boardAsInts.indexOf(line))
    )
    if (coords.length < 1) {
      this
    }
    else {
      this.copy(asText, matches.appended(coords.head), n)
    }
  }

  def checkWin = {
    val y = matches.groupBy(_._1).transform((_, v) => v.length)
    val x = matches.groupBy(_._2).transform((_, v) => v.length)
    x.filter(_._2 == 5).size > 0 || y.filter(_._2 == 5).size > 0
  }

  def sumUnmatched = {
    val unmatchedCoords = (0 to 4).flatMap(y => (0 to 4).map(x => (x, y))).filterNot(matches.contains(_))
    val unmatchedSum = unmatchedCoords.map { case (x: Int, y: Int) => boardAsInts(y)(x) }.sum
    unmatchedSum * lastNumber
  }
}
