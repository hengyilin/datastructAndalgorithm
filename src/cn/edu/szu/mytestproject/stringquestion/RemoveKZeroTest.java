package cn.edu.szu.mytestproject.stringquestion;

/**
 * Author : hengyilin
 * Date   : 16-10-13
 * Version: 1.0
 * Email  : hylin601@126.com
 * Github : www.github.com/hengyilin
 * 去掉字符串中的连续出现的K个0字符
 * 给定一个字符串str和一个整型数字K，如果字符串中出现了K个0则把K个0去掉返回处理后的字符串
 *
 */
public class RemoveKZeroTest {
    public static String removeKZero(String str, int k) {
        if (str == null || k < 1) {
            return str;
        }
        char[] chars = str.toCharArray();
        int count = 0, start = -1;
        for (int i = 0; i != chars.length; i++) {
            if (chars[i] == '0') {
                count++;
                start = start == -1 ? i : start;
            }
            else {
                if (k == count) {
                    while (count-- != 0) {
                        chars[start++] = 0;
                    }
                }
                count = 0;
                start = -1;
            }
        }
        if (count == k) {
            while (count-- != 0) {
                chars[start++] = 0;
            }
        }
        return String.valueOf(chars);
    }
}
