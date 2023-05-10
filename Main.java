import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[][] initialBoard = { { 2, 4, 3 }, { 1, 0, 7 }, { 8, 5, 6 } };
        Taquin taquin = new Taquin();
        List<Etat> path = taquin.rechercheParLargeur(new Etat(initialBoard));
        List<Action> actions = taquin.getActions();
        actions.add(0, null);
        if (path == null) {
            System.out.println("No solution found.");
        } else {
            System.out.println("Solution found!");
            for (int i = 0; i < path.size(); i++) {
                System.out.println("Step " + i + " Action = " + actions.get(i) + ":");
                int[][] board = path.get(i).getBoard();
                for (int[] row : board) {
                    System.out.println(Arrays.toString(row));
                }
            }
        }
    }

}
