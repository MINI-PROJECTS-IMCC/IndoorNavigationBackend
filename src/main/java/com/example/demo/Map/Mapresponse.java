package com.example.demo.Map;


import java.util.List;
import java.util.Map;


public class Mapresponse {
    private String mapUrl;
    private List<Map<String, Object>> points;

    public Mapresponse(String mapUrl, List<Map<String, Object>> points) {
        this.mapUrl = mapUrl;
        this.points = points;
    }

    public String getMapUrl() { return mapUrl; }
    public List<Map<String, Object>> getPoints() { return points; }
}
