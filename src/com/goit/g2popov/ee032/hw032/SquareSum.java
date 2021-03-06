package com.goit.g2popov.ee032.hw032;

import java.util.concurrent.ExecutionException;

/**
 * Используя Phaser и Executors реализовать класс, который бы считал сумму квадратов элементов массива параллельно в
 * заданном количестве потоков
 * Идея в том, чтобы разбить массив на равные части и найти сумму квадратов в каждом кусочке в отдельном потоке параллельно.
 * Используя Phaser, дождаться результатов всех вычислений и сложить их, получив конечный результат.
 */
public interface SquareSum {

        long getSquareSum(int[] values, int numberOfThreads) throws ExecutionException, InterruptedException;
}
