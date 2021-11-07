
package hyungjun.seo.app.secretdiary

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.NumberPicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.edit

class MainActivity : AppCompatActivity() {

    private val numberPicker1: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.numberPicker1)
            .apply{
                minValue = 0
                maxValue = 9
            }
    }

    private val numberPicker2: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.numberPicker2)
            .apply{
                minValue = 0
                maxValue = 9
            }
    }

    private val numberPicker3: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.numberPicker3)
            .apply{
                minValue = 0
                maxValue = 9
            }
    }

    private val openButton: AppCompatButton by lazy {
        findViewById<AppCompatButton>(R.id.openButton)
    }

    private val changePasswordButton: AppCompatButton by lazy {
        findViewById<AppCompatButton>(R.id.changePasswordButton)
    }

    private var changePasswordMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // lazy 하게 init 되려면 numberPicker가 호출이 되는 시점이므로,
        // inCreate 시 호출을 해 apply가 적용되도
        numberPicker1
        numberPicker2
        numberPicker3

        openButton.setOnClickListener{
            if (changePasswordMode) {
                Toast.makeText(this, "비밀번호 변경 중입니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // openButton을 클릭했을 때 저장되어있는 password를 가져와서 비교해야함
            // password를 기기에 저장하는 방식은 2가지가 있는데
            // localDB와 file에 저장하는 방식이 있음
            // file에 저장하는 방식 중 편하게 Preference file을 관리하는게 sharedPreferences
            // name의 file에 mode 방식으로, private는 이 앱에서만 사용하도
            val passwordPreference = getSharedPreferences("password", Context.MODE_PRIVATE)

            // numberPicker1,2,3으로부터 값을 가져와 나열한 문자
            val passwordFromUser = "${numberPicker1.value}${numberPicker2.value}${numberPicker3.value}"

            if (passwordPreference.getString("password", "000").equals(passwordFromUser)) {
                // 패스워드 일치
                // TODO 다이어리 페이지 작성후에 넘겨주어야
                //startActivity()
            } else {
                // 패스워드 불일치
                    // error 메시지를 AlertDialog로 보여줌
                showErrorAlertDialog()
            }
        }

        changePasswordButton.setOnClickListener {
            val passwordPreference = getSharedPreferences("password", Context.MODE_PRIVATE)
            val passwordFromUser = "${numberPicker1.value}${numberPicker2.value}${numberPicker3.value}"

            if (changePasswordMode) {
                // 비밀번호를 저장하는 기능

                // 이런식으로 뒤에 commit을 추가하는 것보다 아래와 같이 선언하는게 좋은 방식
                // 실수하고 추가안하는것을 방지?
                /*
                passwordPreference.edit {
                    val passwordFromUser = "${numberPicker1.value}${numberPicker2.value}${numberPicker3.value}"
                    putString("password", passwordFromUser)
                    // 저장하는 방식으로 apply와 commit이 있는데
                    // apply는 비동기적으로 저장하라고 명령하고 넘어감
                    // commit는 저장될때까지 기다렸다 넘어가고
                    commit()
                }
                */
                passwordPreference.edit(true) {
                    putString("password", passwordFromUser)
                }

                changePasswordMode = false
                changePasswordButton.setBackgroundColor(Color.BLACK)

            } else {
                if (passwordPreference.getString("password", "000").equals(passwordFromUser)) {
                    changePasswordMode = true
                    Toast.makeText(this, "변경할 패스워드를 입력해주세요", Toast.LENGTH_SHORT).show()
                    changePasswordButton.setBackgroundColor(Color.RED)
                } else {
                    // 패스워드 불일치
                    // error 메시지를 AlertDialog로 보여줌
                    showErrorAlertDialog()
                }
            }
        }
    }

    private fun showErrorAlertDialog() {
        AlertDialog.Builder(this)
            .setTitle("실패!!")
            .setMessage("비밀번호가 잘못되었습니다.")
            .setPositiveButton("확인") { _, _ -> }
            .create()
            .show()
    }
}