package com.example.ssg_tube.data.model

import com.google.gson.annotations.SerializedName

// 카테고리
data class CategoryResponse(
    @SerializedName("items") val items: List<CategoryItem>
)

data class CategoryItem(
    @SerializedName("id") val id: String,
    @SerializedName("snippet") val snippet: CategorySnippet,
    @SerializedName("nextTokenPage") val nextTokenPage: String,
    @SerializedName("prevTokenPage") val prevTokenPage: String,
    @SerializedName("pageInfo") val pageInfo: CategoryPageInfoItem
)

data class CategorySnippet(
    @SerializedName("title") val title: String
)

data class CategoryPageInfoItem(
    @SerializedName("totalResults") val totalResults: Int,
    @SerializedName("resultsPerPage") val resultsPerPage: Int
)