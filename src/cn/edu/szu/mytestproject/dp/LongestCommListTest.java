package cn.edu.szu.mytestproject.dp;

import cn.edu.szu.mytestproject.source.StringReserveTest;

/**
 * Author : hengyilin
 * Date   : 16-10-11
 * Version: 1.0
 * Email  : hylin601@126.com
 * Github : www.github.com/hengyilin
 * 最长公共子序列问题
 * 给定两个字符串，返回两个字符串的最长公共子序列
 * 动态规划
 * 构建动态规划表，从上至下从左到右一次填满每一个位(i,j)
 */
public class LongestCommListTest {
    private int[][] getdp(char[] str1, char[] str2) {
        int[][] dp = new int[str1.length][str2.length];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        // 以下循环确定dp矩阵第一列的数值
        for (int i = 1; i < str1.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], str1[i] == str2[0] ? 1 : 0);
        }
        // 以下循环确定dp矩阵第一行的数值
        for (int j = 1; j < str2.length; j++) {
            dp[0][j] = Math.max(dp[0][j - 1], str1[0] == str2[j] ? 1 : 0);
        }
        // 以下嵌套循环用于确定dp矩阵的其余位置，
        // 其余位置由前一个位置的值得到可以由行最大值得到或者由列最大值得到
        for (int i = 1; i < str1.length; i++) {
            for (int j = 1; j < str2.length; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (str1[i] == str2[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp;
    }

    /**
     * 获取最长公共子序列
     * @param str1 字符串1
     * @param str2 子符串2
     * @return 返回最长公共子序列
     */
    private String getLcse(String str1, String str2) {
        if (str1 == null || str2 == null || str1.equals("") || str2.equals("")) {
            return "";
        }
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        int[][] dp = getdp(chs1, chs2);
        int m = chs1.length - 1;
        int n = chs2.length - 1;
        char[] res = new char[dp[m][n]];
        int index = res.length - 1;
        while (index >= 0) {
            if (n > 0 && dp[m][n] == dp[m][n -1]) {
                n--;
            } else if (m > 0 && dp[m][n] == dp[m - 1][n]) {
                m--;
            } else {
                res[index--] = chs1[m];
                m--;
                n--;
            }
        }
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        LongestCommListTest test = new LongestCommListTest();
        String str1 = "1A2C3D4B56";
        String str2 = "B1D23CA45B6A";
        String res = test.getLcse(str1, str2);
        System.out.println("结果是：");
        System.out.println(res);

    }
}
