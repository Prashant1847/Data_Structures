package queue.using_linked_list;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class Queue{
    private Node front, rear;

    public Queue(){
        front = rear = null;
    }

    public boolean isEmpty(){
        return front == null;
    }

    public void enqueue(int data){
        Node newNode = new Node(data);
        if(rear == null){
            front = rear = newNode;
        }else{
            rear.next = newNode;
            rear = newNode;
        }
        System.out.println(data + " enqueued");
    }

    public int dequeue(){
        if(isEmpty()){
            System.out.println("Queue is empty");
            return -1;
        }

        int data = front.data;
        front = front.next;
        if(front == null) rear = null;

        System.out.println(data+" dequeued");
        return data;
    }

    public int peek(){
        if(isEmpty()){
            System.out.println("Queue is empty");
            return-1;
        }

        return front.data;
    }

    public void display(){
        if(isEmpty()){
            System.out.println("Queue is empty");
            return;
        }

        Node temp = front;
        while(temp != null){
            System.out.print(temp.data +" ");
            temp = temp.next;
        }
        System.out.println();
    }

       public static void main(String[] args) {
        Queue q = new Queue();

        
        q.dequeue(); // dequeue on empty
        q.peek();    // peek on empty

        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        q.display();

        q.dequeue();
        q.display();

        q.dequeue();
        q.dequeue();
        q.display();

        q.dequeue(); // dequeue again on empty
        q.peek();    // peek again on empty

        q.enqueue(40);
        q.enqueue(50);
        q.display();
    }

}
