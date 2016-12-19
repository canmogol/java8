package com.fererlab.java8.main;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

public class FutureMain {

    public static void main(String[] args) {
	FutureMain futureMain = new FutureMain();
	futureMain.test();
    }

    private void test() {
	Frame snapshotFrame = null;
	try {
	    snapshotFrame = this.snapshot();
	    // Alert ! null
	    System.out.println("snapshotFrame = " + snapshotFrame);
	} catch (ExecutionException | InterruptedException e) {
	    e.printStackTrace();
	}
    }

    private Frame snapshot() throws ExecutionException, InterruptedException {
	ExecutorService executor = Executors.newFixedThreadPool(1);
	Future<Frame> frameFuture = executor.submit(new Callable<Frame>() {

	    private boolean tryToCapture = true;

	    @Override
	    public Frame call() throws Exception {
		new Timer().schedule(new TimerTask() {
		    @Override
		    public void run() {
			tryToCapture = false;
		    }
		}, 2000);

		Frame frame = null;
		while (tryToCapture) {
		    // query frame from somewhere else
		    frame = queryFrame();
		}
		return frame;
	    }
	});
	return frameFuture.get();
    }

    private Frame queryFrame() {
	return new Frame();
    }

}
