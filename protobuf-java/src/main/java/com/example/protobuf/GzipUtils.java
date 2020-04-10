package com.example.protobuf;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * gzip 工具类
 */
@Slf4j
public class GzipUtils {

    /**
     * 请求响应 gzip 压缩文件流
     *
     * @param response 请求响应对象
     * @param output   输出文件字节数组
     * @throws IOException
     */
    public static void compress(HttpServletResponse response, byte[] output) throws IOException {
        byte[] gzipBytes = compress(output);
        response.setHeader("Content-Encoding", "gzip");
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("UTF-8");
        response.getOutputStream().write(gzipBytes);
    }

    /**
     * gzip 压缩
     *
     * @param data 待压缩字符数组
     * @return
     * @throws IOException
     */
    public static byte[] compress(byte[] data) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(out);
        gzip.write(data);
        gzip.close();
        return out.toByteArray();
    }

    /**
     * gzip 解压
     *
     * @param data 待解压字符数组
     * @return
     * @throws IOException
     */
    public static byte[] uncompress(byte[] data) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        GZIPInputStream unGzip = new GZIPInputStream(in);
        byte[] buffer = new byte[2048];
        int n;
        while ((n = unGzip.read(buffer)) >= 0) {
            out.write(buffer, 0, n);
        }
        return out.toByteArray();
    }
}