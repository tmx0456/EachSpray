package com.eachspray.tan.tanbase.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.DrawableImageViewTarget
import java.lang.ref.WeakReference

/**
 * @author Tan
 * @date 2018/9/26 17:45
 */

open class ImageLoad {

    open fun load(url: String, image: ImageView?) {
        if (image == null) return
        var requestOptions = RequestOptions().centerCrop()
//                .placeholder(R.drawable.default_avatar)
//                .error(R.drawable.default_avatar)
                .transform(CenterCrop())
                .format(DecodeFormat.PREFER_RGB_565)
                .priority(Priority.LOW)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)

        Glide.with(image.context)
                .load(url)
                .apply(requestOptions)
                .into(object : DrawableImageViewTarget(image) {
                })
    }

    open fun load(url: String, image: ImageView?, width: Int, height: Int) {
        if (image == null) return
        var lp = image.layoutParams
        lp.width = width
        lp.height = height
        image.layoutParams = lp
        var requestOptions = RequestOptions().centerCrop()
//                .placeholder(R.drawable.default_banner)
                .override(width, height)
                .format(DecodeFormat.PREFER_RGB_565)
//                .error(R.drawable.default_banner)
                .transform(CenterCrop())
                .dontAnimate()
                .priority(Priority.LOW)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)

        Glide.with(image.context)
                .load(url)
                .apply(requestOptions)
                .into(object : DrawableImageViewTarget(image) {
                })
    }

//    open fun loadCircle(url: String, image: ImageView?) {
//        if (image == null) return
//        var lp = image.layoutParams
//        lp.width = DensityUtil.dip2px(image.context.applicationContext, 40f)
//        lp.height = DensityUtil.dip2px(image.context.applicationContext, 40f)
//        image.layoutParams = lp
//        var requestOptions = RequestOptions().centerCrop()
//                .placeholder(R.drawable.default_icon)
//                .error(R.drawable.default_icon)
//                .format(DecodeFormat.PREFER_RGB_565)
//                .transform(CenterCrop())
//                .override(lp.width)
//                .dontAnimate()
//                .priority(Priority.LOW)
//                .transform(CircleCrop())
//                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
//
//        Glide.with(image.context)
//                .load(url)
//                .apply(requestOptions)
//                .into(object : DrawableImageViewTarget(image) {
//                })
//    }

//    open fun load(url: String, image: ImageView?, width: Int, height: Int, round: Int) {
//        if (image == null) return
//        var lp = image.layoutParams
//        lp.width = width
//        lp.height = height
//        image.layoutParams = lp
//        var requestOptions = RequestOptions().centerCrop()
//                .placeholder(R.drawable.default_banner)
//                .error(R.drawable.default_banner)
//                .format(DecodeFormat.PREFER_RGB_565)
//                .override(width, height)
//                .priority(Priority.LOW)
//                .dontAnimate()
//                .transforms(CenterCrop(), RoundedCorners(DensityUtil.dip2px(image.context.applicationContext, round.toFloat())))
//                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
//
//        Glide.with(image.context)
//                .load(url)
//                .apply(requestOptions)
//                .into(object : DrawableImageViewTarget(image) {
//                })
//    }
//
//    open fun loadRound(url: String, image: ImageView?, round: Int) {
//        if (image == null) return
//        var requestOptions = RequestOptions().centerCrop()
//                .placeholder(R.drawable.default_banner)
//                .error(R.drawable.default_banner)
//                .format(DecodeFormat.PREFER_RGB_565)
//                .priority(Priority.LOW)
//                .dontAnimate()
//                .transforms(CenterCrop(), RoundedCorners(DensityUtil.dip2px(image.context.applicationContext, round.toFloat())))
//                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
//
//        Glide.with(image.context)
//                .load(url)
//                .apply(requestOptions)
//                .into(object : DrawableImageViewTarget(image) {
//                })
//    }

    open fun clearCache(context: WeakReference<Context>) {
        Thread(Runnable {
            Glide.get(context.get()!!.applicationContext).clearDiskCache()
        }).start()
        Glide.get(context.get()!!.applicationContext).clearMemory()
    }

}