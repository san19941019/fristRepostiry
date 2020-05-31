package com.product.domian;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import lombok.Data;

@Data
@Document(indexName = "shopping")
public class ProductDoc {

	@Id
	private Long id;
	
	@Field(analyzer = "ik_max_word",type=FieldType.Text,searchAnalyzer = "ik_max_word")
	private String name;
	
	@Field(analyzer = "ik_max_word",type=FieldType.Text,searchAnalyzer = "ik_max_word")
	private String subName;
	
	private String medias;
	
	private Integer minPrice;
	
	private Integer maxPrice;
	
	@Field(analyzer = "ik_max_word",type=FieldType.Text,searchAnalyzer = "ik_max_word")
	private String productTypeName;
	
	private Long productTypeId;
	
	@Field(analyzer = "ik_max_word",type=FieldType.Text,searchAnalyzer = "ik_max_word")
	private String brandName;
	
	private Long brandId;
	
	private String sort;
	
	private String order;
	
	private Long onSaleTime;
	
	private Integer saleCount;
	
	private Integer viewCount;
	
	private String viewProperties;
	
	private String skuProperties;
	
	
	
	
	
	
}
