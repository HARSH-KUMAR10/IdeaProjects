package sort;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

class BubbleSortNumbers
{
    void optimizedSort(int list[])
    {
        int iterationCount = 0;
        for (int i = 0; i < list.length; i++)
        {
            boolean isSwapped = false;
            for (int j = 0; j < list.length - 1; j++)
            {
                iterationCount++;
                if (list[j] > list[j + 1])
                {
                    int temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                    isSwapped = true;
                }
            }
            if (!isSwapped)
            {
                break;
            }
        }
        System.out.println("\nIteration count:" + iterationCount);
    }

    void sort(int list[])
    {
        int iterationCount = 0;
        for (int i = 0; i < list.length; i++)
        {
            for (int j = 0; j < list.length - 1; j++)
            {
                iterationCount++;
                if (list[j] > list[j + 1])
                {
                    int temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                }
            }
        }
        System.out.println("\nIteration count:" + iterationCount);
    }
}

public class BubbleSort
{
    static void showArray(int[] arr)
    {
        Iterator itr = Arrays.stream(arr).iterator();
        while (itr.hasNext())
        {
            System.out.print(itr.next() + ",");
        }
    }

    public static void main(String[] args)
    {
        BubbleSortNumbers bubbleSortNumbers = new BubbleSortNumbers();
        int x[] = {10, 20, 30, 40, 50};
        int y[] = x.clone();
        System.out.println("Array length:" + x.length);
        System.out.println("Before bubble sorting:");
        showArray(x);
        bubbleSortNumbers.sort(x);
        System.out.println("After bubble sorting:");
        showArray(x);
        System.out.println("\n================================");
        System.out.println("Array length:" + y.length);
        System.out.println("Before optimized bubble sorting:");
        showArray(y);
        bubbleSortNumbers.optimizedSort(y);
        System.out.println("After optimized bubble sorting:");
        showArray(y);
    }
}
