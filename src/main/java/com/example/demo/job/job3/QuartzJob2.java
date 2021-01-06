package com.example.demo.job.job3;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.concurrent.TimeUnit;

/**
 * 三、Quartz（任务的执行由系统自动执行）
 * 第二种：继承QuartzJobBean，重写executeInternal方法，这种方式可以接受JobDetail传递的参数：
 */
@Slf4j
public class QuartzJob2 extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("job1-start");
        sleep(4);
        // 获取参数
        JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
        String date = jobDataMap.getString("date");
        log.info("参数：" + date);
        log.info("job1-end");
    }

    private void sleep(long time) {
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
