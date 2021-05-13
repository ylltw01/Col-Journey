package com.sl.coljourney.algorithm;

/**
 * https://www.nowcoder.com/practice/2cc32b88fff94d7e8fd458b8c7b25ec1?tpId=117&tqId=37836&companyId=665&rp=1&ru=%2Fcompany%2Fhome%2Fcode%2F665&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
 * 牛客题目：进制转换
 * <p>
 * 题目描述
 * 给定一个十进制数M，以及需要转换的进制数N。将十进制数M转化为N进制数
 * 示例1
 * 输入
 * 复制
 * 7,2
 * 返回值
 * 复制
 * "111"
 * 备注:
 * M是32位整数，2<=N<=16.
 */
public class HexadecimalConversionNiu {

    public String solve(int m, int n) {
        if (m == 0) {
            return "0";
        }

        // 处理负数
        boolean minus = false;
        if (m < 0) {
            m = -m;
            minus = true;
        }

        StringBuilder sb = new StringBuilder();
        // 要转换 16 进值就会存在 ABCDEF
        String hex = "0123456789ABCDEF";
        while (m > 0) {
            sb.append(hex.charAt(m % n));
            m = m / n;
        }

        return minus ? "-" + sb.reverse().toString() : sb.reverse().toString();
    }

    public static void main(String[] args) {
        HexadecimalConversionNiu conversionNiu = new HexadecimalConversionNiu();
        String solve = conversionNiu.solve(7, 2);
        System.out.println(solve);
    }
}
