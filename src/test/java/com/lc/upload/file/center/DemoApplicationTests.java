package com.lc.upload.file.center;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.net.MalformedURLException;
import java.net.URL;


public class DemoApplicationTests {

	@Test
	public void contextLoads() {
	    MultipartFile file;
	    String url = "http://192.168.223.128:88/articles/img/3d_32_51_0b_3d32510b9286b19736e0a8f2196e7ca4_50ee1aea698c4605d43842d70ec835038d6a2f8a_4000x3008s3022041u115083308.jpg";
        try {
            URL fileUrl = new URL(url);
            System.out.println(fileUrl.getHost());
            System.out.println(fileUrl.getPath());
            System.out.println(fileUrl.getUserInfo());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }


}
