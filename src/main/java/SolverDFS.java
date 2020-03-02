import java.util.LinkedList;
import java.util.Queue;

public class SolverDFS {


    public Puzzle solve(Puzzle puzzle, Puzzle.DIRECTION[] strategy, int depth) {
        //Puzzle puzzle = puzzleToSolve;
        System.out.println("Depth: " + depth);
        //System.out.println(puzzle.toString());
        if (puzzle.isSolved()) {
            return puzzle;
        }
        if (depth >= 15) {
            System.out.println("No nie siadło panie");
            return null;
        }
        for (int i = 0; i < strategy.length; i++) {
            if (puzzle.canMove(strategy[i])) {
                Puzzle newPuzzle = new Puzzle(puzzle);
                newPuzzle.move(strategy[i]);
                System.out.println(strategy[i]);
                newPuzzle = solve(newPuzzle, strategy, ++depth);
                // tutaj jest problem jak dochodzi do 'dna' to zwraca null (linia 16) i błednie  go obsługuje
                if(newPuzzle.isSolved()){
                    return newPuzzle;
                }
            }
        }
        return null;
    }
}
