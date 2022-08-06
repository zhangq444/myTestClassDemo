package com.newTouch.testClassDemo.enitity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author grzha
 * @date 2022/3/5
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserInfo {

    @ExcelProperty(value = "编号",order = 1)
    private String id;
    @ExcelProperty(value = "姓名",order = 2)
    private String name;
    @ExcelProperty(value = "年龄",order = 3)
    private String age;
    @ExcelProperty(value = "部门",order = 4)
    private String dept;
    @ExcelProperty(value = "日期",order = 5)
    private String date;


}
