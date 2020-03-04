import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PuzzleSaver {

    Puzzle puzzle;
    int len;

    public PuzzleSaver(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    public void save(String fileName, String method) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            switch (method) {
                case "bfs":
                case "dfs":
                    if (puzzle == null) {
                        len = -1;
                        bw.write(len);
                    } else {
                        len = puzzle.getPath().length();
                        bw.write(len + "\n");
                        bw.write(puzzle.getPath());
                    }
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveStats(String fileName, long timeStop, long timeStart) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            bw.write("Dlugosc znalezionego rozwiazania : " + len + "\n");
            bw.write("Czas rozwiazania : " + ((timeStop - timeStart) / 1000) / 1000.0 + " ms");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
