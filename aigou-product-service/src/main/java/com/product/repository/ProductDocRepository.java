package com.product.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.product.domian.ProductDoc;

@Repository
public interface ProductDocRepository extends ElasticsearchRepository<ProductDoc, Long> {

}
