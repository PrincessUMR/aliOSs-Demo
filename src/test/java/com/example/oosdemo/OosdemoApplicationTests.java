package com.example.oosdemo;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;

@SpringBootTest
class OosdemoApplicationTests {

    String endpoint = "http://oss-cn-beijing.aliyuncs.com";
    // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
    String accessKeyId = "LTAI4G2pyD3e5d39vDL791av";
    String accessKeySecret = "Hu7EYBtXVh4yUB92301qqvR5lveo8M";
    ObjectMetadata objectMetadata = new ObjectMetadata();


    @Test
    void uplooad() throws FileNotFoundException {
            // Endpoint以北京为例，其它Region请按实际情况填写。
        objectMetadata.setContentType("image/png");
            String bucketName = "umr-bucket";
            String objectName = "Test.png";
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            // 上传文件流。
        InputStream inputStream = new FileInputStream(new File("D:\\DownloadFile\\imges\\test2.png"));
         ossClient.putObject("umr-bucket", "Test.png", inputStream);

        // 关闭OSSClient。
            ossClient.shutdown();

            System.out.println("测试完成");
    }

    @Test
    public void download() throws IOException {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        String bucketName = "umr-bucket";
        String objectName = "Test.png";
// 调用ossClient.getObject返回一个OSSObject实例，该实例包含文件内容及文件元信息。
        OSSObject ossObject = ossClient.getObject(bucketName, objectName);
// 调用ossObject.getObjectContent获取文件输入流，可读取此输入流获取其内容。
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType("image/png");
        ossClient.getObject(new GetObjectRequest(bucketName, objectName), new File("D:\\DownloadFile\\imges\\test.png"));
        ossClient.shutdown();
    }

}
