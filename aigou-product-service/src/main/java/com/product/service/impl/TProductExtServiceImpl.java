package com.product.service.impl;

import com.product.po.TBrand;
import com.product.po.TProductExt;
import com.product.mapper.TProductExtMapper;
import com.product.service.ITProductExtService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.base.bo.PageQuery;
import com.base.bo.PageResult;
import com.base.bo.ResultBo;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品扩展 服务实现类
 * </p>
 *
 * @author san
 * @since 2020-05-19
 */
@Service
public class TProductExtServiceImpl extends ServiceImpl<TProductExtMapper, TProductExt> implements ITProductExtService {

	@Override
	public PageResult<TProductExt> findAll(PageQuery<TProductExt> pageQuery) {
		// TODO Auto-generated method stub
		IPage<TProductExt> list=this.page(new Page<>(pageQuery.getPage(), pageQuery.getSize()));
		return new PageResult<TProductExt>(list.getTotal(),list.getRecords()) ;
	}

	@Override
	public ResultBo addTProductExt(TProductExt productExt) {
		// TODO Auto-generated method stub
		try {
			this.saveOrUpdate(productExt);
			return new ResultBo("成功");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return new ResultBo(500, "失败");
		}
	}

	@Override
	public ResultBo delTProductExt(String id) {
		try {
			this.remove(new QueryWrapper<TProductExt>().eq("id", id));
			return new ResultBo("成功");
		} catch (Exception e) {
			// TODO: handle exception
			return new ResultBo(500, "失败");
		}
	}
	
	@Override
	public ResultBo delByProduct(String id) {
		try {
			this.remove(new QueryWrapper<TProductExt>().eq("product_id", id));
			return new ResultBo("成功");
		} catch (Exception e) {
			// TODO: handle exception
			return new ResultBo(500, "失败");
		}
	}

	@Override
	public ResultBo delTProductsExt(String ids) {
		// TODO Auto-generated method stub
		try {
			String[] id = ids.split(",");
			this.removeByIds(Arrays.asList(id));
			return new ResultBo("成功");
		} catch (Exception e) {
			// TODO: handle exception
			return new ResultBo(500, "失败");
		}
	}

	@Override
	public PageResult<TProductExt> findById(String id) {
		// TODO Auto-generated method stub
		QueryWrapper<TProductExt> qw = new QueryWrapper<TProductExt>();
		if (id != null) {
			qw.and(wrapper -> wrapper.eq("product_id", id));
		}
	  List<TProductExt> list = this.list(qw);
	return new PageResult<TProductExt>(0L, list);
	}

	@Override
	public ResultBo updateTProductExt(TProductExt tProduct) {
		// TODO Auto-generated method stub
		try {
		UpdateWrapper<TProductExt> uw = new UpdateWrapper<TProductExt>();
		uw.eq("product_id", tProduct.getProductId());
		this.update(tProduct,uw);
		return new ResultBo("成功");
	} catch (Exception e) {
		// TODO: handle exception
		return new ResultBo(500, "失败");
	}
	}
}
