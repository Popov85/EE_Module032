package com.goit.g2popov.ee032.hw032;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Andrey on 07.09.2016.
 */
public class ArraySolver implements SquareSum {

        public static void main(String[] args) throws ExecutionException, InterruptedException {
                int[] array = {1,2,3,4,5,6,7};
                long amount = new ArraySolver().getSquareSum(array, 5);
                System.out.println("Result = "+amount);
        }

        @Override
        public long getSquareSum(int[] values, int threadNum) throws ExecutionException, InterruptedException {
                int amount = 0;
                ExecutorService executor = Executors.newFixedThreadPool(threadNum);
                List<FutureTask<Integer>> taskList = new ArrayList<FutureTask<Integer>>();
                int quantity = getQuantity(values.length, threadNum);
                //System.out.println(quantity);
                FutureTask<Integer> futureTask[] = new FutureTask[threadNum];
                int counter = 0;
                for (int i = 0; i < threadNum - 1; i++) {

                        // Start a thread to process a part of the array
                        int fi = i;
                        futureTask[i] = new FutureTask<Integer>(new Callable<Integer>() {
                                @Override
                                public Integer call() {
                                        return ArraySolver.powPart(values, fi*quantity, quantity);
                                }
                        });
                        taskList.add(futureTask[i]);
                        executor.execute(futureTask[i]);
                        counter++;
                }
                // Start a thread to process the last part of the array
                // Calculate position and quantity

                int fCounter = counter;
                FutureTask<Integer> lastTask = new FutureTask<Integer>(new Callable<Integer>() {
                        @Override
                        public Integer call() {
                                System.out.println("Last task: ["+fCounter*quantity+"; "+(values.length-fCounter*quantity)+"]");
                                return ArraySolver.powPart(values, fCounter*quantity, values.length-(fCounter*quantity));
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
