package com.review.bbs3.domain.dto;

import com.review.bbs3.domain.entity.Article;
import lombok.Getter;

@Getter
public class ArticleDTO {
    private String title;
    private String contents;

    public ArticleDTO(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public Article toEntity(){
        return new Article(this.title, this.contents);
    }

}
