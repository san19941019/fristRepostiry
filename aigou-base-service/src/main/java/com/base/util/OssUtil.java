package com.base.util;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.OSSObject;

public class OssUtil {
	
	private static String endpoint = "http://oss-cn-beijing.aliyuncs.com";
	// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
	private static String accessKeyId = "LTAI4G9PRajqp3sMN6Fy5zaU";
	private static String accessKeySecret = "byO9jBIXTMQj2W7bXxTulUbQJjefvc";
	private static String bucketName = "sanbucket";
	
	public void  create() {
	// 创建OSSClient实例。
	OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

	// 创建存储空间。
	ossClient.createBucket(bucketName);

	// 关闭OSSClient。
	ossClient.shutdown();
	}

	public void  fileUpload(String objectName,InputStream file) {
		// <yourObjectName>上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
		// 创建OSSClient实例。
		OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
		// 上传内容到指定的存储空间（bucketName）并保存为指定的文件名称（objectName）。

		ossClient.putObject(bucketName, objectName, file);
		// 关闭OSSClient。
		ossClient.shutdown();
					
	}
	
	public void  fileDown() throws IOException {
		// Endpoint以杭州为例，其它Region请按实际情况填写。
		// <yourObjectName>从OSS下载文件时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
		String objectName = "abc/efg/123.jpg";

		// 创建OSSClient实例。
		OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

		// 调用ossClient.getObject返回一个OSSObject实例，该实例包含文件内容及文件元信息。
		OSSObject ossObject = ossClient.getObject(bucketName, objectName);
		// 调用ossObject.getObjectContent获取文件输入流，可读取此输入流获取其内容。
		InputStream content = ossObject.getObjectContent();
		if (content != null) {
		    BufferedReader reader = new BufferedReader(new InputStreamReader(content));
		    while (true) {
		        String line = reader.readLine();
		        if (line == null) break;
		        System.out.println("\n" + line);
		    }
		    // 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
				content.close();
		}
		// 关闭OSSClient。
		ossClient.shutdown();						
	}
	
	public void fileDel(String objectName) {

		// 创建OSSClient实例。
		OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

		// 删除文件。如需删除文件夹，请将ObjectName设置为对应的文件夹名称。如果文件夹非空，则需要将文件夹下的所有object删除后才能删除该文件夹。
		ossClient.deleteObject(bucketName, objectName);

		// 关闭OSSClient。
		ossClient.shutdown();
					
	}
	
				
}
