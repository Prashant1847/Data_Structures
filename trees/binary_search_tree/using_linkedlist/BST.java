package trees.binary_search_tree.using_linkedlist;


class Node{
    int data;
    Node left, right;

    Node(int data){
        this.data = data;
    }
}

public class BST {
    private Node root;
    
    public void insert(int data){
        root = insertRecursive(root, data);
    }

    private Node insertRecursive(Node node, int data){
        if(node == null) return new Node(data);

        if(data < node.data){
            node.left = insertRecursive(node.left, data);
        }else if(data > node.data){
            node.right = insertRecursive(node.right, data);
        }else{
            System.out.println("Duplciate value ignored: "+ data);
        }
        
        return node;
    }

    public boolean search(int data){
        return searchRecursive(root, data);
    }

    private boolean searchRecursive(Node node, int data){
        if(node == null) return false;
        if(node.data == data) return true;

        return searchRecursive(node.left, data) || searchRecursive(node.right, data);
    }

    public void delete(int data){
        root = deleteRecursive(root, data);
    }

    private Node deleteRecursive(Node node, int data){
        if(node == null){
            System.out.println("Element not found: "+data);
            return null;
        }

        if(data < node.data){
            node.left = deleteRecursive(node.left, data);
        }else if(data > node.data){
            node.right = deleteRecursive(node.right, data);
        }else{
            //Node found
            if(node.left == null && node.right == null) return null;
            else if(node.left == null){
                return node.right;
            }else if(node.right == null){
                return node.left;
            }else{
                Node successor = findMin(node.right);
                node.data = successor.data;
                node.right = deleteRecursive(node.right, successor.data);
            }
        }

        return node;
    }

    private Node findMin(Node node){
        while(node.left != null) node = node.left;
        return node;
    }

    // Traversals
    public void inorder(Node node) {
        if (node == null)
            return;
        inorder(node.left);
        System.out.print(node.data + " ");
        inorder(node.right);
    }

    public void preorder(Node node) {
        if (node == null)
            return;
        System.out.print(node.data + " ");
        preorder(node.left);
        preorder(node.right);
    }

    public void postorder(Node node) {
        if (node == null)
            return;
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.data + " ");
    }

    public void levelOrder() {
        if (root == null) {
            System.out.println("Tree is empty");
            return;
        }
        java.util.Queue<Node> q = new java.util.LinkedList<>();
        q.add(root);
        System.out.print("Level Order: ");
        while (!q.isEmpty()) {
            Node current = q.poll();
            System.out.print(current.data + " ");
            if (current.left != null)
                q.add(current.left);
            if (current.right != null)
                q.add(current.right);
        }
        System.out.println();
    }

    // Display all traversals
    public void displayTraversals() {
        System.out.print("Inorder: ");
        inorder(root);
        System.out.println();

        System.out.print("Preorder: ");
        preorder(root);
        System.out.println();

        System.out.print("Postorder: ");
        postorder(root);
        System.out.println();

        levelOrder();
    }
    // MAIN TEST
    public static void main(String[] args) {
        BST bst = new BST();

        // Insertions
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);

        System.out.println("Initial Traversals:");
        bst.displayTraversals();

        // Search tests
        // System.out.println("Search 60: " + bst.search(60));
        System.out.println("Search 25: " + bst.search(25));

        // Deletions
        bst.delete(20);
        bst.delete(30);
        bst.delete(50);

        System.out.println("\nAfter Deletions:");
        bst.displayTraversals();
    }


}
