package com.product.service;

import com.product.po.TBrand;
import com.product.po.TProduct;
import com.product.po.TProductExt;
import com.baomidou.mybatisplus.extension.service.IService;
import com.base.bo.PageQuery;
import com.base.bo.PageResult;
import com.base.bo.ResultBo;

/**
 * <p>
 * 商品扩展 服务类
 * </p>
 *
 * @author san
 * @since 2020-05-19
 */
public interface ITProductExtService extends IService<TProductExt> {
	public PageResult<TProductExt> findAll(PageQuery<TProductExt> pageQuery);
	public ResultBo addTProductExt(TProductExt productExt);
	public ResultBo delTProductExt(String id);
	public ResultBo delTProductsExt(String ids);
	public PageResult<TProductExt> findById(String id);
	public ResultBo delByProduct(String id);
	public ResultBo updateTProductExt(TProductExt tProduct);
}
