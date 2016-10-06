package cn.edu.szu.mytestproject.algorithm;

/**
 * Author : hengyilin
 * Date   : 16-10-6
 * Version: 1.0
 * Email  : hylin601@126.com
 * Github : www.github.com/hengyilin
 */
public class QuickSort {
    private static void quickSort(int[] array, int start, int end) {
        if (start < end) {
            int priorityIndex = quickSortParticition(array, start, end);
            quickSort(array, start, priorityIndex -1 );
            quickSort(array, priorityIndex + 1, end);
        }
    }

    /*返回的那个index就是已经排好的元素*/
    private static int quickSortParticition(int[] array, int start, int end) {
        int priority = array[start];
        int low = start + 1;
        int hight = end;
        while (hight > low) {
            // 该循环直到hight == low 时退出
            while (low <= hight && array[low] <= priority) {
                low++;
            }
            while (low <= hight && array[hight] > priority) {
                hight--;
            }
            if (hight > low) {
                int temp = array[hight];
                array[hight] = array[low];
                array[low] = temp;
            }
        }
        while (hight > start && array[hight] >= priority) {
            hight--;
        }
        /*
        判断上述循环退出时原因是什么
         */
        if (array[hight] < priority) {
            array[start] = array[hight];
            array[hight] = priority;
            return hight;
        }
        else {
            return start;
        }
    }

    public static void main(String[] args) {
        int[] array = {2, 8, 4, 5, 6, 3, 2, 1, 4, 5, 6, 9, 0};
        System.out.println("排序前");
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();

        System.out.println("排序后");
        quickSort(array, 0, array.length - 1);
        for (int i : array) {
            System.out.print(i + " ");
        }
    }
}
