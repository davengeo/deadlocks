package org.davengeo.deadlocks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Order(1)
@Component
public class BadCounter implements CommandLineRunner {

    private static Logger LOG = LoggerFactory.getLogger(BadCounter.class);

    @Autowired
    TaskExecutor executor;

    private RedGreenFlag flag = new RedGreenFlag();

    Runnable first = () -> {
        flag.waitForGreen();
        LOG.info("this is another thread");
    };

    Runnable second = () -> {
        flag.waitForGreen();
        LOG.info("and this is other thread");
    };

    @Override
    public void run(String... strings) throws Exception {
        LOG.info("here we are");
        List<Runnable> workers = newArrayList(first, second);

        workers.forEach(worker -> {
            executor.execute(worker);
        });
        Thread.sleep(1000l);
        flag.green();
    }
}
