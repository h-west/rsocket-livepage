package io.hsjang.livepage.document;

import java.util.Map;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class PageGeoJson{

    String page;
    String type;
    GeoJsonPoint geometry;
    Map<String,Object> properties;

    public PageGeoJson(String page, GeoJsonPoint geometry){
        this.type = "Feature";
        this.page = page;
        this.geometry = geometry;
    }

    public PageGeoJson(String page, GeoJsonPoint geometry, Map<String,Object>properties){
        this(page,geometry);
        this.properties = properties;
    }
    
}