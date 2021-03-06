package com.fererlab.java8;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class UtilLogger {

    public static Logger getLogger(String name) {
        Logger logger = Logger.getLogger(name);
        // do not use parent handlers
        logger.setUseParentHandlers(false);
        // if there are any, remove them
        for (Handler handler : logger.getHandlers()) {
            System.out.println("removing already registered handler: " + handler);
            logger.removeHandler(handler);
        }
        // add new console handler
        System.out.println("adding a ConsoleHandler with LogFormatter");
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new UtilLogFormatter());
        logger.addHandler(consoleHandler);
        return logger;
    }

}
