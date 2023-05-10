import java.util.*;

public class Taquin {
    private final int[][] goalState = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
    private List<Action> actions;

    public Taquin() {
        actions = new ArrayList<Action>();
    }

    public List<Etat> rechercheParProfondeur(Etat initialState) {
        Stack<Node> frontier = new Stack<>();
        Set<Etat> explored = new HashSet<>();

        frontier.push(new Node(initialState, null, null));

        while (!frontier.isEmpty()) {
            Node node = frontier.pop();
            Etat state = node.getState();

            if (Arrays.deepEquals(state.getBoard(), goalState)) {
                return getPath(node);
            }

            for (Etat successor : state.getSuccessors()) {
                if (!explored.contains(successor)) {
                    Action nextAction = getAction(state, successor);
                    frontier.push(new Node(successor, node, nextAction));
                }
            }
            explored.add(state);
        }

        return null;
    }

    public List<Etat> rechercheParLargeur(Etat initialState) {
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
                    Action nextAction = getAction(state, successor);
                    frontier.offer(new Node(successor, node, nextAction));
                }
            }
            explored.add(state);
        }

        return null;
    }

    private Action getAction(Etat prevState, Etat nextState) {

        int prevBlankRow = prevState.getBlankRow();
        // System.out.println(prevBlankRow);
        int prevBlankCol = prevState.getBlankCol();
        // System.out.println(prevBlankCol);
        int nextBlankRow = nextState.getBlankRow();
        // System.out.println(nextBlankRow);
        int nextBlankCol = nextState.getBlankCol();
        // System.out.println(nextBlankCol);
        if (nextBlankRow < prevBlankRow) {
            return new Bas();
        } else if (nextBlankRow > prevBlankRow) {
            return new Haut();
        } else if (nextBlankCol < prevBlankCol) {
            return new Droit();
        } else if (nextBlankCol > prevBlankCol) {
            return new Gauche();
        } else {
            return null;
        }
    }

    private List<Etat> getPath(Node node) {
        List<Etat> path = new ArrayList<>();
        Node current = node;

        while (current != null) {
            Action action = current.getAction();
            if (action != null) {
                actions.add(0, action);
            }
            path.add(0, current.getState()); // Add state to beginning of path
            current = current.getParent();
        }

        return path;
    }

    public int[][] getGoalState() {
        return goalState;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

}
