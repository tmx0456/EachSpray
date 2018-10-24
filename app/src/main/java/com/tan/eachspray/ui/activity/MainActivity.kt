package com.tan.eachspray.ui.activity

import android.view.Menu
import android.view.MenuItem
import com.hazz.kotlinmvp.base.BaseActivity
import com.tan.eachspray.R
import com.tan.eachspray.ui.frgment.HomeFragment

class MainActivity : BaseActivity() {

    private var mHomeFragment: HomeFragment? = null

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun initData() {
    }

    override fun initView() {
        val transaction = supportFragmentManager.beginTransaction();
         // 首页
        mHomeFragment?.let {
            transaction.show(it)
        } ?: HomeFragment.getInstance("home").let {
            mHomeFragment = it
            transaction.add(R.id.fl_container, it, "home")
        }

        transaction.commitAllowingStateLoss()
    }

    override fun start() {

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
