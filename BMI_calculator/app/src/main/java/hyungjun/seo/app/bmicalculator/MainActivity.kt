package hyungjun.seo.app.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 아래 경우에는 heightEditText의 타입이 EditText라고 명시
        val heightEditText: EditText = findViewById(R.id.heightEditText)
        // 아래 경우에는 weightEditText의 타입을 명시하지 않고, findViewById의 return값이 EditText라고 명시
        val weightEditText = findViewById<EditText>(R.id.weightEditText)

        val resultButton = findViewById<Button>(R.id.resultButton)
    }
}