package com.newTouch.testClassDemo.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.newTouch.testClassDemo.enitity.UserInfo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author grzha
 * @date 2022/3/5
 */
@Data
@Slf4j
public class DemoListener  extends AnalysisEventListener<UserInfo>{

    private List<UserInfo> userInfoList=new ArrayList<>();


        @Override
    public void invoke(UserInfo userInfo, AnalysisContext analysisContext) {
        userInfoList.add(userInfo);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
      log.info("======读取excel文件完成======");
    }
}
