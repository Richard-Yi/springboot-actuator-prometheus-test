package com.ricstudio.springbootactuatorprometheustest.controller;

import com.ricstudio.springbootactuatorprometheustest.config.PrometheusCustomMonitor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author Richard_yyf
 * @version 1.0 2020/1/17
 */
@RestController
public class TestController {

    @Resource
    private PrometheusCustomMonitor monitor;

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

    @RequestMapping("/order")
    public String order(@RequestParam(defaultValue = "0") String flag) throws Exception {
        monitor.getOrderCount().increment();
        if ("1".equals(flag)) {
            throw new Exception("出错啦");
        }
        Random random = new Random();
        int amount = random.nextInt(100);
        monitor.getAmountSum().record(amount);
        return "下单成功, 金额: " + amount;
    }
}
