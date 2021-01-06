package com.example.demo.job.job1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 一、ScheduledThreadPool实现定时任务（任务的执行需自行调用方法执行）
 */
@Service
@Slf4j
public class ScheduledThreadPoolService {
    // 参数代表可以同时执行的定时任务个数
    private ScheduledExecutorService service = Executors.newScheduledThreadPool(3);

    /**
     * schedule：延时2秒执行一次任务
     */
    public void task0() {

        service.schedule(() -> {
            log.info("task0-start-" + new Date( System.currentTimeMillis()));
            sleep(2);
            log.info("task0-end-" + new Date( System.currentTimeMillis()));
        }, 2, TimeUnit.SECONDS);
    }

    /**
     * scheduleAtFixedRate：2秒后，每间隔4秒执行一次任务
     * 注意，如果任务的执行时间（例如6秒）大于间隔时间，则会等待任务执行结束后直接开始下次任务
     */
    public void task1() {
        service.scheduleAtFixedRate(() -> {
            log.info("task1-start-" + new Date( System.currentTimeMillis()));
            sleep(2);
            log.info("task1-end-" + new Date( System.currentTimeMillis()));
        }, 2, 4, TimeUnit.SECONDS);
    }

    /**
     * scheduleWithFixedDelay：2秒后，每次延时4秒执行一次任务
     * 注意，这里是等待上次任务执行结束后，再延时固定时间后开始下次任务
     */
    public void task2() {
        service.scheduleWithFixedDelay(() -> {
            log.info("task2-start-" + new Date( System.currentTimeMillis()));
            sleep(2);
            log.info("task2-end-" + new Date( System.currentTimeMillis()));
        }, 2, 4, TimeUnit.SECONDS);
    }

    private void sleep(long time) {
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        try {
            ScheduledThreadPoolService scheduledThreadPoolService = new ScheduledThreadPoolService();
            scheduledThreadPoolService.task0();
            scheduledThreadPoolService.task1();
            scheduledThreadPoolService.task2();
        }catch (Exception e){

        }

    }
}
