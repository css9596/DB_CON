package activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.db_con_project.R
import com.example.db_con_project.comm.CommonUtil

class SkeletonDemoActivity : AppCompatActivity() {
    private lateinit var container: FrameLayout
    private lateinit var shimmerLayout: View
    private val commUtil = CommonUtil()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_skeleton_demo)

        container = findViewById(R.id.skeletonDemoContainer)
        shimmerLayout = layoutInflater.inflate(R.layout.item_skeleton, container, false)
        container.addView(shimmerLayout)

        // 2초 후 실제 데이터 표시
        Handler(Looper.getMainLooper()).postDelayed({
            container.removeAllViews()
            val textView = TextView(this).apply {
                text = "실제 데이터가 로드되었습니다!"
                textSize = 18f
            }
            container.addView(textView, FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER
            ))
        }, 2000)

        commUtil.setupSourceCodeClick(
            fab = findViewById(R.id.fab),
            activity = this,
            designSource = "ShimmerFrameLayout을 활용한 스켈레톤 UI",
            javaSource = commUtil.readAssetFile(this, "activity/SkeletonDemoActivity.kt"),
            xmlSource = commUtil.readAssetFile(this, "layout/activity_skeleton_demo.xml")
        )

    }
}

