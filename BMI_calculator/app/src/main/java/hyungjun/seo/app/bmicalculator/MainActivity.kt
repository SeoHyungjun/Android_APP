package hyungjun.seo.app.bmicalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 아래 경우에는 heightEditText의 타입이 EditText라고 명시
        val heightEditText: EditText = findViewById(R.id.heightEditText)
        // 아래 경우에는 weightEditText의 타입을 명시하지 않고, findViewById의 return값이 EditText라고 명시
        val weightEditText = findViewById<EditText>(R.id.weightEditText)

        val resultButton = findViewById<Button>(R.id.resultButton)

        // setOnclickListener()의 경우 전통적인 방법으로 인터페이스를 구현해서 사용
        // 코틀린에서는 람다{} 형식으로 구현해서 사용 가능
        resultButton.setOnClickListener {
            // Log.? -> d = debug, w = warning 등 수준 정의
            // tag : 태그, msg : message
            // logcat에서 현재 에뮬레이터 실행 후 버튼 클릭하면 log message 확인 가능
            //Log.d("MainActivity", "ResultButton이 클릭되었습니다.")

            // 입력칸이 비어있을 경우 Toast 메시지 출력
            if (heightEditText.text.isEmpty() || weightEditText.text.isEmpty()) {
                // context : this -> 이 엑티비티에
                // text : string -> 이 문자열을
                // Toast.LENGTH_SHORT -> 짧게
                // show() -> 출력하겠다.
                Toast.makeText(this, "빈 값이 있습니다.", Toast.LENGTH_SHORT).show()
                // 아래 부분을 실행하지 않고 함수 종료
                // return이 의미하는게 if문인지 setonclicklistener인지 모르므로 명시
                return@setOnClickListener
            } // 이 아래로는 빈 값이 절대 들어올 수 없음.


            // text 값을 toString으로 string으로 변환한 뒤서
            // toInt를 사용하여 Int형으로 변환 (toInt는 string -> Int라서)
            val height: Int = heightEditText.text.toString().toInt()
            val weight: Int = weightEditText.text.toString().toInt()

            // BMI값을 계산해서 보여주는 새로운 엑티비티 생성
            // packageContext : this -> 지금 엑티비티에서
            // 넘어갈 엑티비티 명 : ResultActivity -> ResultActivity로 넘어가겠다.
            // class.java -> 클래스를 넘겨주겠다.
            val intent = Intent(this, ResultActivity::class.java)
            // 함수의 인자로 들어가는 intent를 시작
            // manifests에 새로운 엑티비티를 추가해야 함
            startActivity(intent)
        }
    }
}