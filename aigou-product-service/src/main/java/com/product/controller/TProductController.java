package com.product.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.bo.PageQuery;
import com.base.bo.PageResult;
import com.base.bo.ResultBo;
import com.product.po.Products;
import com.product.po.TProduct;
import com.product.service.ITProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 商品 前端控制器
 * </p>
 *
 * @author san
 * @since 2020-05-19
 */
@Controller
@RequestMapping("/tProduct")
public class TProductController {
	@Autowired
	private ITProductService itProductService;

	@RequestMapping("/findAll")
	@ResponseBody
	public PageResult<TProduct> findAll(@RequestBody PageQuery<TProduct> pageQuery) {
		return itProductService.findAll(pageQuery);
	}

	@RequestMapping("/save")
	@ResponseBody
	public ResultBo save(@RequestBody Products products) {
		return itProductService.addTProduct(products);
	}

	@RequestMapping("/del")
	@ResponseBody
	public ResultBo delTProduct(@RequestParam("id") String id, @RequestParam("logo") String logo) {
		return itProductService.delTProduct(id);
	}

	@RequestMapping("/dels")
	@ResponseBody
	public ResultBo delTProducts(@RequestParam("ids") String ids) {
		return itProductService.delTProducts(ids);
	}
	
	@RequestMapping("/onSale")
	@ResponseBody
	public ResultBo onSale(@RequestParam("ids") String ids) {
		return itProductService.onSale(ids);
	}
	

}
