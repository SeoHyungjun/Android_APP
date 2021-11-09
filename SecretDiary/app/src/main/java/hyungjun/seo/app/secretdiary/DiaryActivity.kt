package hyungjun.seo.app.secretdiary

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.PersistableBundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.widget.addTextChangedListener

class DiaryActivity: AppCompatActivity() {

    // main Looper를 사용하면 main thread와 연
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_diary)

        val diaryEditText = findViewById<EditText>(R.id.diaryEditText)

        val detailPreferences = getSharedPreferences("diary", Context.MODE_PRIVATE)

        diaryEditText.setText(detailPreferences.getString("detail", ""))


        val runnable = Runnable {
            getSharedPreferences("diart", Context.MODE_PRIVATE).edit {
                putString("detail", diaryEditText.text.toString())
            }
        }
/*
        // thread runnable interface 없이 사용하게되면 너무 빈번하게 저장
        diaryEditText.addTextChangedListener {
            detailPreferences.edit {
                putString("detail", diaryEditText.text.toString())
            }
        }
*/
        diaryEditText.addTextChangedListener {
            // main thread가 아닌곳에서는 UI change가 불가능하여
            // 새로 생성한 thread를 main thread와 연결해줘야 함 => 핸들러를 통해서 가능
            handler.removeCallbacks(runnable) // 아직 callback되지않고 남아있는게 있다면 지워줌
            handler.postDelayed(runnable, 500) // 0.5초간 새로운 Text change가 없으면 실행
        }
    }
}