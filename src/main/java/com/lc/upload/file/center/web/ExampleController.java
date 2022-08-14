package com.lc.upload.file.center.web;

import com.lc.upload.file.center.enums.FilePathEnum;
import com.lc.upload.file.center.service.context.UploadStrategyContext;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author: lucheng
 * @data: 2022/4/19 21:52
 * @version: 1.0
 */
//@RestController
//@RequestMapping("/upload")
public class ExampleController {

//    @Autowired
    private UploadStrategyContext uploadStrategyContext;

//    @PostMapping("/img")
    public String upload(MultipartFile file) {
        return uploadStrategyContext.executeUpload(file, FilePathEnum.ARTICLE_IMG.getPath());
    }
}
