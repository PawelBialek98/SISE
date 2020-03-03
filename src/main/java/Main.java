public class Main {
    public static void main(String[] args) {
        /*if (args.length == 0) {
            System.out.println("Brak argumentów wywołania");
            System.exit(1);
        }
        Solver solver;
        switch (args[0]){
            case "bfs": solver = new SolverBFS();
                break;
            case "dfs": solver = new SolverDFS();
                break;
            case "astr": //TODO dodac przypisanie
                break;
            default:
                System.out.println("Niepoprawny akronim algorytmu");

        }*/
        PuzzleLoader puzzleLoader = new PuzzleLoader();
        Puzzle puzzle;
        int[][] correctPuzzle;
        int[][] puzzleToSolve;
        puzzleToSolve = puzzleLoader.load("puzzleToSolve.txt");
        correctPuzzle = generateCorrectPuzzle(puzzleToSolve.length, puzzleToSolve[0].length);
        puzzle = new Puzzle(puzzleToSolve, correctPuzzle);
        System.out.println("------- BEFORE -------");
        System.out.println(puzzle);
        long timeStart;
        long timeStop;
        SolverBFS solverBFS = new SolverBFS();
        SolverDFS solverDFS = new SolverDFS();
        StrategyReader sr = new StrategyReader();
        Puzzle.DIRECTION[] strategy = sr.read("DRUL");
        //Puzzle.DIRECTION[] strategy = {Puzzle.DIRECTION.RIGHT, Puzzle.DIRECTION.DOWN, Puzzle.DIRECTION.UP, Puzzle.DIRECTION.LEFT};
        timeStart = System.nanoTime();
        //Puzzle solvedPuzzle = solverBFS.solve(puzzle, strategy);
        Puzzle solvedPuzzle = solverDFS.solve(puzzle,strategy);
        timeStop = System.nanoTime();
        System.out.println("------- AFTER -------");
        System.out.println(solvedPuzzle);
        System.out.println("Path: " + solvedPuzzle.getPath());
        System.out.println("Path length: " + solvedPuzzle.getPath().length());
        System.out.println("Solved in: " + ((timeStop - timeStart) / 1000) / 1000.0 + "ms");
        PuzzleSaver ps = new PuzzleSaver();
        ps.save("siusiak.txt", "dfs", solvedPuzzle);
    }
    private static int[][] generateCorrectPuzzle(int xSize, int ySize) {
        int[][] correctPuzzle = new int[ySize][xSize];
        int counter = 1;
        for (int y = 0; y < ySize; ++y) {
            for (int x = 0; x < xSize; ++x) {
                correctPuzzle[y][x] = counter;
                ++counter;
            }
        }
        correctPuzzle[ySize - 1][xSize - 1] = 0;
        return correctPuzzle;
    }
}