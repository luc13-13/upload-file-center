package com.lc.upload.file.center.config;

import com.lc.upload.file.center.annotation.OnLocalStrategy;
import com.lc.upload.file.center.properties.UploadProperties;
import com.lc.upload.file.center.service.UploadService;
import com.lc.upload.file.center.service.context.UploadStrategyContext;
import com.lc.upload.file.center.service.impl.LocalUploadServiceImpl;
import com.lc.upload.file.center.service.impl.QiniuUploadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

@EnableConfigurationProperties(value = {UploadProperties.class,UploadStrategyContext.class})
@AutoConfigureAfter(value = {UploadStrategyContext.class, UploadProperties.class})
public class UploadStrategyAutoConfig {
    @Autowired
    public UploadStrategyContext uploadStrategyContext;

    @Autowired
    public UploadProperties uploadProperties;

    @ConditionalOnLocalStrategy
    @Bean(name = "localUploadService")
    public UploadService localUploadService() {
        UploadService uploadService = new LocalUploadServiceImpl(uploadProperties);
        return uploadService;
    }

    @ConditionalOnMissingBean(name = "localUploadService")
    public UploadService qiniuUploadService() {
        UploadService uploadService = new QiniuUploadServiceImpl(uploadProperties);
        return uploadService;
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE, ElementType.METHOD})
    @Documented
    @Conditional(OnLocalStrategy.class)
    private @interface ConditionalOnLocalStrategy {
    }

}
