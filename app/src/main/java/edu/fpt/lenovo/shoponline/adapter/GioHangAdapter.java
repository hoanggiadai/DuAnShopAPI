package edu.fpt.lenovo.shoponline.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import edu.fpt.lenovo.shoponline.R;
import edu.fpt.lenovo.shoponline.model.GioHang;

public class GioHangAdapter extends BaseAdapter {
    Context context;
    ArrayList<GioHang> gioHangArrayList;

    public GioHangAdapter(Context context, ArrayList<GioHang> gioHangArrayList) {
        this.context = context;
        this.gioHangArrayList = gioHangArrayList;
    }

    @Override
    public int getCount() {
        return gioHangArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return gioHangArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        GHViewHolder viewHolder=null;
        if (view==null){
            viewHolder = new GHViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_giohang, null);
            viewHolder.txtTenSP = view.findViewById(R.id.textviewgiohang);
            viewHolder.txtGiaSP = view.findViewById(R.id.textviewgiagiohang);
            viewHolder.imgAnhSP = view.findViewById(R.id.imageviewgiohang);
            viewHolder.btnGiaTri = view.findViewById(R.id.buttonvalues);
            viewHolder.btnTru = view.findViewById(R.id.buttonminus);
            viewHolder.btnCong = view.findViewById(R.id.buttonplus);
            view.setTag(viewHolder);
        } else {
            viewHolder = (GHViewHolder) view.getTag();
        }

        GioHang gioHang = (GioHang) getItem(position);
        viewHolder.txtTenSP.setText(gioHang.getTenSP());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtGiaSP.setText(decimalFormat.format(gioHang.getGiaSP())+" Đ");
        Picasso.get().load(gioHang.getHinhSP())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.erro)
                .into(viewHolder.imgAnhSP);
        viewHolder.btnGiaTri.setText(Integer.toString(gioHang.getSoLuongSP()));
        return view;
    }
    public class GHViewHolder{
        TextView txtTenSP, txtGiaSP;
        ImageView imgAnhSP;
        Button btnTru, btnCong, btnGiaTri;
    }
}
