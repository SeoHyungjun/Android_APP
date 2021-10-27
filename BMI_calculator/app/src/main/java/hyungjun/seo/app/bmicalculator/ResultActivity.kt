package hyungjun.seo.app.bmicalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ResultActivity: AppCompatActivity()  {

    // onCreate 함수는 엑티비티가 실행되었을 때 호출되는 함수
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 새로 생성한 activity_result 레이아웃을 할당
        setContentView(R.layout.activity_result)

    }
}