package com.lc.upload.file.center.utils;

import com.qiniu.util.Hex;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * @author: lucheng
 * @data: 2022/5/10 1:51
 * @version: 1.0
 */
public class FileUtils {
    public static final String FILE_EXT = "jpg,png,jpeg";
    // 文件大小限制，单位kb
    public static final Integer MAX_SIZE = 51200;

    public static String getMd5(InputStream inputStream) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            byte[] buffer = new byte[8192];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                md5.update(buffer, 0, length);
            }
            return new String(Hex.encodeHex(md5.digest()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 获取文件后缀
     * @param originalFilename
     * @return
     */
    public static String getFileExt(String originalFilename) throws Exception{
        if (StringUtils.isBlank(originalFilename)) {
            return "";
        }
        String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
        if (!Arrays.asList(FILE_EXT.split(",")).contains(ext)) {
            throw new Exception("unsupported file format");
        }
        return ext;
    }
}
