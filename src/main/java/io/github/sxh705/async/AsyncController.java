package io.github.sxh705.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;

@Slf4j
@RestController
@RequestMapping("/async")
public class AsyncController {
    @Autowired
    AsyncService asyncService;
    // 
    @GetMapping("/vt")
    public String vt() {
        long start = System.currentTimeMillis();
        int n = 100;
        CountDownLatch countDownLatch = new CountDownLatch(n);
        for (int i = 0; i < n; i++) {
            asyncService.doSomething(countDownLatch);
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        long end = System.currentTimeMillis();
        log.info("耗时：" + (end - start) + "ms");
        return "OK";
    }

}