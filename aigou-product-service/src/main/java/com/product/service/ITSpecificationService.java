package com.product.service;

import com.product.po.TBrand;
import com.product.po.TSpecification;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.base.bo.PageQuery;
import com.base.bo.PageResult;
import com.base.bo.ResultBo;

/**
 * <p>
 * 商品属性 服务类
 * </p>
 *
 * @author san
 * @since 2020-05-19
 */
public interface ITSpecificationService extends IService<TSpecification> {
	public PageResult<TSpecification> findAll(PageQuery<TSpecification> pageQuery);
	public ResultBo delTSpecification(String id);
	public ResultBo delTSpecifications(String ids);
	public PageResult<TSpecification> findByType(String id);
	public ResultBo addTSpecification(List<TSpecification> specification);
	public PageResult<TSpecification> findNotSku(String id);
	public PageResult<TSpecification> findSku(String id);
}
