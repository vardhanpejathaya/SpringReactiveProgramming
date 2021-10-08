package com.vardhan.spring.reactive.springreactivemongocrud.repository;

import com.vardhan.spring.reactive.springreactivemongocrud.dto.ProductDto;
import com.vardhan.spring.reactive.springreactivemongocrud.entity.Product;
import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product,String> {

    Flux<ProductDto> findByPriceBetween(Range<Double> priceRange);
}
