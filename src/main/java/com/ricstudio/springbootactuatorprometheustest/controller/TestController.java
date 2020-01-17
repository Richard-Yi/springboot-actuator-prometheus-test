package com.ricstudio.springbootactuatorprometheustest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author Richard_yyf
 * @version 1.0 2020/1/17
 */
@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "hello, world";
    }

    @GetMapping("/slow")
    public String slow() throws InterruptedException {
        Random random = new Random();
        int delay = random.nextInt(10);
        TimeUnit.SECONDS.sleep(delay);
        return "sleep time: " + delay;
    }
}
