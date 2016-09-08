package com.goit.g2popov.ee032.hw032;

import java.util.Random;

/**
 * Random array generator
 */
public class Generator {

        private final Random rnd = new Random();

        private int[] array;

        public int[] generate(int n) {
                int[] integers = new int[n];
                for (int i = 0; i < n; i++) {
                        integers[i] = rnd.nextInt(10);
                }
                this.array = integers.clone();
                return integers;
        }

        @Override
        public String toString() {
                String s = "";
                for (int i = 0; i < 10; i++) {
                       s+=array[i]+";";
                }
                return s;
        }
}
