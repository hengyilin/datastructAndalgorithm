package cn.edu.szu.mytestproject.algorithm;

/**
 * Author : hengyilin
 * Date   : 16-10-6
 * Version: 1.0
 * Email  : hylin601@126.com
 * Github : www.github.com/hengyilin
 */
public class MergSort {
    /**
     * 归并排序实现
     * @param array
     * @return
     */
    private static void mergeSort(int[] array) {
        if (array.length > 1) {
            int haftLength = array.length / 2;
            int[] tempArray1 = new int[haftLength];
            System.arraycopy(array, 0, tempArray1, 0, haftLength);
            mergeSort(tempArray1);

            int[] tempArray2 = new int[array.length - haftLength];
            System.arraycopy(array, haftLength, tempArray2, 0, array.length - haftLength);
            mergeSort(tempArray2);

            int[] arrayNew = merge(tempArray1, tempArray2);
            System.arraycopy(arrayNew, 0, array, 0, array.length);
        }
    }

    /**
     * 归并两个数组
     * @param array1
     * @param array2
     * @return
     */
    private static int[] merge(int[] array1, int[] array2) {
        int[] array = new int[array1.length + array2.length];
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        while (count1 < array1.length && count2 < array2.length) {
            if (array1[count1] < array2[count2]) {
                array[count3] = array1[count1];
                count1++;
                count3++;
            }
            else {
                array[count3++] = array2[count2++];
            }
        }
        while (count1 < array1.length) {
            array[count3++] = array1[count1++];
        }
        while (count2 < array2.length) {
            array[count3++] = array2[count2++];
        }
        return array;
    }

    public static void main(String[] args) {
        int[] array = {5, 2, 1, 34, 5, 6, 85, 95, 16, 1654, 6164, 322, 1651, 651, 20};
        System.out.println("排序前");
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println("排序后");
        mergeSort(array);
        for (int j : array) {
            System.out.print(j + " ");
        }
    }
}
