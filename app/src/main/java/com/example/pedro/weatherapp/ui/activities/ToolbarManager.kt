package com.example.pedro.weatherapp.ui.activities

import android.support.v7.graphics.drawable.DrawerArrowDrawable
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import com.example.pedro.weatherapp.R
import com.example.pedro.weatherapp.ui.App
import com.example.pedro.weatherapp.ui.utils.ctx
import com.example.pedro.weatherapp.ui.utils.slideEnter
import com.example.pedro.weatherapp.ui.utils.slideExit
import org.jetbrains.anko.toast

interface ToolbarManager {

    val toolbar: Toolbar

    var toolbarTitle: String
        get() = toolbar.title.toString()
        set(v) {
            toolbar.title = v
        }

    fun initToolbar() {
        toolbar.inflateMenu(R.menu.menu_main)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_settings -> App.instance.toast("Setings")
                else -> App.instance.toast("Unknown Option")
            }
            true
        }
    }

    fun enableHomeAsUp(up: () -> Unit) {
        toolbar.navigationIcon = createUpDrawable()
        toolbar.setNavigationOnClickListener { up() }
    }

    private fun createUpDrawable() =
            DrawerArrowDrawable(toolbar.ctx).apply {
                progress = 1f
            }

    fun attachToScroll(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                if (dy > 0) toolbar.slideExit() else toolbar.slideEnter()
            }
        })
    }

}