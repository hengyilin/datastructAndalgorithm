package cn.edu.szu.mytestproject.stringquestion;

/**
 * Author : hengyilin
 * Date   : 16-10-11
 * Version: 1.0
 * Email  : hylin601@126.com
 * Github : www.github.com/hengyilin
 * 变形词测试
 * 变形词：如果str1和str2中出现的子符相同且每种字符出现的次数也
 * 相同那么就说明str1和str2互为变形词
 * 思路：用一个数组map[256]对应Ascii码表，每种字符的位置在数组map中是其Ascii码数组map
 * 中存放出现的次数，先遍历str1把上str1中出现的次数记录到map数组中，然后遍历str2并在map
 * 数组中减去次数，最后map数组为0说明两者互为变形词。
 */
public class VariantsTest {
    private boolean isDeformation(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() != str2.length()) {
            return false;
        }
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        int[] map = new int[256];
        for (int i = 0; i < chars1.length; i++) {
            map[chars1[i]]++;
        }
        for (int j = 0; j < chars2.length; j++) {
            if (map[chars2[j]]-- == 0) {
                return false;
            }
        }
        return true;
    }
}
