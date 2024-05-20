package com.example.ssg_tube.presentaion.util

enum class CategoryType(val categoryId: String, val categoryName: String) {
    MOVIE_AND_ANIMATION("1", "영화및 애니메이션"),
    MUSIC("10", "음악"),
    PETS_AND_ANIMALS("15", "애완동물및 동물"),
    SPORTS("17", "스포츠"),
    GAMING("20", "게임"),
    NEWS_AND_POLITICS("25", "뉴스및 정치"),
    SCIENCE_AND_TECHNOLOGY("28", "과학및 기술");

    companion object {
        fun from(categoryName: String): CategoryType? {
            return entries.find { it.categoryName == categoryName }
        }
    }
}