package cn.edu.szu.mytestproject.algorithm;

/**
 * Author : hengyilin
 * Date   : 16-10-6
 * Version: 1.0
 * Email  : hylin601@126.com
 * Github : www.github.com/hengyilin
 */
public class HeapSort {
    private static void heapAdajust(int[] array,int index, int n) {
        int temp = array[index];
        int child = 0;
        while (index * 2 + 1 < n) {
            child = index * 2 + 1;
            //child 为左右孩子中最大的那个
            if (child != n - 1 && array[child] < array[child + 1]) {
                child++;
            }
            // 如果指定的节点大于较大的孩子则不用调整
            if (temp > array[child]) {
                break;
            }
            else {
                array[index] = array[child];
                index = child;
            }
        }
        array[index] = temp;
    }

    private static void heapSort(int[] array, int n) {
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapAdajust(array, i, n);
        }
        System.out.println("构建堆后的结果是：");
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println("开始排序");

        for (int i = 0; i < n-1; i++) {
            System.out.println("最大的元素是：" + array[0]);
            swap(array, 0, n - i - 1);
            heapAdajust(array, 0, n - i - 1);
        }
    }

    private static void swap(int[] array,int low, int high) {
        int temp = array[low];
        array[low] = array[high];
        array[high] = temp;
    }
    public static void main(String[] args) {
        int[] array = {28, 25, 14, 98, 37, 69, 46, 121,36,15,73,91,64,18,90};
        System.out.println("排序前");
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("排序后");
        heapSort(array, array.length);
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();

    }
}
