package org.circuitsoft.gpjvm;

import java.util.stream.IntStream;

public class GPUOffloadTest {
    public static void main(String[] args) {
        long initStart = System.currentTimeMillis();
        int length = 100000;
        int[] a = new int[length];
        int[] b = new int[length];
        int[] result = new int[length];

        IntStream.range(0, length).parallel().forEach(p -> {
            a[p] = 1;
            b[p] = 2;
        });
        long initEnd = System.currentTimeMillis();

        long runStart = System.currentTimeMillis();
        IntStream.range(0, length).parallel().forEach(p -> result[p] = a[p] + b[p]);
        long runEnd = System.currentTimeMillis();

        long termStart = System.currentTimeMillis();
        IntStream.range(0, length).forEach(p -> System.out.println(result[p] + ", " + a[p] + ", " + b[p]));
        long termEnd = System.currentTimeMillis();

        System.out.println("Init time (ms): " + (initEnd - initStart));
        System.out.println("Main run time (ms): " + (runEnd - runStart));
        System.out.println("Termination time (ms): " + (termEnd - termStart));
        System.out.println("Total time (ms): " + (termEnd - initStart));
    }
}
