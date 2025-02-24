package com.example.constraintlayout;

import android.os.Bundle;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = findViewById(R.id.imageView);
        TextView titleText = findViewById(R.id.titleText);
        TextView cameraType = findViewById(R.id.cameraType);
        TextView settings = findViewById(R.id.settings);
        Button btnDiscard = findViewById(R.id.btnDiscard);
        Button btnUpload = findViewById(R.id.btnUpload);
        ImageView favoriteIcon = findViewById(R.id.favoriteIcon);

        btnDiscard.setOnClickListener(view ->
                Toast.makeText(MainActivity.this, "Discard clicked", Toast.LENGTH_SHORT).show()
        );

        btnUpload.setOnClickListener(view ->
                Toast.makeText(MainActivity.this, "Upload clicked", Toast.LENGTH_SHORT).show()
        );

        favoriteIcon.setOnClickListener(view ->
                Toast.makeText(MainActivity.this, "Favorite clicked", Toast.LENGTH_SHORT).show()
        );
    }
}
