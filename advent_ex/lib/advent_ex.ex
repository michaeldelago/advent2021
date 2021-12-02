defmodule AdventEx do
  def main(args) do
    day =
      case args do
        [head | _tail] ->
          head |> Integer.parse() |> elem(0)

        _ ->
          {{_year, _month, day}, _} = :calendar.local_time()
          day
      end

    day_input = File.read!("../inputs/#{day}") |> String.split("\n")

    stripped_input =
      cond do
        List.last(day_input) == "" -> List.delete_at(day_input, length(day_input) - 1)
        true -> day_input
      end

    case day do
      1 -> AdventEx.DayOne.tasks(stripped_input)
      2 -> AdventEx.DayTwo.tasks(stripped_input)
    end
  end
end
