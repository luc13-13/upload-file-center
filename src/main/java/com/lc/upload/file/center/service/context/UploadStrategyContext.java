package com.lc.upload.file.center.service.context;

import com.lc.upload.file.center.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import static com.lc.upload.file.center.enums.UploadStrategyEnum.getBeanName;
import java.util.Map;

import static org.springframework.aop.interceptor.ExposeBeanNameAdvisors.getBeanName;

/**
 * 采用策略模式，将所有可能的服务封装在map中，调用时根据约定，从map中取出指定的服务
 * 适用于多个服务参数相同，但处理逻辑有区别的情景
 * @author: lucheng
 * @data: 2022/5/10 2:40
 * @version: 1.0
 */
@Service
public class UploadStrategyContext {
    @Value("${upload.enable}")
    private String uploadModel;

    @Autowired
    private Map<String, UploadService> uploadServiceMap;

    public String executeUpload(MultipartFile file, String path) {
        return uploadServiceMap.get(getBeanName(uploadModel)).uploadFile(file, path);
    }
}
