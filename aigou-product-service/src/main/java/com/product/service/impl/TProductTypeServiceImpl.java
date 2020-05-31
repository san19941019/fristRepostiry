package com.product.service.impl;

import com.product.po.TBrand;
import com.product.po.TProductType;
import com.product.mapper.TProductTypeMapper;
import com.product.service.ITProductTypeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.base.bo.PageResult;
import com.base.bo.ResultBo;
import com.base.util.JedisUtil;
import com.base.util.VelocityUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

/**
 * <p>
 * 商品目录 服务实现类
 * </p>
 *
 * @author san
 * @since 2020-05-09
 */
@Service
public class TProductTypeServiceImpl extends ServiceImpl<TProductTypeMapper, TProductType> implements ITProductTypeService {
	
	@Autowired
	ObjectMapper om;
	
	@Override
	public ResultBo findAll() {
		// TODO Auto-generated method stub
		try {
		String	data=new JedisUtil().getJedis("TProductType");
		if(data==null) {
			List<TProductType> list=this.list();
			list=listType(list);
			new JedisUtil().putJedis("TProductType", om.writeValueAsString(list));
			return new ResultBo<>(200, "成功").setData(list);
		}else {	
			return new ResultBo<>(500, "失败").setData(om.readValue(data,List.class));
		}
		}catch (Exception e) {
			// TODO: handle exception
			return new ResultBo(500, "失败");
		}
	}
	
	@Override
	public ResultBo addProduct(TProductType product) {
		try {
			product.setUpdateTime(System.currentTimeMillis());
			if (product.getId() == null) {
				product.setCreateTime(System.currentTimeMillis());
			}
			this.saveOrUpdate(product);
			return new ResultBo("成功");
		} catch (Exception e) {
			// TODO: handle exception
			return new ResultBo(500, "失败");
		}
	}

	@Override
	public ResultBo delProduct(String id) {
		try {
			this.remove(new QueryWrapper<TProductType>().eq("id", id));
			return new ResultBo("成功");
		} catch (Exception e) {
			// TODO: handle exception
			return new ResultBo(500, "失败");
		}
	}
	
	@Override
	public void staticFile() {
		try {
			String classPath=ResourceUtils.getURL("classpath:").getPath();
			String typeVm=classPath+"template/home/product.type.vm";
			String filePath=classPath+"template/home/product.type.vm.html";
			List<TProductType> list=listType(this.list());
			System.out.println(111);
			Map<String,Object> maps=new HashMap<>();
			maps.put("model", list);
			new VelocityUtil().createFile(typeVm, filePath, maps);
			String homeVm=classPath+"template/home.vm";
			System.out.println(homeVm);
			filePath="D:\\ecommerce\\home.html";
			maps.clear();
			Map<String,Object> data=new HashMap<>();
			data.put("staticRoot", classPath);
			maps.put("model",data);
			System.out.println(maps.get("model"));
			new VelocityUtil().createFile(homeVm, filePath, maps);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
	
	public List<TProductType> listType(List<TProductType> list){
		List<TProductType> types=new ArrayList<>();
		Map<Long,TProductType> maps=new HashMap<Long,TProductType>();
		for(TProductType pt:list) {
			maps.put(pt.getId(), pt);
		}
		for(TProductType pt:list) {
			if(pt.getPid()==0) types.add(pt);
			TProductType ppid=maps.get(pt.getPid());
			if(ppid!=null) {
                 if(ppid.getTProductType()==null) ppid.setTProductType(new ArrayList<>());
                 ppid.getTProductType().add(pt);
		}
		}
		return types;
	}
	
	
}
