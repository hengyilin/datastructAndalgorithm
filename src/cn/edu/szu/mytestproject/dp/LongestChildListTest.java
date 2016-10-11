package cn.edu.szu.mytestproject.dp;

/**
 * Author : hengyilin
 * Date   : 16-10-11
 * Version: 1.0
 * Email  : hylin601@126.com
 * Github : www.github.com/hengyilin
 * 最长递增子序列
 */
public class LongestChildListTest {
    // 方法一：复杂度为O(n^2)
    private int[] getdp1(int[] arr) {
        int[] dp = new int[arr.length];
        // 循环嵌套复杂度达到O(n^2)
        for (int i = 0; i < arr.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return dp;
    }

    private int[] generateLis(int[] arr, int[] dp) {
        int len = 0;
        int index = 0;
        /*用循环找出dp数组中的最大那个值也即是最长递增子序列的长度
        和达到最长递增子序列的结尾那个数的下标*/
        for (int i = 0; i < dp.length; i++) {
            if (len > dp[i]) {
                len = dp[i];
                index = i;
            }
        }
        /*最后要得到的子序列长度为最长递增子序列的长度*/
        int[] list = new int[len];
        list[--len] = arr[index];
        /*
        * 在原数组中从右往左遍历找出所有满足条件的子序列
        * */
        for (int i = index; i >= 0; i--) {
            if (arr[i] < arr[index] && dp[i] == dp[index] - 1) {
                list[--len] = arr[i];
                index = i;
            }
        }
        return list;
    }

    private int[] findLongestChildList(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int[] dp = getdp1(arr);
        int[] list = generateLis(arr, dp);
        return list;
    }

    public void main(String[] args) {
        int[] arr = {2, 1, 5, 3, 6, 4, 8, 9, 7};
        System.out.println("最长递增子序列为：" + findLongestChildList(arr).toString());

    }
}
