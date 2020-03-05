public interface Solver {
    Puzzle solve(Puzzle puzzleToSolve, Puzzle.DIRECTION[] strategy);
    int getProcessed();
    int getVisited();
    int getDepth();
}
