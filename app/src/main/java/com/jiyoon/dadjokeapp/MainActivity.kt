package com.jiyoon.dadjokeapp

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.NotificationCompat
import com.jiyoon.dadjokeapp.databinding.ActivityMain2Binding
import com.jiyoon.dadjokeapp.databinding.ActivityMainBinding
import android.app.NotificationManager
import android.app.NotificationChannel
import android.content.Context
import android.os.Build
import android.view.Window
import android.widget.ImageView
import android.widget.Toast
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    val TAG: String = "로그"

    // TODO: random joke 생성 (리스트 만들어서 번호 따라 다르게 이걸 onrestart에 활용)
    val jokeList = ArrayList<String>()
    val random = Random()
    fun randomNumberGenerator(from: Int, to: Int): Int {
        return random.nextInt(to - from) + from
    }

    var randomNumber = randomNumberGenerator(1, 4)

    var listOfJokes = mutableListOf(
        R.string.joke1, R.string.joke2, R.string.joke3, R.string.joke4, R.string.joke5,
        R.string.joke6, R.string.joke7, R.string.joke8, R.string.joke9, R.string.joke10
    )

    var listOfAnswers = mutableListOf(
        R.string.answer1, R.string.answer2, R.string.answer3, R.string.answer4,
        R.string.answer5, R.string.answer6, R.string.answer7, R.string.answer8,
        R.string.answer9, R.string.answer10,
    )

    var isClicked = false

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "MainActivity - onCreate() called")
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.studyingButton.setOnClickListener {

            isClicked = true
            
            var intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "MainActivity - onResume() called")

        isClicked = true

        binding.studyingImage.setImageResource(R.drawable.studying_cat1)
        binding.studyingTalk.text = "주인! \n 나 이제 인간들 말을 할 수 있다냥!"
        binding.studyingText.text = "당신의 고양이는 열심히 공부해서 \n 사람 말을 할 수 있게 됐습니다."
        binding.studyingButton.visibility = View.VISIBLE
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "MainActivity - onPause() called")

        isClicked = false

        // pause에 대기 화면 넣으려 했는데 그러면 화면 전환때 대기화면이 잠깐 보임
        binding.studyingImage.setImageResource(R.drawable.waiting_cat1)
        binding.studyingTalk.text = " 언제 오냥...? "
        binding.studyingText.text = " 고양이가 당신을 기다립니다. "
        binding.studyingButton.visibility = View.INVISIBLE
    }

    override fun onStop() {
        super.onStop()

        Log.d(TAG, "MainActivity - onStop() called")
        // stop에 대기화면 넣으면 안 뜸 흐으으음
        var builder = NotificationCompat.Builder(this, "MyChannel")
            .setSmallIcon(R.drawable.ic_cat_alarm2)
            .setContentTitle("고양이가 당신을 기다리고 있습니다!")
            .setContentText("혹시 재미없었냥?")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { // 오레오 버전 이후에는 알림을 받을 때 채널이 필요
            val channel_id = "MyChannel" // 알림을 받을 채널 id 설정
            val channel_name = "waitingCat" // 채널 이름 설정
            val descriptionText = "The Cat is Waiting" // 채널 설명글 설정
            val importance = NotificationManager.IMPORTANCE_DEFAULT // 알림 우선순위 설정
            val channel = NotificationChannel(channel_id, channel_name, importance).apply {
                description = descriptionText
            }

            // 만든 채널 정보를 시스템에 등록
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

            // 알림 표시: 알림의 고유 ID(ex: 1002), 알림 결과
            notificationManager.notify(1002, builder.build())
        }
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "MainActivity - onRestart() called")
        val text = "돌아와줘서 고맙다냥!"
        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "MainActivity - onDestroy() called")
    }
}

