package com.product.controller;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import com.base.bo.PageResult;
import com.base.bo.ResultBo;
import com.product.client.CommonClient;
import com.product.po.TProductType;
import com.product.service.ITProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 商品目录 前端控制器
 * </p>
 *
 * @author san
 * @since 2020-05-09
 */
@Controller
@RequestMapping("/tProductType")
public class TProductTypeController {
	
	@Autowired
	private CommonClient cc;
	
	@Autowired
	private ITProductTypeService itProductTypeService; 
	
	
	@RequestMapping("/findAll")
	@ResponseBody
	public ResultBo findAll(){
		return itProductTypeService.findAll();
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public ResultBo save(@RequestBody TProductType productType){
		return itProductTypeService.addProduct(productType);
	}
	
	@RequestMapping("/del")
	@ResponseBody
	public ResultBo del(@RequestParam("id") String id,@RequestParam("logo") String logo){
		if(logo!=null && !"".equals(logo))cc.fileDel(logo.split("https://sanbucket.oss-cn-beijing.aliyuncs.com/")[1]);
		return itProductTypeService.delProduct(id);
	}
	
	@RequestMapping("/staticFile")
	@ResponseBody
	public void staticFile(){
		itProductTypeService.staticFile();
	}

	@RequestMapping("/upload")
	@ResponseBody
	public ResultBo upload(@RequestPart("file") MultipartFile file){
		return cc.fileUpload(file);
	}
	
	@RequestMapping("/fileDel")
	@ResponseBody
	public ResultBo fileDel(@RequestParam("fileName")String fileName) {
		return cc.fileDel(fileName);
	}
}

