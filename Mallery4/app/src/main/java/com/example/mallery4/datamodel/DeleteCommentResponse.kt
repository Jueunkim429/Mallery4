package com.example.mallery4.datamodel

import com.google.gson.annotations.SerializedName

data class DeleteCommentResponse(
    @SerializedName("result") var result: String,
    @SerializedName("commentId") var commentId:Long,
    @SerializedName("message") var message:String
)