package com.product.service.impl;

import com.product.po.Products;
import com.product.po.TBrand;
import com.product.po.TProduct;
import com.product.po.TProductType;
import com.product.repository.ProductDocRepository;
import com.product.domian.ProductDoc;
import com.product.mapper.TProductMapper;
import com.product.service.ITProductExtService;
import com.product.service.ITProductService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.base.bo.PageQuery;
import com.base.bo.PageResult;
import com.base.bo.ResultBo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 商品 服务实现类
 * </p>
 *
 * @author san
 * @since 2020-05-19
 */
@Service
public class TProductServiceImpl extends ServiceImpl<TProductMapper, TProduct> implements ITProductService {
	@Autowired
	private ITProductExtService itProductExtService;
	
	@Autowired
	private TProductMapper productMapper; 
	
	@Autowired
	private ProductDocRepository pdr;
	
	@Override
	public PageResult<TProduct> findAll(PageQuery<TProduct> pageQuery) {
		// TODO Auto-generated method stub
		IPage<TProduct> list=this.page(new Page<>(pageQuery.getPage(), pageQuery.getSize()));
		return new PageResult<TProduct>(list.getTotal(),list.getRecords()) ;
	}

	@Override
	@Transactional
	public ResultBo addTProduct(Products products) {
		// TODO Auto-generated method stub
		try {
			products.getProduct().setUpdateTime(System.currentTimeMillis());
			if (products.getProduct().getId() == null) {
				products.getProduct().setCreateTime(System.currentTimeMillis());
			}
			this.saveOrUpdate(products.getProduct());
			products.getProductExt().setProductId(products.getProduct().getId());
			itProductExtService.addTProductExt(products.getProductExt());
			return new ResultBo("成功");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return new ResultBo(500, "失败");
		}
	}

	@Override
	@Transactional
	public ResultBo delTProduct(String id) {
		try {
			this.remove(new QueryWrapper<TProduct>().eq("id", id));
			itProductExtService.delByProduct(id);
			return new ResultBo("成功");
		} catch (Exception e) {
			// TODO: handle exception
			return new ResultBo(500, "失败");
		}
	}

	@Override
	public ResultBo delTProducts(String ids) {
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
	public ResultBo onSale(String ids) {
		System.out.println("------------");
		try {
		String[] id = ids.split(",");
		List<Long> lid=Arrays.stream(id).map(s ->Long.parseLong(s.trim())).collect(Collectors.toList());
		List<ProductDoc> lpd=new ArrayList<ProductDoc>();
		lid.stream().forEach(pid ->{
			TProduct product=productMapper.selectById(pid);
			ProductDoc pd=new ProductDoc();
			pd.setId(pid);
			pd.setName(product.getName());
			pd.setSubName(product.getSubName());
			lpd.add(pd);
		});
		
		pdr.saveAll(lpd);
		return new ResultBo("成功");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return new ResultBo(500, "失败");
		}
	}

}
