package com.example.restaurant.naver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class SearchImageReq {
    private String query = ""; //필수

    private int display = 1; //1~5

    private int start = 1; //1

    private String sort = "sim"; //sim (유사도순), date (날짜순)

    private String filter = "all;";//all, large, medium, small

    public MultiValueMap<String, String> toMultiValueMap(){
        var map = new LinkedMultiValueMap<String, String>();

        map.add("query", query);
        map.add("display", String.valueOf(display));
        map.add("start",String.valueOf(start));
        map.add("sort", sort);
        map.add("filter", filter);

        return map;

    }


}
