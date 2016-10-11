package cn.edu.szu.mytestproject.dp;

/**
 * Author : hengyilin
 * Date   : 16-10-8
 * Version: 1.0
 * Email  : hylin601@126.com
 * Github : www.github.com/hengyilin
 * 题目：换钱的最少货币数
 * 考点：动态规划
 *
 */
public class MinNumberMoneny {
    private static int minNumber(int[] money, int aimMoney) {
        if (money == null || money.length == 0 || aimMoney < 0) {
            return -1;
        }
        int length = money.length;
        int MAX = Integer.MAX_VALUE;
        int[][] dp = new int[length][aimMoney + 1];
        for (int j = 1; j < +aimMoney; j++) {
            dp[0][j] = MAX;
            if (j - money[0] >= 0 && dp[0][j - money[0]] != MAX) {
                dp[0][j] = dp[0][j - money[0]] + 1;
            }
        }
        int left = 0;
        for (int i = 1; i < length; i++) {
            for (int j = 1; j <= aimMoney; j++) {
                left = MAX;
                if (j - money[i] >= 0 && dp[i][j - money[i]] != MAX) {
                    left = dp[i][j-money[i]] + 1;
                }
                dp[i][j] = Math.min(left, dp[i - 1][j]);
            }
        }
        return dp[length - 1][aimMoney] != MAX ? dp[length - 1][aimMoney] : -1;
    }

    public static void main(String[] args) {
        int[] monek = {5,2,3};
        int aim = 2;
        System.out.println("最少的钱数为：" + minNumber(monek, aim));
    }
}
