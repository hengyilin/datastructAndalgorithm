package cn.edu.szu.mytestproject.algorithm;

/**
 * Author : hengyilin
 * Date   : 16-10-6
 * Version: 1.0
 * Email  : hylin601@126.com
 * Github : www.github.com/hengyilin
 */
public class SelectionSort {
    private static int[] selectionSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int minKey = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minKey]) {
                    minKey = j;
                }
            }
            if (minKey != i) {
                int temp = array[i];
                array[i] = array[minKey];
                array[minKey] = temp;
            }
        }
        return array;
    }
    public static void main(String[] args) {
        int[] array = {4, 2, 3, 6, 5, 8, 7, 9, 1};
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int j : selectionSort(array)) {
            System.out.print(j + " ");
        }
        System.out.println();

    }
}
