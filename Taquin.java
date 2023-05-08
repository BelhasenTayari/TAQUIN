import java.util.*;

public class Taquin {
    private final int[][] goalState = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };

    public List<Etat> search(Etat initialState) {
        Queue<Node> frontier = new LinkedList<>();
        Set<Etat> explored = new HashSet<>();

        frontier.offer(new Node(initialState, null, null));

        while (!frontier.isEmpty()) {
            Node node = frontier.poll();
            Etat state = node.getState();

            if (Arrays.deepEquals(state.getBoard(), goalState)) {
                return getPath(node);
            }

            for (Etat successor : state.getSuccessors()) {
                if (!explored.contains(successor)) {
                    frontier.offer(new Node(successor, node, null));
                }
            }

            explored.add(state);
        }

        return null;
    }

    private List<Etat> getPath(Node node) {
        List<Etat> path = new ArrayList<>();
        Node current = node;

        while (current != null) {
            path.add(0, current.getState());
            current = current.getParent();
        }

        return path;
    }

}
