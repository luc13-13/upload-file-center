package com.lc.upload.file.center.service.impl;

import com.lc.upload.file.center.properties.UploadProperties;
import com.lc.upload.file.center.service.AbstractUploadService;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author: lucheng
 * @data: 2022/5/14 1:24
 * @version: 1.0
 */
public class QiniuUploadServiceImpl extends AbstractUploadService {
    private String qiniuUrl;
    private String qiniuPath;

    public QiniuUploadServiceImpl(UploadProperties uploadProperties) {
        super(uploadProperties);
        this.qiniuPath = uploadProperties.path;
        this.qiniuUrl = uploadProperties.url;
    }

    @Override
    public boolean exist(String filePath) {
        return false;
    }

    @Override
    public void upload(String path, String filename, InputStream inputStream) throws IOException {

    }

    @Override
    public String getFileAccessUrl(String filePath) {
        return null;
    }
}
