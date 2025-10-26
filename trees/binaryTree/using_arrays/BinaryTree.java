package trees.binaryTree.using_arrays;
import java.util.*;

public class BinaryTree{
    private int[] tree;
    private int size;

    public BinaryTree(int capacity){
        tree = new int[capacity];
        size = 0;

        Arrays.fill(tree, -1);
    }



    public int leftChild(int index){
        return 2 * index + 1;
    }

    public int rightChild(int index){
        return 2 * index + 2;
    }

    public void insert(int data){
        if(size >= tree.length){
            System.out.println("Tree is full");
            return;
        }

        tree[size++] = data;
        System.out.println(data+" inserted at index "+ (size - 1));
    }

    private int find(int data){
        for(int i = 0; i < size; i++){
            if(tree[i] == data) return i;
        }
        return -1;
    }


    public void insertLeft(int parent, int data){
        int parentIndex = find(parent);
        if(parentIndex == -1){
            System.out.println("Parent not found");
            return;
        }

        int leftIndex = leftChild(parentIndex);
        if(leftIndex >= tree.length){
            System.out.println("Left index out of bounds");
        }

        if(tree[leftIndex] != -1){
            System.out.println("Left child already exist for "+parent);
            return;
        }

        tree[leftIndex] = data;
        if(leftIndex >= size) size = leftIndex + 1;
        System.out.println(data + " inserted to left of "+parent);
    }


    public void insertRight(int parent, int data){
        int parentIndex = find(parent);
        if(parentIndex == -1){
            System.out.println("Parent "+parent+" not found");
            return;
        }

        int rightIndex = rightChild(parentIndex);
        if(rightIndex >= tree.length){
            System.out.println("Right index out of bounds");
            return;
        }

        if(tree[rightIndex] != -1){
            System.out.println("Right child already exists");
            return;
        }

        tree[rightIndex] = data;
        if(rightIndex >= size) size = rightIndex + 1;
        System.out.println(data+" inserted to rigth of "+parent);
    }


    public boolean search(int data){
        return find(data) != -1;
    }

    public void delete(int data){
        int index = find(data);
        if(index == -1){
            System.out.println("Node "+data+" not found!");
            return;
        }
        //replace with last node
        int lastValue = tree[size - 1];
        tree[index] = lastValue;
        tree[size - 1] = -1;
        size--;
        System.out.println("Deleted "+data+", repalced with "+lastValue);

    }

    private void preorder(int index){
        if(index >= size || tree[index] == -1) return;

        System.out.print(tree[index] + " ");
        preorder(leftChild(index));
        preorder(rightChild(index));
    }

    private void inorder(int index){
        if(index >= size || tree[index] == -1) return;

        inorder(leftChild(index));
        System.out.print(tree[index]+ " ");
        inorder(rightChild(index));
    }

    
    private void postorder(int index) {
        if (index >= size || tree[index] == -1)
            return;
        postorder(leftChild(index));
        postorder(rightChild(index));
        System.out.print(tree[index] + " ");
    }

    // Display functions
    public void displayLevelOrder() {
        System.out.print("Level Order: ");
        for (int i = 0; i < size; i++) {
            if (tree[i] != -1) System.out.print(tree[i] + " ");
        }
        System.out.println();
    }

    public void preorderTraversal() {
        System.out.print("Preorder: ");
        preorder(0);
        System.out.println();
    }

    public void inorderTraversal() {
        System.out.print("Inorder: ");
        inorder(0);
        System.out.println();
    }

    public void postorderTraversal() {
        System.out.print("Postorder: ");
        postorder(0);
        System.out.println();
    }

    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree(15);

        // Base inserts
        bt.insert(1);
        bt.insert(2);
        bt.insert(3);
        bt.insert(4);
        bt.insert(5);
        bt.displayLevelOrder();

        // Targeted insertions
        bt.insertLeft(2, 6);
        bt.insertRight(2, 7);
        bt.insertLeft(3, 8);
        bt.insertRight(3, 9);
        bt.displayLevelOrder();

        // Traversals
        bt.preorderTraversal();
        bt.inorderTraversal();
        bt.postorderTraversal();

        // Search
        System.out.println("Searching 8: " + bt.search(8));
        System.out.println("Searching 99: " + bt.search(99));

        // Deletion
        bt.delete(3);
        bt.displayLevelOrder();

        // Inserting beyond capacity
        bt.insert(10);
        bt.insert(11);
        bt.insert(12);
        bt.insert(13);
        bt.insert(14);
        bt.insert(15);
        bt.insert(16); // should fail
    }

}