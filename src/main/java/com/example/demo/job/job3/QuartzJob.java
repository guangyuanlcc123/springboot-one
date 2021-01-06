package com.example.demo.job.job3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 三、Quartz（任务的执行由系统自动执行）
 * 第一种：直接定义任务类，并注册到Spring IoC容器中
 */
@Service
@Slf4j
public class QuartzJob {

    public void hello(){
        log.info("job0-start");
        sleep(4);
        log.info("job0-end");
    }

    private void sleep(long time) {
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
