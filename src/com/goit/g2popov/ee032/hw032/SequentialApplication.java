package com.goit.g2popov.ee032.hw032;

import java.util.concurrent.ExecutionException;

/**
 * Created by Andrey on 08.09.2016.
 */
public class SequentialApplication implements SquareSum {

        @Override
        public long getSquareSum(int[] values, int numberOfThreads) throws ExecutionException, InterruptedException {
                long start = System.nanoTime();
                long sum = 0;
                for (int i = 0; i < values.length; i++) {
                        sum+=Math.pow(values[i],2);
                }
                long finish = System.nanoTime();
                System.out.println("Sequential app took: "+ (finish-start)/1000000+" ms");
                return sum;
        }
}
