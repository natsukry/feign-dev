package com.lx.util;

import com.lx.feign.ReqClient;
import feign.Feign;
import feign.FeignException;
import feign.form.spring.SpringFormEncoder;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Description:
 * Author:Jason Li
 * Date:2022-09-14
 * Time:10:23 PM
 */
@Component
public class SendReqWithDependencies {
    public void sendReq() {

        ReqClient reqClient = Feign.builder()
                .encoder(new SpringFormEncoder()).target(ReqClient.class, "http://localhost:1999");
        String response = "empty";
        try {
            response = reqClient.sendRequest("/get/addr", "a1");
        } catch (FeignException e) {
            String responseBody = StandardCharsets.UTF_8.decode(e.responseBody().get()).toString();
            System.out.println(responseBody);
        }
        System.out.println(response);
    }
}
