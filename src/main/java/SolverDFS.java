public class SolverDFS implements Solver {

    private int depth = 0;
    private int processed = 0;
    private int visited = 0;

    @Override
    public int getDepth() {
        return depth;
    }

    @Override
    public int getProcessed() {
        return processed;
    }

    @Override
    public int getVisited() {
        return visited;
    }

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
                processed++;
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
