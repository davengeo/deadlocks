package org.davengeo.deadlocks;

import java.util.Random;

public class Philosopher {
    private Chopstick left, right;

    private Random random;

    public Philosopher(Chopstick left, Chopstick right) {
        this.left = left;
        this.right = right;
        this.random = new Random();
    }

    public void run() throws InterruptedException {
        while(true) {
            Thread.sleep(random.nextInt(1000));
            synchronized (left) {
                synchronized (right) {
                    Thread.sleep(random.nextInt(1000));
                }
            }
        }
    }
}
