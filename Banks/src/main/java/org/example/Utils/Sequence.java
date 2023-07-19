package org.example.Utils;

public class Sequence {
    private static long sequence = 0;

    public static long next() {
        return sequence++;
    }
}
