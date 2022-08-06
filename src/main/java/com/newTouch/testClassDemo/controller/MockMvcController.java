package com.newTouch.testClassDemo.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.newTouch.testClassDemo.enitity.UserInfo;
import com.newTouch.testClassDemo.listener.DemoListener;
import com.newTouch.testclassdemo.enitity.QueryRequest;
import com.newTouch.testclassdemo.enitity.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 这个是提供给mockmvc使用的controller
 *
 * @author grzha
 */
@Controller
@RequestMapping(path = "/mockMvcTest")
@Slf4j
public class MockMvcController {

    private static List<String> responseData = Lists.newArrayList("Java", "Study", "Demo", "MockMvc");

    /**
     * 测试get请求的控制器
     *
     * @param id
     * @param name
     * @param request
     * @return
     */
    @GetMapping(path = "/testGetRequest1")
    @ResponseBody
    public ResponseVo testGetRequest1(@RequestParam(value = "id", required = false) String id,
                                      @RequestParam(value = "name", required = false) String name,
                                      HttpServletRequest request) {
        log.info("======MockMvcController testGetRequest1 , id:{},name:{},userId:{}", id, name, request.getAttribute("userId"));
        return ResponseVo.success(responseData);
    }

    /**
     * 测试get请求的控制器2
     *
     * @param dto
     * @param request
     * @return
     */
    @GetMapping(path = "/testGetRequest2")
    @ResponseBody
    public ResponseVo testGetRequest2(@ModelAttribute QueryRequest dto, HttpServletRequest request) {
        log.info("======MockMvcController testGetRequest1 , dto:{},userId:{}", JSON.toJSONString(dto), request.getAttribute("userId"));
        return ResponseVo.success(responseData);
    }

    /**
     * 测试post请求的控制器1
     *
     * @param dto
     * @param request
     * @return
     */
    @PostMapping(path = "/testPostRequest1")
    @ResponseBody
    public ResponseVo testPostRequest1(@ModelAttribute QueryRequest dto, HttpServletRequest request) {
        log.info("======MockMvcController testPostRequest1 , dto:{},userId:{}", JSON.toJSONString(dto), request.getAttribute("userId"));
        return ResponseVo.success(responseData);
    }

    /**
     * 测试post请求的控制器2
     *
     * @param dto
     * @param request
     * @return
     */
    @PostMapping(path = "/testPostRequest2")
    @ResponseBody
    public ResponseVo testPostRequest2(@RequestBody QueryRequest dto, HttpServletRequest request) {
        log.info("======MockMvcController testPostRequest2 , dto:{},userId:{}", JSON.toJSONString(dto), request.getAttribute("userId"));
        return ResponseVo.success(responseData);
    }

    /**
     * 测试excel上传
     *
     * @param dto
     * @param request
     * @param file
     * @return
     */
    @PostMapping(path = "/testUploadExcel")
    @ResponseBody
    public ResponseVo testUploadExcel(@RequestBody QueryRequest dto, HttpServletRequest request, MultipartFile file) {
        log.info("======MockMvcController testUploadExcel , dto:{},userId:{},file:{}", JSON.toJSONString(dto), request.getAttribute("userId"), file);
        DemoListener demoListener = new DemoListener();
        try {
            EasyExcel.read(file.getInputStream(), UserInfo.class, demoListener).sheet().doRead();
        } catch (IOException e) {
            log.info("======读取excel文件出现异常");
        }
        List<UserInfo> userInfoList = demoListener.getUserInfoList();
        log.info("======userInfoList:{}", JSON.toJSONString(userInfoList));
        return ResponseVo.success(userInfoList);
    }

    /**
     * 测试excel的下载
     *
     * @param dto
     * @param request
     * @param response
     */
    @PostMapping(path = "/testDownLoadExcel")
    @ResponseBody
    public void testDownLoadExcel(@RequestBody QueryRequest dto, HttpServletRequest request, HttpServletResponse response) {
        log.info("======MockMvcController testDownLoadExcel , dto:{},userId:{}", JSON.toJSONString(dto), request.getAttribute("userId"));
        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            // 这里需要设置不关闭流
            EasyExcel.write(response.getOutputStream(), UserInfo.class).autoCloseStream(Boolean.TRUE).sheet("模板")
                    .doWrite(data());
        } catch (Exception e) {
            log.error("MockMvcController testDownLoadExcel ,error:{}", e);
        }
    }

    private List<UserInfo> data() {
        List<UserInfo> userInfoList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setId("" + i);
            userInfo.setName("钱七" + i);
            userInfo.setAge(i + 10 + "");
            userInfo.setDept("部门" + i);
            userInfo.setDate("2022-0" + i + "-05 08:05:03");
            userInfoList.add(userInfo);
        }
        return userInfoList;
    }


}
