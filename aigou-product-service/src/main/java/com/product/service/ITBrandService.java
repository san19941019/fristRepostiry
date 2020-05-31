package com.product.service;

import com.product.po.TBrand;
import com.baomidou.mybatisplus.extension.service.IService;
import com.base.bo.PageQuery;
import com.base.bo.PageResult;
import com.base.bo.ResultBo;

/**
 * <p>
 * 品牌信息 服务类
 * </p>
 *
 * @author san
 * @since 2020-05-09
 */
public interface ITBrandService extends IService<TBrand> {
	public PageResult<TBrand> findAll(PageQuery<TBrand> pageQuery);
	public ResultBo addBrand(TBrand brand);
	public ResultBo delBrand(String id);
	public ResultBo delBrands(String ids);
	public PageResult<TBrand> findByType(String productTypeId);
}
