package com.product.controller;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.bo.PageQuery;
import com.base.bo.PageResult;
import com.base.bo.ResultBo;
import com.product.po.TSpecification;
import com.product.service.ITSpecificationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 商品属性 前端控制器
 * </p>
 *
 * @author san
 * @since 2020-05-19
 */
@Controller
@RequestMapping("/tSpecification")
public class TSpecificationController {

	@Autowired
	private ITSpecificationService itSpecificationService;

	@RequestMapping("/findAll")
	@ResponseBody
	public PageResult<TSpecification> findAll(@RequestBody PageQuery<TSpecification> pageQuery) {
		return itSpecificationService.findAll(pageQuery);
	}
	
	@RequestMapping("/findByType")
	@ResponseBody
	public PageResult<TSpecification> findByType(String id){
		return itSpecificationService.findByType(id);
	}

	@RequestMapping("/findNotSku")
	@ResponseBody
	public PageResult<TSpecification> findNotSku(String id){
		return itSpecificationService.findNotSku(id);
	}
	
	@RequestMapping("/findSku")
	@ResponseBody
	public PageResult<TSpecification> findSku(String id){
		return itSpecificationService.findSku(id);
	}

	@RequestMapping("/save")
	@ResponseBody
	@Transactional
	public ResultBo save(@RequestBody List<TSpecification> spec,@RequestParam("id")Long id) {
		itSpecificationService.delTSpecification(id.toString());
		for(TSpecification i : spec) {
			i.setProductTypeId(id);
		}
		return itSpecificationService.addTSpecification(spec);
	}

	@RequestMapping("/del")
	@ResponseBody
	public ResultBo delTProduct(@RequestParam("id") String id) {
		return itSpecificationService.delTSpecification(id);
	}

	@RequestMapping("/dels")
	@ResponseBody
	public ResultBo delTProducts(@RequestParam("ids") String ids) {
		return itSpecificationService.delTSpecification(ids);
	}
}

