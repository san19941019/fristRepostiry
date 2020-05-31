
package com.product.service;

import com.product.po.TProductType;
import com.baomidou.mybatisplus.extension.service.IService;
import com.base.bo.PageResult;
import com.base.bo.ResultBo;

/**
 * <p>
 * 商品目录 服务类
 * </p>
 *
 * @author san
 * @since 2020-05-09
 */
public interface ITProductTypeService extends IService<TProductType> {
	public ResultBo findAll();

	public ResultBo addProduct(TProductType product);

	public ResultBo delProduct(String id);

	public void staticFile();
}
