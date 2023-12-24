import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    Tree myTree;  // A Tree object for storing TreeNode objects

    public Main() {
        myTree = new Tree();  // Initializes myTree object by creating an instance of the Tree class
    }

    public static void main(String[] args) {
        System.out.print('\u000c');
        Main main = new Main();  // Create an instance of the Main class
        main.process();  // Call the process method on the Main object
    }

    public void process() {
        char choice;
        do {
            choice = menu();  // Calls the menu method to get the user's input
            switch (choice) {
                case ('a'), ('A') -> add();  // Adds a new item to the Tree
                case ('f'), ('F') -> find();  // Finds an item in the Tree
                case ('p'), ('P') -> print();  // Prints the items in the Tree
                case ('d'), ('D') -> delete();  // Deletes an item from the Tree
                case ('s'), ('S') -> myTree.save();  // Saves the Tree to a file
                case ('r'), ('R') -> read();  // Reads the Tree from a file
                case ('n'), ('N') -> print2();  // Prints the Tree items in pre-order and post-order traversal
                case ('q'), ('Q') -> System.out.println("Goodbye");
                default -> System.out.println("Invalid Input");  // Default message if the user enters an invalid option
            }
        } while (choice != 'Q' && choice != 'q');  // Loop will keep running until the user enters 'Q' or 'q'
    }

    public char menu() {  // Displays the menu to the user and gets their input
        displayMenu();
        Scanner scanner = new Scanner(System.in);
        return scanner.next().charAt(0);
    }

    public void displayMenu() {
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        System.out.println("Welcome to shop");
        System.out.println("Press a/A : Add item");
        System.out.println("Press f/F : Find item");
        System.out.println("Press p/P : Print all items with total");
        System.out.println("Press d/D : Delete item");
        System.out.println("Press s/S : Save item");
        System.out.println("Press r/R : Read saved items");
        System.out.println("Press n/N : Print item details in different order");
        System.out.println("Press q/Q : Exit shop");
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
    }

    public void add() {
        // declare variables to hold item name, cost, and ID
        String name;
        int cost = 0;
        int ID = 0;
        boolean valid = false;

        // loop until valid input is received from user
        do {
            // prompt user for item name
            System.out.println("Enter Item Name");
            Scanner scanner = new Scanner(System.in);
            name = scanner.nextLine();

            // validate cost input
            do {
                try {
                    // prompt user for item cost
                    System.out.println("Enter Item cost");
                    Scanner scanner2 = new Scanner(System.in);
                    cost = scanner2.nextInt();

                    // check if cost is negative
                    if (cost < 0) {
                        System.out.println("Seriously ??");
                    } else {
                        // set valid to true if cost is non-negative
                        valid = true;
                    }
                } catch (InputMismatchException ime) {
                    // catch InputMismatchException if user inputs non-integer value for cost
                    System.out.println("Only Integers are allowed");
                }
            } while (!valid);
            do {
                try {
                    // prompt user for item ID
                    System.out.println("Enter Item ID");
                    Scanner scanner3 = new Scanner(System.in);
                    ID = scanner3.nextInt();

                    // check if ID is negative
                    if (ID < 0) {
                        System.out.println("Seriously ??");
                    } else {
                        // set valid to false if ID is non-negative
                        valid = false;
                    }
                } catch (InputMismatchException ime) {
                    // catch InputMismatchException if user inputs non-integer value for ID
                    System.out.println("Only Integers are allowed");
                }
            } while (valid);

            //**there is 2 method addToTree for adding and addToTree2 for adding and balancing**
            valid = myTree.addToTree(name, ID, cost);
            if(!valid){
                System.out.println("Item already in tree");
            }else{
                System.out.println("Your Item has been successfully Added");
            }
        } while (!valid);
    }

    public void find() {
        // prompt user to enter the ID of the item to be found
        System.out.println("Enter the ID number of the item you are looking for");
        Scanner scanner = new Scanner(System.in);
        // read the ID input from the user
        int Id = scanner.nextInt();
        // call the findinTree() method of the myTree object to search for the ID in the tree
        boolean found = myTree.findinTree(Id);
        if (!found) {
            // if item not found, print message
            System.out.println("NO RESULT FOUND");
        }
    }

    public void print() {
        if(myTree.getRoot()!= null) {
            // set the total cost of items to zero
            myTree.setTotal(0);
            // call the traverseTree() method of the myTree object to traverse the tree and accumulate the total cost
            myTree.traverseTree(myTree.getRoot());
            // print the total cost of items
            System.out.println("Total Item Cost => £" + myTree.getTotal());
        }else{
            System.out.println("Tree is empty !!");
        }
    }

    public void delete() {
        // prompt user to enter the ID of the item to be deleted
        Scanner scanner = new Scanner(System.in);
        int ID = scanner.nextInt();
        // call the deleteFromTree() method of the myTree object to delete the item from the tree
        myTree.deleteFromTree(ID);
    }

    public void read(){
        if(myTree.read()){
            print();
        }
    }

    public void print2() {
        if(myTree.getRoot() != null) {
            // print the items in the tree using pre-order traversal
            System.out.println("Printing Tree Item by Pre Order Traversal method");
            myTree.preOrderTraversal(myTree.getRoot());
            // print the items in the tree using post-order traversal
            System.out.println("Printing Tree Item by Post Order Traversal method");
            myTree.postOrderTraversal(myTree.getRoot());
        }else{
            System.out.println("Tree is empty :-(");
        }
    }
}