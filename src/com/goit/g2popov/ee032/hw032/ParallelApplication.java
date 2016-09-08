package com.goit.g2popov.ee032.hw032;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Andrey on 08.09.2016.
 */
public class ParallelApplication implements SquareSum {

        @Override
        public long getSquareSum(int[] values, int threadNum) throws ExecutionException, InterruptedException {
                long start = System.nanoTime();
                int amount = 0;
                ExecutorService executor = Executors.newFixedThreadPool(threadNum);
                List<FutureTask<Integer>> taskList = new ArrayList<FutureTask<Integer>>();
                int quantity = getQuantity(values.length, threadNum);
                FutureTask<Integer> futureTask[] = new FutureTask[threadNum];
                int counter = 0;
                for (int i = 0; i < threadNum - 1; i++) {

                        // Start a thread to process a part of the array
                        int fi = i;
                        futureTask[i] = new FutureTask<Integer>(new Callable<Integer>() {
                                @Override
                                public Integer call() {
                                        return ParallelApplication.powPart(values,
                                                fi*quantity, quantity);
                                }
                        });
                        taskList.add(futureTask[i]);
                        executor.execute(futureTask[i]);
                        counter++;
                }
                // Start a thread to process the last part of the array
                int fCounter = counter;
                FutureTask<Integer> lastTask = new FutureTask<Integer>(new Callable<Integer>() {
                        @Override
                        public Integer call() {
                                return ParallelApplication.powPart(values, fCounter*quantity,
                                        values.length-(fCounter*quantity));
                        }
                });
                taskList.add(lastTask);
                executor.execute(lastTask);

                // Wait until all results are available and combine them at the same time
                for (int j = 0; j < threadNum; j++) {
                        FutureTask<Integer> fTask = taskList.get(j);
                        amount += fTask.get();
                }
                executor.shutdown();
                long finish = System.nanoTime();
                System.out.println("Parallel app took: "+ (finish-start)/1000000+" ms");
                return amount;
        }

        private int getQuantity(int length, int threads) {
                return Math.round(length/threads);
        }

        private static int powPart(int[] data, int position, int quantity) {
                int sum = 0;
                for (int i = position; i < (position + quantity); i++) {
                        sum+=Math.pow(data[i],2);
                }
                return sum;
        }
}
