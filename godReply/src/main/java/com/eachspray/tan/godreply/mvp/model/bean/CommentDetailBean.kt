package com.moos.example.bean

/**
 * Created by moos on 2018/4/20.
 */

open class CommentDetailBean(
        var id: Int,
        var nickName: String,
        var userLogo: String,
        var content: String,
        var imgId: String,
        var replyTotal: Int,
        var createDate: String,
        var replyList: ArrayList<ReplyDetailBean>
)
