package com.fererlab.java8;


import org.junit.runner.Description;
import org.junit.runner.notification.RunListener;

import java.util.logging.Logger;

public class LogResultListener extends RunListener {

    private Logger logger = UtilLogger.getLogger(LogResultListener.class.getName());

    @Override
    public void testStarted(Description description) throws Exception {
        logger.info("" + description.getTestClass().getSimpleName() + " :: " + description.getMethodName());
        super.testStarted(description);
    }

    @Override
    public void testFinished(Description description) throws Exception {
        logger.info("" + description.getTestClass().getSimpleName() + " :: " + description.getMethodName());
        super.testFinished(description);
    }

}
