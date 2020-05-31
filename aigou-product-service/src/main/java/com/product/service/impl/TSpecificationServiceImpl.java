package com.product.service.impl;

import com.product.po.TBrand;
import com.product.po.TSku;
import com.product.po.TSpecification;
import com.product.mapper.TSpecificationMapper;
import com.product.service.ITSpecificationService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
 * 商品属性 服务实现类
 * </p>
 *
 * @author san
 * @since 2020-05-19
 */
@Service
public class TSpecificationServiceImpl extends ServiceImpl<TSpecificationMapper, TSpecification> implements ITSpecificationService {

	@Override
	public PageResult<TSpecification> findAll(PageQuery<TSpecification> pageQuery) {
		// TODO Auto-generated method stub
		IPage<TSpecification> list=this.page(new Page<>(pageQuery.getPage(), pageQuery.getSize()));
		return new PageResult<TSpecification>(list.getTotal(),list.getRecords()) ;
	}

	@Override
	public ResultBo addTSpecification(List<TSpecification> specification) {
		// TODO Auto-generated method stub
		try {
			this.saveBatch(specification);
			return new ResultBo("成功");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return new ResultBo(500, "失败");
		}
	}

	@Override
	public ResultBo delTSpecification(String id) {
		// TODO Auto-generated method stub
		try {
			this.remove(new QueryWrapper<TSpecification>().eq("product_type_id", id));
			return new ResultBo("成功");
		} catch (Exception e) {
			// TODO: handle exception
			return new ResultBo(500, "失败");
		}
	}

	@Override
	public ResultBo delTSpecifications(String ids) {
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
	public PageResult<TSpecification> findByType(String id) {
		// TODO Auto-generated method stub
		QueryWrapper<TSpecification> qw = new QueryWrapper<TSpecification>();
		if (id != null) {
			qw.and(wrapper -> wrapper.eq("product_type_id", id));
		}
	List<TSpecification> list = this.list(qw);
	return new PageResult<TSpecification>(0L, list);
	}

	@Override
	public PageResult<TSpecification> findNotSku(String id) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				QueryWrapper<TSpecification> qw = new QueryWrapper<TSpecification>();
				if (id != null) {
					qw.eq("product_type_id", id);
					qw.eq("isSku", false);
				}
			List<TSpecification> list = this.list(qw);
			return new PageResult<TSpecification>(0L, list);
	}
	
	@Override
	public PageResult<TSpecification> findSku(String id) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				QueryWrapper<TSpecification> qw = new QueryWrapper<TSpecification>();
				if (id != null) {
					qw.eq("product_type_id", id);
					qw.eq("isSku", true);
				}
			List<TSpecification> list = this.list(qw);
			return new PageResult<TSpecification>(0L, list);
	}

}
