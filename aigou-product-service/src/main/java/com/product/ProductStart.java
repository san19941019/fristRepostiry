package com.product;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import com.product.domian.ProductDoc;
import com.product.repository.ProductDocRepository;

@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
@MapperScan("com.product.mapper")
public class ProductStart {
	public static void main(String[] args) {
		SpringApplication.run(ProductStart.class, args);
	}
	
	
}
