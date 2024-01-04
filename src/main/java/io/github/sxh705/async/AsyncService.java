package io.github.sxh705.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Service
public class AsyncService {
    @Async
    public void doSomething(CountDownLatch countDownLatch) {
        // log.info("Thread.currentThread(): {}", currentThread());
        try {
            Thread.sleep(50);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        countDownLatch.countDown();
    }
}
