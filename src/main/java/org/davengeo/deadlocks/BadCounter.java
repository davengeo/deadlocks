package org.davengeo.deadlocks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
public class BadCounter implements CommandLineRunner {

    private static Logger LOG = LoggerFactory.getLogger(BadCounter.class);

    @Override
    public void run(String... strings) throws Exception {
        LOG.info("here we are");
    }
}
