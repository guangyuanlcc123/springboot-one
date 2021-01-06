package com.example.demo.job.job3.Quartz;

import com.example.demo.job.job3.QuartzJob2;
import org.quartz.JobDataMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.*;

/**
 * 三、Quartz（任务的执行由系统自动执行）
 */
@Configuration
public class QuartzConfig {

    /*QuartzJob方法*/
    //2、配置JobDetail：JobDetail使用MethodInvokingJobDetailFactoryBean配置，把JobDetail和QuartzJob所定义的任务关联起来
    @Bean
    public MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean() {
        MethodInvokingJobDetailFactoryBean bean = new MethodInvokingJobDetailFactoryBean();
        // 指定任务类在IoC容器中的Bean名称
        bean.setTargetBeanName("quartzJob");
        // 指定要执行的方法名称
        bean.setTargetMethod("hello");
        return bean;
    }
    //3、Trigger配置：用SimpleTriggerFactoryBean配置的触发器关联MethodInvokingJobDetailFactoryBean配置的JobDetail
    @Bean
    public SimpleTriggerFactoryBean simpleTriggerFactoryBean() {
        SimpleTriggerFactoryBean bean = new SimpleTriggerFactoryBean();
        bean.setRepeatCount(1); //设置任务执行一次后执行的次数（总执行次数 = count + 1）
        bean.setRepeatInterval(2000);
        // 关联JobDetail
        bean.setJobDetail(methodInvokingJobDetailFactoryBean().getObject());
        return bean;
    }
    /*QuartzJob方法结束*/



    /*QuartzJob2方法*/
    //2、配置JobDetail：JobDetail使用JobDetailFactoryBean配置，把JobDetail和QuartzJob2关联起来，同时传递参数
    @Bean
    public JobDetailFactoryBean jobDetailFactoryBean() {
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        // 指定任务类名称
        bean.setJobClass(QuartzJob2.class);
        // 准备参数
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("date", "2020-12-02");
        // 传递参数
        bean.setJobDataMap(jobDataMap);
        return bean;
    }
    //3、Trigger配置：用CronTriggerFactoryBean配置的触发器关联JobDetailFactoryBean配置的JobDetail
    @Bean
    public CronTriggerFactoryBean cronTriggerFactoryBean() {
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setCronExpression("0 */1 11-12 2 * ?");
        // 关联JobDetail
        bean.setJobDetail(jobDetailFactoryBean().getObject());
        return bean;
    }
    /*QuartzJob方法结束*/



    //4、配置Scheduler：通过SchedulerFactoryBean来配置Scheduler，来注册Trigger
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        // 注册两个Trigger
        bean.setTriggers(simpleTriggerFactoryBean().getObject(), cronTriggerFactoryBean().getObject());
        return bean;
    }
}
