package cn.edu.szu.mytestproject.dp;

/**
 * Author : hengyilin
 * Date   : 16-10-8
 * Version: 1.0
 * Email  : hylin601@126.com
 * Github : www.github.com/hengyilin
 * 问题：矩阵最短路径和
 * 考点：动态规划问题
 */
public class ShortPathMetrix {
    /**
     * 计算给定矩阵从左上方定点到右下方定点的最短距离
     * 动态规划
     * 时间复杂度为O(MxN)
     * 空间复杂度为O(MxN)
     *
     * @param metrix
     * @return
     */
    private static int minPathSum1(int[][] metrix) {
        if (metrix == null || metrix.length == 0 || metrix[0] == null || metrix[0].length == 0) {
            return 0;
        }
        int row = metrix.length;
        int col = metrix[0].length;
        int dp[][] = new int[row][col];
        dp[0][0] = metrix[0][0];
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + metrix[i][0]; // 计算第一列的数值
        }
        for (int i = 1; i < col; i++) {
            dp[0][i] = dp[0][i - 1] + metrix[0][i]; // 计算第一行的数值
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + metrix[i][j]; // 动态规划方法
            }
        }
        return dp[row - 1][col - 1];
    }

    /**
     * 改进的动态规划路径算法
     *
     * @param metrix
     * @return
     */
    private static int minPathSum2(int[][] metrix) {
        if (metrix == null || metrix.length == 0 || metrix[0] == null || metrix[0].length == 0) {
            return 0;
        }
        int more = Math.max(metrix.length, metrix[0].length);
        int less = Math.min(metrix.length, metrix[0].length);
        boolean rowMore = more == metrix.length;
        int dp[] = new int[less];
        dp[0] = metrix[0][0];
        for (int i = 1; i < less; i++) {
            dp[i] = dp[i - 1] + (rowMore ? metrix[0][i] : metrix[i][0]);
        }
        for (int i = 1; i < more; i++) {
            dp[0] = dp[0] + (rowMore ? metrix[i][0] : metrix[0][i]);//辅助数组的第一个元素总是由原来的第一个元素来确定
            for (int j = 1; j < less; j++) {
                dp[j] = Math.min(dp[j - 1], dp[j]) + (rowMore ? metrix[i][j] : metrix[j][i]);
            }
        }
        return dp[less - 1];

    }
    public static void main(String[] args) {
        int[][] metrix = {{8,3,5,2,6,1}, {0,5,7,9,10,2}, {3,4,1,5,8,4}};
        System.out.println("方法一：矩阵最短路径为：" + minPathSum1(metrix));
        System.out.println("方法二：矩阵最短路径为：" + minPathSum2(metrix));
    }
}
