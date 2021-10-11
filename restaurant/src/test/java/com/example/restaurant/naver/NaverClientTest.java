package com.example.restaurant.naver;

import com.example.restaurant.naver.dto.SearchImageReq;
import com.example.restaurant.naver.dto.SearchLocalReq;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NaverClientTest {

    @Autowired
    private NaverClient naverClient;

    @Test
    public void searchLocalTest(){
        var search = new SearchLocalReq();
        search.setQuery("갈비집");

        var result = naverClient.searchLocal(search);


        System.out.println(result);

        //SearchLocalRes(lastBuildDate=Tue, 28 Sep 2021 15:17:02 +0900,
        // total=1, start=1, display=1, category=null,
        // items=[SearchLocalRes.SearchLocalItem(title=장수갈비, link=,
        // description=, telephone=, address=서울특별시 중구 충무로1가 25-45,
        // roadAddress=서울특별시 중구 명동2길 54-1, mapx=310365, mapy=551469)])

        Assertions.assertNotNull(result.getItems().stream().findFirst().get().getCategory());
    }

    @Test
    public void searchImageTest(){
        var search = new SearchImageReq();
        search.setQuery("갈비집");

        var result = naverClient.searchImage(search);
        System.out.println(result);
        //SearchImageRes(lastBuildDate=Tue, 28 Sep 2021 15:29:48 +0900, total=14859, start=1,
        // display=1, items=[SearchImageRes.SearchLocalItem(title=여수 맛집 '은행동 갈비집' 연말 모임, 회식 장소로 인기,
        // link=http://imgnews.naver.net/image/5346/2019/12/20/0000146216_002_20191220155406734.jpg,
        // thumbnail=https://search.pstatic.net/common/?src=http://imgnews.naver.net/image/5346/2019/12/20/0000146216_002_20191220155406734.jpg&type=b150,
        // sizeheight=342, sizewidth=600)])
    }
}
