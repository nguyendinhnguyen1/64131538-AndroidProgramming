package com.example.nt21_quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Button btnXacNhan = findViewById(R.id.btnOK);

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Xử lý đăng nhập
                //Lấy dữ liệu
                //B1. Tìm tham chiếu đến đk
                EditText edtTenDN = findViewById(R.id.edtUsername);
                EditText edtPass = findViewById(R.id.edtPass);
                EditText edtMail = findViewById(R.id.edtMail);
                //B2. Lấy dữ liệu
                String tenDangNhap = edtTenDN.getText().toString();
                String mk = edtPass.getText().toString();
                String mail = edtMail.getText().toString();
                //Kiểm tra mật khẩu
                if (tenDangNhap.equals("buithanhphap") && mk.equals("456")) //MK đúng
                {
                    //Chuyển sang màn hình home
                    Intent iQuiz = new Intent(LoginActivity.this, HomeActivity.class);
                    //Gói dữ liệu vào iQuiz, dạng key - value; key được dùng để bên kia lọc ra dữ liệu
                    iQuiz.putExtra("ten_dang_nhap",tenDangNhap);
                    iQuiz.putExtra("mat_khau",mk);
                    iQuiz.putExtra("email",mail);
                    //Gửi đi
                    startActivity(iQuiz);

                } else {
                    Toast.makeText(LoginActivity.this,"BẠN NHẬP SAI THÔNG TIN",Toast.LENGTH_LONG);
                }
            }
        });
    }
}