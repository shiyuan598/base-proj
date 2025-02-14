package com.base;

public class FibonacciService {

    // 生成指定长度的斐波那契数列
    public static int[] generateFibonacci(int n) {
        if (n <= 0) {
            return new int[0];
        }
        int[] fibonacci = new int[n];
        if (n >= 1) {
            fibonacci[0] = 0;
        }
        if (n >= 2) {
            fibonacci[1] = 1;
        }
        for (int i = 2; i < n; i++) {
            fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];
        }
        return fibonacci;
    }
}
