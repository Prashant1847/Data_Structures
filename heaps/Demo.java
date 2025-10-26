package heaps;

import java.util.ArrayList;

public class Demo{
    public static void main(String[] args) throws Exception{
        // MinHeap<Integer> heap = new MinHeap<>();
        
        // heap.insert(34);
        // heap.insert(46);
        // heap.insert(22);
        // heap.insert(89);
        // heap.insert(26);

        // System.out.println(heap.remove());
        // System.out.println(heap.remove());

        // ArrayList<Integer> list = heap.heapSort();


        MaxHeap heap = new MaxHeap();
        
        heap.insert(34);
        heap.insert(46);
        heap.insert(22);
        heap.insert(89);
        heap.insert(26);

        System.out.println(heap.remove());
        System.out.println(heap.remove());

        ArrayList<Integer> list = heap.heapSort();
        System.out.println(list);

    }
}