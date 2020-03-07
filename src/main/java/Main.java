public class Main {
    public static void main(String[] args) {
        if (args.length != 5) {
            System.out.println("Nieodpowiednia ilosc argumentow wywolania");
            System.exit(1);
        }
        PuzzleLoader puzzleLoader = new PuzzleLoader();
        Solver solver = new SolverBFS();
        Puzzle puzzle;
        Puzzle.DIRECTION[] strategy = new Puzzle.DIRECTION[4];
        switch (args[0]){
            case "bfs":
                solver = new SolverBFS();
                strategy = StrategyReader.read(args[1]);
                break;
            case "dfs":
                solver = new SolverDFS();
                strategy = StrategyReader.read(args[1]);
                break;
            case "astr":
                solver = new SolverAStar();
                ((SolverAStar) solver).setStrategy(args[1]);
                strategy = StrategyReader.read("RDUL");
                break;
            default:
                System.out.println("Niepoprawny akronim algorytmu");
        }
        int[][] correctPuzzle;
        int[][] puzzleToSolve;
        puzzleToSolve = puzzleLoader.load(args[2]);
        correctPuzzle = generateCorrectPuzzle(puzzleToSolve.length, puzzleToSolve[0].length);
        puzzle = new Puzzle(puzzleToSolve, correctPuzzle);
        /*System.out.println("------- BEFORE -------");
        System.out.println(puzzle);*/
        long timeStart = System.nanoTime();
        Puzzle solvedPuzzle = solver.solve(puzzle, strategy);
        long timeStop = System.nanoTime();
        /*System.out.println("------- AFTER -------");
        System.out.println(solvedPuzzle);
        System.out.println("Path: " + solvedPuzzle.getPath());
        System.out.println("Path length: " + solvedPuzzle.getPath().length());
        System.out.println("Solved in: " + ((timeStop - timeStart) / 1000) / 1000.0 + "ms");*/
        PuzzleSaver ps = new PuzzleSaver(solvedPuzzle);
        ps.save(args[3]);
        ps.saveStats(args[4], timeStop, timeStart, solver);
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