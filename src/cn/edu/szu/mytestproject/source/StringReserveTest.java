package cn.edu.szu.mytestproject.source;

import java.util.Scanner;
import java.util.Stack;

/**
 * Author : hengyilin
 * Date   : 16-9-29
 * Version: 1.0
 * Email  : hylin601@126.com
 * Github : www.github.com/hengyilin
 * Description : 字符串反转输出
 */
public class StringReserveTest {
    public static void main(String[] args) {
        Stack<Character> strStack = new Stack<>();
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        char[] chs = str.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            strStack.push(chs[i]);
        }
        for (int i = 0; i < chs.length; i++) {
            System.out.println(strStack.pop());
        }
    }
}
