package activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.db_con_project.adapter.FeatureAdapter
import com.example.db_con_project.data.Feature

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FeatureAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val features = listOf(
            Feature(
                "마이크로 인터랙션",
                "버튼 등 UI의 터치 피드백, 애니메이션 등",
                MicroInteractionDemoActivity::class.java,
                "디자인 소스 예시",
                "Java 소스 예시",
                "XML 소스 예시"
            ),
            Feature(
                "스켈레톤 UI",
                "로딩 중 스켈레톤 애니메이션 표시",
                SkeletonDemoActivity::class.java,
                "디자인 소스 예시",
                "Java 소스 예시",
                "XML 소스 예시"
            ),
            Feature(
                "무한스크롤",
                "리스트 끝에서 자동으로 더 불러오기",
                InfiniteScrollDemoActivity::class.java,
                "디자인 소스 예시",
                "Java 소스 예시",
                "XML 소스 예시"
            ),
            Feature(
                "모달 UI (팝업/바텀시트/스낵바)",
                "팝업, 바텀시트, 스낵바 등 모달 UI",
                ModalDemoActivity::class.java,
                "디자인 소스 예시",
                "Java 소스 예시",
                "XML 소스 예시"
            ),
        )

        recyclerView = findViewById(R.id.recyclerView)
        adapter = FeatureAdapter(features) { feature ->
            val intent = Intent(this, feature.demoActivity)
            intent.putExtra("designSource", feature.designSource)
            intent.putExtra("javaSource", feature.javaSource)
            intent.putExtra("xmlSource", feature.xmlSource)
            startActivity(intent)
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}

