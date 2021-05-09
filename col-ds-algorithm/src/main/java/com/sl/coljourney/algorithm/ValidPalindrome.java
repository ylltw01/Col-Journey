package com.sl.coljourney.algorithm;

/**
 * https://www.nowcoder.com/practice/e297fdd8e9f543059b0b5f05f3a7f3b2?tpId=117&tqId=37852&companyId=665&rp=1&ru=%2Fcompany%2Fhome%2Fcode%2F665&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
 * https://leetcode-cn.com/problems/valid-palindrome/submissions/
 * 125. 验证回文串
 * 难度：简单
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * <p>
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 * <p>
 * 示例 1:
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * <p>
 * 示例 2:
 * 输入: "race a car"
 * 输出: false
 */
public class ValidPalindrome {

    public boolean isPalindrome(String s) {
        int si = 0;
        int ei = s.length() - 1;

        while (si <= ei) {
            if (!Character.isAlphabetic(s.charAt(si)) && !Character.isDigit(s.charAt(si))) {
                si++;
                continue;
            }
            if (!Character.isAlphabetic(s.charAt(ei)) && !Character.isDigit(s.charAt(ei))) {
                ei--;
                continue;
            }
            if (Character.toUpperCase(s.charAt(si)) != Character.toUpperCase(s.charAt(ei))) {
                return false;
            }
            si++;
            ei--;
        }
        return true;
    }

    public static void main(String[] args) {
        ValidPalindrome validPalindrome = new ValidPalindrome();
//        boolean palindrome = validPalindrome.isPalindrome("A man, a plan, a canal: Panama");
        boolean palindrome = validPalindrome.isPalindrome("0P");
        System.out.println(palindrome);
    }

}
