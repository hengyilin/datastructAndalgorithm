package cn.edu.szu.mytestproject.dp;

/**
 * Author : hengyilin
 * Date   : 16-10-11
 * Version: 1.0
 * Email  : hylin601@126.com
 * Github : www.github.com/hengyilin
 * 最长公共子串问题：
 * 给定两个字符串上str1和str2返回两个字符串的最长公共子串
 * 思路：动态规划：生成动态规划表
 * 要求实现复杂度为O(MxN)而外空间复杂度为：O(1)
 *
 */
public class LongestCommSubStringTest {
    /**
     * 初始化动态表
     * @param str1 字符数组1
     * @param str2 字符数组2
     * @return 返回动态表
     */
    private int[][] getdp(char[] str1, char[] str2) {
        int[][] dp = new int[str1.length][str2.length];
        // 初始化动态表第一列的值
        for (int i = 0; i< str1.length; i++) {
            if (str1[i] == str2[0]) {
                dp[i][0] = 1;
            } else {
                dp[i][0] = 0;
            }
        }
        // 初始化动态表的第一列的值
        for (int i = 0; i < str2.length; i++) {
            if (str1[0] == str2[i]) {
                dp[0][i] = 1;
            }else {
                dp[0][i] = 0;
            }
        }
        for (int i = 1; i < str1.length; i++) {
            for (int j = 1; j < str2.length; j++) {
                if (str1[i] == str2[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else {
                    dp[i][j] = 0;
                }
            }
        }
        return dp;
    }

    /**
     * 获取最长公共子串
     *
     * @param str1 第一个字符串
     * @param str2 第二个字符串
     * @return 返回两个字符串的最长公共子串
     */
    private String getLongestSubString(String str1, String str2) {
        if (str1 == null || str2 == null || str1.equals("") || str2.equals("")) {
            return "";
        }
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        int[][] dp = getdp(chs1, chs2);
        int end = 0;
        int max = 0;
        for (int i = 0; i < chs1.length; i++) {
            for (int j = 0; j< chs2.length; j++) {
                if (dp[i][j] > max) {
                    end = i;
                    max = dp[i][j];
                }
            }
        }
        return str1.substring(end - max + 1, end + 1);
    }

    /**
     * 以下算法可以实现时间复杂度为O(MxN)空间复杂度为：O(1)的获取最长公共子串
     *
     * @param str1 第一个字符串
     * @param str2 第二个字符串
     * @return 返回的最长公共子串
     */
    private String getLongestSubString2(String str1, String str2) {
        if (str1 == null || str2 == null || str1.equals("") || str2.equals("")) {
            return "";
        }
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        int row = 0;
        int col = chs2.length - 1;
        int max = 0;
        int end = 0;
        while (row < chs1.length) {
            int i = row;
            int j = col;
            int len = 0;
            while (i < chs1.length && j < chs2.length) {
                if (chs1[i] != chs2[j]) {
                    len = 0;
                }
                else {
                    len++;
                }
                if (len > max) {
                    end = i;
                    max = len;
                }
                i++;
                j++;
            }
            if (col > 0) {
                col--;
            }else {
                row++;
            }
        }
        return str1.substring(end - max + 1, end + 1);
    }

    public static void main(String[] args) {
        String str1 = "ABCDEFVMPAIEJNVOAI";
        String str2 = "venaovnaeABCDEF";
        LongestCommSubStringTest longestCommSubStringTest = new LongestCommSubStringTest();
        System.out.println("最长公共子序列是：" + longestCommSubStringTest.getLongestSubString(str1, str2));
        System.out.println("最长公共子串为：" + longestCommSubStringTest.getLongestSubString2(str1, str2));
    }
}
