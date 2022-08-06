package com.newTouch.testClassDemo.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.newTouch.testClassDemo.enitity.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class RestTemplateControllerTest {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void test01() {
        Map<String,Object> param= Maps.newHashMap();
        param.put("userId","EX-ZHANGQIAN033");
        ResponseVo vo = restTemplate.getForObject("http://localhost:8081/restTemplateTest/testGetRequest1?id=10&name=zhangqiang", ResponseVo.class,param);
        log.info("======vo:{}", JSON.toJSONString(vo));
    }

    @Test
    public void test02(){
        Map<String,Object> param= Maps.newHashMap();
        param.put("userId","EX-ZHANGQIAN033");
        ResponseVo vo = restTemplate.getForObject("http://localhost:8081/restTemplateTest/testGetRequest2?id=10&name=zhangqiang", ResponseVo.class,param);
        log.info("======vo:{}", JSON.toJSONString(vo));
    }

    @Test
    public void test03(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("id", "11");
        map.add("name","zhangqiang");
        map.add("userId","EX-ZHANGQIAN033");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseVo vo = restTemplate.postForObject("http://localhost:8081/restTemplateTest/testPostRequest1",request, ResponseVo.class);
        log.info("======vo:{}", JSON.toJSONString(vo));
    }

    @Test
    public void test04(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, String> map= Maps.newHashMap();
        map.put("id", "11");
        map.put("name","zhangqiang");
        map.put("userId","EX-ZHANGQIAN033");

        HttpEntity<String> request = new HttpEntity<String>(JSON.toJSONString(map), headers);
        ResponseVo vo = restTemplate.postForObject("http://localhost:8081/restTemplateTest/testPostRequest2",request, ResponseVo.class);
        log.info("======vo:{}", JSON.toJSONString(vo));
    }

    @Test
    public void test05() throws Exception {
        FileSystemResource fileSystemResource = new FileSystemResource(new File("D:\\ideastudyspace\\testClassDemo\\src\\main\\resources\\file\\用户信息测试.xlsx"));
        // headers参数
        HttpHeaders requestHeaders = new HttpHeaders();
        // body体参数
        MultiValueMap<String, Object> requestBody = new LinkedMultiValueMap<>();
        // 设置header是文件上传
        requestHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
        // 参数设置文件
        requestBody.add("file", fileSystemResource);
        requestBody.add("id", "11");
        requestBody.add("name","zhangqiang");
        requestBody.add("userId","EX-ZHANGQIAN033");
        // 封装所有参数
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(requestBody, requestHeaders);

        ResponseVo vo = restTemplate.postForObject("http://localhost:8081/restTemplateTest/testUploadExcel",requestEntity, ResponseVo.class);
        log.info("======vo:{}", JSON.toJSONString(vo));
    }

    @Test
    public void Test06() throws Exception {
        // headers参数
        HttpHeaders requestHeaders = new HttpHeaders();
        // body体参数
        Map<String, String> requestBody= Maps.newHashMap();
        // 设置header是文件上传
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        // 参数设置文件
        requestBody.put("id", "11");
        requestBody.put("name","zhangqiang");
        requestBody.put("userId","EX-ZHANGQIAN033");
        // 封装所有参数
        HttpEntity<String> requestEntity = new HttpEntity<String>(JSON.toJSONString(requestBody), requestHeaders);

        byte[] bytes = restTemplate.postForObject("http://localhost:8081/restTemplateTest/testDownLoadExcel", requestEntity, byte[].class);
        FileUtils.writeByteArrayToFile(new File("D:\\2.xlsx"), bytes);
        log.info("======success");

    }

}
