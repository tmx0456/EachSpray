package com.eachspray.tan.godreply.ui.activity

import android.support.v7.widget.OrientationHelper
import com.alibaba.android.arouter.facade.annotation.Route
import com.eachspray.tan.godreply.R
import com.eachspray.tan.godreply.ui.adapter.GodReplyAdapter
import com.eachspray.tan.godreply.ui.viewpager.ViewPagerLayoutManager
import com.hazz.kotlinmvp.base.BaseActivity
import kotlinx.android.synthetic.main.activity_god_reply.*

/**
 * @author Tan
 * @date 2018/9/28 10:04
 */
@Route(path = "/godreply/GodReplyActivity")
class GodReplyActivity : BaseActivity() {

    var mLayoutManager: ViewPagerLayoutManager? = null

    override fun layoutId(): Int = R.layout.activity_god_reply

    override fun initData() {

    }

    override fun initView() {
        mLayoutManager = ViewPagerLayoutManager(this, OrientationHelper.HORIZONTAL)

        mRecyclerView.layoutManager = mLayoutManager
        var list = ArrayList<String>()
        list.add("111")
        list.add("111")
        list.add("111")
        list.add("111")
        mRecyclerView.adapter = GodReplyAdapter(this, list)
    }

    override fun start() {

    }

}