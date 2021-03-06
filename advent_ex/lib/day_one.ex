defmodule AdventEx.DayOne do
  @spec tasks(Enum.t) :: true
  def tasks(day_input) do
    IO.puts("Day One, part 1: #{part_one(day_input)}")
    IO.puts("Day One, part 2: #{part_two(day_input)}")
    :true
  end

  @spec part_one(Enum.t) :: non_neg_integer
  def part_one(day_input) do
    as_ints =
      day_input
      |> Enum.map(fn x -> x
      |> Integer.parse()
      |> elem(0) end)

    shifted = as_ints |> Enum.slice(Range.new(1, length(as_ints)))
    Enum.zip([as_ints, shifted]) |> Enum.filter(fn {x, y} -> x < y end) |> length()
  end

  @spec part_two(Enum.t) :: non_neg_integer
  def part_two(day_input) do
    as_ints =
      day_input
      |> Enum.map(fn x -> x |> Integer.parse() |> elem(0) end)
      |> Enum.chunk_every(3, 1)
      |> Enum.map(&Enum.sum/1)

    shifted = as_ints |> Enum.slice(Range.new(1, length(as_ints)))
    Enum.zip([as_ints, shifted]) |> Enum.filter(fn {x, y} -> x < y end) |> length()
  end
end
