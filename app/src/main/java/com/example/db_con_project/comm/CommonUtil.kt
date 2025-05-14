package com.example.db_con_project.comm

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.example.db_con_project.SourceCodeActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CommonUtil {
    fun setupSourceCodeClick(
        fab: FloatingActionButton,
        activity: Activity,
        designSource: String,
        javaSource: String,
        xmlSource: String
    ) {
        fab.setOnClickListener {
            val intent = Intent(activity, SourceCodeActivity::class.java)
            intent.putExtra("designSource", designSource)
            intent.putExtra("javaSource", javaSource)
            intent.putExtra("xmlSource", xmlSource)
            activity.startActivity(intent)
        }
    }

    fun readRawResource(context: Context, resId: Int): String {
        val inputStream = context.resources.openRawResource(resId)
        return inputStream.bufferedReader().use { it.readText() }
    }

    fun readAssetFile(context: Context, filePath: String): String {
        return context.assets.open(filePath).bufferedReader().use { it.readText() }
    }

}
