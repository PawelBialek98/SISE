import java.util.Arrays;

public class Puzzle {
    public enum DIRECTION {UP, DOWN, LEFT, RIGHT}
    private final int[][] puzzle;
    private int[][] correctPuzzle;
    private String path = "";
    private int zeroX, zeroY;
    private int score;
    public Puzzle(int[][] puzzle, int[][] correctPuzzle) {
        this.puzzle = puzzle;
        this.correctPuzzle = correctPuzzle;
        findZeroTile();
    }
    public Puzzle(Puzzle newPuzzle) {
        puzzle = new int[newPuzzle.puzzle.length][newPuzzle.puzzle[0].length];
        for (int i = 0; i < puzzle.length; i++) {
            puzzle[i] = Arrays.copyOf(newPuzzle.puzzle[i], puzzle[i].length);
        }
        correctPuzzle = newPuzzle.correctPuzzle;
        zeroX = newPuzzle.zeroX;
        zeroY = newPuzzle.zeroY;
        path = newPuzzle.path;
    }
    public boolean isSolved() {
        for (int y = 0; y < puzzle.length; ++y) {
            for (int x = 0; x < puzzle[y].length; ++x) {
                if (puzzle[y][x] != correctPuzzle[y][x]) {
                    return false;
                }
            }
        }
        return true;
    }
    public boolean canMove(DIRECTION direction) {
        switch (direction) {
            case UP:
                if (zeroY != 0) {
                    return true;
                }
                break;
            case DOWN:
                if (zeroY != puzzle.length - 1) {
                    return true;
                }
                break;
            case LEFT:
                if (zeroX != 0) {
                    return true;
                }
                break;
            case RIGHT:
                if (zeroX != puzzle[zeroY].length - 1) {
                    return true;
                }
                break;
        }
        return false;
    }
    public void move(DIRECTION direction) {
        switch (direction) {
            case UP:
                swap(zeroY, zeroX, (zeroY - 1), zeroX);
                path += "U";
                break;
            case DOWN:
                swap(zeroY, zeroX, (zeroY + 1), zeroX);
                path += "D";
                break;
            case LEFT:
                swap(zeroY, zeroX, zeroY, (zeroX - 1));
                path += "L";
                break;
            case RIGHT:
                swap(zeroY, zeroX, zeroY, (zeroX + 1));
                path += "R";
                break;
        }
    }
    private void swap(int y1, int x1, int y2, int x2) {
        int previous = getTile(y1, x1);
        setTile(y1, x1, getTile(y2, x2));
        setTile(y2, x2, previous);
        zeroY = y2;
        zeroX = x2;
    }
    private void setTile(int y, int x, int tile) {
        puzzle[y][x] = tile;
    }
    private int getTile(int y, int x) {
        return puzzle[y][x];
    }
    private void findZeroTile() {
        for (int y = 0; y < puzzle.length; ++y) {
            for (int x = 0; x < puzzle[y].length; ++x) {
                if (puzzle[y][x] == 0) {
                    zeroY = y;
                    zeroX = x;
                }
            }
        }
    }
    public String getPath() {
        return path;
    }
    public int[][] getPuzzle() {
        return puzzle;
    }
    public int[][] getCorrectPuzzle() {
        return correctPuzzle;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int y = 0; y < puzzle.length; ++y) {
            for (int x = 0; x < puzzle[y].length; ++x) {
                output.append(puzzle[y][x]).append(" ");
            }
            output.append(System.lineSeparator());
        }
        return output.toString();
    }
}