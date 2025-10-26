package queue.using_linked_list;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class CircularQueue {
    private Node front, rear;

    public CircularQueue(){
        front = rear = null;
    }

    public boolean isEmpty(){
        return front == null;
    }

    public void enqueue(int data){
        Node newNode = new Node(data);
        if(isEmpty()){
            front = rear = newNode;
            rear.next = front;
        }else{
            rear.next = newNode;
            rear = newNode;
            rear.next = front;
        }
        System.out.println(data +" enqueued");
    }

    public int dequeue(){
        if(isEmpty()){
            System.out.println("Queue is Empty()");;
            return -1;
        }

        int data = front.data;
        if(front == rear){
            front = rear = null;
        }else{
            front = front.next;
            rear.next = front;
        }
        System.out.println(data+" dequeued");
        return data;
    }

    public int peek(){
        if(isEmpty()){
            System.out.println("Queue is empty");
            return -1;
        }
        return front.data;
    }

    public void display(){
        if(isEmpty()){
            System.out.println("Queue is empty");
            return;
        }

        Node temp = front;
        do{
            System.out.print(temp.data +" ");
            temp = temp.next;
        }while(temp != front);
        System.out.println();
    }


    public static void main(String[] args) {
        CircularQueue q = new CircularQueue();

        // Subtle testing for empty queue
        q.dequeue();
        q.peek();

        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        q.display();

        q.dequeue();
        q.display();

        q.dequeue();
        q.dequeue();
        q.display();

        q.dequeue(); // dequeue on empty
        q.peek();    // peek on empty

        q.enqueue(40);
        q.enqueue(50);
        q.display();
    }
}
