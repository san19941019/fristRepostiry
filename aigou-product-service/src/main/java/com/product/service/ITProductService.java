package com.product.service;

import com.product.po.Products;
import com.product.po.TBrand;
import com.product.po.TProduct;
import com.product.po.TProduct;
import com.baomidou.mybatisplus.extension.service.IService;
import com.base.bo.PageQuery;
import com.base.bo.PageResult;
import com.base.bo.ResultBo;

/**
 * <p>
 * 商品 服务类
 * </p>
 *
 * @author san
 * @since 2020-05-19
 */
public interface ITProductService extends IService<TProduct> {
	public PageResult<TProduct> findAll(PageQuery<TProduct> pageQuery);
	public ResultBo delTProduct(String id);
	public ResultBo delTProducts(String ids);
	public ResultBo addTProduct(Products products);
	public ResultBo onSale(String ids);
}
