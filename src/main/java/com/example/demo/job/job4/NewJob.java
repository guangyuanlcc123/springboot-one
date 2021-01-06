package com.example.demo.job.job4;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.concurrent.TimeUnit;


/**
 * 四、Quartz----Scheduler（任务的执行需自行调用方法执行）
 */
@Slf4j
public class NewJob implements Job {

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        log.info("TestJob执行啦！");
        sleep(4);
        log.info("TestJob结束了！");
    }

    private void sleep(long time) {
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
