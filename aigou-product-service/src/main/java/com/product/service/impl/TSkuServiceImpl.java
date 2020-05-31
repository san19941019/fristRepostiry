package com.product.service.impl;

import com.product.po.TProductExt;
import com.product.po.TSku;
import com.product.po.TSpecification;
import com.product.mapper.TSkuMapper;
import com.product.service.ITSkuService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * SKU 服务实现类
 * </p>
 *
 * @author san
 * @since 2020-05-19
 */
@Service
public class TSkuServiceImpl extends ServiceImpl<TSkuMapper, TSku> implements ITSkuService {

	@Override
	public PageResult<TSku> findAll(PageQuery<TSku> pageQuery) {
		// TODO Auto-generated method stub
		IPage<TSku> list=this.page(new Page<>(pageQuery.getPage(), pageQuery.getSize()));
		return new PageResult<TSku>(list.getTotal(),list.getRecords()) ;
	}

	@Override
	public ResultBo addTSku(List<TSku> sku) {
		try {
			this.saveBatch(sku);
			return new ResultBo("成功");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return new ResultBo(500, "失败");
		}
	}

	@Override
	public ResultBo delTSku(String id) {
		try {
			this.remove(new QueryWrapper<TSku>().eq("product_id", id));
			return new ResultBo("成功");
		} catch (Exception e) {
			// TODO: handle exception
			return new ResultBo(500, "失败");
		}
	}

	@Override
	public ResultBo delTSkus(String ids) {
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
	public PageResult<TSku> findById(String id) {
		// TODO Auto-generated method stub
		QueryWrapper<TSku> qw = new QueryWrapper<TSku>();
		if (id != null) {
			qw.eq("product_id", id);
		}
	List<TSku> list = this.list(qw);
	return new PageResult<TSku>(0L, list);
	}


}
