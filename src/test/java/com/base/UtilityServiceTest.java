package com.base;

import org.junit.jupiter.api.Test;

import java.security.SecureRandom;
import java.util.Base64;

public class UtilityServiceTest {

    @Test
    public void testGenerateFibonacci() {

        int length = 10; // 可以修改这里的长度来生成不同长度的斐波那契数列
        int[] fibonacciSequence = FibonacciService.generateFibonacci(length);

        for (int num : fibonacciSequence) {
            System.out.print(num + " ");
        }
    }

    @Test
    public void sayHello() {
        System.out.println("Hello World!");
    }

    @Test
    public void getSecret() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] keyBytes = new byte[32]; // 256 bits
        secureRandom.nextBytes(keyBytes);
        String secret = Base64.getEncoder().encodeToString(keyBytes);
        System.out.println("Generated JWT Secret: " + secret);
    }
}
