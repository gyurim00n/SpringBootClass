package org.doit.ik.blog.repository;

import org.doit.ik.blog.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long>{

}
