import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[][] initialBoard = { { 2, 8, 3 }, { 5, 0, 7 }, { 4, 1, 6 } };
        Taquin taquin = new Taquin();
        List<Etat> path = taquin.search(new Etat(initialBoard));

        if (path == null) {
            System.out.println("No solution found.");
        } else {
            System.out.println("Solution found!");
            for (int i = 0; i < path.size(); i++) {
                System.out.println("Step " + i + ":");
                int[][] board = path.get(i).getBoard();
                for (int[] row : board) {
                    System.out.println(Arrays.toString(row));
                }
            }
        }
    }

}
