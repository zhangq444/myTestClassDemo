package com.newTouch.testClassDemo.controller;

import com.alibaba.fastjson.JSON;
import com.newTouch.testClassDemo.enitity.QueryRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.io.File;
import java.nio.charset.StandardCharsets;

@SpringBootTest
@Slf4j
@AutoConfigureMockMvc
public class MockMvcControllerTest2 {

    @Resource
    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        log.info("======================测试开始=======================");
    }

    @AfterEach
    public void after() {
        log.info("======================测试结束=======================");
    }

    @Test
    public void test01() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/mockMvcTest/testGetRequest1?id=10&name=zhangqiang")
                .requestAttr("userId", "EX-ZHANGQIAN033")
        ).andDo(result -> {
            log.info("======response:{}", result.getResponse().getContentAsString(StandardCharsets.UTF_8));
        });
    }

    @Test
    public void test02() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/mockMvcTest/testGetRequest2")
                .requestAttr("userId", "EX-ZHANGQIAN033")
                .param("id", "12")
                .param("name", "zhangqiang")
        ).andDo(result -> {
            log.info("======response:{}", result.getResponse().getContentAsString(StandardCharsets.UTF_8));
        });
    }

    @Test
    public void test03() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/mockMvcTest/testPostRequest1")
                .requestAttr("userId", "EX-ZHANGQIAN033")
                .param("id", "12")
                .param("name", "zhangqiang")
        ).andDo(result -> {
            log.info("======response:{}", result.getResponse().getContentAsString(StandardCharsets.UTF_8));
        });
    }

    @Test
    public void test04() throws Exception {
        QueryRequest request = QueryRequest.builder().id("13").name("zhangqiang").build();
        mockMvc.perform(MockMvcRequestBuilders.post("/mockMvcTest/testPostRequest2")
                .requestAttr("userId", "EX-ZHANGQIAN033")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(request))
        ).andDo(result -> {
            log.info("======response:{}", result.getResponse().getContentAsString(StandardCharsets.UTF_8));
        });
    }

    @Test
    public void test05_1() throws Exception {
        byte[] bytes = FileUtils.readFileToByteArray(new File("D:\\ideastudyspace\\testClassDemo\\src\\main\\resources\\file\\用户信息测试.xlsx"));
        QueryRequest request = QueryRequest.builder().id("15").name("zhangqiang").build();
        mockMvc.perform(MockMvcRequestBuilders.multipart("/mockMvcTest/testUploadExcel")
                .file("file", bytes)
                .requestAttr("userId", "EX-ZHANGQIAN033")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(request))
        ).andDo(result -> {
            log.info("======response:{}", result.getResponse().getContentAsString(StandardCharsets.UTF_8));
        });
    }

    @Test
    public void test05_2() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", FileUtils.openInputStream(new File("D:\\ideastudyspace\\testClassDemo\\src\\main\\resources\\file\\用户信息测试.xlsx")));
        QueryRequest request = QueryRequest.builder().id("15").name("zhangqiang").build();
        mockMvc.perform(MockMvcRequestBuilders.multipart("/mockMvcTest/testUploadExcel")
                .file(file)
                .requestAttr("userId", "EX-ZHANGQIAN033")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(request))
        ).andDo(result -> {
            log.info("======response:{}", result.getResponse().getContentAsString(StandardCharsets.UTF_8));
        });
    }

    @Test
    public void test06() throws Exception {
        QueryRequest request = QueryRequest.builder().id("17").name("zhangqiang").build();
        mockMvc.perform(MockMvcRequestBuilders.post("/mockMvcTest/testDownLoadExcel")
                .requestAttr("userId", "EX-ZHANGQIAN033")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(request))
        ).andDo(result -> {
            byte[] bytes = result.getResponse().getContentAsByteArray();
            FileUtils.writeByteArrayToFile(new File("D:\\1.xlsx"), bytes);
            log.info("======response:{}", result.getResponse().getStatus());
        });
    }


}
