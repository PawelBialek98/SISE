import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PuzzleSaver {

    private Puzzle puzzle;
    private int len;

    public PuzzleSaver(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    public void save(String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            if (puzzle == null) {
                len = -1;
                bw.write(len);
            } else {
                len = puzzle.getPath().length();
                bw.write(len + "\n");
                bw.write(puzzle.getPath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveStats(String fileName, long timeStop, long timeStart, Solver solver) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            bw.write(/*"Dlugosc znalezionego rozwiazania : " +*/ len + "\n");
            bw.write(/*"Liczba stanow odwiedzonych : " +*/ solver.getVisited() + "\n");
            bw.write(/*"Liczba stanow przetworzonych : " +*/ solver.getProcessed() + "\n");
            bw.write(/*"Maksymalna osiagnieta glebokosc rekursji : "*/ + solver.getDepth() + "\n");
            bw.write(/*"Czas rozwiazania : "*/ + ((timeStop - timeStart) / 1000) / 1000.0 + " ms");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
