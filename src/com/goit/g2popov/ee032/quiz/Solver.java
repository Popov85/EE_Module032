package com.goit.g2popov.ee032.quiz;

import com.goit.g2popov.ee032.hw032.SquareSum;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by Andrey on 06.09.2016.
 */
public class Solver implements SquareSum {
        private int N;
        private final int[] data = {56,32,96,58,45,75,63,52};
        private final CyclicBarrier barrier;

        boolean done = false;

        public static void main(String[] args) {
                int data[] = {56,32,96,58,45,75,63,52};
                new Solver().getSquareSum(data, 4);
        }

        @Override
        public long getSquareSum(int[] values, int numberOfThreads) {
                return 0;
        }

        class Worker implements Runnable {
                int label;
                int quantity;

                Worker(int label, int quantity) {
                        this.label = label;
                        this.quantity = quantity;
                }

                public void run() {
                        while (!done) {
                                processPartOfArray(label, quantity);
                                try {
                                        barrier.await();
                                } catch (InterruptedException ex) {
                                        return;
                                } catch (BrokenBarrierException ex) {
                                        return;
                                }
                        }
                }
        }

        public Solver() {
                N = 4;
                barrier = new CyclicBarrier(N, new Runnable() {
                                public void run() {
                                        sumSeparateResults();
                                }
                        });
                for (int i = 0; i < N; ++i) {

                        //new Thread(new Worker(i)).start();
                }
                waitUntilDone();
        }

        private void sumSeparateResults() {

        }

        private void processPartOfArray(int label, int quantity) {
                long sum = 0;
                for (int i = label; i < (label+quantity); i++) {
                        sum +=data[i];
                }
        }

        public synchronized void waitUntilDone() {

                while (!done) {

                        try {
                                this.wait();

                        } catch (InterruptedException ignore) {
                                // log.debug("interrupted: " + ignore.getMessage());
                        }
                }
        }
}
