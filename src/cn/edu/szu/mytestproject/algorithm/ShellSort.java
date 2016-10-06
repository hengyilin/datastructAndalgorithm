package cn.edu.szu.mytestproject.algorithm;

/**
 * Author : hengyilin
 * Date   : 16-10-6
 * Version: 1.0
 * Email  : hylin601@126.com
 * Github : www.github.com/hengyilin
 */
public class ShellSort {
    private static int[] shellSort(int[] array) {
        int length = array.length;
        int deltaLength = array.length;
        /*
        int d=a.length;
            while(true)
            {
                d=d/2;
                for(int x=0;x<d;x++)
                {
                    for(int i=x+d;i<a.length;i=i+d)
                    {
                        int temp=a[i];
                        int j;
                        for(j=i-d;j>=0&&a[j]>temp;j=j-d)
                        {
                            a[j+d]=a[j];
                        }
                        a[j+d]=temp;
                    }
                }
                if(d==1)
                {
                    break;
                }
            }
         */
        while (true) {
            deltaLength = deltaLength / 2;
            for (int i = 0; i < deltaLength; i++) {
                for (int j = i + deltaLength; j < length; j += deltaLength) {
                    int temp = array[j]; // 保留当前要排序的元素
                    int z;
                    for (z = j - deltaLength; z >= 0 && array[z] > temp; z -= deltaLength) {
                        array[z + deltaLength] = array[z]; //
                    }
                    array[z + deltaLength] = temp;
                }
            }
            if (deltaLength == 1) {
                break;
            }
        }
        return array;
    }

    public static void main(String[] args) {
        int[] array = {9, 2, 13, 4, 5, 6, 5, 4, 86, 92, 321, 5, 4, 6, 8, 56};
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int j : shellSort(array)) {
            System.out.print(j + " ");
        }
    }
}
