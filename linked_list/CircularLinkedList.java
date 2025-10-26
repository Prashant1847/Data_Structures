package linked_list;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
    }
}

public class CircularLinkedList {
    private Node head;

    public void insertAtEnd(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            head.next = head;
            return;
        }
        Node temp = head;
        while (temp.next != head) temp = temp.next;
        temp.next = newNode;
        newNode.next = head;
    }

    public void insertAtBeginning(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            head.next = head;
            return;
        }
        Node temp = head;
        while (temp.next != head) temp = temp.next;
        newNode.next = head;
        temp.next = newNode;
        head = newNode;
    }

    public void delete(int key) {
        if (head == null) return;
        if (head.data == key && head.next == head) {
            head = null;
            return;
        }
        
        Node curr = head, prev = null;
        while (curr.next != head && curr.data != key) {
            prev = curr;
            curr = curr.next;
        }
        if (curr.data != key) return;
        if (curr == head) {
            prev = head;
            while (prev.next != head) prev = prev.next;
            head = head.next;
            prev.next = head;
        } else {
            prev.next = curr.next;
        }
    }

    public boolean search(int key) {
        if (head == null) return false;
        Node temp = head;
        do {
            if (temp.data == key) return true;
            temp = temp.next;
        } while (temp != head);
        return false;
    }

    public void display() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        Node temp = head;
        do {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        } while (temp != head);
        System.out.println("(head)");
    }

    public static void main(String[] args) {
        CircularLinkedList list = new CircularLinkedList();
        list.insertAtEnd(10);
        list.insertAtEnd(20);
        list.insertAtBeginning(5);
        list.insertAtEnd(30);
        list.display();
        list.delete(10);
        list.display();
        System.out.println("Search 20: " + list.search(20));
    }
}
