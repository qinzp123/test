package com.example.demo.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TestCron {

    @Scheduled(cron = "0 0/10 * * * ?")
    public void testCron1() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("任务1------------执行："+now);
    }

    @Scheduled(cron = "0 */10 * * * ?")
    public void testCron2() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("任务2执行："+now);
    }
}
