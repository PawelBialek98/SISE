public class StrategyReader {

    public static Puzzle.DIRECTION[] read (String strategy){
        Puzzle.DIRECTION[] finalDir = new Puzzle.DIRECTION[strategy.length()];
        for(int i = 0; i < strategy.length(); i++){
            switch (strategy.charAt(i)){
                case 'L':
                    finalDir[i] = Puzzle.DIRECTION.LEFT;
                    break;
                case 'R':
                    finalDir[i] = Puzzle.DIRECTION.RIGHT;
                    break;
                case 'U':
                    finalDir[i] = Puzzle.DIRECTION.UP;
                    break;
                case 'D':
                    finalDir[i] = Puzzle.DIRECTION.DOWN;
                    break;
            }
        }
        return finalDir;
    }
}
