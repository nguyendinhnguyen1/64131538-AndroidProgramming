package com.example.tinhtong;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
    }
    public void xulyCong(View view){
        EditText editTextA = findViewById(R.id.edtA);
        EditText editTextB = findViewById(R.id.edtB);
        EditText editTextKetqua = findViewById(R.id.edtKq);

       String strA = editTextA.getText().toString();
       String strB = editTextB.getText().toString();

       int soA = Integer.parseInt(strA);
       int soB = Integer.parseInt(strB);

       int tong = soA + soB;
       String strTong = String.valueOf(tong);

       editTextKetqua.setText(strTong);
    }
}