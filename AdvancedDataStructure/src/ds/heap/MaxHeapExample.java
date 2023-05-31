package ds.heap;

import java.util.ArrayList;
import java.util.Iterator;

class MaxHeapClass
{
    static ArrayList<Integer> arr = new ArrayList<>();

    MaxHeapClass()
    {
        arr.add(null);
        arr.add(50);
        arr.add(30);
        arr.add(40);
        arr.add(10);
        arr.add(5);
        arr.add(20);
        arr.add(30);
    }

    private static void checkParents()
    {
        float index = arr.size() - 1;
        while (index > 1)
        {
            boolean isSwapped = false;
            if (arr.get((int) index) > arr.get((int)(index/2)))
            {
                int temp = arr.get((int) index);
                arr.set((int) index,arr.get((int) index / 2));
                arr.set((int) index / 2 ,temp);
                isSwapped = true;
            }
            if(isSwapped){
                index = (int) index / 2;
            }else{
                index = 0;
            }
        }
    }

    void showMaxHeap()
    {
        Iterator itr = arr.iterator();
        while (itr.hasNext())
        {
            System.out.print(itr.next() + ", ");
        }
        System.out.println();
    }

    void add(int a)
    {
        arr.add(a);
        checkParents();
    }
}

public class MaxHeapExample
{
    public static void main(String[] args)
    {
        MaxHeapClass maxHeapClass = new MaxHeapClass();
        maxHeapClass.showMaxHeap();
        maxHeapClass.add(60);
        maxHeapClass.showMaxHeap();
        maxHeapClass.add(45);
        maxHeapClass.showMaxHeap();

    }
}
