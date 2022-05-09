package com.lc.upload.file.center.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author: lucheng
 * @data: 2022/5/9 21:53
 * @version: 1.0
 */
public interface UploadService {
    /**
     * 文件上传接口，path取决于不同上传策略。如果采用本地上传策略，则上传至本地目录；如果采用云端上传策略，上传至qiniu
     * @param file
     * @param path
     * @return
     */
    String uploadFile(MultipartFile file, String path);
}
