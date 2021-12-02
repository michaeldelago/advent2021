defmodule AdventEx.DayTwo do
  @spec tasks(Enum.t()) :: true
  def tasks(day_input) do
    IO.puts("Day Two, part 1: #{part_one(day_input)}")
    IO.puts("Day Two, part 2: #{part_two(day_input)}")
    true
  end

  @spec part_one(Enum.t()) :: non_neg_integer
  def part_one(day_input) do
    day_input
    |> Stream.map(fn x -> x |> String.split() |> List.to_tuple() end)
    |> Stream.map(fn {op, n} -> {op, n |> Integer.parse() |> elem(0)} end)
    |> Enum.map(fn instruction ->
      case instruction do
        {"forward", n} ->
          {0, n}

        {"up", n} ->
          {-n, 0}

        {"down", n} ->
          {n, 0}
      end
    end)
    |> Enum.reduce(fn {x, y}, {a, b} -> {x + a, y + b} end)
    |> then(fn {x, y} -> x * y end)
  end

  @spec part_two(Enum.t()) :: non_neg_integer
  def part_two(day_input) do
    formatted_input =
      day_input
      |> Stream.map(fn x -> x |> String.split() |> List.to_tuple() end)
      |> Stream.map(fn {op, n} -> {op, n |> Integer.parse() |> elem(0)} end)

    formatted_input
    |> Enum.map(fn instruction ->
      case instruction do
        {"forward", n} ->
          {n, n, 0}

        {"up", n} ->
          {0, 0, -n}

        {"down", n} ->
          {0, 0, n}
      end
    end)
    |> Enum.reduce({0, 0, 0}, fn {dep, hor, aim}, {acc_d, acc_h, acc_a} ->
      vector = acc_a * dep
      {acc_d + vector, acc_h + hor, acc_a + aim}
    end)
    |> then(fn {x, y, _} -> x * y end)
  end
end
