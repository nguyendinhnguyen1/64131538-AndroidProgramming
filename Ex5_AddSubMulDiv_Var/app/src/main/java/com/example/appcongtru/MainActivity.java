package com.example.appcongtru;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    // khai báo các cái đối tượng tương ứng với các điều khiển

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // TimView
        TimView();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

        void TimView(){
            edtSoA = (EditText) findViewById(R.id.editTextA);
            edtSoB = (EditText) findViewById(R.id.editTextB);
            btnCong = (Button) findViewById(R.id.buttonCong);
            btnTru = (Button) findViewById(R.id.buttonTru);
            btnNhan = (Button) findViewById(R.id.buttonNhan);
            btnChia = (Button) findViewById(R.id.buttonChia);
        }
    EditText edtSoA;
    EditText edtSoB;
    Button btnCong,btnTru,btnNhan,btnChia;
    TextView tvKetQua;
}