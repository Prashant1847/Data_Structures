package trees.binary_search_tree.using_arrays;

import java.util.*;

public class BST {
    private int[] tree;
    private int capacity;

    public BST(int capacity){
        this.capacity = capacity;
        tree = new int[capacity];
        Arrays.fill(tree, -1);
    }

    private int left(int i){
        return 2 * i + 1;
    }

    private int right(int i){
        return 2 * i + 2;
    }

    public void insert(int data){
        if(tree[0] == -1){
            tree[0] = data;
            System.out.println("Inserted root: "+data);
            return;
        }

        int i = 0;
        while(i < capacity && tree[i] != -1){
            if(data < tree[i]){
                i = left(i);
            }else if(data > tree[i]){
                i = right(i);
            }else{
                System.out.println("Duplicate element ignored "+data);
                return;
            }
        }

        if(i >= capacity){
            System.out.println("Tree capacity exceeded");
        }

        tree[i] = data;
        System.out.println("Inserted "+data);
    }

    public boolean search(int data){
        int i = 0;
        while(i < capacity && tree[i] != -1){
            if(tree[i] == data){
                return true;
            }else if( data < tree[i]){
                i = left(i);
            }else{
                i = right(i);
            }
        }
        return false;
    }

    public void delete(int data){
        if(!search(data)){
            System.out.println("Element not found: "+data);
            return;
        }

        deleteRecursive(0, data);
        System.out.println("Delted: " + data);
    }

    private int deleteRecursive(int i, int data){
        if(i >= capacity || tree[i] == -1) return -1;

        if(data < tree[i]){
            deleteRecursive(left(i), data);
        }else if(data > tree[i]){
            deleteRecursive(right(i), data);
        }else{
            //node found
            int leftIndex = left(i), rightIndex = right(i);

            //case 1: left
            if((leftIndex >= capacity || tree[leftIndex] == -1) &&
                (rightIndex >= capacity || tree[rightIndex] == -1) ){
                    tree[i] = -1;
            }
            
            //case 2: one child

            else if(leftIndex >= capacity || tree[leftIndex] == -1){
                int succIndex = rightIndex;
                tree[i] = tree[succIndex];
                deleteRecursive(succIndex, tree[succIndex]);
            }else if(rightIndex >= capacity || tree[rightIndex] == -1){
                int preIndex = leftIndex;
                tree[i] = tree[preIndex];
                deleteRecursive(leftIndex, tree[preIndex]);
            }else{
                //two children replace with inorder successor
                int succ  = findMin(rightIndex);
                tree[i] = succ;
                deleteRecursive(rightIndex, succ);
            }
        }
        return i;
    }

    private int findMin(int i){
        while(i < capacity && left(i) < capacity && tree[left(i)] != -1){
            i = left(i);
        }
        return tree[i];
    }

    public void inorder(int i){
        if(i >= capacity || tree[i] == -1) return;
        inorder(left(i));
        System.out.print(tree[i]+" ");
        inorder(right(i));
    }

     public void preorder(int i) {
        if (i >= capacity || tree[i] == -1)
            return;
        System.out.print(tree[i] + " ");
        preorder(left(i));
        preorder(right(i));
    }

    public void postorder(int i) {
        if (i >= capacity || tree[i] == -1)
            return;
        postorder(left(i));
        postorder(right(i));
        System.out.print(tree[i] + " ");
    }

    public void levelOrder() {
        System.out.print("Level Order: ");
        for (int val : tree) {
            if (val != -1)
                System.out.print(val + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        BST bst = new BST(31);

        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);

        System.out.println("Inorder:");
        bst.inorder(0);
        System.out.println();

        System.out.println("Preorder:");
        bst.preorder(0);
        System.out.println();

        System.out.println("Postorder:");
        bst.postorder(0);
        System.out.println();

        bst.levelOrder();

        System.out.println("Search 60: " + bst.search(60));
        System.out.println("Search 25: " + bst.search(25));

        bst.delete(20);
        bst.delete(30);
        bst.delete(50);

        System.out.println("After Deletions (Inorder):");
        bst.inorder(0);
        System.out.println();

        bst.levelOrder();
    }
}
