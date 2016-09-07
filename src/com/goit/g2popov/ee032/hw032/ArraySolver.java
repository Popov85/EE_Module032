package com.goit.g2popov.ee032.hw032;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Andrey on 07.09.2016.
 */
public class ArraySolver implements SquareSum {

        public static void main(String[] args) throws ExecutionException, InterruptedException {
                int[] array = {1,2,3,4,5,6};
                long amount = new ArraySolver().getSquareSum(array, 2);
                System.out.println("Result = "+amount);
        }

        @Override
        public long getSquareSum(int[] values, int numberOfThreads) throws ExecutionException, InterruptedException {

                int amount = 0;

                // Prepare to execute and store the Futures
                int threadNum = 2;
                ExecutorService executor = Executors.newFixedThreadPool(threadNum);
                List<FutureTask<Integer>> taskList = new ArrayList<FutureTask<Integer>>();

                // Start thread for the first half of the array
                FutureTask<Integer> futureTask_1 = new FutureTask<Integer>(new Callable<Integer>() {
                        @Override
                        public Integer call() {
                                return ArraySolver.powPart(values, 0, 3);
                        }
                });
                taskList.add(futureTask_1);
                executor.execute(futureTask_1);

                // Start thread for the second half of the numbers
                FutureTask<Integer> futureTask_2 = new FutureTask<Integer>(new Callable<Integer>() {
                        @Override
                        public Integer call() {
                                return ArraySolver.powPart(values, 0, 3);
                        }
                });
                taskList.add(futureTask_2);
                executor.execute(futureTask_2);

                // Wait until all results are available and combine them at the same time
                for (int j = 0; j < threadNum; j++) {
                        FutureTask<Integer> futureTask = taskList.get(j);
                        amount += futureTask.get();
                }
                executor.shutdown();

                return amount;
        }

        private static int powPart(int[] data, int position, int quantity) {
                int sum = 0;
                for (int i = position; i < (position + quantity); i++) {
                      sum+=Math.pow(data[i],2);
                }
                return sum;
        }
}
