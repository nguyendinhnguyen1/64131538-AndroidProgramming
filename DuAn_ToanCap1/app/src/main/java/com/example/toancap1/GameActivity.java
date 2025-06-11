package com.example.toancap1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;
import android.widget.ImageButton;

public class GameActivity extends AppCompatActivity {

    TextView txtScore, txtQuestion;     // Hiển thị điểm và câu hỏi
    Button btn1, btn2, btn3;            // 3 nút đáp án
    int score = 0;                      // Điểm người chơi
    int correctAnswer;                 // Đáp án đúng hiện tại
    String currentQuestion = "";       // Câu hỏi hiện tại (dạng chuỗi)
    Random random = new Random();      // Đối tượng để sinh số ngẫu nhiên

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Gán các view từ layout vào biến
        txtScore = findViewById(R.id.btn_diem);
        txtQuestion = findViewById(R.id.btn_cauhoi);
        btn1 = findViewById(R.id.btn_dapan1);
        btn2 = findViewById(R.id.btn_dapan2);
        btn3 = findViewById(R.id.btn_dapan3);
        ImageButton btnHome = findViewById(R.id.btn_home);
        Button btnSaveScore = findViewById(R.id.btn_luudiem);

        // Sinh câu hỏi đầu tiên
        generateQuestion();

        // ===== SỰ KIỆN: Nhấn nút "Home" =====
        btnHome.setOnClickListener(v -> {
            // Trở về màn hình chính
            Intent intent = new Intent(GameActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Kết thúc màn chơi hiện tại
        });

        // ===== SỰ KIỆN: Nhấn nút "Lưu điểm" =====
        btnSaveScore.setOnClickListener(v -> {
            // Lưu điểm cao nhất vào SharedPreferences
            SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
            int savedScore = prefs.getInt("saved_score", 0);

            if (score > savedScore) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("saved_score", score);
                editor.apply();  // Lưu thay đổi
            }
        });

        // ===== SỰ KIỆN: Khi người dùng chọn một đáp án =====
        View.OnClickListener answerClickListener = view -> {
            Button clicked = (Button) view;
            int selected = Integer.parseInt(clicked.getText().toString());

            if (selected == correctAnswer) {
                // Đáp án đúng → tăng điểm, sinh câu mới
                score++;
                txtScore.setText(String.valueOf(score));
                generateQuestion();
            } else {
                // Đáp án sai → chuyển sang màn hình Game Over
                Intent intent = new Intent(GameActivity.this, GameOverActivity.class);
                intent.putExtra("score", score);
                intent.putExtra("question", txtQuestion.getText().toString()); // Gửi câu hỏi
                intent.putExtra("correctAnswer", correctAnswer);              // Gửi đáp án
                startActivity(intent);
            }
        };

        // Gán sự kiện click cho 3 nút đáp án
        btn1.setOnClickListener(answerClickListener);
        btn2.setOnClickListener(answerClickListener);
        btn3.setOnClickListener(answerClickListener);
    }

    // ===== Hàm sinh câu hỏi và tạo 3 đáp án (1 đúng + 2 sai) =====
    private void generateQuestion() {
        int a = random.nextInt(10) + 1;
        int b = random.nextInt(10) + 1;
        String[] ops = {"+", "-", "*", "/"};
        String op = ops[random.nextInt(ops.length)];

        switch (op) {
            case "+":
                correctAnswer = a + b;
                currentQuestion = a + " + " + b + " = ?";
                break;
            case "-":
                correctAnswer = a - b;
                currentQuestion = a + " - " + b + " = ?";
                break;
            case "*":
                correctAnswer = a * b;
                currentQuestion = a + " × " + b + " = ?";
                break;
            case "/":
                b = random.nextInt(9) + 1;       // Tránh chia cho 0
                correctAnswer = a;
                int result = a * b;              // Đảo phép toán để luôn có số chia nguyên
                currentQuestion = result + " / " + b + " = ?";
                break;
        }

        txtQuestion.setText(currentQuestion); // Hiển thị câu hỏi

        // Tạo vị trí ngẫu nhiên cho đáp án đúng
        int correctPosition = random.nextInt(3);
        int wrong1 = correctAnswer + random.nextInt(5) + 1;
        int wrong2 = correctAnswer - random.nextInt(5) - 1;
        if (wrong1 == correctAnswer) wrong1 += 3;
        if (wrong2 == correctAnswer) wrong2 -= 3;

        // Gán giá trị cho 3 nút đáp án
        Button[] buttons = {btn1, btn2, btn3};
        buttons[correctPosition].setText(String.valueOf(correctAnswer));
        buttons[(correctPosition + 1) % 3].setText(String.valueOf(wrong1));
        buttons[(correctPosition + 2) % 3].setText(String.valueOf(wrong2));
    }
}
