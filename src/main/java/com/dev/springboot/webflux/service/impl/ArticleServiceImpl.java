package com.dev.springboot.webflux.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.springboot.webflux.model.Article;
import com.dev.springboot.webflux.repository.IArticleRepository;
import com.dev.springboot.webflux.service.IArticleService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ArticleServiceImpl implements IArticleService {

	@Autowired
	private IArticleRepository articleRepository;

	@Override
	public Mono<Article> saveArticle(Article article) {

		return articleRepository.save(article);

		// for Mono<String> return type
		// return Mono.just("saved successfully");
	}

	@Override
	public Flux<Article> findAllArticles() {

		return articleRepository.findAll().switchIfEmpty(Flux.empty());
	}

	@Override
	public Mono<Article> findOneArticle(Integer id) {

		return articleRepository.findById(id).switchIfEmpty(Mono.empty());
	}

	@Override
	public Flux<Article> findByAuthor(String author) {

		return articleRepository.findByAuthor(author);
	}

	@Override
	public Mono<Void> deleteArticle(Integer id) {

		return articleRepository.deleteById(id);
	}
}
