public class TreeNode {
    // Define private member variables.
    private int ID;// Unique identifier for the node.
    private String  name;// Name of the node.
    private int cost;// The cost of reaching the node.
    private TreeNode left;// The left child of the node.
    private TreeNode right;// The right child of the node.
    private TreeNode previous;// The parent of the node.

    // Default constructor that initializes the member variables to default values.
    public TreeNode(){
        ID = 0;
        name = "";
        cost = 0;
        left = null;
        right = null;
        previous = null;
    }

    // Parameterized constructor that allows the caller to set the ID, name, and cost properties of the node.
    public TreeNode(String name , int ID , int cost){
        this.name = name;
        this.ID = ID;
        this.cost = cost;
        left = null;
        right = null;
        previous = null;
    }

    // Getter and setter methods for the cost, ID, and name properties of the node.
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and setter methods for the left, right, and previous properties of the node.
    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public TreeNode getPrevious() {
        return previous;
    }

    public void setPrevious(TreeNode previous) {
        this.previous = previous;
    }
}
