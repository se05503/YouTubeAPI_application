package com.example.ssg_tube.data.model

import com.google.gson.annotations.SerializedName
import java.util.Date

// 검색
data class SearchResponse(
    @SerializedName("items") val items: List<SearchItem>
)

data class SearchItem(
    @SerializedName("id") val id: SearchVideoId,
    @SerializedName("snippet") val snippet: VideoSearchSnippet
)

data class SearchVideoId(
    @SerializedName("kind") val kind: String,
    @SerializedName("videoId") val videoId: String, // id.type 속성의 값이 youtube#video인 경우 이 속성이 표시되고 속성의 값은 YouTube가 검색어와 일치하는 동영상을 고유하게 식별하는 데 사용하는 ID를 포함합니다.

)

data class VideoSearchSnippet(
    @SerializedName("publishedAt") val date : Date, // 검색 결과가 식별하는 리소스의 생성 날짜 및 시간입니다
    @SerializedName("title") val title:String, // 검색 결과의 제목입니다.
    @SerializedName("thumbnails") val thumbnails: ThumbnailKey, // 키는 미리보기 이미지의 이름이고 값은 미리보기 이미지에 대한 기타 정보를 포함하는 개체입니다.
    @SerializedName("description") val description : String, // 검색 결과의 설명입니다.
    @SerializedName("channelId") val channelId : String // YouTube가 검색 결과로 식별하는 리소스(비디오)를 게시한 채널을 고유하게 식별하는 데 사용하는 값입니다.

)

data class ThumbnailKey(
    @SerializedName("default") val default: ThumbnailValue,
    @SerializedName("medium") val medium: ThumbnailValue,
    @SerializedName("high") val high: ThumbnailValue
)

data class ThumbnailValue(
    @SerializedName("url") val url: String
)
