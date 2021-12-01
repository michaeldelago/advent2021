defmodule AdventExTest do
  use ExUnit.Case
  doctest AdventEx

  test "greets the world" do
    assert AdventEx.hello() == :world
  end
end
