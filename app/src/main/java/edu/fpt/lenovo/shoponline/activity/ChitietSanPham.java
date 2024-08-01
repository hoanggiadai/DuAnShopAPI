package edu.fpt.lenovo.shoponline.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

import edu.fpt.lenovo.shoponline.R;
import edu.fpt.lenovo.shoponline.model.GioHang;
import edu.fpt.lenovo.shoponline.model.SanPham;

public class ChitietSanPham extends AppCompatActivity {
    Toolbar toolbar;
    ImageView imgAnhSP;
    TextView txtTenSP, txtGiaSP, txtMoTaSP;
    Spinner spinnerSoLuongSP;
    Button btnThemGioHang;
    int id=0;
    String tenDT="";
    int giaDT=0;
    String hinhDT="";
    String moTaDT="";
    int idSanPhamDT=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        Anhxa();
        ActionToolbar(); // Xử lý sự kiện quay lại
        GetDataChiTiet(); // Lấy dữ liệu chi tiết dựa vào id
        GetDataSpinner(); // Xử lý số lượng sản phẩm
        EventButton(); // Xử lý sự kiện thêm vào giỏ hàng
    }
    void Anhxa() {
        toolbar = findViewById(R.id.toolbarchitietsanpham);
        imgAnhSP = findViewById(R.id.imageviewchitietsanpham);
        txtTenSP = findViewById(R.id.textviewtenchitietsanpham);
        txtGiaSP = findViewById(R.id.textviewgiachitietsanpham);
        txtMoTaSP = findViewById(R.id.textviewmotachitietsanpham);
        spinnerSoLuongSP = findViewById(R.id.spinner);
        btnThemGioHang = findViewById(R.id.buttondatmua);
    }
    void ActionToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    void GetDataChiTiet() {
        SanPham sanPham = (SanPham) getIntent().getSerializableExtra("thongtinsanpham");
        // Lấy dữ liệu trong mảng dựa vào id
        id = sanPham.getID();
        tenDT = sanPham.getTenSanPham();
        giaDT = sanPham.getGiaSanPham();
        hinhDT = sanPham.getHinhAnhSanPham();
        moTaDT = sanPham.getMoTaSanPham();
        idSanPhamDT = sanPham.getIDSanPham();

        // Đưa dữ liệu lấy được vào form mới
        txtTenSP.setText(tenDT);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtGiaSP.setText("Giá: "+decimalFormat.format(giaDT)+" Đ");
        txtMoTaSP.setText(moTaDT);
        Picasso.get().load(hinhDT)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.erro)
                .into(imgAnhSP);

    }

    void GetDataSpinner(){
        Integer[] soLuong = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, soLuong);
        spinnerSoLuongSP.setAdapter(arrayAdapter);
    }

    void EventButton(){
        btnThemGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.manggiohang.size()>0){
                    int sl = Integer.parseInt(spinnerSoLuongSP.getSelectedItem().toString());
                    boolean tonTaiMaHang = false;

                    for (int i=0;i<MainActivity.manggiohang.size(); i++){
                        if (MainActivity.manggiohang.get(i).getIdSP()==id){
                            MainActivity.manggiohang.get(i).setSoLuongSP(MainActivity.manggiohang.get(i).getSoLuongSP()+sl);
                            if (MainActivity.manggiohang.get(i).getSoLuongSP()>=10){
                                MainActivity.manggiohang.get(i).setSoLuongSP(10);
                            }
                            MainActivity.manggiohang.get(i).setGiaSP(giaDT*MainActivity.manggiohang.get(i).getSoLuongSP());
                            tonTaiMaHang = true;
                        }
                    }

                    if (tonTaiMaHang==false){
                        int soLuong1 = Integer.parseInt(spinnerSoLuongSP.getSelectedItem().toString());
                        long gia1 = giaDT*soLuong1;
                        MainActivity.manggiohang.add(new GioHang(id, tenDT, gia1, hinhDT, soLuong1));
                    }
                } else {
                    int soLuong2 = Integer.parseInt(spinnerSoLuongSP.getSelectedItem().toString());
                    long gia2 = giaDT*soLuong2;
                    MainActivity.manggiohang.add(new GioHang(id, tenDT, gia2, hinhDT, soLuong2));
                }
                Intent intent = new Intent(getApplicationContext(), GioHangActivity.class);
                startActivity(intent);
            }
        });

    }
}
