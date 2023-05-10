public class Node {
    private Etat state;
    private Node parent;
    private Action action;

    public Node(Etat state, Node parent, Action action) {
        this.state = state;
        this.parent = parent;
        this.action = action;

    }

    public Etat getState() {
        return state;
    }

    public Action getAction() {
        return action;
    }

    public Node getParent() {
        return parent;
    }

    @Override
    public String toString() {
        return "Node [action=" + action + "]";
    }

}