package com.example.ssg_tube.data.remote

import com.example.ssg_tube.Constants
import com.example.ssg_tube.data.model.CategoryResponse
import com.example.ssg_tube.data.model.ChannelResponse
import com.example.ssg_tube.data.model.SearchResponse
import com.example.ssg_tube.data.model.VideoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeAPI {

    //영상의 제목, 설명, 썸네일, 조회수 등)를 받아올 것인지 지정할 수 있습니다. 이를 지정하는 것이 part 파라미터
    //snippet는 영상의 기본 정보(영상 제목, 설명, 썸네일, 채널 정보 등)를 가져오기 위한 파라미터 값
    //그런데 만약 영상의 조회수, 좋아요 수, 싫어요 수 등의 통계 정보를 받아오고 싶다면,
    //statistics라는 다른 파라미터 값을 추가로 사용
    //영상의 기본 정보와 통계 정보 모두를 요청하려면 part 파라미터에 "snippet,statistics"와 같이 두 값을 모두 지정

    // 최신 인기 비디오 목록
    @GET("videos")
    suspend fun videoPopularList(
        @Query("part") part: String,
        @Query("chart") chart: String,
        @Query("regionCode") regionCode: String,
        @Query("key") apiKey: String = Constants.AUTHORIZATION
    ): VideoResponse

    // 비디오 카테고리 ( 한국 카테고리 목록 )
    @GET("videoCategories")
    suspend fun videoCategories(
        @Query("part") part: String,
        @Query("regionCode") regionCode: String,
        @Query("key") apiKey: String = Constants.AUTHORIZATION
    ): CategoryResponse

    // 카테고리중 가장 인기있는 비디오 목록
    @GET("videos")
    suspend fun videoCategoriesList(
        @Query("part") part: String,
        @Query("chart") chart: String,
        @Query("regionCode") regionCode: String,
        @Query("videoCategoryId") videoCategoryId: String,
        @Query("key") apiKey: String = Constants.AUTHORIZATION
    ): VideoResponse

    // 선택한 카테고리에 해당하는 각 채널들의 정보
    @GET("channels")
    suspend fun videoChannel(
        @Query("part") part: String,
        @Query("id") id: String,
        @Query("key") apiKey: String = Constants.AUTHORIZATION
    ): ChannelResponse

    // 비디오 검색
    @GET("search")
    suspend fun videoSearch(
        // 필수 매개변수
        @Query("part") part: String, // part 매개변수는 API 응답이 포함하는 search 리소스 속성 하나 이상의 쉼표로 구분된 목록을 지정합니다. 매개변수 값을 snippet로 설정합니다.
        // 선택 매개변수
        @Query("q") query: String, // 검색어
        @Query("order") order: String, // 기본값 : relevance (date: 시간순, rating: 평점순, viewCount: 조회수)
        @Query("maxResults") maxResults: Int, // maxResults 매개변수는 결과 집합에 반환해야 하는 최대 항목 수를 지정합니다. 사용 가능한 값: 0~50 기본값은 5입니다.
        @Query("type") type: String, // type 매개변수는 특정 유형의 리소스만 검색하도록 검색어를 제한합니다. 값은 쉼표로 구분된 리소스 유형 목록입니다. 기본값은 video,channel,playlist입니다.
        @Query("videoType") videoType: String, // 특정 유형의 동영상으로 검색을 제한할 수 있습니다. 이 매개변수의 값을 지정하는 경우 type 매개변수의 값도 video로 설정해야 합니다. any - 모든 동영상 반환, episode - 에피소드, movie - 영화
        @Query("key") apiKey: String = Constants.AUTHORIZATION
    ): SearchResponse
}