package com.atguigu.gulimall.gulimall.thirdparty;

import com.aliyun.oss.OSSClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class GulimallThirdPartyApplicationTests {

        @Autowired
    private OSSClient ossClient;

    @Test
    void contextLoads() throws FileNotFoundException {

        FileInputStream inputStream = new FileInputStream(new File("E:\\Users\\sczyh30\\local\\0821资料\\04-JavaSE-资料\\壁纸\\精选1080P电脑壁纸第二期\\1 (48).jpg"));

        this.ossClient.putObject("cgmall",new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "/zs.jpg",inputStream);

        ossClient.shutdown();
        System.out.println(" 上传完成 ");
    }

}
