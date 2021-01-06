package com.example.demo.job.job2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 二、@Scheduled：Spring框架的定时任务实现（任务的执行由系统自动执行）
 */
@Service
@Slf4j
public class ScheduleService {

    public static final String time = "0 40/1 11-12 2 * ?";

    /**
     * fixedRate：每间隔2秒执行一次任务
     * 注意，默认情况下定时任务是在同一线程同步执行的，如果任务的执行时间（例如6秒）大于间隔时间，则会等待任务执行结束后直接开始下次任务
     */
    @Scheduled(fixedRate = 200000)
    @Async
    public void task0() {
        log.info("task0-start");
        sleep(3);
        log.info("task0-end");
    }

    /**
     * fixedDelay：每次延时2秒执行一次任务
     * 注意，这里是等待上次任务执行结束后，再延时固定时间后开始下次任务
     */
    @Scheduled(fixedDelay = 200000)
    @Async
    public void task1() {
        log.info("task1-start");
        sleep(3);
        log.info("task1-end");
    }

    /**
     * initialDelay：首次任务启动的延时时间
     */
    @Scheduled(initialDelay = 2000, fixedDelay = 300000)
    @Async
    public void task2() {
        log.info("task2-start");
        sleep(3);
        log.info("task2-end");
    }


    /**
     *  cron属性对应时间表达式的定义规则：
     *   1、按顺序依次是：秒、分、时、日、月、周，中间用空格间隔
     *   2、月、周可以用数字或英文单词的前三个字母表示
     *   3、日和周可能会冲突，因此两个可以有一个配置为?
     *   4、常用通配符的含义：
     *      * 表示任意值，例如在秒字段上设置*，表示每秒都触发
     *      ? 表示不指定值，只能出现在日或者周的位置，用于处理日和周可能存在的冲突，
     *      例如2020年8月15是周六，如果又在周的位置上指定为周一，那就会产生冲突到导致定时任务失效。如果我们不关心日或者周的时候，也可以将其设置为?
     *      - 表示时间区间，例如在秒上设置1-3，表示第1、2、3秒都会触发
     *      / 表示时间间隔，例如在秒上设置2/4，表示从第2秒开始每间隔4秒触发一次
     *      , 表示列举多个值，例如MON,WED,FRI表示周一、周三、周五触发
     */
//  几个表达式的例子：
//      0 0 9 * * ? （每天早上9点触发）
//      0 */30 * * * ?（每30分钟触发一次）
//      0 30 18 ？ * MON-FRI（每周一到周五的18:30分触发）
//      0 10 12 1,15 * ?（每月1号、15号的12:10触发）
//      0 */10 7-8 1,15 * ?（每月1号、15号早上7点到8点每10分钟触发一次）
    @Scheduled(cron = ScheduleService.time)
    @Async
    public void task3() {
        log.info("task3-start");
        sleep(3);
        log.info("task3-end");
    }

    private void sleep(long time) {
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
