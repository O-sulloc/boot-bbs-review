package com.review.bbs3.domain.entity.repository;

import com.review.bbs3.domain.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}
