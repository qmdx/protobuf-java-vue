package com.example.protobuf;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;

/**
 * Google Protobuf 测试
 *
 * @author hubin
 */
public class ProtobufTest {


    @Test
    public void parse() {
        try {
            WindGps.Wind wind = WindGps.Wind.parseFrom(new FileInputStream(new File("D:/abc.pb")));
            List<WindGps.Wind.Gps> gpsList = wind.getGpsList();
            gpsList.forEach(e -> System.err.println(e.getCoordinate()));
            System.out.println(gpsList.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void build() {
        WindGps.Wind.Builder builder = WindGps.Wind.getDefaultInstance().toBuilder();
        for (int i=0; i< 20000; i++) {
            WindGps.Wind.Gps.Builder wgb = WindGps.Wind.Gps.getDefaultInstance().toBuilder();
            wgb.setLongitude(30.3);
            wgb.setDimension(130.11);
            wgb.setCoordinate(173.5);
            builder.addGps(wgb);
        }
        byteToFile(builder.build().toByteArray(), "D:/abc.pb");
    }

    /**
     * byte[] 转 file
     *
     * @param bytes
     * @param path
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static void byteToFile(byte[] bytes, String path) {
        try {
            // 根据绝对路径初始化文件
            File localFile = new File(path);
            if (!localFile.exists()) {
                localFile.createNewFile();
            }
            // 输出流
            OutputStream os = new FileOutputStream(localFile);
            os.write(bytes);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
