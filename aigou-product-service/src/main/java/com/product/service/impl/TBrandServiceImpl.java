package com.product.service.impl;

import com.product.po.TBrand;
import com.product.mapper.TBrandMapper;
import com.product.service.ITBrandService;
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
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * 品牌信息 服务实现类
 * </p>
 *
 * @author san
 * @since 2020-05-09
 */
@Service
public class TBrandServiceImpl extends ServiceImpl<TBrandMapper, TBrand> implements ITBrandService {

	@Override
	public PageResult<TBrand> findAll(PageQuery<TBrand> pageQuery) {
		QueryWrapper<TBrand> qw = new QueryWrapper<TBrand>();
		if (pageQuery.getQuery() != null) {
			if (pageQuery.getQuery().getName() != null && !"".equals(pageQuery.getQuery().getName())) {
				qw.and(wrapper -> wrapper.like("name", "%" + pageQuery.getQuery().getName() + "%").or()
						.eq("firstLetter", pageQuery.getQuery().getName()).or()
						.like("englishName", "%" + pageQuery.getQuery().getName() + "%"));
			}
			if (pageQuery.getQuery().getProductTypeId() != null) {
				qw.and(wrapper -> wrapper.eq("product_type_id", pageQuery.getQuery().getProductTypeId()));
			}
		}
		IPage<TBrand> list = this.page(new Page<>(pageQuery.getPage(), pageQuery.getSize()), qw);
		return new PageResult<>(list.getTotal(), list.getRecords());

	}
	
	@Override
	public PageResult<TBrand>  findByType(String productTypeId)  {
		QueryWrapper<TBrand> qw = new QueryWrapper<TBrand>();
			if (productTypeId != null) {
				qw.and(wrapper -> wrapper.eq("product_type_id", productTypeId));
			}
		
		List<TBrand> list = this.list(qw);
		return new PageResult<TBrand>(0L, list);

	}


	@Override
	public ResultBo addBrand(TBrand brand) {
		try {
			brand.setUpdateTime(System.currentTimeMillis());
			if (brand.getId() == null) {
				brand.setCreateTime(System.currentTimeMillis());
			}
			this.saveOrUpdate(brand);
			return new ResultBo("成功");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return new ResultBo(500, "失败");
		}
	}

	@Override
	public ResultBo delBrand(String id) {
		try {
			this.remove(new QueryWrapper<TBrand>().eq("id", id));
			return new ResultBo("成功");
		} catch (Exception e) {
			// TODO: handle exception
			return new ResultBo(500, "失败");
		}
	}

	@Override
	public ResultBo delBrands(String ids) {
		try {
			String[] id = ids.split(",");
			this.removeByIds(Arrays.asList(id));
			return new ResultBo("成功");
		} catch (Exception e) {
			// TODO: handle exception
			return new ResultBo(500, "失败");
		}
	}

}
