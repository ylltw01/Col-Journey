package com.sl.coljourney.algorithm.sword;

/**
 * 斐波那契额数列
 *
 * @author L
 */
public class Fibonacci {

    /**
     * 基于递归
     */
    public int solution(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return solution(n - 1) + solution(n - 2);
    }

    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();
        System.out.println(fibonacci.solution(8));
    }


}
