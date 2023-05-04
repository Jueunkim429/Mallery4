package com.example.mallery4

import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.*
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.storage.StorageManager
import android.provider.MediaStore
import android.util.AttributeSet
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.getSystemService
import androidx.core.view.doOnLayout
import kotlinx.android.synthetic.main.activity_draw.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import javax.microedition.khronos.opengles.GL10

class DrawActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_draw)

        // 뒤로가기 버튼
        val back = findViewById<ImageView>(R.id.back)
        back.setOnClickListener {
            onBackPressed()
        }

        // 저장
        val saveText = findViewById<TextView>(R.id.save)
        saveText.setOnClickListener {
            saveImage()
            val choice = Intent(this, DecorateActivity::class.java)

            //액티비티 이동
            startActivity(choice)
        }

        // 선택한 사진 가져오기
        val customView = findViewById<CustomView>(R.id.customView)
        if (intent.hasExtra("uri")) {
            val uriString = intent.getStringExtra("uri")
            if (uriString != null) {
                val uri = Uri.parse(uriString)
                val drawable = Drawable.createFromStream(contentResolver.openInputStream(uri), uri.toString())
                customView.background = drawable
            }
        }

        // 꾸미기 속성들
        val pen = findViewById<ImageView>(R.id.pen)
        val erase = findViewById<ImageView>(R.id.erase)
        val sticker = findViewById<ImageView>(R.id.sticker)
        val pen_color = findViewById<LinearLayout>(R.id.pen_color)
        pen_color.visibility = View.GONE //처음에는 안보임
        val erase_margin = findViewById<LinearLayout>(R.id.erase_margin)

        pen.setOnClickListener {
            pen.setImageDrawable(resources.getDrawable(R.drawable.draw_pen2))
            erase.setImageDrawable(resources.getDrawable(R.drawable.draw_erase))
            pen_color.visibility = View.VISIBLE
            erase_margin.visibility = View.GONE
        }

        sticker.setOnClickListener {
            pen.setImageDrawable(resources.getDrawable(R.drawable.draw_pen))
            erase.setImageDrawable(resources.getDrawable(R.drawable.draw_erase))
            pen_color.visibility = View.GONE
            erase_margin.visibility = View.GONE
        }

    }

   private fun saveImage() {
       customView.setDrawingCacheEnabled(true) // 캐쉬허용
       // 캐쉬에서 가져온 비트맵을 복사해서 새로운 비트맵(스크린샷) 생성
       val screenshot = Bitmap.createBitmap(customView.drawingCache)
       customView.setDrawingCacheEnabled(false) // 캐쉬닫기


       // 이미지 저장 정보
       val values = ContentValues().apply {
           put(MediaStore.Images.Media.DISPLAY_NAME, "my.png")
           put(MediaStore.Images.Media.MIME_TYPE, "image/png")
           put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_DCIM + "/Mallery4")
           Log.e("접근","접근")
       }

       // 저장소에 이미지 저장
       val contentResolver = applicationContext.contentResolver
       val uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
       uri?.let {
           contentResolver.openOutputStream(uri)?.use { outputStream ->
               screenshot.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
               outputStream.flush()
               Toast.makeText(this, "저장 성공!", Toast.LENGTH_SHORT).show()
           }
       } ?: run {
           Toast.makeText(this, "저장 실패", Toast.LENGTH_SHORT).show()
       }
   }


    fun setRed(v: View) {
        val customView = findViewById<CustomView>(R.id.customView)
        customView.whatColor = 1
        println(customView.whatColor)
    }

    fun setOrange(v: View) {
        val customView = findViewById<CustomView>(R.id.customView)
        customView.whatColor = 2
        println(customView.whatColor)
    }

    fun setYellow(v: View) {
        val customView = findViewById<CustomView>(R.id.customView)
        customView.whatColor = 3
        println(customView.whatColor)
    }

    fun setGreen(v: View) {
        val customView = findViewById<CustomView>(R.id.customView)
        customView.whatColor = 4
        println(customView.whatColor)
    }

    fun setBlue(v: View) {
        val customView = findViewById<CustomView>(R.id.customView)
        customView.whatColor = 5
        println(customView.whatColor)
    }

    fun setPurple(v: View) {
        val customView = findViewById<CustomView>(R.id.customView)
        customView.whatColor = 6
        println(customView.whatColor)
    }


    fun setBlack(v: View) {
        val customView = findViewById<CustomView>(R.id.customView)
        customView.whatColor = 7
        println(customView.whatColor)
    }

    fun setWhite(v: View) {
        val customView = findViewById<CustomView>(R.id.customView)
        customView.whatColor = 8
        println(customView.whatColor)
    }

    fun clearPaint(v: View) {
        val customView = findViewById<CustomView>(R.id.customView)
        customView.clearPaint()
        pen.setImageDrawable(resources.getDrawable(R.drawable.draw_pen))
        erase.setImageDrawable(resources.getDrawable(R.drawable.draw_erase2))
        pen_color.visibility = View.GONE
        erase_margin.visibility = View.VISIBLE
    }



}



