package com.common.controller;


import java.io.InputStream;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.base.bo.ResultBo;
import com.base.util.OssUtil;


@Controller
@RequestMapping("/common")
public class OssControlller {

	
	@RequestMapping("/upload")
	@ResponseBody
	public ResultBo fileUpload(@RequestPart("file") MultipartFile file) {
		String name;
		InputStream is;
		if(file.isEmpty()) {return new ResultBo("无文件") ;}
		try {
			name=System.currentTimeMillis()+"/"+file.getOriginalFilename();
			is=file.getInputStream();
			new OssUtil().fileUpload(name, is);
			return new ResultBo("上传成功").setData("https://sanbucket.oss-cn-beijing.aliyuncs.com/"+name); 
		}catch (Exception e) {
			// TODO: handle exception
			return new ResultBo("上传失败");
		}
		
	}
	
	@RequestMapping("/fileDel")
	@ResponseBody
	public ResultBo fileDel(@RequestParam("fileName")String fileName) {
		try {
			new OssUtil().fileDel(fileName);
			return new ResultBo("删除成功"); 
		}catch (Exception e) {
			// TODO: handle exception
			return new ResultBo("删除失败");
		}
	}
	
}
