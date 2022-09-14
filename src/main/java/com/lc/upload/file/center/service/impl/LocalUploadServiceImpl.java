package com.lc.upload.file.center.service.impl;

import com.lc.upload.file.center.properties.UploadProperties;
import com.lc.upload.file.center.service.AbstractUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

/**
 * @author: lucheng
 * @data: 2022/5/10 2:28
 * @version: 1.0
 */
//@Service("localUploadServiceImpl")
//@Slf4j
public class LocalUploadServiceImpl extends AbstractUploadService {
    Logger log = Logger.getLogger("localUploadServiceImpl");
//    @Value("${upload.local.path}")
    private String localPath;

//    @Value("${upload.local.url}")
    private String url;

    public LocalUploadServiceImpl(UploadProperties uploadProperties) {
        super(uploadProperties);
    }

    @Override
    public boolean exist(String filePath) {
        log.info("检查文件是否已存在: " + filePath);
        return new File(filePath).exists();
    }

    @Override
    public void upload(String path, String filename, InputStream inputStream) throws IOException {
        // 检查目录是否存在, 不存在则创建
        File directory = new File(localPath + path);
        if(!directory.exists()) {
            log.info("目录不存在");
            if(!directory.mkdirs()) {
                throw new IllegalStateException("目录创建失败！");
            }
            log.info("目录创建成功: "+directory.getAbsolutePath());
        }

        File targetFile = new File(localPath + path + filename);
        if (targetFile.createNewFile()) {
            // TODO:采用NIO的形式上传文件
            // 从inputStream创建buffer
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            // 对目标文件创建输出buffer
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(targetFile));
            byte[] bytes = new byte[1024];
            int length;
            while ((length = bis.read(bytes)) != -1) {
                bos.write(bytes, 0, length);
            }
            bos.flush();
            inputStream.close();
            bis.close();
            bos.close();
        }
        log.info("文件上传完毕: "+targetFile.getAbsolutePath());
    }

    @Override
    public String getFileAccessUrl(String filePath) {
        return url+filePath;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
