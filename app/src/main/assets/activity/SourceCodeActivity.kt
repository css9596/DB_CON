package activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout

class SourceCodeActivity : AppCompatActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_source_code)

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val webView = findViewById<WebView>(R.id.webView)
        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true

        val designSource = intent.getStringExtra("designSource") ?: ""
        val javaSource = intent.getStringExtra("javaSource") ?: ""
        val xmlSource = intent.getStringExtra("xmlSource") ?: ""

        tabLayout.addTab(tabLayout.newTab().setText("디자인"))
        tabLayout.addTab(tabLayout.newTab().setText("Java"))
        tabLayout.addTab(tabLayout.newTab().setText("XML"))

        // 소스코드를 하이라이팅된 HTML로 변환하는 함수
        fun makeHtml(source: String, language: String): String {
            val safeSource = source.replace("<", "&lt;").replace(">", "&gt;")
            return """
                <!DOCTYPE html>
                <html>
                <head>
                  <meta charset="utf-8">
                  <link href="https://cdnjs.cloudflare.com/ajax/libs/prism/1.29.0/themes/prism.min.css" rel="stylesheet"/>
                  <script src="https://cdnjs.cloudflare.com/ajax/libs/prism/1.29.0/prism.min.js"></script>
                  <script src="https://cdnjs.cloudflare.com/ajax/libs/prism/1.29.0/components/prism-$language.min.js"></script>
                  <style>
                    body { margin:0; padding:16px; background:#222; }
                    pre { font-size:15px; }
                  </style>
                </head>
                <body>
                  <pre><code class="language-$language">$safeSource</code></pre>
                </body>
                </html>
            """.trimIndent()
        }

        // 각 탭에 맞는 소스코드와 언어 지정
        val sources = listOf(
            Pair(designSource, "markup"), // 디자인 탭: xml로 가정
            Pair(javaSource, "java"),
            Pair(xmlSource, "markup")
        )

        // 초기값 설정
        webView.loadDataWithBaseURL(
            null,
            makeHtml(sources[0].first, sources[0].second),
            "text/html",
            "utf-8",
            null
        )

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val (src, lang) = sources[tab.position]
                webView.loadDataWithBaseURL(
                    null,
                    makeHtml(src, lang),
                    "text/html",
                    "utf-8",
                    null
                )
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }
}
