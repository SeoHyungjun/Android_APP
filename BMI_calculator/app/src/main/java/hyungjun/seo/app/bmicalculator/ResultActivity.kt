package hyungjun.seo.app.bmicalculator

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow

class ResultActivity: AppCompatActivity()  {

    // onCreate 함수는 엑티비티가 실행되었을 때 호출되는 함수
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 새로 생성한 activity_result 레이아웃을 할당
        setContentView(R.layout.activity_result)

        // 이전 엑티비티에서 intent에 담아서 보낸 값을 가져오기
        // name : 가져올 값
        // defaultValue : height가 없으면 default 값을 가져
        val height = intent.getIntExtra("height", 0)
        val weight = intent.getIntExtra("weight", 0)

        // bmi는 몸무게 / 키^2
        // Math.pow(a, b) : a^b
        // a와 b는 double형이 들어가야 하므로 int/int=int형이니까 int/double=double이 되도록 하거나
        // int.toDouble()을 써야한다.
        // val bmi = weight / Math.pow(height / 100.0, 2.0)

        // 그러나 Math.pow는 Kotolin스럽지 않으므로 코틀린스럽게 변경 (option + enter하면 자동 바꿈)
        // Kotlin에서는 double형이 pow를 가지고있음
        val bmi = weight / (height / 100.0).pow(2.0)

        // lambda와 같이 when문의 결과값이 resultText에 들어감
        val resultText = when {
            // bmi >= 35.0 을 만족하면 resultText에 "고도비만"이, 아니라면 다른 조건에 맞는 값이 들어감
            bmi >= 35.0 -> "고도 비만"
            bmi >= 30.0 -> "중경도 비만"
            bmi >= 25.0 -> "경도 비만"
            bmi >= 23.0 -> "과체중"
            bmi >= 18.5 -> "정상체중"
            else -> "저체중"
        }

        val resultValueTextView = findViewById<TextView>(R.id.bmiResultValueTextView)
        val resultStringTextView = findViewById<TextView>(R.id.bmiResultStringTextView)

        resultValueTextView.text = bmi.toString()
        resultStringTextView.text = resultText
    }
}