package com.newTouch.testclassdemo.enitity;

import lombok.*;

/**
 * @author grzha
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ResponseVo<T> {

    private String responseCode;
    private String responseMsg;
    private T data;

    public static ResponseVo success() {
        return new ResponseVo("000000", "success", null);
    }

    public static <T> ResponseVo success(T data) {
        return new ResponseVo("000000", "success", data);
    }

    public static ResponseVo fail(String code, String msg) {
        return new ResponseVo(code, msg, null);
    }


}
