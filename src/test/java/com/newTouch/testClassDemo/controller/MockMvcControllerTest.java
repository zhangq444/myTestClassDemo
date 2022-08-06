package com.newTouch.testClassDemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;

@SpringBootTest
@Slf4j
public class MockMvcControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void init(){
        mockMvc= MockMvcBuilders.webAppContextSetup(context).build();
        log.info("======================测试开始=======================");
    }

    @AfterEach
    public void after(){
        log.info("======================测试结束=======================");
    }

    @Test
    public void test01() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/mockMvcTest/testGetRequest1?id=10&name=zhangqiang")
                        .requestAttr("userId","EX-ZHANGQIAN033")
                ).andDo(result -> {
                    log.info("======response:{}",result.getResponse().getContentAsString(StandardCharsets.UTF_8));
                });
    }





}
