package com.lx.feign;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * Description:
 * Author:Jason Li
 * Date:2022-09-14
 * Time:9:30 PM
 */
public interface ReqClient {
    @RequestLine("GET /get/addr")
    String hello();

    @RequestLine("GET {resource}")
    @Headers({"mock: {mock}"})
    String sendRequest(@Param("resource") String resource, @Param("mock") String mock);
}
