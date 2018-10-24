package com.tan.eachspray.mvp.model

import com.eachspray.tan.tanbase.net.RetrofitManager
import com.hazz.kotlinmvp.rx.scheduler.SchedulerUtils
import com.tan.eachspray.api.ApiService
import com.tan.eachspray.mvp.model.bean.HomeBean
import io.reactivex.Observable

/**
 * Created by xuhao on 2017/11/21.
 * desc: 首页精选 model
 */

class HomeModel{

    /**
     * 获取首页 Banner 数据
     */
    fun requestHomeData(num:Int):Observable<HomeBean>{
        return RetrofitManager.getService(ApiService::class.java).getFirstHomeData(num)
                .compose(SchedulerUtils.ioToMain<HomeBean>())
    }

    /**
     * 加载更多
     */
    fun loadMoreData(url:String):Observable<HomeBean>{
        return RetrofitManager.getService(ApiService::class.java).getMoreHomeData(url)
                .compose(SchedulerUtils.ioToMain<HomeBean>())
    }



}

