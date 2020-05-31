package com.product.controller;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.bo.PageQuery;
import com.base.bo.PageResult;
import com.base.bo.ResultBo;
import com.product.po.TSku;
import com.product.po.TSpecification;
import com.product.service.ITSkuService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * SKU 前端控制器
 * </p>
 *
 * @author san
 * @since 2020-05-19
 */
@Controller
@RequestMapping("/tSku")
public class TSkuController {
	@Autowired
	private ITSkuService itskuService;

	@RequestMapping("/findAll")
	@ResponseBody
	public PageResult<TSku> findAll(@RequestBody PageQuery<TSku> pageQuery) {
		return itskuService.findAll(pageQuery);
	}

	@RequestMapping("/findById")
	@ResponseBody
	public PageResult<TSku> findById(String id) {
		return itskuService.findById(id);
	}
	@RequestMapping("/save")
	@ResponseBody
	@Transactional
	public ResultBo save(@RequestBody List<TSku> sku,@RequestParam("id")Long id) {
		System.out.println(id);
		itskuService.delTSku(id.toString());
		for(TSku i : sku) {
			i.setProductId(id);
		}
		return itskuService.addTSku(sku);
	}

	@RequestMapping("/del")
	@ResponseBody
	public ResultBo delTProduct(@RequestParam("id") String id) {
		return itskuService.delTSku(id);
	}

	@RequestMapping("/dels")
	@ResponseBody
	public ResultBo delTProducts(@RequestParam("ids") String ids) {
		return itskuService.delTSku(ids);
	}
}

