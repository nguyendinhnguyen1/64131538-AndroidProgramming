package com.example.toancap1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class GameOverActivity extends AppCompatActivity {

    TextView txtDiem, txtCauHoi;       // Hiển thị điểm và câu hỏi sai
    Button btnLuuDiem;                 // Nút lưu điểm
    ImageButton btnQuayLai, btnHome;  // Nút chơi lại và về màn chính

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        // Gán các View từ layout
        txtDiem = findViewById(R.id.btn_diem);
        txtCauHoi = findViewById(R.id.btn_cauhoi);
        btnLuuDiem = findViewById(R.id.btn_luudiem);
        btnQuayLai = findViewById(R.id.btn_quaylai);
        btnHome = findViewById(R.id.btn_home);

        // ===== SỰ KIỆN: Nhận dữ liệu từ GameActivity =====
        Intent intent = getIntent();
        int score = intent.getIntExtra("score", 0);                      // Điểm hiện tại
        String question = intent.getStringExtra("question");            // Câu hỏi sai
        int correctAnswer = intent.getIntExtra("correctAnswer", 0);     // Đáp án đúng

        // Loại bỏ dấu '?' ở cuối câu hỏi (nếu có)
        if (question != null && question.endsWith("?")) {
            question = question.substring(0, question.length() - 1);
        }

        // Hiển thị câu hỏi kèm đáp án đúng
        String fullQuestion = question + correctAnswer;
        txtCauHoi.setText(fullQuestion);
        txtDiem.setText(String.valueOf(score));

        // ===== SỰ KIỆN: Nhấn nút "Quay lại" để chơi lại =====
        btnQuayLai.setOnClickListener(v -> {
            Intent intent1 = new Intent(GameOverActivity.this, GameActivity.class);
            startActivity(intent1);  // Chuyển sang màn chơi mới
            finish();                // Kết thúc màn hiện tại
        });

        // ===== SỰ KIỆN: Nhấn nút "Home" để về màn hình chính =====
        btnHome.setOnClickListener(v -> {
            Intent intent2 = new Intent(GameOverActivity.this, MainActivity.class);
            startActivity(intent2);  // Trở về màn hình chính
            finish();
        });

        // ===== SỰ KIỆN: Nhấn nút "Lưu điểm" nếu điểm cao hơn điểm cũ =====
        btnLuuDiem.setOnClickListener(v -> {
            SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
            int savedScore = prefs.getInt("saved_score", 0);
            if (score > savedScore) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("saved_score", score);  // Ghi đè điểm cao
                editor.apply();
            }
        });
    }
}
