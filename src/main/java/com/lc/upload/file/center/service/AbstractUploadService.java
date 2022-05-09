package com.lc.upload.file.center.service;

import com.lc.upload.file.center.service.UploadService;
import com.lc.upload.file.center.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidParameterException;

/**
 * @author: lucheng
 * @data: 2022/5/9 21:55
 * @version: 1.0
 */
@Slf4j
public abstract class AbstractUploadService implements UploadService{

    /**
     * 默认file为md文件
     * @param file
     * @param path
     * @return
     */
    @Override
    public String uploadFile(MultipartFile file, String path) {
        try {
            // 检查文件大小
            if(FileUtils.MAX_SIZE < (file.getSize()/1024)) {
                int maxSize = FileUtils.MAX_SIZE/1024;
                throw new InvalidParameterException("文件大小不能超过"+ maxSize + "Mb");
            }
            // TODO:在FileUtils中规定文件后缀集
            String extName = FileUtils.getFileExt(file.getOriginalFilename());

            // 计算文件名
            String fileName = FileUtils.getMd5(file.getInputStream()) + extName;
            log.info("上传文件：{}",fileName);
            /** 优化路径检查，这里的path只是{@link FilePathEnum}中的相对路径，检查没有意义 */
            if(!exist(path + fileName)) {
                upload(path, fileName, file.getInputStream());
            } else {
                log.info("文件已存在：{}",path+fileName);
            }
            return getFileAccessUrl(path + fileName);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("文件上传失败");
        }
    }


    /**
     * 检查文件服务器中是否已有指定文件
     * @param filePath 目标文件在服务器中的绝对路径
     * @return
     */
    public abstract boolean exist(String filePath);


    /**
     * 上传文件至指定目录
     * @param path
     * @param filename
     * @param inputStream
     */
    public abstract void upload(String path, String filename, InputStream inputStream) throws IOException;

    /**
     * 将服务器目录映射为地址
     * @param filePath
     * @return
     */
    public abstract String getFileAccessUrl(String filePath);


}
