package com.eachspray.tan.godreply.mvp.model.bean

/**
 * @author Tan
 * @date 2018/10/10 17:02
 */
data class GodReplyBean(
        var total: Int,
        var nickName: String,
        var content: String,
        var replyList: ArrayList<Reply>) {
    data class Reply(
            var nickName: String,
            var content: String
    )
}

