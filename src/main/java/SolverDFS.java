public class SolverDFS implements Solver {
    int depth = 0;

    public Puzzle solve(Puzzle puzzle, Puzzle.DIRECTION[] strategy) {
        if (puzzle.isSolved()) {
            return puzzle;
        }
        if (depth >= 16) {
            return null;
        }
        for (int i = 0; i < strategy.length; i++) {
            if (puzzle.canMove(strategy[i])) {
                Puzzle newPuzzle = new Puzzle(puzzle);
                newPuzzle.move(strategy[i]);
                //depth to jest to samo co Path length - długość wykonania programu zwiększa sięwykładniczo zależnie od depth
                depth++;
                newPuzzle = solve(newPuzzle, strategy);
                if(newPuzzle != null && newPuzzle.isSolved()){
                    System.out.println(depth);
                    return newPuzzle;

                }
                depth--;
            }
        }
        return null;
    }
}
