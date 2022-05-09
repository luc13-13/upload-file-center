package com.lc.upload.file.center.enums;

import lombok.Getter;

/**
 * 规定所有path以 ‘/’ 结尾，便于路径拼接
 * @author: lucheng
 * @data: 2022/5/10 1:44
 * @version: 1.0
 */
@Getter
public enum FilePathEnum {
    ARTICLE_IMG("images/","文章图片目录");

    FilePathEnum(String path, String desc) {
        this.path = path;
        this.desc = desc;
    }

    private String path;
    private String desc;
}
