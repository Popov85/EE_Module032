package com.goit.g2popov.ee032.quiz;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Andrey on 05.09.2016.
 */
public class TestQuestion10 {

        public static void main(String[] args) {
                new TestQuestion10().test();
        }

        public void test() {
                ScheduledExecutorService executorService =
                        Executors.newSingleThreadScheduledExecutor();
                System.out.println("Task scheduled");
                executorService.scheduleAtFixedRate(() ->
                        System.out.println("Task executed"), 1, 1, TimeUnit.SECONDS);
        }
}
