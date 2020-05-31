package com.product.controller;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.base.bo.PageQuery;
import com.base.bo.PageResult;
import com.base.bo.ResultBo;
import com.product.client.CommonClient;
import com.product.po.TBrand;
import com.product.service.ITBrandService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 品牌信息 前端控制器
 * </p>
 *
 * @author san
 * @since 2020-05-09
 */
@Controller
@RequestMapping("/tBrand")
public class TBrandController {
	@Autowired
	private CommonClient cc;
 
	@Autowired
	private ITBrandService itBrandService; 
	
	
	@RequestMapping("/findAll")
	@ResponseBody
	public PageResult<TBrand> findAll(@RequestBody PageQuery<TBrand> pageQuery){
		return itBrandService.findAll(pageQuery);
	}
	
	@RequestMapping("/findByType")
	@ResponseBody
	public PageResult<TBrand> findByType(String productTypeId){
		return itBrandService.findByType(productTypeId);
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public ResultBo save(@RequestBody TBrand brand){
		return itBrandService.addBrand(brand);
	}
	
	@RequestMapping("/del")
	@ResponseBody
	public ResultBo delBrand(@RequestParam("id") String id,@RequestParam("logo") String logo){
		if(logo!=null && !"".equals(logo))cc.fileDel(logo.split("https://sanbucket.oss-cn-beijing.aliyuncs.com/")[1]);
		return itBrandService.delBrand(id);
	}
	
	@RequestMapping("/dels")
	@ResponseBody
	public ResultBo delBrands(@RequestParam("ids") String ids){
		return itBrandService.delBrand(ids);
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

