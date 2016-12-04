package com.fererlab.java8.main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by alicana on 11.12.2015.
 */
public class Exp1 {

    public static void main(String... args) {

        Exp1 exp1 = new Exp1();
        exp1.invokeThreads();
    }

    ForkJoinPool fjpool;

    public Exp1() {

        fjpool = new ForkJoinPool();

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
