package com.fererlab.java8;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BranchPrediction {
    public static void main(String[] args) {
	BranchPrediction branchPrediction = new BranchPrediction();
	branchPrediction.calculate();
    }

    private void calculate() {
	// Generate data
	int arraySize = 32768;
	int data[] = new int[arraySize];

	Random rnd = new Random(0);
	for (int c = 0; c < arraySize; ++c)
	    data[c] = rnd.nextInt() % 256;

	List<Integer> integers = IntStream.of(data).boxed().collect(Collectors.toList());

	// Test
	long start = System.nanoTime();
	final int[] sum = {0};

	for (int i = 0; i < 50000; ++i) {
	    // Primary loop
	    integers.stream().filter(integer -> integer >= 128).forEach(integer -> sum[0] += integer);
	}

	System.out.println((System.nanoTime() - start) / 1000000000.0);
	System.out.println("functional sum = " + sum[0]);

	// Test
	start = System.nanoTime();
	sum[0] = 0;

	for (int i = 0; i < 50000; ++i) {
	    for (int c = 0; c < arraySize; ++c) {

		if (data[c] >= 128)
		    sum[0] += data[c];
	    }
	}

	System.out.println((System.nanoTime() - start) / 1000000000.0);
	System.out.println("unsorted sum = " + sum[0]);

	// !!! With this, the next loop runs faster
	Arrays.sort(data);

	// Test
	start = System.nanoTime();
	sum[0] = 0;

	for (int i = 0; i < 50000; ++i) {
	    for (int c = 0; c < arraySize; ++c) {

		if (data[c] >= 128)
		    sum[0] += data[c];
	    }
	}

	System.out.println((System.nanoTime() - start) / 1000000000.0);
	System.out.println("sorted sum = " + sum[0]);

    }


}