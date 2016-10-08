package cn.edu.szu.mytestproject.dp;

/**
 * Author : hengyilin
 * Date   : 16-9-30
 * Version: 1.0
 * Email  : hylin601@126.com
 * Github : www.github.com/hengyilin
 * 斐波纳契数列
 */
public class Fibonacci {
    /**
     * 递归方法求解斐波纳契数列
     * 复杂度O(2^n)
     * @param n
     * @return
     */
    private static int f1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return f1(n- 1) + f1(n -2);
    }

    /**
     * 顺序求解斐波纳契数列
     * 复杂度O(n)
     * @param n
     * @return
     */
    private static int f2(int n) {
        if (n < 0) {
            return 0;
        }
        int result = 1;
        int previous = 1;
        int temp = 0;
        for (int i = 3; i <= n; i++) {
            temp = result;
            result = previous + result;
            previous = temp;
        }
        return result;
    }

    private static int f3(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int[][] base = {{1,1},{1,0}}; // 基础矩阵
        int[][] result = matrixPower(base, n - 2);
        return result[0][0] + result[0][1];
    }

    private static int[][] matrixPower(int[][] base, int number) {
        int[][] result = new int[base.length][base[0].length];
        for (int i = 0; i < result.length; i++) {
            result[i][i] = 1; // 构造单位矩阵
        }
        int[][] temp = base;
        for (; number != 0; number >>= 1) {
            if ((number & 1) != 0) {
                result = multiMatrix(result, temp);
            }
            temp = multiMatrix(temp, temp); // 自己忽略啦注意！！！！！！
        }
        return result;
    }

    private static int[][] multiMatrix(int[][] base, int[][] power) {
        int[][] result = new int[base.length][power[0].length];// 矩阵乘法运算规则：行x列
        for (int i = 0; i < base.length; i++) {
            for (int j = 0; j < power[0].length; j++) {
                for (int k = 0; k < power.length; k++) {
                    result[i][j] += base[i][k] * power[k][j];
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("递归方法求解斐波纳契数列");
        System.out.println(f1(10));
        System.out.println("顺序方法求解斐波纳契数列");
        System.out.println(f2(10));
        System.out.println("矩阵方法求解斐波纳契数列");
        System.out.println(f3(10));
    }
}
