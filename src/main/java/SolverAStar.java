import java.util.*;

public class SolverAStar implements Solver {

    private final Queue<Puzzle> frontiers = new LinkedList<>();

    private String strategy;
    private int processed = 0;
    private int visited = 0;
    private int depth = 0;

    @Override
    public int getDepth() {
        return depth;
    }

    @Override
    public int getProcessed(){
        return processed;
    }

    @Override
    public int getVisited() {
        return visited;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    @Override
    public Puzzle solve(Puzzle puzzleToSolve, Puzzle.DIRECTION[] strategy) {
        //calculate(puzzleToSolve);
        //int score = puzzleToSolve.getScore();
        Puzzle puzzle = new Puzzle(puzzleToSolve);
        while (!puzzle.isSolved()) {
            List<Puzzle> puzzles = new ArrayList<>();
            for (int i = 0; i < strategy.length; i++) {
                if (puzzle.canMove(strategy[i])) {
                    Puzzle newPuzzle = new Puzzle(puzzle);
                    newPuzzle.move(strategy[i]);
                    calculate(newPuzzle);
                    puzzles.add(newPuzzle);
                }
            }
            puzzle = Collections.min(puzzles, Comparator.comparing(Puzzle::getScore));
        }
        /*frontiers.add(puzzleToSolve);
        while (!frontiers.isEmpty()) {
            Puzzle puzzle = frontiers.poll();
            if (puzzle.isSolved()) {
                return puzzle;
            }
            for (int i = 0; i < strategy.length; i++) {
                if (puzzle.canMove(strategy[i])) {
                    Puzzle newPuzzle = new Puzzle(puzzle);
                    newPuzzle.move(strategy[i]);
                    frontiers.add(newPuzzle);
                }
            }
        }*/
        return null;
    }

    private void calculate(Puzzle puzzle){
        int score = puzzle.getPath().length();
        score += (strategy.equals("hamm")) ?  calculateHamming(puzzle) : calculateManhattan(puzzle);
        puzzle.setScore(score);
    }

    private int calculateHamming(Puzzle puzzle){
        int dist = 0;
        int[][] puzzleXD = puzzle.getPuzzle();
        for (int y = 0; y < puzzleXD.length; ++y) {
            for (int x = 0; x < puzzleXD[y].length; ++x) {
                if (puzzleXD[y][x] != puzzle.getCorrectPuzzle()[y][x]) {
                    dist++;
                }
            }
        }
        return dist;
    }

    private int calculateManhattan(Puzzle puzzle){
        int yCorrect, xCorrect, dist = 0;
        int[][] puzzleXD = puzzle.getPuzzle();
        int[] coTOzaguwno;
        for (int y = 0; y < puzzleXD.length; ++y) {
            for (int x = 0; x < puzzleXD[y].length; ++x) {
                if (puzzleXD[y][x] != puzzle.getCorrectPuzzle()[y][x]) {
                    coTOzaguwno = calculateCoordinates(puzzle,puzzleXD[y][x]);
                    dist += Math.abs(y-coTOzaguwno[0]) + Math.abs(x-coTOzaguwno[1]);
                }
            }
        }
        return dist;
    }

    private int[] calculateCoordinates(Puzzle puzzle,int number){

        int[][] puzzleXD = puzzle.getPuzzle();
        for (int y = 0; y < puzzleXD.length; ++y) {
            for (int x = 0; x < puzzleXD[y].length; ++x) {
                if (puzzleXD[y][x] == number) {
                    return new int[]{y,x};
                }
            }
        }
        return null;
    }
}
