package org.davengeo.deadlocks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Order(1)
@Component
public class BadCounter implements CommandLineRunner {

    private static Logger LOG = LoggerFactory.getLogger(BadCounter.class);

    @Autowired
    TaskExecutor executor;

    private RedGreenFlag flag = new RedGreenFlag();

    Counter counter = new Counter();

    @Override
    public void run(String... strings) throws Exception {

        IntStream.range(1, 4).forEach(i -> {
            executor.execute(() -> {

                flag.waitForGreen();

                IntStream.range(0, 100).forEach(j -> {
                    counter.increment();
                });
                LOG.info("counter:{}", counter.getCounter());
            });
        });

        flag.green();
        Thread.sleep(500l);

        LOG.info("final counter:{}", counter.getCounter());
        LOG.info("but should be:{}", 300);
    }

    class Counter {
        private int counter = 0;

        public void increment() {
            counter++;
        }

        public int getCounter() {
            return counter;
        }
    }
}
