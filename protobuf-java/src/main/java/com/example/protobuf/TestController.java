package com.example.protobuf;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Google Protobuf 测试
 *
 * @author hubin
 */
@RestController
public class TestController {
    private long LENGTH = 2000000;

    @CrossOrigin
    @GetMapping("/pb")
    public void pb(HttpServletResponse response) {
        try {
            WindGps.Wind.Builder builder = WindGps.Wind.getDefaultInstance().toBuilder();
            for (int i=0; i< LENGTH; i++) {
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


    @CrossOrigin
    @GetMapping("/json")
    public List<Map<String, Double>> json(HttpServletResponse response) {
        List<Map<String, Double>> wind = new ArrayList<>();
        for (int i=0; i< LENGTH; i++) {
            Map<String, Double> gps = new HashMap<>();
            gps.put("l", 30.3);
            gps.put("d", 130.11);
            gps.put("c", 173.5);
            wind.add(gps);
        }
        return wind;
    }
}
