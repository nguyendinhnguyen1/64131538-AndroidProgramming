package com.example.quanlysach;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase csdl; // <-- Khai báo biến toàn cục để dễ quản lý

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Mở hoặc tạo database
        csdl = openOrCreateDatabase("qlSach.db", MODE_PRIVATE, null);

        // 2. Xóa bảng nếu đã tồn tại
        String sqlXoaBangSach = "DROP TABLE IF EXISTS BOOKS";
        csdl.execSQL(sqlXoaBangSach);

        // 3. Tạo bảng mới
        String sqlTaoBangSach = "CREATE TABLE BOOKS(" +
                "BookID INTEGER PRIMARY KEY, " +
                "BookName TEXT, " +
                "Page INTEGER, " +
                "Price FLOAT, " +
                "Description TEXT)";
        csdl.execSQL(sqlTaoBangSach);

        // 4. Thêm dữ liệu mẫu
        csdl.execSQL("INSERT INTO BOOKS VALUES (1, 'Lập trình Android', 300, 19.99, 'Sách cơ bản Android')");
        csdl.execSQL("INSERT INTO BOOKS VALUES (2, 'Học Java', 250, 15.5, 'Sách học Java cơ bản')");

        // 5. Lấy dữ liệu
        String sqlSelect = "SELECT * FROM BOOKS";
        Cursor rs = csdl.rawQuery(sqlSelect, null);

        // 6. Tạo danh sách
        ArrayList<Sach> dsSach = new ArrayList<>();
        ArrayList<String> dsTenSach = new ArrayList<>();

        while (rs.moveToNext()) {
            int ma_sach = rs.getInt(0);
            String ten_sach = rs.getString(1);
            int so_trang = rs.getInt(2);
            float gia_ban = rs.getFloat(3);
            String mo_ta = rs.getString(4);

            Sach sach = new Sach(ma_sach, ten_sach, so_trang, gia_ban, mo_ta);
            dsSach.add(sach);
            dsTenSach.add(ten_sach);
        }

        // 7. Hiển thị ListView
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        ListView listViewSACH = findViewById(R.id.lvsach);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                dsTenSach
        );
        listViewSACH.setAdapter(adapter);
    }
}
