package com.sl.coljourney.algorithm;

/**
 * 1
 * 2	15
 * 3	16	14
 * 4	17	21	13
 * 5	18	19	20	12
 * 6	7	8	9	10	11
 * <p>
 * 1
 * 2	33
 * 3	34	32
 * 4	35	57	31
 * 5	36	58	56	30
 * 6	37	59	72	55	29
 * 7	38	60	73	71	54	28
 * 8	39	61	74	78	70	53	27
 * 9	40	62	75	76	77	69	52	26
 * 10	41	63	64	65	66	67	68	51	25
 * 11	42	43	44	45	46	47	48	49	50	24
 * 12	13	14	15	16	17	18	19	20	21	22	23
 * <p>
 * <p>
 * **厂的手写代码题，用代码写出能生成以上规律的程序
 *
 * @author L
 */

public class TrigonNumTest {

    public static void main(String[] args) {

        computePlanOne(6);
        computePlanOne(12);
    }

    /**
     * 方案一，全部基于递归
     * 该方案需要了解到的一个规律 三角形的斜边顶点的值 = 3 * (length - 1) + 外层三角顶点值
     * 例如：33 = 3 * (12 - 1)
     * 57 = 3 * (12 - 3 - 1) + 33
     * 72 = 3 * (12 - 3 - 3 - 1) + 57
     */
    private static void computePlanOne(int length) {
        for (int i = 1; i <= length; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(lineNumber(length, i, j, 0) + "\t");
            }
            System.out.println();
        }
    }

    private static int lineNumber(int length, int x, int y, int gap) {
        // 计算竖线，也就是纵坐标都为1的情况
        if (y == 1) {
            return x + gap;
        }
        // 计算横线，也就是横坐标与三角形高度一样的情况
        if (x == length) {
            return x + y - 1 + gap;
        }
        // 计算三角形的斜边，也就是三角形的两个直角边都一样的情况
        if (x == y) {
            return lineNumber(length, x + 1, y + 1, gap) + 1;
        }
        // 从新计算内三角形，每次计算高度会减少3，横坐标会减少2，纵坐标会减少1
        // debug信息：
        // 6 - 3 = 3, 5 - 2 = 3 , 2 - 1 = 1, 15
        // 6 - 3 = 3, 5 - 2 = 3 , 3 - 1 = 2, 15
        //     x == length , 3 + 2 - 1 + 15
        // 6 - 3 = 3, 5 - 2 = 3 , 4 - 1 = 3, 15
        //     3 + 3 - 1 + 15
        if (length - 3 > 0) {
            return lineNumber(length - 3, x - 2, y - 1, 3 * (length - 1) + gap);
        }
        return 0;
    }

    /**
     * 方案二，基于二维数组 + 递归实现
     * 此方案略有瑕疵（嗯... 就是有bug）
     */
    private static void computePlanTwo(int totalLen) {
        int[][] arr = new int[totalLen][totalLen];
        findNum(arr, 0, 0, totalLen, totalLen, 1);
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println();
        }
    }

    private static void findNum(int[][] arr, int x, int y, int totalLen, int len, int value) {
        if (len < 1) {
            return;
        }
        for (int i = x; i < x + len; i++) {
            arr[i][y] = value;
            value += 1;
        }
        for (int j = y + 1; j < y + len && j < len; j++) {
            arr[x + len - 1][j] = value;
            value += 1;
        }
        for (int i = x + len - 1, j = y + len - 1; i > x && j > y; i--, j--) {
            if (totalLen != len && i == j) {
                continue;
            }
            if (i != totalLen - 1 && j != totalLen - 1) {
                arr[i][j] = value;
                value += 1;
            }
        }
        findNum(arr, x + 2, y + 1, totalLen, len - 3, value);
    }

}
