package com.example.flo

data class Album(
    var title: String? = "",
    var singer : String? = "",
    var coverimg: Int? = null,
    var songs: ArrayList<Song>? = null
)
