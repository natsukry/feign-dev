package com.lx.feign;

import com.lx.App;
import feign.Feign;
import feign.FeignException;
import feign.form.spring.SpringFormEncoder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.charset.Charset;

/**
 * Description:
 * Author:Jason Li
 * Date:2022-09-14
 * Time:9:33 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
class FeignClientTest {


    @Test
    public void testFeign(){
       ReqClient reqClient = Feign.builder()
               .encoder(new SpringFormEncoder())
               .target(ReqClient.class,"http://localhost:1999");
        String hello = reqClient.hello();
        System.out.println(hello);

    }

    @Test
    public void sendReq(){
       ReqClient reqClient = Feign.builder()
               .encoder(new SpringFormEncoder()).target(ReqClient.class,"http://localhost:1999");
        String response = "empty";
       try{
         response = reqClient.sendRequest("/get/addr","a1");
       }catch (FeignException e){
           String responseBody = Charset.forName("utf-8").decode(e.responseBody().get()).toString();
           System.out.println(responseBody);
       }
        System.out.println(response);

    }




}