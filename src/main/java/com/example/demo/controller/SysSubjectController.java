package com.example.demo.controller;


import com.example.demo.entity.SysSubject;
import com.example.demo.job.job1.ScheduledThreadPoolService;
import com.example.demo.job.job4.newjob.QuartzInitServlet;
import com.example.demo.rabbit_mq.FanoutSender;
import com.example.demo.server.SysSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Scheduler;
import org.quartz.TriggerKey;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Controller
@Api("swaggerDemoController相关的api")
@RequestMapping("tk")
public class SysSubjectController {

    @Autowired
    private SysSubjectService sysSubjectService;

    @Autowired
    private FanoutSender fanoutSender;

    @Autowired
    private QuartzInitServlet quartzInitServlet;

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private ScheduledThreadPoolService scheduledThreadPoolService;

    @ApiOperation(value = "调用mq", notes = "根据mq类型调用不同的队列")
    @RequestMapping(value = "send",method = RequestMethod.GET)
    @ResponseBody
    public String send(HttpServletRequest request, @RequestParam(value = "num") Integer i) {
        if(i == 0){
            fanoutSender.send(i);
        }else if (i == 1){
            fanoutSender.send();
        }else if(i == 2){
            fanoutSender.send1();
        }else if(i == 3){
            fanoutSender.send2();
        }else if(i == 4){
            fanoutSender.send3();
        }
        log.info("_____________________________________________________________________________________________");
        log.info(request.getServletContext().getRealPath("video"));
        log.info("_____________________________________________________________________________________________");
        return "成功";
    }

    @RequestMapping(value = "/job2",method = RequestMethod.GET)
    @ResponseBody
    public String job2(){
        try {
            scheduledThreadPoolService.task1();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "index";
    }

    @RequestMapping(value = "/job",method = RequestMethod.GET)
    @ResponseBody
    public String job(){
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey("jy2Job", "jy2Group");
            if (scheduler.checkExists(triggerKey)) {
                CronTriggerImpl cronTrigger = new CronTriggerImpl();
                cronTrigger.setName("jy2Job");
                cronTrigger.setGroup("jy2Group");
                cronTrigger.setDescription("测试");
                cronTrigger.setCronExpression("0/2 * * * * ?");
                cronTrigger.setMisfireInstruction(-1);
                scheduler.rescheduleJob(triggerKey, cronTrigger);
                scheduler.resumeTrigger(triggerKey);
            } else {
                quartzInitServlet.test();
                quartzInitServlet.test1();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "index";
    }

    @RequestMapping(value = "test",method = RequestMethod.GET)
    public String test(){
        log.info("*****************************************************************");
        return "test";
    }

    @ApiOperation(value = "sysSubject", notes = "sysSubject")
    @RequestMapping(value = "sysSubject",method = RequestMethod.GET)
    @ResponseBody
    public SysSubject getSysSubject(@RequestParam("sysSubjectId") Integer sysSubjectId){
        try {
           return   sysSubjectService.getSysSubject(sysSubjectId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @ApiOperation(value = "page", notes = "page")
    @RequestMapping(value = "page",method = RequestMethod.GET)
    @ResponseBody
    public List<SysSubject> getSysSubjects(@RequestParam("pageSize") Integer pageSize, @RequestParam("page") Integer page) {
        try {
            return sysSubjectService.getSysSubjects(pageSize,page);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @ApiOperation(value = "status", notes = "status")
    @RequestMapping(value = "status",method = RequestMethod.GET)
    @ResponseBody
    public List<SysSubject> getSysSubjectsByStatus(@RequestParam("status") Integer status) {
        try {
            return sysSubjectService.getSysSubjectsByStatus(status);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @ApiOperation(value = "name", notes = "name")
    @RequestMapping(value = "name",method = RequestMethod.GET)
    @ResponseBody
    public List<SysSubject> getSysSubjectsByName(@RequestParam("name")String name) {
        try {
            return sysSubjectService.getSysSubjectsByName(name);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
