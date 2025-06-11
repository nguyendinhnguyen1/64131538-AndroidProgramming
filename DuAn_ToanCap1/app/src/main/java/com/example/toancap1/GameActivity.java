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

    TextView txtScore, txtQuestion;
    Button btn1, btn2, btn3;
    int score = 0;
    int correctAnswer;
    String currentQuestion = "";
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        txtScore = findViewById(R.id.btn_diem);
        txtQuestion = findViewById(R.id.btn_cauhoi);
        btn1 = findViewById(R.id.btn_dapan1);
        btn2 = findViewById(R.id.btn_dapan2);
        btn3 = findViewById(R.id.btn_dapan3);
        ImageButton btnHome = findViewById(R.id.btn_home);
        Button btnSaveScore = findViewById(R.id.btn_luudiem);

        generateQuestion();

        // Quay về màn hình chính
        btnHome.setOnClickListener(v -> {
            Intent intent = new Intent(GameActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        // Lưu điểm cao nhất
        btnSaveScore.setOnClickListener(v -> {
            SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
            int savedScore = prefs.getInt("saved_score", 0);

            if (score > savedScore) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("saved_score", score);
                editor.apply();
            }
        });

        // Xử lý khi chọn đáp án
        View.OnClickListener answerClickListener = view -> {
            Button clicked = (Button) view;
            int selected = Integer.parseInt(clicked.getText().toString());

            if (selected == correctAnswer) {
                score++;
                txtScore.setText(String.valueOf(score));
                generateQuestion();
            } else {
                // Chuyển sang GameOverActivity và truyền dữ liệu
                Intent intent = new Intent(GameActivity.this, GameOverActivity.class);
                intent.putExtra("score", score);
                intent.putExtra("question", txtQuestion.getText().toString());  // Truyền câu hỏi
                intent.putExtra("correctAnswer", correctAnswer);                // Truyền đáp án
                startActivity(intent);
            }
        };

        btn1.setOnClickListener(answerClickListener);
        btn2.setOnClickListener(answerClickListener);
        btn3.setOnClickListener(answerClickListener);
    }

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
                b = random.nextInt(9) + 1;
                correctAnswer = a;
                int result = a * b;
                currentQuestion = result + " / " + b + " = ?";
                break;
        }

        txtQuestion.setText(currentQuestion);

        int correctPosition = random.nextInt(3);
        int wrong1 = correctAnswer + random.nextInt(5) + 1;
        int wrong2 = correctAnswer - random.nextInt(5) - 1;
        if (wrong1 == correctAnswer) wrong1 += 3;
        if (wrong2 == correctAnswer) wrong2 -= 3;

        Button[] buttons = {btn1, btn2, btn3};
        buttons[correctPosition].setText(String.valueOf(correctAnswer));
        buttons[(correctPosition + 1) % 3].setText(String.valueOf(wrong1));
        buttons[(correctPosition + 2) % 3].setText(String.valueOf(wrong2));
    }
}
