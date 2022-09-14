package com.lc.upload.file.center.enums;

import lombok.Getter;

/**
 * @author: lucheng
 * @data: 2022/5/9 21:57
 * @version: 1.0
 */
@Getter
public enum UploadStrategyEnum {

    UPLOAD_LOCAL("local","localUploadService"),
    UPLOAD_QINIU("qiniu","qiniuUploadService");

    UploadStrategyEnum(String strategy, String beanName) {
        this.strategy = strategy;
        this.beanName = beanName;
    }

    private final String strategy;
    private final String beanName;

    public static String getBeanName(String strategy) {
        for(UploadStrategyEnum strategyEnum : UploadStrategyEnum.values()) {
            if (strategyEnum.getStrategy().equals(strategy)) {
                return strategyEnum.getBeanName();
            }
        }
        return null;
    }
}
