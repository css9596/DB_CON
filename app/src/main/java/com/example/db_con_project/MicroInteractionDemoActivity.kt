package com.example.db_con_project

import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.db_con_project.comm.CommonUtil

class MicroInteractionDemoActivity : AppCompatActivity() {
    private val commUtil = CommonUtil()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_micro_interaction_demo)

        val container = findViewById<FrameLayout>(R.id.demoContainer)
        val button = Button(this).apply {
            text = "눌러보세요!"
            setTextColor(android.graphics.Color.WHITE)
            setOnClickListener {
                // 클릭 시 애니메이션(마이크로 인터랙션)
                animate().rotationBy(360f).setDuration(400).start()
            }
        }
        container.addView(button, FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT,
            Gravity.CENTER
        ))

        commUtil.setupSourceCodeClick(
            fab = findViewById(R.id.fab),
            activity = this,
            designSource = "버튼에 클릭 애니메이션 적용",
            javaSource = commUtil.readAssetFile(this, "activity/MicroInteractionDemoActivity.kt"),
            xmlSource = commUtil.readAssetFile(this, "layout/activity_micro_interaction_demo.xml")
        )

    }
}

