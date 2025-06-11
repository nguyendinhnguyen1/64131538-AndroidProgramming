package com.example.toancap1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class GameOverActivity extends AppCompatActivity {

    TextView txtDiem, txtCauHoi;
    Button btnLuuDiem;
    ImageButton btnQuayLai, btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        txtDiem = findViewById(R.id.btn_diem);
        txtCauHoi = findViewById(R.id.btn_cauhoi);
        btnLuuDiem = findViewById(R.id.btn_luudiem);
        btnQuayLai = findViewById(R.id.btn_quaylai);
        btnHome = findViewById(R.id.btn_home);

        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        int score = intent.getIntExtra("score", 0);
        String question = intent.getStringExtra("question");
        int correctAnswer = intent.getIntExtra("correctAnswer", 0);

        // Xử lý bỏ dấu ?
        if (question != null && question.endsWith("?")) {
            question = question.substring(0, question.length() - 1);
        }

        // Hiển thị câu hỏi + đáp án đúng
        String fullQuestion = question + correctAnswer;
        txtCauHoi.setText(fullQuestion);
        txtDiem.setText(String.valueOf(score));

        // Nút quay lại: chơi lại
        btnQuayLai.setOnClickListener(v -> {
            Intent intent1 = new Intent(GameOverActivity.this, GameActivity.class);
            startActivity(intent1);
            finish();
        });

        // Nút về màn hình chính
        btnHome.setOnClickListener(v -> {
            Intent intent2 = new Intent(GameOverActivity.this, MainActivity.class);
            startActivity(intent2);
            finish();
        });

        // Nút lưu điểm: lưu vào SharedPreferences nếu cao hơn
        btnLuuDiem.setOnClickListener(v -> {
            SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
            int savedScore = prefs.getInt("saved_score", 0);
            if (score > savedScore) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("saved_score", score);
                editor.apply();
            }
        });
    }
}
