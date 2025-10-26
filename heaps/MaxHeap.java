package heaps;

import java.util.ArrayList;

public class MaxHeap {
    private ArrayList<Integer> list;

    public MaxHeap(){
        list = new ArrayList<>();
    }


    private void swap(int first, int second){
        Integer temp = list.get(first);
        list.set(first, list.get(second));
        list.set(second, temp);
    }

    private int parent(int index){
        return (index - 1) / 2;
    }

    private int left(int index){
        return (2 * index)  + 1;
    }

    private int right(int index){
        return ( 2 * index) + 2;
    }

    private void upheap(int index){
        if(index == 0) return;
        int p = parent(index);

        if(list.get(p) < list.get(index)){
            swap(index, p);
            upheap(p);
        }
    }

    public void insert(int value){
        list.add(value);
        upheap(list.size() - 1);
    }

    public void downHeap(int index){
        int max = index;
        int left = left(index);
        int right =  right(index);

        if(left < list.size() && list.get(max) < list.get(left)){
            max = left;
        }

        if(right < list.size() && list.get(max) < list.get(right)){
            max = right;
        }

        if(max != index){
            swap(max, index);
            downHeap(max);
        }
    }

    public int remove(){
        if(list.isEmpty()) return -1;

        int temp = list.get(0);

        int last= list.remove(list.size() - 1);

        if(!list.isEmpty()){
            list.set(0, last);
            downHeap(0);
        }

        return temp;
    }

    public ArrayList<Integer> heapSort(){
        ArrayList<Integer> answer = new ArrayList<>();

        while(!list.isEmpty()){
            answer.add(this.remove());
        }

        return answer;
    }
    
}
