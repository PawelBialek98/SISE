import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PuzzleLoader {
    public int[][] load(String fileName) {
        int[][] puzzle;
        BufferedReader br;
        String line;
        String cvsSplitBy = " ";
        try {
            br = new BufferedReader(new FileReader(fileName));
            line = br.readLine();
            String[] size = line.split(cvsSplitBy);
            puzzle = new int[Integer.parseInt(size[0])][Integer.parseInt(size[1])];
            int puzzleLine = 0;
            while ((line = br.readLine()) != null) {
                String[] tiles = line.split(cvsSplitBy);
                for (int i = 0; i < puzzle[0].length; ++i) {
                    puzzle[puzzleLine][i] = Integer.parseInt(tiles[i]);
                }
                ++puzzleLine;
            }
            br.close();
            return puzzle;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //chcialem to zrobic tutaj ale no niestety pass by value czyli sie nie zmienia
    /*public void readArgs(Solver solver, Puzzle.DIRECTION[] strategy, String[] args){
        switch (args[0]){
            case "bfs":
                solver = new SolverBFS();
                strategy = StrategyReader.read(args[1]);
                break;
            case "dfs":
                solver = new SolverDFS();
                strategy = StrategyReader.read(args[1]);
                break;
            case "astr": //TODO dodac przypisanie
                break;
            default:
                System.out.println("Niepoprawny akronim algorytmu");
        }
    }*/
}