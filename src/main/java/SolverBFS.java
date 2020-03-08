import java.util.LinkedList;
import java.util.Queue;

public class SolverBFS implements Solver{
    private final Queue<Puzzle> frontiers = new LinkedList<>();
    private int visited = 0;
    private int processed = 0;
    private int depth = 0;

    @Override
    public int getDepth() {
        return depth;
    }

    public int getVisited() {
        return visited;
    }

    public int getProcessed() {
        return processed;
    }

    public Puzzle solve(Puzzle puzzleToSolve, Puzzle.DIRECTION[] strategy) {
        frontiers.add(puzzleToSolve);
        while (!frontiers.isEmpty()) {
            Puzzle puzzle = frontiers.poll();
            processed++;
            depth = Math.max(depth, puzzle.getPath().length());
            if (puzzle.isSolved()) {
                return puzzle;
            }
            for (int i = 0; i < strategy.length; i++) {
                if (puzzle.canMove(strategy[i])) {
                    visited++;
                    Puzzle newPuzzle = new Puzzle(puzzle);
                    newPuzzle.move(strategy[i]);
                    frontiers.add(newPuzzle);
                }
            }
        }
        return null;
    }
}