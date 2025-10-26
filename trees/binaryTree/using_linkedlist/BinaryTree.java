package trees.binaryTree.using_linkedlist;

import java.util.LinkedList;
import java.util.Queue;

class Node{
    int data;
    Node left, right;
    Node(int data){
        this.data = data;
    }
}

public class BinaryTree {
    private Node root;
    
    public void insertRoot(int data){
        if(root != null){
            System.out.println("Root already exists!");
            return;
        }
        root = new Node(data);
        System.out.println("Root inserted: "+data);
    }

    private Node searchNode(Node node, int data){
        if(node == null) return null;
        if(node.data == data) return node;

        Node leftSearch = searchNode(node.left, data);
        if(leftSearch != null) return leftSearch;

        return searchNode(node.right, data);
    }

    public boolean search(int data){
        return searchNode(root, data) != null;
    }

    public void insertLeft(int parentData, int data){
        Node parent = searchNode(root, parentData);
        if(parent == null){
            System.out.println("Parent not found");
            return;
        }

        if(parent.left != null){
            System.out.println("Left child alerady exists");
            return;
        }

        parent.left = new Node(data);
        System.out.println(data+" inserted to the left of "+parentData);
    }

     // Insert right child to a given parent value
    public void insertRight(int parentData, int data) {
        Node parent = searchNode(root, parentData);
        if (parent == null) {
            System.out.println("Parent " + parentData + " not found!");
            return;
        }
        if (parent.right != null) {
            System.out.println("Right child already exists for " + parentData);
            return;
        }
        parent.right = new Node(data);
        System.out.println(data + " inserted to right of " + parentData);
    }

    public void delete(int data){
        if(root == null){
            System.out.println("Tree is empty");
            return;
        }

        if(root.left == null && root.right == null){
            if(root.data == data){
                root = null;
                System.out.println("Deleted root");
            }else{
                System.out.println("Node not found!1");
            }
            return;
        }

        Queue<Node> q = new LinkedList<>();
        q.add(root);
        Node keyNode = null, temp = null, parentOfDeepest = null;

        while(!q.isEmpty()){
            temp = q.poll();
            if(temp.data == data){
                keyNode = temp;
            }

            if(temp.left != null){
                parentOfDeepest = temp;
                q.add(temp.left);
            }

            if(temp.right != null){
                parentOfDeepest = temp;
                q.add(temp.right);
            }
        }

        if(keyNode == null){
            System.out.println("Node "+data+" not found");
            return;
        }

        int deepestData = temp.data;
        deleteDeepest(temp, parentOfDeepest);
        keyNode.data = deepestData;
        System.out.println("Deleted node "+data+" and replaced with "+deepestData);
    }

    private void deleteDeepest(Node delNode, Node parent){
        if(parent == null) return;
        if(parent.left == delNode){
            parent.left = null;
        }else if(parent.right == delNode){
            parent.right = null;
        }
    }

    public void preorderTraversal(Node node) {
        if (node == null)
            return;
        System.out.print(node.data + " ");
        preorderTraversal(node.left);
        preorderTraversal(node.right);
    }

    public void inorderTraversal(Node node) {
        if (node == null)
            return;
        inorderTraversal(node.left);
        System.out.print(node.data + " ");
        inorderTraversal(node.right);
    }

    public void postorderTraversal(Node node) {
        if (node == null)
            return;
        postorderTraversal(node.left);
        postorderTraversal(node.right);
        System.out.print(node.data + " ");
    }

    public void levelOrderTraversal() {
        if (root == null) {
            System.out.println("Tree is empty");
            return;
        }
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        System.out.print("Level Order: ");
        while (!q.isEmpty()) {
            Node temp = q.poll();
            System.out.print(temp.data + " ");
            if (temp.left != null)
                q.add(temp.left);
            if (temp.right != null)
                q.add(temp.right);
        }
        System.out.println();
    }

    public void displayTraversals() {
        System.out.print("Preorder: ");
        preorderTraversal(root);
        System.out.println();

        System.out.print("Inorder: ");
        inorderTraversal(root);
        System.out.println();

        System.out.print("Postorder: ");
        postorderTraversal(root);
        System.out.println();
    }


    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();

        bt.insertRoot(1);
        bt.insertLeft(1, 2);
        bt.insertRight(1, 3);
        bt.insertLeft(2, 4);
        bt.insertRight(2, 5);
        bt.insertLeft(3, 6);
        bt.insertRight(3, 7);

        bt.levelOrderTraversal();
        bt.displayTraversals();

        System.out.println("Search 5: " + bt.search(5));
        System.out.println("Search 99: " + bt.search(99));

        bt.delete(3);
        bt.levelOrderTraversal();
        bt.displayTraversals();

        bt.insertRight(2, 9); // after deletion
        bt.levelOrderTraversal();
    }

}
