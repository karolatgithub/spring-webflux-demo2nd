package com.dev.springboot.webflux.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.dev.springboot.webflux.model.Article;

import reactor.core.publisher.Flux;

public interface IArticleRepository extends ReactiveMongoRepository<Article, Integer> {

	@Query("{ 'author' : { '$regex' : '^?0', $options: 'i' } }")
	Flux<Article> findByAuthor(String author);

}
