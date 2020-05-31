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
	// ���������˺�AccessKeyӵ������API�ķ���Ȩ�ޣ����պܸߡ�ǿ�ҽ�����������ʹ��RAM�˺Ž���API���ʻ��ճ���ά�����¼ https://ram.console.aliyun.com ����RAM�˺š�
	private static String accessKeyId = "LTAI4G9PRajqp3sMN6Fy5zaU";
	private static String accessKeySecret = "byO9jBIXTMQj2W7bXxTulUbQJjefvc";
	private static String bucketName = "sanbucket";
	
	public void  create() {
	// ����OSSClientʵ����
	OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

	// �����洢�ռ䡣
	ossClient.createBucket(bucketName);

	// �ر�OSSClient��
	ossClient.shutdown();
	}

	public void  fileUpload(String objectName,InputStream file) {
		// <yourObjectName>�ϴ��ļ���OSSʱ��Ҫָ�������ļ���׺���ڵ�����·��������abc/efg/123.jpg��
		// ����OSSClientʵ����
		OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
		// �ϴ����ݵ�ָ���Ĵ洢�ռ䣨bucketName��������Ϊָ�����ļ����ƣ�objectName����

		ossClient.putObject(bucketName, objectName, file);
		// �ر�OSSClient��
		ossClient.shutdown();
					
	}
	
	public void  fileDown() throws IOException {
		// Endpoint�Ժ���Ϊ��������Region�밴ʵ�������д��
		// <yourObjectName>��OSS�����ļ�ʱ��Ҫָ�������ļ���׺���ڵ�����·��������abc/efg/123.jpg��
		String objectName = "abc/efg/123.jpg";

		// ����OSSClientʵ����
		OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

		// ����ossClient.getObject����һ��OSSObjectʵ������ʵ�������ļ����ݼ��ļ�Ԫ��Ϣ��
		OSSObject ossObject = ossClient.getObject(bucketName, objectName);
		// ����ossObject.getObjectContent��ȡ�ļ����������ɶ�ȡ����������ȡ�����ݡ�
		InputStream content = ossObject.getObjectContent();
		if (content != null) {
		    BufferedReader reader = new BufferedReader(new InputStreamReader(content));
		    while (true) {
		        String line = reader.readLine();
		        if (line == null) break;
		        System.out.println("\n" + line);
		    }
		    // ���ݶ�ȡ��ɺ󣬻�ȡ��������رգ�������������й©���������������ӿ��ã������޷�����������
				content.close();
		}
		// �ر�OSSClient��
		ossClient.shutdown();						
	}
	
	public void fileDel(String objectName) {

		// ����OSSClientʵ����
		OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

		// ɾ���ļ�������ɾ���ļ��У��뽫ObjectName����Ϊ��Ӧ���ļ������ơ�����ļ��зǿգ�����Ҫ���ļ����µ�����objectɾ�������ɾ�����ļ��С�
		ossClient.deleteObject(bucketName, objectName);

		// �ر�OSSClient��
		ossClient.shutdown();
					
	}
	
				
}
