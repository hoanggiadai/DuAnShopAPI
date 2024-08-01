package edu.fpt.lenovo.shoponline.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.DecimalFormat;

import edu.fpt.lenovo.shoponline.R;
import edu.fpt.lenovo.shoponline.adapter.GioHangAdapter;
import edu.fpt.lenovo.shoponline.ultil.CheckConnection;

public class GioHangActivity extends AppCompatActivity {
    Toolbar toolbar;
    ListView listViewgiohang;
    TextView txtThongBao;
    static TextView txtTongTien;
    Button btnThanhToan, btnTiepTucMuaHang;
    GioHangAdapter gioHangAdapter;

    void Anhxa(){
        listViewgiohang = findViewById(R.id.listviewgiohang);
        txtThongBao = findViewById(R.id.textviewthongbao);
        txtTongTien = findViewById(R.id.textviewtongtien);
        btnThanhToan = findViewById(R.id.buttonthanhtoangiohang);
        btnTiepTucMuaHang = findViewById(R.id.buttontieptucmuahang);
        toolbar = findViewById(R.id.toolbargiohang);
        gioHangAdapter = new GioHangAdapter(GioHangActivity.this, MainActivity.manggiohang);
        listViewgiohang.setAdapter(gioHangAdapter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giohang);
        Anhxa();
        ActionToolBar();
        CheckData();
        ClickItemListView();
        ClickButton();
        UpdateTongTien();
    }

    void ActionToolBar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    void CheckData(){
        if (MainActivity.manggiohang.size()<=0){
            gioHangAdapter.notifyDataSetChanged();
            txtThongBao.setVisibility(View.VISIBLE);
            listViewgiohang.setVisibility(View.INVISIBLE);
        } else {
            gioHangAdapter.notifyDataSetChanged();
            txtThongBao.setVisibility(View.INVISIBLE);
            listViewgiohang.setVisibility(View.VISIBLE);
        }
    }

    void ClickItemListView(){
        listViewgiohang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(GioHangActivity.this);
                builder.setTitle("Xác nhận xóa sản phẩm");
                builder.setMessage("Bạn chắc chắn muốn xóa sản phẩm này không");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (MainActivity.manggiohang.size()<=0){
                            txtThongBao.setVisibility(View.VISIBLE);
                        } else {
                            MainActivity.manggiohang.remove(position);
                            gioHangAdapter.notifyDataSetChanged();
                            UpdateTongTien();

                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        gioHangAdapter.isEmpty();
                        UpdateTongTien();
                    }
                });
                return true;
            }
        });
    }

    public static void UpdateTongTien(){
        long tongTien = 0;
        for (int i =0; i<MainActivity.manggiohang.size(); i++){
            tongTien += MainActivity.manggiohang.get(i).getGiaSP();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtTongTien.setText(decimalFormat.format(tongTien)+" Đ");

    }

    void ClickButton(){
        btnTiepTucMuaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.manggiohang.size()>0){
//                    Intent intent = new Intent(getApplicationContext(), ThongtTinKhachHang.class);
//                    startActivity(intent);
                } else {
                    CheckConnection.ShowToastLong(getApplicationContext(), "Giỏ hàng của bạn trống");
                }
            }
        });
    }
}
