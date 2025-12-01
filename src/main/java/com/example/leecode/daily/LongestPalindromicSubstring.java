package com.example.leecode.daily;

/**
 * Given a string s, return the longest palindromic substring in s.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "babad"
 * Output: "bab"
 * Explanation: "aba" is also a valid answer.
 * Example 2:
 * <p>
 * Input: s = "cbbd"
 * Output: "bb"
 */
public class LongestPalindromicSubstring {

    //暴力解题法
    public String longestPalindrome(String s) {
        String ans = "";
        for (int i = 0; i < s.length(); i++) {
            int len = s.length() - i;
//            if(len==s.length()&&isPalindromicSubstring(s)){
//                return s;
//            }
            for (int j = 0; j < s.length() + 1 - len; j++) {
                ans = s.substring(j, j + len);
                if (isPalindromicSubstring(ans)) {
                    return ans;
                }
            }
        }
        return ans;
    }

    public boolean isPalindromicSubstring(String s) {
        if (s.length() == 1) {
            return true;
        }
        for (int i = 0; i < s.length(); i++) {
            char head = s.charAt(i);
            char tail = s.charAt(s.length() - 1 - i);
            if (head != tail) {
                return false;
            }
        }
        return true;
    }

    //动态规划解题法
    public String solution(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;
        // dp[i][j] 表示 s[i..j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        // 初始化：所有长度为 1 的子串都是回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        char[] charArray = s.toCharArray();
        // 递推开始
        // 先枚举子串长度
        for (int L = 2; L <= len; L++) {
            // 枚举左边界，左边界的上限设置可以宽松一些
            for (int i = 0; i < len; i++) {
                // 由 L 和 i 可以确定右边界，即 j - i + 1 = L 得
                int j = L + i - 1;
                // 如果右边界越界，就可以退出当前循环
                if (j >= len) {
                    break;
                }

                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 只要 dp[i][L] == true 成立，就表示子串 s[i..L] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    public static void main(String[] args) {
        LongestPalindromicSubstring test = new LongestPalindromicSubstring();
        String aba = test.longestPalindrome("cabac");
        String cbbd = test.longestPalindrome("cbbd");
        System.out.println(aba);
        System.out.println(cbbd);
    }
}
