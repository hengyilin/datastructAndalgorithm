package cn.edu.szu.mytestproject.algorithm;

/**
 * Author : hengyilin
 * Date   : 16-10-6
 * Version: 1.0
 * Email  : hylin601@126.com
 * Github : www.github.com/hengyilin
 */
public class BubbleSort {
    private static int[] bubbleSort(int[] array) {
        boolean falg = true;
        for (int i = 0; (i < array.length - 1) && falg; i++) {
            falg = false;
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j+1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    falg = true;
                }
            }
            for (int a : array) {
                System.out.print(a +" ");
            }
            System.out.println();

        }
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
        return array;
    }
    public static void main(String[] args) {
        int[] array = {8,5,1,6,4,2,3,9,7};
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
        int []array1 = bubbleSort(array);
        for (int i : array1) {
            System.out.print(i + " ");
        }
        System.out.println();

    }
}
