package activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.db_con_project.comm.CommonUtil

class InfiniteScrollDemoActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private val items = mutableListOf<String>()
    private lateinit var adapter: ArrayAdapter<String>
    private var isLoading = false
    private val commUtil = CommonUtil()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infinite_scroll_demo)

        recyclerView = RecyclerView(this).apply {
            layoutManager = LinearLayoutManager(this@InfiniteScrollDemoActivity)
        }
        val container = findViewById<FrameLayout>(R.id.infinite_container)
        container.addView(recyclerView)

        // 초기 데이터
        repeat(20) { items.add("아이템 ${it + 1}") }
        val rvAdapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
            override fun getItemCount() = items.size
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
                val tv = TextView(parent.context).apply { textSize = 16f; setPadding(16, 32, 16, 32) }
                return object : RecyclerView.ViewHolder(tv) {}
            }
            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
                (holder.itemView as TextView).text = items[position]
            }
        }
        recyclerView.adapter = rvAdapter

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(rv: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(rv, dx, dy)
                val lm = rv.layoutManager as LinearLayoutManager
                if (!isLoading && lm.findLastCompletelyVisibleItemPosition() == items.size - 1) {
                    isLoading = true
                    // 데이터 추가
                    Handler(Looper.getMainLooper()).postDelayed({
                        val start = items.size + 1
                        repeat(10) { items.add("아이템 ${start + it}") }
                        rvAdapter.notifyDataSetChanged()
                        isLoading = false
                    }, 1200)
                }
            }
        })

        commUtil.setupSourceCodeClick(
            fab = findViewById(R.id.fab),
            activity = this,
            designSource = "RecyclerView에 EndlessScrollListener 적용",
            javaSource = "onScrolled에서 마지막 아이템 도달 시 데이터 추가",
            xmlSource = "<RecyclerView ... />"
        )

    }
}

