package org.davengeo.deadlocks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

public class RedGreenFlag {

    private static Logger LOG = LoggerFactory.getLogger(RedGreenFlag.class);

    private CountDownLatch flag = new CountDownLatch(1);

    public void waitForGreen() {
        try {
            flag.await();
        } catch (InterruptedException e) {
            LOG.error("Exception: {})", e);
        }
    }

    public void green() {
        flag.countDown();
    }

}
