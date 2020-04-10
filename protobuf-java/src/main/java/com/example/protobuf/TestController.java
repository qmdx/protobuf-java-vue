package com.example.protobuf;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Google Protobuf 测试
 *
 * @author hubin
 */
@RestController
public class TestController {

    @CrossOrigin
    @GetMapping("/pb")
    public void pb(HttpServletResponse response) {
        try {
            WindGps.Wind.Builder builder = WindGps.Wind.getDefaultInstance().toBuilder();
            for (int i=0; i< 200000; i++) {
                WindGps.Wind.Gps.Builder wgb = WindGps.Wind.Gps.getDefaultInstance().toBuilder();
                wgb.setLongitude(30.3);
                wgb.setDimension(130.11);
                wgb.setCoordinate(173.5);
                builder.addGps(wgb);
            }
            GzipUtils.compress(response, builder.build().toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
