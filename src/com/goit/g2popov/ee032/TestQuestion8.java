package com.goit.g2popov.ee032;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Andrey on 05.09.2016.
 */
public class TestQuestion8 {

        public static void main(String[] args) {
                new TestQuestion8().test();
        }

        public void test() {
                ExecutorService executorService = Executors.newSingleThreadExecutor();
                Future<String> f = executorService.submit((Callable<String>) () -> {
                throw new RuntimeException("Exception happened");
                });
                try {
                       System.out.println("result: " + f.get());
                } catch (Exception e) {

                }
                executorService.shutdown();
        }
}
