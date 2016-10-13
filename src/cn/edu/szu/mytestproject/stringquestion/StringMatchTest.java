package cn.edu.szu.mytestproject.stringquestion;

/**
 * Author : hengyilin
 * Date   : 16-10-13
 * Version: 1.0
 * Email  : hylin601@126.com
 * Github : www.github.com/hengyilin
 * 字符串匹配问题
 * 其中.可以代表任意一个字符 * 代表前一个字符可以有0个或多个
 *
 */
public class StringMatchTest {
    /**
     * 判断给定的两个字符串是不是符合标准的
     * @param str 待匹配字符串
     * @param exp 匹配的表达式
     * @return true 符合条件 false 不符合条件
     */
    private boolean isVaildString(char[] str, char[] exp) {
        for (int i = 0; i != str.length; i++) {
            if (str[i] == '*' || str[i] == '.') {
                return false;
            }
        }
        for (int i = 0; i != exp.length; i++) {
            if (exp[i] == '*' && (i == 0 || exp[i - 1] == '*')) {
                return false;
            }
        }
        return true;
    }

    public boolean isMatch(String str, String exp) {
        if (str == null || exp == null) {
            return false;
        }
        char[] s = str.toCharArray();
        char[] e = exp.toCharArray();
        return isVaildString(s, e) ? process(s, e, 0, 0) : false;
    }

    /**
     * 递归函数的意义是：对于s字符数组来说从si位置开始到末尾能不能被字符数组e的ei位置开始到末尾匹配？
     * 所以一开始进入递归函数是传入si = 0 ei = 0
     *
     * @param s
     * @param e
     * @param si
     * @param ei
     * @return
     */
    private boolean process(char[] s, char[] e, int si, int ei) {
        // 递归退出的条件
        if (ei == e.length) { // 当ei == e.length 此时出现数组越界 自然要返回false
            return false;
        }
        // 当ei = e数组的长度时递归退出因为此时已经匹配完啦
        // 当e数组出现*之后，*后面的字符就不可能匹配到啦自然也是递归退出的条件
        if (ei + 1 == e.length || e[ei + 1] != '*') {
            return si != s.length && (e[ei] == s[si] || e[ei] == '.') && (process(s, e, si + 1, ei + 1));
        }
        // 当ei不是数组e的最后一个元素，且ei的下一元素就是*就要走下面的代码块
        while (si != s.length && (e[ei] == s[si] || e[ei] == '.')) {
            if (process(s, e, si, ei + 2)) {
                return true;
            }
            si++;
        }
        return process(s, e, si, ei + 2);
    }

    public static void main(String[] args) {
        StringMatchTest test = new StringMatchTest();
        String str = "abcdefg";
        String exp = "abcdefg";
        System.out.println("是否匹配：" + test.isMatch(str, exp));
    }
}
