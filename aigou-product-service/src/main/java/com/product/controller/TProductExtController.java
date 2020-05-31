package com.product.controller;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.bo.PageQuery;
import com.base.bo.PageResult;
import com.base.bo.ResultBo;
import com.product.po.TBrand;
import com.product.po.TProduct;
import com.product.po.TProductExt;
import com.product.service.ITProductExtService;
import com.product.service.ITProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 商品扩展 前端控制器
 * </p>
 *
 * @author san
 * @since 2020-05-19
 */
@Controller
@RequestMapping("/tProductExt")
public class TProductExtController {
	@Autowired
	private ITProductExtService itProductExtService;

	@RequestMapping("/findAll")
	@ResponseBody
	public PageResult<TProductExt> findAll(@RequestBody PageQuery<TProductExt> pageQuery) {
		return itProductExtService.findAll(pageQuery);
	}
	
	@RequestMapping("/findById")
	@ResponseBody
	public PageResult<TProductExt> findByType(String id){
		return itProductExtService.findById(id);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public ResultBo update(@RequestBody TProductExt TProduct) {
		return itProductExtService.updateTProductExt(TProduct);
	}

	@RequestMapping("/save")
	@ResponseBody
	public ResultBo save(@RequestBody TProductExt TProduct) {
		System.out.println(TProduct.getProductId());
		return itProductExtService.addTProductExt(TProduct);
	}

	@RequestMapping("/del")
	@ResponseBody
	public ResultBo delTProduct(@RequestParam("id") String id, @RequestParam("logo") String logo) {
		return itProductExtService.delTProductExt(id);
	}

	@RequestMapping("/dels")
	@ResponseBody
	public ResultBo delTProducts(@RequestParam("ids") String ids) {
		return itProductExtService.delTProductsExt(ids);
	}
}

