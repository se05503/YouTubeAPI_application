package com.example.ssg_tube.data.model

import com.google.gson.annotations.SerializedName
import java.util.Date

// 검색
data class SearchResponse(
    @SerializedName("nextPageToken") val nextPageToken: String?, // pageToken 매개변수의 값으로 사용하여 결과 집합의 다음 페이지를 검색할 수 있는 토큰입니다 → 무한 스크롤 기능시 필요
    @SerializedName("prevPageToken") val prevPageToken: String?, // pageToken 매개변수의 값으로 사용하여 결과 집합의 이전 페이지를 검색할 수 있는 토큰입니다.
    @SerializedName("pageInfo") val pageInfo: SearchPageInfoItem, // pageInfo 객체는 결과 집합의 페이지 정보를 요약합니다.
    @SerializedName("items") val items: List<SearchItem>
)

data class SearchItem(
    @SerializedName("id") val id: SearchVideoId,
    @SerializedName("snippet") val snippet: VideoSearchSnippet,
)

data class SearchVideoId(
    @SerializedName("kind") val kind: String,
    @SerializedName("videoId") val videoId: String, // id.type 속성의 값이 youtube#video인 경우 이 속성이 표시되고 속성의 값은 YouTube가 검색어와 일치하는 동영상을 고유하게 식별하는 데 사용하는 ID를 포함합니다.
)

data class VideoSearchSnippet(
    @SerializedName("publishedAt") val date: Date, // 검색 결과가 식별하는 리소스의 생성 날짜 및 시간입니다
    @SerializedName("title") val title: String, // 검색 결과의 제목입니다.
    @SerializedName("thumbnails") val thumbnails: ThumbnailKey, // 키는 미리보기 이미지의 이름이고 값은 미리보기 이미지에 대한 기타 정보를 포함하는 개체입니다.
    @SerializedName("description") val description: String, // 검색 결과의 설명입니다.
    @SerializedName("channelId") val channelId: String // YouTube가 검색 결과로 식별하는 리소스(비디오)를 게시한 채널을 고유하게 식별하는 데 사용하는 값입니다.

)

data class ThumbnailKey(
    @SerializedName("default") val default: ThumbnailValue,
    @SerializedName("medium") val medium: ThumbnailValue,
    @SerializedName("high") val high: ThumbnailValue
)

data class ThumbnailValue(
    @SerializedName("url") val url: String
)

data class SearchPageInfoItem(
    @SerializedName("totalResults") val totalResults: Int?, // 결과 집합의 총 결과 수입니다.이 값은 근사값이며 정확한 값을 나타내지 않을 수 있습니다. 또한 최댓값은 1,000,000입니다. 페이지로 나누기 링크를 만드는 데 이 값을 사용해서는 안 됩니다. 대신 nextPageToken 및 prevPageToken 속성 값을 사용하여 페이지로 나누기 링크를 표시할지 결정합니다.
    @SerializedName("resultsPerPage") val resultsPerPage: Int? // API 응답에 포함된 결과 수입니다.
)
