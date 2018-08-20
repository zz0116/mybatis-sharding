package com.zyz.demo.sharding.model;

import com.zyz.demo.sharding.annotation.Sharding;
import com.zyz.demo.sharding.annotation.TableSeg;

@TableSeg(tableName = "article")
@Sharding(shardingKey = "uid")
public class Article {
    private int uid;
    private String title;
    private String content;

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
