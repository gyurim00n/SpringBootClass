package org.doit.ik.blog.controller;

import java.util.List;

import org.doit.ik.blog.domain.Article;
import org.doit.ik.blog.dto.ArticleListViewResponse;
import org.doit.ik.blog.dto.ArticleViewResponse;
import org.doit.ik.blog.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/blog")
public class BlogViewController {
	
	private final BlogService blogService;
	
	@GetMapping("/articles")
	public String getArticles(Model model) {
		// List<Article> ->  List<ArticleListViewResponse> 변환
		List<ArticleListViewResponse> articles = this.blogService.findAll().stream().map(ArticleListViewResponse::new).toList();
		model.addAttribute("articles", articles);
		return "/blog/articleList";
	} 
	
	@GetMapping("/articles/{id}")	
	public String getArticle(@PathVariable(name = "id") Long id, Model model ) { 		
		Article article = this.blogService.findById(id);
		model.addAttribute("article", new ArticleViewResponse(article));
		return "/blog/article";
	}
	
	@GetMapping("/new-article")		
	public String newArticle(@RequestParam(name = "id", required = false) Long id, Model model ) { 
		if (id == null ) {
			model.addAttribute("article", new ArticleViewResponse());	// 생성: 빈 객체
		}else {
			Article article = this.blogService.findById(id);
			model.addAttribute("article", new ArticleViewResponse(article));	// 수정: id해당 객체
		} // if
		
		return "/blog/newArticle";
	}

}
