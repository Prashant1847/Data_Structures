package queue.using_arrays;

public class Queue{
    private int[] arr;
    private int front, rear, capacity;

    public Queue(int capacity){
        this.capacity = capacity;
        arr = new int[capacity];
        front = -1;
        rear = -1;
    }

    public boolean isFull(){
        return rear == capacity - 1;
    }

    public boolean isEmpty(){
        return front == -1 || front > rear;
    }

    public void enqueue(int data){
        if(isFull()){
            System.out.println("Queue is full");
            return;
        }

        if(front == -1) front = 0;
        arr[++rear] = data;
        System.out.println(data +" enqueued");
    }


    public int dequeue(){
        if(isEmpty()){
            System.out.println("Queue is empty");
            return -1;
        }

        int data = arr[front];
        if(front == rear){
            front = rear = -1;
        }else{
            front++;
        }
        System.out.println(data+" deqeued");
        return data;
    }

    public int peek(){
        if(isEmpty()){
            System.err.println("Queue is empty");
            return -1;
        }

        return arr[front];
    }

    public void display(){
        if(isEmpty()){
            System.out.println("Queue is empty");
            return;
        }

        for(int i = front; i <= rear; i++){
            System.out.print(arr[i]+ " ");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        Queue q = new Queue(5);
        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        q.display();
        q.dequeue();
        q.display();
        q.dequeue();
        q.dequeue();
        q.enqueue(40);
        q.enqueue(50);
        q.display();
    }
}
