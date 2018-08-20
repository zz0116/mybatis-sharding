package com.zyz.demo.sharding.mapper;

import com.zyz.demo.sharding.model.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ArticleMapper {
    @Select("select title, content from article where uid = #{uid}")
    List<Article> getArticleByUser(@Param("uid") int uid);

    @Insert("insert into article (uid, title, content) values (#{article.uid}, #{article.title}, #{article.content})")
    boolean addArticle(@Param("article") Article article);
}
