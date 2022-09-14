package com.lc.upload.file.center.annotation;


import com.lc.upload.file.center.service.context.UploadStrategyContext;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class OnLocalStrategy implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String strategy = context.getBeanFactory().getBean(UploadStrategyContext.class).getUploadEnable();
        return "local".equals(strategy);
    }
}
