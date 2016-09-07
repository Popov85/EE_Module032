package com.goit.g2popov.ee032.quiz;

import java.util.Random;
import java.util.concurrent.Exchanger;
import java.util.stream.IntStream;

/**
 * Created by Andrey on 05.09.2016.
 */
public class TestQuestion4 {

        public static void main(String[] args) {
                new TestQuestion4().test();
        }

        public void test() {
        Exchanger<String> exchanger = new Exchanger<> ();
        Random random = new Random();
        IntStream.range(0,2).forEach((i) -> new Thread(() -> {
                try {
                        Thread.sleep(random.nextInt(1000));
                        String name = Thread.currentThread().getName();
                        System.out.println(name + " ready to exchange");
                        System.out.println(name + " < ­ > " + exchanger.exchange(name));
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }
        }));
        }
}

