import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PuzzleSaver {

    public void save(String fileName, String method, Puzzle puzzle) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            switch (method) {
                case "bfs":
                    //TODO to nw czy gitara
                    bw.write(puzzle.getPath().length() + "\n");
                    bw.write(puzzle.getPath());
                    break;
                case "dfs":
                    if (puzzle == null) {
                        bw.write(-1);
                    } else {
                        bw.write(puzzle.getPath().length() + "\n");
                        bw.write(puzzle.getPath());
                    }
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
