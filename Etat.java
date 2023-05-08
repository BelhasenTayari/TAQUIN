import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Etat {
    private int[][] board;
    private int blankRow;
    private int blankCol;

    public Etat(int[][] board) {
        this.board = board;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    blankRow = i;
                    blankCol = j;
                }
            }
        }
    }

    public int[][] getBoard() {
        return board;
    }

    public List<Etat> getSuccessors() {
        List<Etat> successors = new ArrayList<>();

        int[][] moves = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        for (int[] move : moves) {
            int newRow = blankRow + move[0];
            int newCol = blankCol + move[1];

            if (newRow < 0 || newRow >= board.length || newCol < 0 || newCol >= board[0].length) {
                continue;
            }

            int[][] newBoard = new int[board.length][board[0].length];
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    newBoard[i][j] = board[i][j];
                }
            }
            newBoard[blankRow][blankCol] = newBoard[newRow][newCol];
            newBoard[newRow][newCol] = 0;

            successors.add(new Etat(newBoard));
        }

        return successors;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Etat) {
            Etat other = (Etat) obj;
            return Arrays.deepEquals(this.board, other.board);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(this.board);
    }
}