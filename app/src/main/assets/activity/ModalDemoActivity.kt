package activity

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.example.db_con_project.comm.CommonUtil
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar

class ModalDemoActivity : AppCompatActivity() {
    private val commUtil = CommonUtil()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modal_demo)

        val container = findViewById<FrameLayout>(R.id.modal_container)
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
        }
        val btnPopup = Button(this).apply { text = "팝업" }
        val btnBottomSheet = Button(this).apply { text = "바텀시트" }
        val btnSnackbar = Button(this).apply { text = "스낵바" }
        layout.addView(btnPopup)
        layout.addView(btnBottomSheet)
        layout.addView(btnSnackbar)
        container.addView(layout)

        btnPopup.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("팝업")
                .setMessage("이것은 팝업입니다.")
                .setPositiveButton("확인", null)
                .show()
        }
        btnBottomSheet.setOnClickListener {
            val dialog = BottomSheetDialog(this)
            val view = layoutInflater.inflate(R.layout.bottom_sheet, null, false)
            dialog.setContentView(view)
            dialog.show()
        }
        btnSnackbar.setOnClickListener {
            Snackbar.make(container, "이것은 스낵바입니다.", Snackbar.LENGTH_SHORT).show()
        }

        commUtil.setupSourceCodeClick(
            fab = findViewById(R.id.fab),
            activity = this,
            designSource = "AlertDialog, BottomSheetDialog, Snackbar 활용",
            javaSource = "AlertDialog.Builder(...).show() 등",
            xmlSource = "<Button ... />"
        )
    }
}

