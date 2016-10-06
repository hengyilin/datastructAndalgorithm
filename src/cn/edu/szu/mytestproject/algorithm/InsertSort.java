package cn.edu.szu.mytestproject.algorithm;

/**
 * Author : hengyilin
 * Date   : 16-10-6
 * Version: 1.0
 * Email  : hylin601@126.com
 * Github : www.github.com/hengyilin
 */
public class InsertSort {
    private static int[] insertSort(int[] array) {
       // int j;
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j = i - 1;
            while (j > -1 && temp > array[j]) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = temp;
        }
        return array;
    }
    public static void main(String[] args) {
        int[] array = {4, 2, 1, 3, 8, 6, 5, 9, 7};
        for (int i : array) {
            System.out.print(i + " ");

        }
        System.out.println();
        for (int j : insertSort(array)) {
            System.out.print(j + " ");

        }
        System.out.println();

    }
}
