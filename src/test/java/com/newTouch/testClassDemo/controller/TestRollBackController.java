package com.newTouch.testClassDemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class TestRollBackController {

    @Test
    public void test01(){
        System.out.println("第一次提交");
        System.out.println("第二次提交");
    }



}
