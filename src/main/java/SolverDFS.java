import java.util.LinkedList;
import java.util.Queue;

public class SolverDFS {

    //TODO poprawić metodę
    public Puzzle solve(Puzzle puzzle, Puzzle.DIRECTION[] strategy, int depth) {
        if (puzzle.isSolved()) {
            return puzzle;
        }
        if (depth >= 23) {
            return null;
        }
        for (int i = 0; i < strategy.length; i++) {
            if (puzzle.canMove(strategy[i])) {
                Puzzle newPuzzle = new Puzzle(puzzle);
                newPuzzle.move(strategy[i]);
                newPuzzle = solve(newPuzzle, strategy, ++depth);
                // tutaj jest problem jak dochodzi do 'dna' to zwraca null (linia 16) i błednie  go obsługuje
                if(newPuzzle != null && newPuzzle.isSolved()){
                    return newPuzzle;
                }
            }
        }
        return null;
    }
}
