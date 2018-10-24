package com.tan.eachspray.ui.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eachspray.tan.godreply.ui.activity.GodReplyActivity
import com.eachspray.tan.tanbase.utils.RandUtils
import com.tan.eachspray.R
import com.tan.eachspray.view.recyclerview.ViewHolder
import com.tan.eachspray.view.recyclerview.adapter.CommonAdapter

/**
 * @author Tan
 * @date 2018/9/27 15:25
 */

class HomeIconAdapter(context: Context, data: ArrayList<String>)
    : CommonAdapter<String>(context, data, -1) {

    override fun bindData(holder: ViewHolder, data: String, position: Int) {
        holder.setText(R.id.tvTitle, data)
        holder.setOnItemClickListener(View.OnClickListener {
//            ARouter.getInstance().build("/godreply/GodReplyActivity").navigation()
            if (position == 0){
                Log.e("Tag", "随机 : " + RandUtils().randInt(0,2))
            }else{
                mContext.startActivity(Intent(mContext, GodReplyActivity::class.java))
            }
        })
    }

    /**
     *  创建布局
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        return ViewHolder(inflaterView(R.layout.item_home_banner, parent))

        return ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home_icon,parent,false))
    }

    override fun getItemCount(): Int {
        return when {
            mData.isEmpty() -> 0
            else ->  mData.size
        }
    }

}