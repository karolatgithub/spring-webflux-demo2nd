package com.dev.springboot.webflux.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.springboot.webflux.component.ApplicationContextProvider;
import com.dev.springboot.webflux.model.Article;
import com.dev.springboot.webflux.service.IArticleService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

	private IArticleService getArticleService() {
		return (IArticleService) ApplicationContextProvider.GET_CONTEXT().getBean("articleServiceImpl");
	}

	@GetMapping("/findAll")
	public Flux<Article> getAllArticles() {
		return getArticleService().findAllArticles();
	}

	@PostMapping("/save")
	public Mono<ResponseEntity<Article>> createArticle(@RequestBody Article article) {
		return getArticleService().saveArticle(article)
				.map(savedArticle -> new ResponseEntity<>(savedArticle, HttpStatus.CREATED));
	}

	@GetMapping("/id/{id}")
	public Mono<ResponseEntity<Article>> getArticleById(@PathVariable("id") Integer articleId) {
		return getArticleService().findOneArticle(articleId).map(article -> ResponseEntity.ok(article))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@GetMapping("/author/{author}")
	public Flux<Article> getArticleByAuthor(@PathVariable("author") String author) {
		return getArticleService().findByAuthor(author);
	}

	@PutMapping("/update/{id}")
	public Mono<ResponseEntity<Article>> updateArticle(@PathVariable("id") Integer articleId,
			@RequestBody Article article) {
		return getArticleService().findOneArticle(articleId).flatMap(existingArticle -> {
			existingArticle.setTitle(article.getTitle());
			existingArticle.setContent(article.getContent());
			existingArticle.setAuthor(article.getAuthor());
			existingArticle.setPublishedAt(article.getPublishedAt());
			return getArticleService().saveArticle(existingArticle);
		}).map(updatedArticle -> new ResponseEntity<>(updatedArticle, HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@DeleteMapping("/delete/{id}")
	public Mono<ResponseEntity<Void>> deleteArticle(@PathVariable("id") Integer articleId) {
		return getArticleService().deleteArticle(articleId).then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
				.onErrorResume(error -> Mono.just(new ResponseEntity<Void>(HttpStatus.NOT_FOUND)));
	}
}
