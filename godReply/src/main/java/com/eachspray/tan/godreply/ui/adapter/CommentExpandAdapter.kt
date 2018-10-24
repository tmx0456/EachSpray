package com.eachspray.tan.godreply.ui.adapter

import android.content.Context
import android.graphics.Color
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ImageView
import android.widget.TextView
import com.eachspray.tan.godreply.R
import com.moos.example.bean.CommentDetailBean
import com.moos.example.bean.ReplyDetailBean
import java.lang.IllegalArgumentException
import java.util.*

/**
 * @author Tan
 * @date 2018/10/8 16:00
 */
 
class CommentExpandAdapter (context: Context, commentList:ArrayList<CommentDetailBean>): BaseExpandableListAdapter() {
    var commentList = commentList
    val context = context
    var isLike = false

    override fun getGroup(groupPosition: Int): Any =commentList[groupPosition]

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean =true

    override fun hasStableIds(): Boolean = true

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        val groupHolder: GroupHolder

        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.comment_item_layout, parent,false)
            groupHolder = GroupHolder(convertView)
            convertView.tag = groupHolder
        }else{
            groupHolder = convertView.tag as GroupHolder
        }
//        ImageLoad().load(feedImageUrl.toString(),groupHolder.logo)

        groupHolder.tv_name.text = commentList[groupPosition].nickName
        groupHolder.tv_time.text = commentList[groupPosition].createDate
        groupHolder.tv_content.text = commentList[groupPosition].content
        groupHolder.iv_like.setOnClickListener {
            if (isLike) {
                isLike = false
                groupHolder.iv_like.setColorFilter(Color.parseColor("#aaaaaa"))
            } else {
                isLike = true
                groupHolder.iv_like.setColorFilter(Color.parseColor("#FF5C5C"))
            }
        }
        return convertView!!
    }

    override fun getChildrenCount(groupPosition: Int): Int = commentList[groupPosition].replyList!!.size

    override fun getChild(groupPosition: Int, childPosition: Int): Any =commentList[groupPosition].replyList!![childPosition]

    override fun getGroupId(groupPosition: Int): Long =groupPosition.toLong()

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View {

        var convertView = convertView
        val childHolder: ChildHolder
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.comment_reply_item_layout, parent, false)
            childHolder = ChildHolder(convertView)
            convertView!!.tag = childHolder
        } else {
            childHolder = convertView.tag as ChildHolder
        }

        val replyUser = commentList[groupPosition].replyList[childPosition].nickName
        if (!TextUtils.isEmpty(replyUser)) {
            childHolder.tv_name.text = "$replyUser:"
        } else {
            childHolder.tv_name.text = "无名" + ":"
        }

        childHolder.tv_content.text = commentList[groupPosition].replyList[childPosition].content

        return convertView
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long =getCombinedChildId(groupPosition.toLong(), childPosition.toLong())



    override fun getGroupCount(): Int = commentList.size


    private inner class GroupHolder(view: View) {
         var logo: ImageView
        var tv_name: TextView
        var tv_content: TextView
        var tv_time: TextView
        var iv_like: ImageView

        init {
            logo = view.findViewById(R.id.comment_item_logo) as ImageView
            tv_content = view.findViewById(R.id.comment_item_content) as TextView
            tv_name = view.findViewById(R.id.comment_item_userName) as TextView
            tv_time = view.findViewById(R.id.comment_item_time) as TextView
            iv_like = view.findViewById(R.id.comment_item_like) as ImageView
        }
    }

    private inner class ChildHolder(view: View) {
        var tv_name: TextView
        var tv_content: TextView

        init {
            tv_name = view.findViewById(R.id.reply_item_user) as TextView
            tv_content = view.findViewById(R.id.reply_item_content) as TextView
        }
    }


    /**
     * by moos on 2018/04/20
     * func:评论成功后插入一条数据
     * @param commentList 新的评论数据
     */
    fun addTheCommentData(commentListBean: CommentDetailBean?) {
        if (commentListBean != null) {

            commentList.add(commentListBean)
            notifyDataSetChanged()
        } else {
            throw IllegalArgumentException("评论数据为空!")
        }

    }

    /**
     * by moos on 2018/04/20
     * func:回复成功后插入一条数据
     * @param replyDetailBean 新的回复数据
     */
    fun addTheReplyData(replyDetailBean: ReplyDetailBean, groupPosition: Int) {
        if (replyDetailBean != null) {
            Log.e("TAG", "addTheReplyData: >>>>该刷新回复列表了:" + replyDetailBean.toString())
            if (commentList[groupPosition].replyList != null) {
                commentList[groupPosition].replyList.add(replyDetailBean)
            } else {
                val replyList = ArrayList<ReplyDetailBean>()
                replyList.add(replyDetailBean)
                commentList[groupPosition].replyList = replyList
            }
            notifyDataSetChanged()
        } else {
            throw IllegalArgumentException("回复数据为空!")
        }

    }

    /**
     * by moos on 2018/04/20
     * func:添加和展示所有回复
     * @param replyBeanList 所有回复数据
     * @param groupPosition 当前的评论
     */
    private fun addReplyList(replyBeanList: ArrayList<ReplyDetailBean>, groupPosition: Int) {
        if (commentList[groupPosition].replyList != null) {
            commentList[groupPosition].replyList!!.clear()
            commentList[groupPosition].replyList!!.addAll(replyBeanList)
        } else {

            commentList[groupPosition].replyList = replyBeanList
        }

        notifyDataSetChanged()
    }

}