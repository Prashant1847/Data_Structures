package linked_list;


class Node {
    int data;       
    Node next;      

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

// Singly Linked List implementation
public class SinglyLinkedList {
    private Node head;

    
    public void insertAtEnd(int data) {
        Node newNode = new Node(data);
        if (head == null) {  // If list is empty
            head = newNode;
            return;
        }

        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }

    
    public void insertAtBeginning(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }

    
    public void insertAfter(int target, int data) {
        Node temp = head;
        while (temp != null && temp.data != target) {
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("Node with value " + target + " not found.");
            return;
        }

        Node newNode = new Node(data);
        newNode.next = temp.next;
        temp.next = newNode;
    }

    
    public void delete(int key) {
        if (head == null) return;

        if (head.data == key) {
            head = head.next;
            return;
        }

        Node temp = head;
        while (temp.next != null && temp.next.data != key) {
            temp = temp.next;
        }

        if (temp.next == null) {
            System.out.println("Node with value " + key + " not found.");
            return;
        }

        temp.next = temp.next.next;
    }

    
    public boolean search(int key) {
        Node temp = head;
        while (temp != null) {
            if (temp.data == key) return true;
            temp = temp.next;
        }
        return false;
    }

    
    public void display() {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }

        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    
    public int length() {
        int count = 0;
        Node temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    
    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();

        list.insertAtEnd(10);
        list.insertAtEnd(20);
        list.insertAtBeginning(5);
        list.insertAfter(10, 15);
        
        System.out.println("Linked List:");
        list.display();

        System.out.println("Length: " + list.length());

        System.out.println("\nSearching 20: " + list.search(20));
        System.out.println("Deleting 10...");
        list.delete(10);

        System.out.println("\nUpdated List:");
        list.display();
    }
}
