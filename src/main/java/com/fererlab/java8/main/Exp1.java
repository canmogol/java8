package com.fererlab.java8.main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

/**
 * Created by alicana on 11.12.2015.
 */
public class Exp1 {

    ForkJoinPool fjpool;

    public Exp1() {

	fjpool = new ForkJoinPool();

    }

    public static void main(String... args) {

	Exp1 exp1 = new Exp1();
	exp1.invokeThreads();
    }

    public void invokeThreads() {

	List<WorkerThread> workerThreads = new ArrayList<WorkerThread>() {{
	    add(new WorkerThread(0, 7500));
	    add(new WorkerThread(1, 1400));
	    add(new WorkerThread(2, 5000));
	}};

	System.out.println("---------- 0");
	List<Future<Boolean>> result = fjpool.invokeAll(workerThreads);

	System.out.println("---------- 1");
	for (Future<Boolean> booleanFuture : result) {
	    try {
		System.out.println("-- booleanFuture: " + booleanFuture.get());
	    } catch (InterruptedException | ExecutionException e) {
		e.printStackTrace();
	    }
	}
	System.out.println("---------- 2");
    }


    private class WorkerThread implements Callable<Boolean> {

	private final int id;

	private final int sleepTime;

	private WorkerThread(int id, int sleepTime) {
	    this.id = id;
	    this.sleepTime = sleepTime;
	}

	@Override
	public Boolean call() throws Exception {
	    try {
		System.out.println("begin id = " + id);
		Thread.sleep(sleepTime);
		System.out.println("end id = " + id);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	    return true;
	}
    }

}
