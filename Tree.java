import java.io.*;

public class Tree {
    private TreeNode root;
    private int total;
    public Tree(){
        root = null;
        total = 0;
    }
    
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public TreeNode getRoot() {
        return root;
    }

    /*
     * Adds a node to the binary search tree.
     * Returns true if the node was successfully added, false if the node with the same ID already exists.
     */
    public boolean addToTree(String name, int ID, int cost) {
        TreeNode newNode = new TreeNode(name, ID, cost);
        if (root == null) {
            // If the root is null, set the new node as the root of the tree.
            root = newNode;
        } else {
            TreeNode current = root;
            TreeNode previous = null;
            boolean found = false;
            while (current != null && !found) {
                // Traverse the tree to find the correct position to insert the new node.
                previous = current;
                if (newNode.getID() == current.getID()) {
                    found = true;
                } else if (newNode.getID() < current.getID()) {
                    current = current.getLeft();
                } else {
                    current = current.getRight();
                }
            }
            if (!found) {
                // If the node with the same ID does not exist, insert the new node into the tree.
                if (newNode.getID() < previous.getID()) {
                    previous.setLeft(newNode);
                } else {
                    previous.setRight(newNode);
                }
            } else {
                return false;
            }
        }
        return true;
    }

    /*
     * This method is same as above method but this method also include balancing 
     * and previous child
     */
    public boolean addToTree2(String name, int ID, int cost) {
        TreeNode newNode = new TreeNode(name, ID, cost);
        if (root == null) {
            // If the root is null, set the new node as the root of the tree.
            root = newNode;
        } else {
            TreeNode current = root;
            TreeNode previous = null;
            boolean found = false;
            while (current != null && !found) {
                // Traverse the tree to find the correct position to insert the new node.
                current.setPrevious(previous);
                previous = current;
                if (newNode.getID() == current.getID()) {
                    found = true;
                } else if (newNode.getID() < current.getID()) {
                    current = current.getLeft();
                } else {
                    current = current.getRight();
                }
            }
            if (!found) {
                // If the node with the same ID does not exist, insert the new node into the tree.
                if (newNode.getID() < previous.getID()) {
                    previous.setLeft(newNode);
                    newNode.setPrevious(previous);
                } else {
                    previous.setRight(newNode);
                    newNode.setPrevious(previous);
                }
            } else {
                return false;
            }
        }

        // Perform balancing on the new node and its ancestors.
        balance(newNode);
        return true;
    }

    public void balance(TreeNode newNode) {
        TreeNode previous;
        // If the node has a parent, continue with the loop
        if (newNode.getPrevious() != null) {
            // Keep looping up the tree until the root node is reached
            do {
                int leftH = 0;
                int rightH = 0;
                // Check the height of the left subtree
                if (newNode.getLeft() != null) {
                    leftH = getHeight(newNode.getLeft());
                }
                // Check the height of the right subtree
                if (newNode.getRight() != null) {
                    rightH = getHeight(newNode.getRight());
                }
                // Calculate the difference in height between the left and right subtrees
                int difference = leftH - rightH;
                // Check if the tree is unbalanced
                if (difference > 1 || difference < -1) {
                    String st = "";
                    // Determine the type of rotation needed
                    if (leftH > rightH) {
                        st = Rotation(newNode, 'l');
                    } else {
                        st = Rotation(newNode, 'r');
                    }
                    // Perform the necessary rotation based on the type
                    if (st.equals("left")) {
                        previous = newNode.getPrevious();
                        newNode.setPrevious(newNode.getLeft());
                        newNode.getLeft().setRight(newNode);
                        if(previous != null){
                            previous.setLeft(newNode.getLeft());
                            previous.getRight().setPrevious(previous);
                        }else{
                            // If the node has no parent, it is the root
                            root = newNode.getLeft();
                        }
                        newNode.setLeft(null);
                        newNode = previous;
                    } else if (st.equals("right")) {
                        previous = newNode.getPrevious();
                        newNode.setPrevious(newNode.getRight());
                        newNode.getRight().setLeft(newNode);
                        if(previous != null) {
                            previous.setRight(newNode.getRight());
                            previous.getRight().setPrevious(previous);
                        }else {
                            root = newNode.getRight();
                        }
                        newNode.setRight(null);
                        newNode = previous;
                    } else if (st.equals("leftRight")) {
                        newNode.setLeft(newNode.getLeft().getRight());
                        newNode.getLeft().setLeft(newNode.getLeft().getPrevious());
                        newNode.getLeft().getLeft().setRight(null);
                        newNode.getLeft().setRight(newNode);
                        if(newNode.getPrevious()!= null){
                            if(newNode.getPrevious().getLeft() == newNode){
                                newNode.getPrevious().setLeft(newNode.getLeft());
                                newNode.getPrevious().getLeft().getRight().setLeft(null);
                            }else if(newNode.getPrevious().getRight() == newNode){
                                newNode.getPrevious().setRight(newNode.getLeft());
                                newNode.getPrevious().getRight().getRight().setLeft(null);
                            }else {
                                System.out.println("Something went wrong :-(");
                            }
                        }else {
                            root = newNode.getLeft();
                            root.getRight().setLeft(null);
                        }

                    } else if (st.equals("rightLeft")) {
                        newNode.setRight(newNode.getRight().getLeft());
                        newNode.getRight().setRight(newNode.getRight().getPrevious());
                        newNode.getRight().getRight().setLeft(null);
                        newNode.getRight().setLeft(newNode);
                        if(newNode.getPrevious()!= null){
                            if(newNode.getPrevious().getRight() == newNode){
                                newNode.getPrevious().setRight(newNode.getRight());
                                newNode.getPrevious().getRight().getLeft().setRight(null);
                            }else if(newNode.getPrevious().getLeft() == newNode){
                                newNode.getPrevious().setLeft(newNode.getRight());
                                newNode.getPrevious().getLeft().getLeft().setRight(null);
                            }else {
                                System.out.println("Something went wrong :-(");
                            }
                        }else{
                            root = newNode.getRight();
                            root.getLeft().setRight(null);
                        }
                    } else {
                        System.out.println("Something went wrong");
                    }
                }
                if(newNode != null) {
                    newNode = newNode.getPrevious();
                }
            } while (newNode != null);
        }
    }

    public String Rotation(TreeNode Node, char position){
        if(position == 'l'){
            if(Node.getLeft().getRight() != null){
                return "leftRight";
            }else if(Node.getLeft().getLeft() != null){
                return "left";
            }else{
                System.out.println("error");
                return null;
            }
        } else{
            if (Node.getRight().getLeft() != null) {
                return "rightLeft";
            }else if(Node.getRight().getRight() != null) {
                return "right";
            }else{
                System.out.println("errorrrrrrrrrrrr");
                return null;
            }
        }
    }
    public int getHeight(TreeNode node){
        if(node ==null){
            return 0;
        }
        int leftH = getHeight(node.getLeft());
        int rightH = getHeight(node.getRight());
        if(rightH < leftH) {
            return 1+leftH;
        }else{
            return 1+rightH;
        }
    }

    public void traverseTree(TreeNode current){
        if(current != null){
            // Traverse the left subtree
            traverseTree(current.getLeft());

            // Print the item information
            System.out.println("Item ID => " + current.getID() + " , Item Name => " + current.getName() + " , Item Cost => £" + current.getCost());

            // Add the cost of the current node to the total cost
            total = current.getCost() + total;

            // Traverse the right subtree
            traverseTree(current.getRight());
        }
    }

    // This method searches the binary search tree for a node with a given item ID, and returns true if found, false otherwise
    public boolean findinTree(int ID){
        TreeNode newNode = root;
        boolean found = false;
        while(newNode != null && !found){
            if(newNode.getID() == ID  ){
                // Print the item name and cost of the found node
                System.out.println("Item Name " + newNode.getName());
                System.out.println("Item Cost " +newNode.getCost());
                found = true;
            } else if(newNode.getID() > ID){
                // Search the left subtree if the current node's ID is greater than the target ID
                newNode = newNode.getLeft();
            }else {
                // Search the right subtree if the current node's ID is less than the target ID
                newNode = newNode.getRight();
            }
        }
        return found;
    }

    // This method deletes a node with the given item ID from the binary search tree
    public void deleteFromTree(int ID){
         TreeNode current = root;
         TreeNode previous = null;
         TreeNode toDelete = null;
         boolean found = false;
         while(current != null && !found){
             if(current.getID() == ID){
                 toDelete = current;
                 found = true;
             }
             else if(current.getID() > ID){
                 previous = current;
                 current = current.getLeft();
             }else {
                 previous = current;
                 current = current.getRight();
             }
         }
         if(found){
             if(toDelete.getLeft() == null && toDelete.getRight() == null){
                 if(previous == null ){
                     root = null;
                 }else if(previous.getRight() == toDelete){
                    previous.setRight(null);
                }else{
                    previous.setLeft(null);
                }
             } else if(toDelete.getLeft() != null && toDelete.getRight() != null){
                 current = toDelete.getLeft();
                 previous = null;
                 if(current.getRight() != null) {
                     while (current != null) {
                         if (current.getRight() == null) {
                             toDelete.setCost(current.getCost());
                             toDelete.setID(current.getID());
                             toDelete.setName(current.getName());
                             previous.setRight(null);
                         }
                         previous = current;
                         current = current.getRight();
                     }
                 }else {
                     toDelete.setCost(current.getCost());
                     toDelete.setID(current.getID());
                     toDelete.setName(current.getName());
                     toDelete.setLeft(current.getLeft());
                 }
             }else if (toDelete.getRight() != null || toDelete.getLeft() != null) {
                 if(previous == null){
                     if(root.getRight() != null){
                         root = root.getRight();
                     }else {
                         root = root.getLeft();
                     }
                 }else if(previous.getRight() == toDelete){
                    if(toDelete.getRight() != null){
                        previous.setRight(toDelete.getRight());
                    }else{
                        previous.setRight(toDelete.getLeft());
                    }
                }else{
                    if(toDelete.getRight() != null){
                        previous.setLeft(toDelete.getRight());
                    }else{
                        previous.setLeft(toDelete.getLeft());
                    }
                }
             }
             System.out.println("Item has been deleted");
         }else{
             System.out.println("NO Result Found");
        }
    }

    // Method to save the contents of the binary tree to a file
    public void save() {
        FileOutputStream outputStream = null;
        PrintWriter printWriter = null;
        TreeNode file = root;
        if(file != null) {
            try {
                outputStream = new FileOutputStream("Write.txt");
                printWriter = new PrintWriter(outputStream);
                preOrderTraversalToFile(file, printWriter);
                System.out.println("Tree has been successfully saved");
            } catch (IOException e) {
                // Printing if there is an error
                System.out.println("Sorry, there has been a problem opening or writing to the file");
                System.out.println("/t" + e);
            } finally {
                if (printWriter != null) {
                    printWriter.close();
                }
            }
        }else{
            System.out.println("There is nothing to save");
        }
    }

    // Recursive method to traverse the binary tree in pre-order and write its contents to a file
    public void preOrderTraversalToFile(TreeNode current, PrintWriter printWriter) {
        String item = "";
        if (current != null) {
            // Write the current node's ID, name, and cost to the file
            printWriter.println(current.getID() + " " + current.getName() + " " + current.getCost());

            // Recursively call the pre-order traversal function on the left child of the current node
            preOrderTraversalToFile(current.getLeft(), printWriter);

            // Recursively call the pre-order traversal function on the right child of the current node
            preOrderTraversalToFile(current.getRight(), printWriter);
        }
    }

    // Method to read the contents of the file and recreate the binary tree
    public boolean read() {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        boolean valid = true;
        try {
            fileReader = new FileReader("Write.txt");
            bufferedReader = new BufferedReader(fileReader);

            // Read the first line of the file
            String nextLine = bufferedReader.readLine();
            if(nextLine != null) {

                // Iterate over each line of the file
                while (nextLine != null) {
                    // Split the line into its three components: ID, name, and cost
                    String[] list = nextLine.split(" ");
                    int ID = Integer.parseInt(list[0]);
                    String name = list[1];
                    int cost = Integer.parseInt(list[2]);

                    // Add a new node to the binary tree using the extracted data
                    addToTree(name, ID, cost);

                    // Read the next line of the file
                    nextLine = bufferedReader.readLine();
                }
            }else{
                System.out.println("File is empty");
                valid = false;
            }

        } catch (FileNotFoundException e) {
            System.out.println("File was not found");
            valid = false;
        } catch (IOException e) {
            System.out.println("IO Error reading from file: " + e);
            valid = false;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    // Ignore any exceptions thrown while closing the reader
                }
            }
        }
        return valid;
    }

    // Pre-order traversal of a binary tree, starting at the given current node
    public void preOrderTraversal(TreeNode current) {
        // If the current node is not null, visit the node and its children
        if (current != null) {
            // Output the current node's ID, name, and cost to the console
            System.out.println("Item ID => " + current.getID() + " , Item Name => " + current.getName() + " , Item Cost => £" + current.getCost());

            // Recursively call the pre-order traversal function on the left child of the current node
            preOrderTraversal(current.getLeft());

            // Recursively call the pre-order traversal function on the right child of the current node
            preOrderTraversal(current.getRight());
        }
    }

    // Post-order traversal of a binary tree, starting at the given current node
    public void postOrderTraversal(TreeNode current) {
        // If the current node is not null, visit the node's children and then the node itself
        if (current != null) {
            // Recursively call the post-order traversal function on the left child of the current node
            postOrderTraversal(current.getLeft());

            // Recursively call the post-order traversal function on the right child of the current node
            postOrderTraversal(current.getRight());

            // Output the current node's ID, name, and cost to the console
            System.out.println("Item ID => " + current.getID() + " , Item Name => " + current.getName() + " , Item Cost => £" + current.getCost());
        }
    }

}
