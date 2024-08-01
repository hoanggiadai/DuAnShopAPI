package edu.fpt.lenovo.shoponline.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.fpt.lenovo.shoponline.R;
import edu.fpt.lenovo.shoponline.adapter.DienThoaiAdapter;
import edu.fpt.lenovo.shoponline.model.SanPham;
import edu.fpt.lenovo.shoponline.ultil.CheckConnection;
import edu.fpt.lenovo.shoponline.ultil.server;

public class DienThoaiActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView listViewdienthoai;
    DienThoaiAdapter dienThoaiAdapter;
    ArrayList<SanPham> mangdienthoai;
    int idDienThoai = 0;
    int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dien_thoai);
        Anhxa();
        ActionToolbarDienThoai();
        GetIDLoaiSanPham();
        GetDataDienThoai(page);
    }

    void Anhxa(){
        toolbar = findViewById(R.id.toolbardienthoai);
        listViewdienthoai = findViewById(R.id.listviewdienthoai);
        mangdienthoai = new ArrayList<>();
        dienThoaiAdapter = new DienThoaiAdapter(getApplicationContext(), mangdienthoai);
        listViewdienthoai.setAdapter(dienThoaiAdapter);
        listViewdienthoai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), ChitietSanPham.class);
                intent.putExtra("thongtinsanpham", mangdienthoai.get(i));
                startActivity(intent);
            }
        });
    }

    void ActionToolbarDienThoai(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    void GetIDLoaiSanPham(){
        idDienThoai = getIntent().getIntExtra("idloaisanpham", -1);
    }

    void GetDataDienThoai(int page){
        // B1: Sử dụng thư viện Volley
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        // B2: Tạo đối tượng request
        // StringRequest(phuongThuc, duongDan, thanhCong, thatBai)
        String path = server.Duongdandienthoai+String.valueOf(page);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, path,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        int id=0;
                        String tenDT="";
                        int giaDT=0;
                        String hinhDT="";
                        String moTaDT="";
                        int idSanPhamDT=0;
                        if (response!=null){
                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                for (int i=0; i<jsonArray.length(); i++){
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    id = jsonObject.getInt("id");
                                    tenDT = jsonObject.getString("tenSP");
                                    giaDT = jsonObject.getInt("giaSP");
                                    hinhDT = jsonObject.getString("hinhAnhSP");
                                    moTaDT = jsonObject.getString("moTaSP");
                                    idSanPhamDT = jsonObject.getInt("idSanPham");
                                    mangdienthoai.add(new SanPham(id, tenDT, giaDT, hinhDT, moTaDT, idSanPhamDT));
                                    dienThoaiAdapter.notifyDataSetChanged();
                                }
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        else {
                            CheckConnection.ShowToastLong(getApplicationContext(), "Không có dữ liệu");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put("idSanPham", String.valueOf(idDienThoai));

                return param;
            }
        };
        requestQueue.add(stringRequest);
    }
}