package com.review.bbs3.controller;

import com.review.bbs3.domain.dto.ArticleDTO;
import com.review.bbs3.domain.entity.Article;
import com.review.bbs3.domain.entity.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("articles")
public class ArticleController {

    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/new")
    public String getForm(){

        return "articles/new";
    }

    @PostMapping("")
    public String add(ArticleDTO articleDTO){
        log.info(articleDTO.getTitle());
        Article savedArticle = articleRepository.save(articleDTO.toEntity());
        log.info("generatedId:{}",savedArticle.getId());
        log.info("generatedId:{}",savedArticle.getContents());

        return String.format("redirect:/articles/list/%d", savedArticle.getId());
        // 글 아이디를 통해 방금 작성한 글 보러 redirect
    }

    @GetMapping("showOne/{id}")
    public String getOne(@PathVariable Long id, Model model){
        Optional<Article> optArticle =articleRepository.findById(id);

        if(!optArticle.isEmpty()){
            // 잘 가지고 오면
            model.addAttribute("article", optArticle.get());

            return "articles/showOne";
        }else{
            //못 가져오면
            return "articles/error";
        }
    }

    @GetMapping("list")
    public String getAll(Model model){
        List<Article> articleList = articleRepository.findAll();
        model.addAttribute("articleList",articleList);
        return "articles/list";
    }
}
