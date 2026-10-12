package org.doit.ik.blog.controller;

import java.util.List;

import org.doit.ik.blog.domain.Article;
import org.doit.ik.blog.dto.AddArticleRequest;
import org.doit.ik.blog.dto.ArticleResponse;
import org.doit.ik.blog.dto.UpdateArticleRequest;
import org.doit.ik.blog.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BlogApiController {
	
	private final BlogService blogService;
	
	@PostMapping("/api/articles")
	public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest articleRequest) {
		Article savedArticle = this.blogService.save(articleRequest);		
		return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
	}
	
	@GetMapping("/api/articles")
	public ResponseEntity<List<ArticleResponse>> findAllArticles() {
		// List<Article> -> List<ArticleResponse> 변환
		List<ArticleResponse> articles =  this.blogService.findAll().stream().map(ArticleResponse::new).toList();	
		return ResponseEntity.ok().body(articles);
	}
	
	@GetMapping("/api/articles/{id}")	 
	public  ResponseEntity<ArticleResponse> findArticle(@PathVariable(name = "id") long id) {
		Article article = this.blogService.findById(id);			
		return ResponseEntity.ok().body( new ArticleResponse(article));
	}
	
	@DeleteMapping("/api/articles/{id}") 
	public ResponseEntity<Void> deleteArticle(@PathVariable(name = "id") long id){
		this.blogService.delete(id);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/api/articles/{id}")	 
	public ResponseEntity<Article> updateArticle(@PathVariable(name = "id") long id, @RequestBody UpdateArticleRequest	request) {
		Article updatedArticle = this.blogService.update(id, request);
		return ResponseEntity.ok().body(updatedArticle);
	}

}
