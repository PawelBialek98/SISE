import java.util.*;

public class SolverAStar implements Solver {

    //private final Queue<Puzzle> frontiers = new LinkedList<>();
    private Set<Puzzle> frontiers = new HashSet<>();
    private Set<Puzzle> explored = new HashSet<>();
    private Map<Integer, Puzzle> map = new HashMap<>();

    private boolean strategy;
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
        this.strategy = strategy.equals("hamm");
    }

    @Override
    public Puzzle solve(Puzzle puzzleToSolve, Puzzle.DIRECTION[] strategy) {
        calculate(puzzleToSolve);

        while (!frontiers.isEmpty()){
            Puzzle puzzle = Collections.min(frontiers, Comparator.comparing(Puzzle::getScore));
            frontiers.remove(puzzle);

            depth = Math.max(depth, puzzle.getPath().length());
            if (puzzle.isSolved()) {
                return puzzle;
            }

            for (int i = 0; i < strategy.length; i++) {
                if (puzzle.canMove(strategy[i])) {
                    Puzzle newPuzzle = new Puzzle(puzzle);
                    newPuzzle.move(strategy[i]);
                    calculate(newPuzzle);
                    //System.out.println("score" + newPuzzle.getScore());
                }
            }
            processed++;
            //System.out.println("next" + nextPuzzle.getScore());
            //System.out.println(puzzle);
            //System.out.println(processed);
            //System.out.println("Best score" + puzzle.getScore());
            //frontiers.add(nextPuzzle);
            //if(processed == 20){
            //    return null;
            //}
        }
        return null;
    }

    private void calculate(Puzzle puzzle){
        if(puzzle != null && explored.add(puzzle)){
            int score = puzzle.getPath().length();
            score += strategy ?  calculateHamming(puzzle) : calculateManhattan(puzzle);
            puzzle.setScore(score);
            frontiers.add(puzzle);
            visited++;
        }
    }

    private int calculateHamming(Puzzle puzzle){
        int dist = 0;
        int[][] puzzleTmp = puzzle.getPuzzle();
        for (int y = 0; y < puzzleTmp.length; ++y) {
            for (int x = 0; x < puzzleTmp[y].length; ++x) {
                if (puzzleTmp[y][x] != puzzle.getCorrectPuzzle()[y][x]) {
                    dist++;
                }
            }
        }
        //System.out.println("XD");
        return dist;
    }

    private int calculateManhattan(Puzzle puzzle){
        int dist = 0;
        int[][] puzzleTmp = puzzle.getPuzzle();
        int[] coTOzaguwno;
        for (int y = 0; y < puzzleTmp.length; ++y) {
            for (int x = 0; x < puzzleTmp[y].length; ++x) {
                if (puzzleTmp[y][x] != puzzle.getCorrectPuzzle()[y][x]) {
                    coTOzaguwno = calculateCoordinates(puzzle,puzzleTmp[y][x]);
                    dist += Math.abs(y-coTOzaguwno[0]) + Math.abs(x-coTOzaguwno[1]);
                }
            }
        }
        return dist;
    }

    private int[] calculateCoordinates(Puzzle puzzle,int number){

        int[][] puzzleXD = puzzle.getCorrectPuzzle();
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
