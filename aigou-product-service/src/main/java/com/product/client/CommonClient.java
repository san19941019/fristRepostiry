package com.product.client;

import javax.ws.rs.core.MediaType;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.base.bo.ResultBo;



@FeignClient(value="aigou-common-service")
public interface CommonClient {
	
	@RequestMapping(value="/common/upload",consumes = MediaType.MULTIPART_FORM_DATA)
	@ResponseBody
	public ResultBo fileUpload(@RequestPart("file") MultipartFile file);
	
	@RequestMapping(value="/common/fileDel")
	@ResponseBody
	public ResultBo fileDel(@RequestParam("fileName")String fileName);

}
