package com.goit.g2popov.ee032.hw032;

import java.util.concurrent.ExecutionException;

/**
 * Created by Andrey on 08.09.2016.
 */
public class App {

        private final static int ARRAY_SIZE = 1000000;

        private final static int THREADS = 4;

        public static void main(String[] args) {
                Generator generator = new Generator();
                int[] array = generator.generate(ARRAY_SIZE);

                System.out.println("ARRAY_SIZE: "+ARRAY_SIZE);
                System.out.println("THREADS: "+THREADS);

                try {
                        System.out.println("Sequential result: "+new SequentialApplication().getSquareSum(array, 1));
                        System.out.println("Parallel result: "+new ParallelApplication().getSquareSum(array, THREADS));

                } catch (ExecutionException e) {
                        e.printStackTrace();
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }

        }
}
