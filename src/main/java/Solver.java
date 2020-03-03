public interface Solver {
    Puzzle solve(Puzzle puzzleToSolve, Puzzle.DIRECTION[] strategy);
}
