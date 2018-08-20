package com.zyz.demo.sharding.service;

import com.zyz.demo.sharding.mapper.ArticleMapper;
import com.zyz.demo.sharding.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    private ArticleMapper articleMapper;

    @Autowired
    public void setArticleMapper(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    public List<Article> selectArticleByUid(int uid) {
        return articleMapper.getArticleByUser(uid);
    }

    public boolean saveUser(Article user) {
        return articleMapper.addArticle(user);
    }
}
