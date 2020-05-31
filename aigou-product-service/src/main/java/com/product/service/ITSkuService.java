package com.product.service;

import com.product.po.TBrand;
import com.product.po.TSku;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.base.bo.PageQuery;
import com.base.bo.PageResult;
import com.base.bo.ResultBo;

/**
 * <p>
 * SKU 服务类
 * </p>
 *
 * @author san
 * @since 2020-05-19
 */
public interface ITSkuService extends IService<TSku> {
	public PageResult<TSku> findAll(PageQuery<TSku> pageQuery);
	public ResultBo delTSku(String id);
	public ResultBo delTSkus(String ids);
	ResultBo addTSku(List<TSku> sku);
	public PageResult<TSku> findById(String id);
}
