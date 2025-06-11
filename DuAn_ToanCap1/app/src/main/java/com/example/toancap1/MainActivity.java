package com.example.toancap1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // TextView dùng để hiển thị điểm cao nhất đã lưu
    private TextView txtSavedScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Tìm đối tượng nút "Chơi" từ giao diện
        ImageButton playButton = findViewById(R.id.play_button);

        // Tìm TextView để hiển thị điểm cao
        txtSavedScore = findViewById(R.id.btn_save);

        // Sự kiện khi người dùng nhấn nút "Chơi"
        playButton.setOnClickListener(v -> {
            // Chuyển sang màn hình GameActivity (bắt đầu chơi)
            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Sự kiện khi màn hình chính được hiển thị lại (ví dụ sau khi chơi xong quay về)
        // Đọc điểm cao đã lưu từ SharedPreferences
        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        int savedScore = prefs.getInt("saved_score", 0);

        // Hiển thị điểm cao lên giao diện
        txtSavedScore.setText("" + savedScore);
    }
}
