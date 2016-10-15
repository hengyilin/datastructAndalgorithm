package cn.edu.szu.mytestproject.stack;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Stream;

/**
 * Author : hengyilin
 * Date   : 16-10-15
 * Version: 1.0
 * Email  : hylin601@126.com
 * Github : www.github.com/hengyilin
 * 生成窗口函数的最大值数组
 * 实现思路：利用双端队列
 */
public class WidowFunction {
    public static int[] getMaxWindow(int[] array, int w) {
        if (array == null || w < 1 || array.length < w) {
            return null;
        }
        // 队列的对头始终维持的是数组中遍历到的最大的元素的下标
        LinkedList<Integer> qmax = new LinkedList<>();
        int[] res = new int[array.length - w + 1];
        int index = 0;
        for (int i = 0; i < array.length ;i++){
            while (!qmax.isEmpty() && array[qmax.peekLast()] <= array[i]) {
                // 队列不为空 而且队列里的最后一个元素是小于数组当前遍历到的数
                // 队头要维护的是到目前为止遍历到的最大值
                qmax.pollLast(); // 队列中的当前最后一个元素出列队
            }
            qmax.addLast(i); // 如果队列为空或者队列当前的最后一个元素比数组当前遍历到的元素要大
            // 注意下标过期
            if (qmax.peekFirst() == i - w){
                qmax.pollFirst();
            }
            // 找到第一个数之后，以后每次更新都要把最大值放到数组中保存
            if (i >= w - 1) {
                res[index++] = array[qmax.peekFirst()];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] array = {4, 3, 5,4, 3, 3, 6, 7};
        System.out.println(Arrays.toString(array));
        int[] maxWindow = getMaxWindow(array,3);
        System.out.println(Arrays.toString(maxWindow));

    }
}
