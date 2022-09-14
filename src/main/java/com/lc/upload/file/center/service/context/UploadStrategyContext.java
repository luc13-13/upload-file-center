package com.lc.upload.file.center.service.context;

import com.lc.upload.file.center.enums.UploadStrategyEnum;
import com.lc.upload.file.center.service.UploadService;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.multipart.MultipartFile;
import static com.lc.upload.file.center.enums.UploadStrategyEnum.getBeanName;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.aop.interceptor.ExposeBeanNameAdvisors.getBeanName;

/**
 * 采用策略模式，将所有可能的服务封装在map中，调用时根据约定，从map中取出指定的服务
 * 适用于多个服务参数相同，但处理逻辑有区别的情景
 *
 * @author: lucheng
 * @data: 2022/5/10 2:40
 * @version: 1.0
 */
@Data
@ConfigurationProperties(prefix = "upload.strategy")
public class UploadStrategyContext implements InitializingBean {
    //    @Value("${upload.enable}")
    private String uploadEnable;

    //    @Autowired
    private Map<String, UploadService> uploadServiceMap;

    public String executeUpload(MultipartFile file, String path) {
        return uploadServiceMap.get(
                getBeanName(UploadStrategyEnum.getBeanName(uploadEnable)))
                .uploadFile(file, path);
    }

    public UploadStrategyContext() {

    }

    public UploadStrategyContext(String uploadModel, Map<String, UploadService> uploadServiceMap) {
        this.uploadEnable = uploadModel;
        this.uploadServiceMap = uploadServiceMap;
    }

    public void putStrategyContext() {

    }

    public String getUploadEnable() {
        return uploadEnable;
    }

    public void setUploadEnable(String uploadEnable) {
        this.uploadEnable = uploadEnable;
    }

    public Map<String, UploadService> getUploadServiceMap() {
        return uploadServiceMap;
    }

    public void setUploadServiceMap(Map<String, UploadService> uploadServiceMap) {
        this.uploadServiceMap = uploadServiceMap;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.uploadServiceMap = new HashMap<>();
    }
}
