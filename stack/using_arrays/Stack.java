package stack.using_arrays;

public class Stack {
    private int[] arr;
    private int top;
    private int capacity;

    public Stack(int capacity) {
        this.capacity = capacity;
        arr = new int[capacity];
        top = -1; // stack is initially empty
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == capacity - 1;
    }

    public void push(int data) {
        if (isFull()) {
            System.out.println("Stack overflow! Cannot push " + data);
            return;
        }
        arr[++top] = data;
        System.out.println(data + " pushed");
    }

    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack underflow! Cannot pop");
            return -1;
        }
        int data = arr[top--];
        System.out.println(data + " popped");
        return data;
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty! Cannot peek");
            return -1;
        }
        return arr[top];
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return;
        }
        System.out.print("Stack elements (top -> bottom): ");
        for (int i = top; i >= 0; i--) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Stack stack = new Stack(5);

        // Subtle testing
        stack.pop(); // pop on empty
        stack.peek(); // peek on empty

        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.display();

        stack.pop();
        stack.display();

        stack.push(40);
        stack.push(50);
        stack.push(60); // testing overflow
        stack.display();

        System.out.println("Top element: " + stack.peek());
    }
}
