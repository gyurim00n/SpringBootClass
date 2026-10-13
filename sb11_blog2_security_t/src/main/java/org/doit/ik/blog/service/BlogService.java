package org.doit.ik.blog.service;

import java.util.List;

import org.doit.ik.blog.domain.Article;
import org.doit.ik.blog.dto.AddArticleRequest;
import org.doit.ik.blog.dto.UpdateArticleRequest;
import org.doit.ik.blog.repository.BlogRepository;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BlogService {
	
	private final BlogRepository blogRepository;
	
	public Article save(AddArticleRequest articleRequest) {
		return this.blogRepository.save(articleRequest.toEntity());
	}
	
	public List<Article> findAll(){
		return this.blogRepository.findAll();
	}
	
	public Article findById(long id) {
		return this.blogRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("> not found: " + id));	
	}
	
	public void delete(long id) {
		this.blogRepository.deleteById(id);
	}
	
	@Transactional
	public Article update(long id, UpdateArticleRequest request) {
		Article article = this.blogRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("> not found: " + id));
		article.update(request.getTitle(), request.getContent());
		return article;
	}
	
	

}
