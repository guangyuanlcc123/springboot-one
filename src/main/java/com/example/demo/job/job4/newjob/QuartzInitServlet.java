package com.example.demo.job.job4.newjob;

import com.example.demo.job.job4.NewJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServlet;
import java.util.Date;

//loadOnStartup表示：网站启动的时候自动初始化QuartzInitServlet（即init被调用）

/**
 * 四、Quartz----Scheduler（任务的执行需自行调用方法执行）
 */
@Service
@Slf4j
public class QuartzInitServlet extends HttpServlet {

    @Autowired
    Scheduler scheduler;

    public void test() {
        try {

            //1.创建Scheduler的工厂
            SchedulerFactory sf = new StdSchedulerFactory();
            //2.从工厂中获取调度器实例
            if(scheduler == null){
                scheduler = sf.getScheduler();
            }
//            Scheduler scheduler = sf.getScheduler();

            // 准备参数
            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put("data", "jy1Job");
            //3.创建JobDetail
            JobDetail jb1 = JobBuilder.newJob(NewJob.class) // Show 为一个job,是要执行的一个任务。
                    .withDescription("这是我的测试定时任务。") //job的描述
                    .withIdentity("jy1Job", "jy2Group") //job 的name和group
                    .setJobData(jobDataMap)
                    .build();
            //任务运行的时间，SimpleSchedle类型触发器有效
            long time = System.currentTimeMillis() + 3 * 1000L; // 3秒后启动任务
            Date statTime = new Date(time);

            //4.创建Trigger
            //使用SimpleScheduleBuilder或者CronScheduleBuilder
            Trigger t = TriggerBuilder.newTrigger()
                    .withDescription("")
                    .withIdentity("jy1Job", "jy2Group") //job 的name和group
                    //.withSchedule(SimpleScheduleBuilder.simpleSchedule())
                    .startAt(statTime)  //默认当前时间启动 ,也可以写为：.startNow();
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?")) //两秒执行一次
                    .build();


            //5.注册任务和定时器
            scheduler.scheduleJob(jb1, t);

            //6.启动 调度器
            scheduler.start();
        } catch (Exception e) {
            log.info("定时任务出现异常 ： " + e);
        }
    }

    public void test1(){
        try {
            //1.创建Scheduler的工厂
            SchedulerFactory sf = new StdSchedulerFactory();
            //2.从工厂中获取调度器实例
            if(scheduler == null){
                scheduler = sf.getScheduler();
            }

            // 准备参数
            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put("data", "jy2Job");
            //3.创建JobDetail
            JobDetail jb2 = JobBuilder.newJob(NewJob.class) // Show 为一个job,是要执行的一个任务。
                    .withDescription("这是我的测试定时任务。") //job的描述
                    .withIdentity("jy2Job", "jy2Group") //job 的name和group
                    .setJobData(jobDataMap)
                    .build();


            //4.创建Trigger
            //使用SimpleScheduleBuilder或者CronScheduleBuilder
            CronTriggerImpl cronTrigger = new CronTriggerImpl();
            cronTrigger.setName("jy2Job");
            cronTrigger.setGroup("jy2Group");
            cronTrigger.setDescription("测试");
            cronTrigger.setCronExpression("0/2 * * * * ?");
            cronTrigger.setMisfireInstruction(-1);

            //5.注册任务和定时器
            scheduler.scheduleJob(jb2, cronTrigger);

            //6.启动 调度器
            scheduler.start();
        } catch (Exception e) {
            log.info("定时任务出现异常 ： " + e);
        }
    }

    public static void main(String[] args) {
        QuartzInitServlet quartzInitServlet = new QuartzInitServlet();
        quartzInitServlet.test();
        quartzInitServlet.test1();
    }
}
