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
public class QueryRequest {

    private String id;
    private String name;


}
