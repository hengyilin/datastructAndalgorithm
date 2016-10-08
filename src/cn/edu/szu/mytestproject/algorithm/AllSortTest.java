package cn.edu.szu.mytestproject.algorithm;

/**
 * Author : hengyilin
 * Date   : 16-10-6
 * Version: 1.0
 * Email  : hylin601@126.com
 * Github : www.github.com/hengyilin
 */
public class AllSortTest {
    public static void main(String[] args) {
        int[] array = {9, 2, 1, 5, 4, 8, 7, 6, 3, 0};

        // heapSortTest(array);
//        quickSortTest(array);
//        bubbleSortTest(array);
//        selectSortTest(array);
//        insertSortTest(array);
//        shellSort(array);
        mergeSort(array);

        for (int i : array) {
            System.out.print(i + " ");
        }
    }

    private static void bubbleSortTest(int[] array) {
        System.out.println("排序前");
        for (int i : array) {
            System.out.print(i+ " ");
        }
        boolean flag = true;

        for (int i = 0; i < array.length -1 && flag; i++) {
            flag = false;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] < array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j +1] = temp;
                    flag = true;
                }
            }
        }

        System.out.println("\n排序后");
        for (int i : array) {
            System.out.print(i + " ");
        }
    }

    private static void selectSortTest(int[] array) {
        System.out.println("排序前：");
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println("排序后：");

        for (int i = 0; i < array.length; i++) {
            int minKey = i;
            for (int j = i; j < array.length; j++) {
                if (array[minKey] > array[j]){
                    minKey = j;
                }
            }
            if (minKey != i) {
                int temp = array[minKey];
                array[minKey] = array[i];
                array[i] = temp;
            }
        }
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static void insertSortTest(int[] array) {
        System.out.println("排序前：");
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println("排序后：");
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j = i -1;
            while (j >= 0 && array[j] > temp) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = temp;
        }
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static void shellSort(int[] array) {
        System.out.println("排序前：");
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println("排序后：");

        int length = array.length;
        int deltaLength = length;
        while (true) {
            deltaLength = deltaLength / 2;
            for (int x = 0; x < deltaLength; x++) {
                for (int y = x + deltaLength; y < length; y++) {
                    int temp = array[y];
                    int z = y - deltaLength;
                    while(z >= 0 && array[z] > temp){
                        array[z + deltaLength] = array[z];
                        z -= deltaLength;
                    }
                    array[z + deltaLength] = temp;
                }
            }
            if (deltaLength == 1) {
                break;
            }
        }

        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static void mergeSort(int[] array) {
        if (array.length > 1) {
            int haftLength = array.length/2;
            int[] tempArray1 = new int[haftLength];
            int[] tempArray2 = new int[array.length - haftLength];
            System.arraycopy(array, 0, tempArray1, 0, haftLength);
            mergeSort(tempArray1);
            System.arraycopy(array, haftLength, tempArray2, 0, tempArray2.length);
            mergeSort(tempArray2);
            int[] newArray = mergeArray(tempArray1, tempArray2);
            System.arraycopy(newArray, 0, array, 0, newArray.length);
        }
    }

    private static int[] mergeArray(int[] array1, int[] array2) {
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        int[] array = new int[array1.length + array2.length];
        while (count1 < array1.length && count2 < array2.length) {
            if (array1[count1] < array2[count2]) {
                array[count3++] = array1[count1++];
            } else {
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

    private static void quickSortTest(int[] array) {
        System.out.println("排序前：");
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println("排序后：");
        quickSort(array, 0, array.length - 1);
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static void quickSort(int[] array, int start, int end) {
        if (start < end) {
            int paticition = quickSortParticition(array, start, end);
            quickSort(array, start, paticition - 1);
            quickSort(array, paticition + 1, end);
        }
    }

    private static int quickSortParticition(int[] array, int start, int end) {
        int temp = array[start];
        int low = start + 1;
        int high = end;
        while (low < high) {
            while (low <= high && array[low] <= temp) {
                low++;
            }
            while (low <= high && array[high] > temp) {
                high--;
            }
            if (high > low) {
                swap(array, low, high);
            }
        }
        while (high > start && array[high] >= temp) {
            high--;
        }
        if (array[high] < temp) {
            array[start] = array[high];
            array[high] = temp;
            return high;
        } else {
            return start;
        }
    }

    private static void heapSortTest(int[] array) {
        System.out.println("开始堆排序");
        int length = array.length;
        System.out.println("排序前的序列为：");
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = length / 2 - 1; i >= 0; i--) {
            heapAdjust(array, i, length);
        }
        System.out.println("建堆后元素为：");
        for (int i : array) {
            System.out.println(i + " ");
        }
        for (int i = 0; i < length - 1; i++) {
            swap(array, 0, length - i - 1);
            heapAdjust(array, 0, length - i - 1); // 每次调整都要从第一个位置开始调整
        }
        System.out.println("排序后元素为：");
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static void heapAdjust(int[] array, int index, int length) {
        int temp = array[index];
        int child = 0;
        while (index * 2 + 1 < length) {
            child = index * 2 + 1;
            if (child != length - 1 && array[child] < array[child + 1]) {
                child++;
            }
            if (temp > array[child])
                break;
            else {
                array[index] = array[child];
                index = child;
            }
        }
        array[index] = temp;
    }

    private static void swap(int[] array, int num1, int num2) {
        int temp = array[num1];
        array[num1] = array[num2];
        array[num2] = temp;
    }
}

