package com.dev.springboot.webflux.service;

import com.dev.springboot.webflux.model.Article;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IArticleService {

   public Mono<Article> saveArticle(Article article);

   public Flux<Article> findAllArticles();

   public Mono<Article> findOneArticle(Integer id);

   public Flux<Article> findByAuthor(String author);

   public Mono<Void> deleteArticle(Integer id);

}
