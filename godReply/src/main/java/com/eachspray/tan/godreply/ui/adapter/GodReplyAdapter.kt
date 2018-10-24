package com.eachspray.tan.godreply.ui.adapter

import android.content.Context
import android.graphics.Color
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialog
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ExpandableListView
import android.widget.Toast
import com.eachspray.tan.godreply.R
import com.google.gson.Gson
import com.moos.example.bean.CommentBean
import com.moos.example.bean.CommentDetailBean
import com.moos.example.bean.ReplyDetailBean
import com.tan.eachspray.view.recyclerview.ViewHolder
import com.tan.eachspray.view.recyclerview.adapter.CommonAdapter

/**
 * @author Tan
 * @date 2018/9/27 15:25
 */

class GodReplyAdapter(context: Context, data: ArrayList<String>)
    : CommonAdapter<String>(context, data, -1) {

    var dialog : BottomSheetDialog? = null
    var adapter :CommentExpandAdapter? =null
    var commentDetailBeanList : ArrayList<CommentDetailBean>? = null
    var expandableListView : ExpandableListView?= null
    val context = context
    val testJson = "{\n" +
            "\t\t\"total\": 3,\n" +
            "\t\t\"list\": [{\n" +
            "\t\t\t\"id\": 42,\n" +
            "\t\t\t\"nickName\": \"程序猿\",\n" +
            "\t\t\t\"userLogo\": \"http://ucardstorevideo.b0.upaiyun.com/userLogo/9fa13ec6-dddd-46cb-9df0-4bbb32d83fc1.png\",\n" +
            "\t\t\t\"content\": \"时间是一切财富中最宝贵的财富。\",\n" +
            "\t\t\t\"imgId\": \"xcclsscrt0tev11ok364\",\n" +
            "\t\t\t\"replyTotal\": 1,\n" +
            "\t\t\t\"createDate\": \"三分钟前\",\n" +
            "\t\t\t\"replyList\": [{\n" +
            "\t\t\t\t\"nickName\": \"沐風\",\n" +
            "\t\t\t\t\"userLogo\": \"http://ucardstorevideo.b0.upaiyun.com/userLogo/9fa13ec6-dddd-46cb-9df0-4bbb32d83fc1.png\",\n" +
            "\t\t\t\t\"id\": 40,\n" +
            "\t\t\t\t\"commentId\": \"42\",\n" +
            "\t\t\t\t\"content\": \"时间总是在不经意中擦肩而过,不留一点痕迹.\",\n" +
            "\t\t\t\t\"status\": \"01\",\n" +
            "\t\t\t\t\"createDate\": \"一个小时前\"\n" +
            "\t\t\t}]\n" +
            "\t\t}, {\n" +
            "\t\t\t\"id\": 41,\n" +
            "\t\t\t\"nickName\": \"设计狗\",\n" +
            "\t\t\t\"userLogo\": \"http://ucardstorevideo.b0.upaiyun.com/userLogo/9fa13ec6-dddd-46cb-9df0-4bbb32d83fc1.png\",\n" +
            "\t\t\t\"content\": \"这世界要是没有爱情，它在我们心中还会有什么意义！这就如一盏没有亮光的走马灯。\",\n" +
            "\t\t\t\"imgId\": \"xcclsscrt0tev11ok364\",\n" +
            "\t\t\t\"replyTotal\": 1,\n" +
            "\t\t\t\"createDate\": \"一天前\",\n" +
            "\t\t\t\"replyList\": [{\n" +
            "\t\t\t\t\"nickName\": \"沐風\",\n" +
            "\t\t\t\t\"userLogo\": \"http://ucardstorevideo.b0.upaiyun.com/userLogo/9fa13ec6-dddd-46cb-9df0-4bbb32d83fc1.png\",\n" +
            "\t\t\t\t\"commentId\": \"41\",\n" +
            "\t\t\t\t\"content\": \"时间总是在不经意中擦肩而过,不留一点痕迹.\",\n" +
            "\t\t\t\t\"status\": \"01\",\n" +
            "\t\t\t\t\"createDate\": \"三小时前\"\n" +
            "\t\t\t}]\n" +
            "\t\t}, {\n" +
            "\t\t\t\"id\": 40,\n" +
            "\t\t\t\"nickName\": \"产品喵\",\n" +
            "\t\t\t\"userLogo\": \"http://ucardstorevideo.b0.upaiyun.com/userLogo/9fa13ec6-dddd-46cb-9df0-4bbb32d83fc1.png\",\n" +
            "\t\t\t\"content\": \"笨蛋自以为聪明，聪明人才知道自己是笨蛋。\",\n" +
            "\t\t\t\"imgId\": \"xcclsscrt0tev11ok364\",\n" +
            "\t\t\t\"replyTotal\": 0,\n" +
            "\t\t\t\"createDate\": \"三天前\",\n" +
            "\t\t\t\"replyList\": []\n" +
            "\t\t}]\n" +
            "\t}"

    override fun bindData(holder: ViewHolder, data: String, position: Int) {
//        holder.setText(R.id.tvTitle, data)
        holder.setOnItemClickListener(View.OnClickListener {

        })
       expandableListView  = holder.getView<ExpandableListView>(R.id.detail_page_lv_comment)
        expandableListView!!.setGroupIndicator(null)
        var gson = Gson()
        var godReplyBean = gson.fromJson<CommentBean>(testJson, CommentBean::class.java)

        Log.e("TAG", "0000 ========>>>>>> " + godReplyBean.total)
        Log.e("TAG", "0000 ========>>>>>> " + godReplyBean.list.toString())
        Log.e("TAG", "1111111 ========>>>>>> " + commentDetailBeanList)
        Log.e("TAG", "2222222222 ========>>>>>> " + godReplyBean.list!!)

        commentDetailBeanList = ArrayList<CommentDetailBean>()
        commentDetailBeanList!!.addAll(godReplyBean.list)
        adapter = CommentExpandAdapter(context,commentDetailBeanList!!)

        expandableListView!!.setAdapter(adapter)
        for (i in commentDetailBeanList!!.indices){
            expandableListView!!.expandGroup(i)
        }

        expandableListView!!.setOnGroupClickListener(object : ExpandableListView.OnGroupClickListener {
            override fun onGroupClick(parent: ExpandableListView?, v: View?, groupPosition: Int, id: Long): Boolean {
                showReplyDialog(groupPosition);
                return true
            }
        })
    }

    /**
     *  创建布局
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.frgment_god_reply,parent,false))
    }

    override fun getItemCount(): Int {
        return when {
            mData.isEmpty() -> 0
            else ->  mData.size
        }
    }

    /**
     * by moos on 2018/04/20
     * func:弹出评论框
     */
    private fun showCommentDialog() {
        dialog = BottomSheetDialog(context)
        var commentView = LayoutInflater.from(context).inflate(R.layout.comment_dialog_layout, null)
        var commentText = commentView.findViewById(R.id.dialog_comment_et) as EditText
        var bt_comment = commentView.findViewById(R.id.dialog_comment_bt) as Button
        dialog!!.setContentView(commentView)
        /**
         * 解决bsd显示不全的情况
         */
        var parent = commentView.parent as View
        var behavior = BottomSheetBehavior.from(parent)
        commentView.measure(0, 0)
        behavior.peekHeight = commentView.measuredHeight

        bt_comment.setOnClickListener {
            var commentContent = commentText.text.toString().trim { it <= ' ' }
//            if (!TextUtils.isEmpty(commentContent)) {
//
//                //commentOnWork(commentContent);
//                dialog!!.dismiss()
//                val detailBean = CommentDetailBean("小明", commentContent, "刚刚")
//                adapter!!.addTheCommentData(detailBean)
//                Toast.makeText(this@MainActivity, "评论成功", Toast.LENGTH_SHORT).show()
//
//            } else {
//                Toast.makeText(this@MainActivity, "评论内容不能为空", Toast.LENGTH_SHORT).show()
//            }
        }
        commentText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                if (!TextUtils.isEmpty(charSequence) && charSequence.length > 2) {
                    bt_comment.setBackgroundColor(Color.parseColor("#FFB568"))
                } else {
                    bt_comment.setBackgroundColor(Color.parseColor("#D8D8D8"))
                }
            }

            override fun afterTextChanged(editable: Editable) {

            }
        })
        dialog!!.show()
    }

    /**
     * by moos on 2018/04/20
     * func:弹出回复框
     */
    private fun showReplyDialog(position: Int) {
        dialog = BottomSheetDialog(context)
        var commentView = LayoutInflater.from(context).inflate(R.layout.comment_dialog_layout, null)
        var commentText = commentView.findViewById<EditText>(R.id.dialog_comment_et)
        var bt_comment = commentView.findViewById<Button>(R.id.dialog_comment_bt)
        Log.e("Tag","1111 ===>>>>> " + commentText)
        Log.e("Tag","1111 ===>>>>> " + commentDetailBeanList)
        Log.e("Tag","1111 ===>>>>> " + commentDetailBeanList!![position])
        Log.e("Tag","1111 ===>>>>> " + commentDetailBeanList!![position]!!.nickName)
        commentText?.hint = "回复 " + commentDetailBeanList!![position]?.nickName + " 的评论:"
        dialog!!.setContentView(commentView)
        bt_comment!!.setOnClickListener {
            var replyContent = commentText.text.toString().trim { it <= ' ' }
            if (!TextUtils.isEmpty(replyContent)) {

                dialog!!.dismiss()
                val detailBean = ReplyDetailBean("小红",replyContent)

                adapter!!.addTheReplyData(detailBean, position)
                expandableListView!!.expandGroup(position)
                Toast.makeText(context, "回复成功", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "回复内容不能为空", Toast.LENGTH_SHORT).show()
            }
        }
        commentText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                if (!TextUtils.isEmpty(charSequence) && charSequence.length > 2) {
                    bt_comment.setBackgroundColor(Color.parseColor("#FFB568"))
                } else {
                    bt_comment.setBackgroundColor(Color.parseColor("#D8D8D8"))
                }
            }

            override fun afterTextChanged(editable: Editable) {

            }
        })
        dialog!!.show()
    }
}