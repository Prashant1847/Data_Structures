package linked_list;


class Node {
    int data;
    Node next, prev;

    Node(int data) {
        this.data = data;
    }
}

public class CircularDoublyLinkedList {
    private Node head;

    public void insertAtEnd(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            head.next = head;
            head.prev = head;
            return;
        }
        Node tail = head.prev;
        tail.next = newNode;
        newNode.prev = tail;
        newNode.next = head;
        head.prev = newNode;
    }

    public void insertAtBeginning(int data) {
        insertAtEnd(data);
        head = head.prev;
    }

    public void insertAfter(int target, int data) {
        if (head == null) return;
        Node temp = head;
        do {
            if (temp.data == target) {
                Node newNode = new Node(data);
                newNode.next = temp.next;
                newNode.prev = temp;
                temp.next.prev = newNode;
                temp.next = newNode;
                return;
            }
            temp = temp.next;
        } while (temp != head);
    }

    public void delete(int key) {
        if (head == null) return;
        Node temp = head;
        do {
            if (temp.data == key) {
                if (temp.next == temp) {
                    head = null;
                    return;
                }
                temp.prev.next = temp.next;
                temp.next.prev = temp.prev;
                if (temp == head) head = temp.next;
                return;
            }
            temp = temp.next;
        } while (temp != head);
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

    public void displayForward() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        Node temp = head;
        do {
            System.out.print(temp.data + " <-> ");
            temp = temp.next;
        } while (temp != head);
        System.out.println("(head)");
    }

    public void displayBackward() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        Node temp = head.prev;
        do {
            System.out.print(temp.data + " <-> ");
            temp = temp.prev;
        } while (temp != head.prev);
        System.out.println("(head)");
    }

    public static void main(String[] args) {
        CircularDoublyLinkedList list = new CircularDoublyLinkedList();
        list.insertAtEnd(10);
        list.insertAtEnd(20);
        list.insertAtBeginning(5);
        list.insertAfter(10, 15);
        list.displayForward();
        list.delete(10);
        list.displayForward();
        list.displayBackward();
        System.out.println("Search 20: " + list.search(20));
    }
}
