package edu.fpt.lenovo.shoponline.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import edu.fpt.lenovo.shoponline.R;
import edu.fpt.lenovo.shoponline.model.SanPham;

public class LaptopAdapter extends BaseAdapter {
    ArrayList<SanPham> sanPhamArrayList;
    Context context;

    public LaptopAdapter(Context context, ArrayList<SanPham> sanPhamArrayList) {
        this.sanPhamArrayList = sanPhamArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return sanPhamArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return sanPhamArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderLT viewHolderLT=null;
        if (convertView==null){
            viewHolderLT=new ViewHolderLT();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.item_laptop,null);
            viewHolderLT.txtTenLT=convertView.findViewById(R.id.textviewlaptop);
            viewHolderLT.txtGiaLT=convertView.findViewById(R.id.textviewgialaptop);
            viewHolderLT.txtMoTaLT=convertView.findViewById(R.id.textviewmotalaptop);
            viewHolderLT.imgLT=convertView.findViewById(R.id.imageviewlaptop);
            convertView.setTag(viewHolderLT);
        }else {
            viewHolderLT=(ViewHolderLT) convertView.getTag();
        }
        SanPham sanPham=(SanPham) getItem(position);
        viewHolderLT.txtTenLT.setText(sanPham.getTenSanPham());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        viewHolderLT.txtGiaLT.setText("Giá: "+decimalFormat.format(sanPham.getGiaSanPham())+" Đ");
        viewHolderLT.txtMoTaLT.setMaxLines(2);
        viewHolderLT.txtMoTaLT.setText(sanPham.getMoTaSanPham());
        Picasso.get().load(sanPham.getHinhAnhSanPham())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.erro)
                .into(viewHolderLT.imgLT);
        return convertView;
    }

    public class ViewHolderLT{
        public TextView txtTenLT, txtGiaLT, txtMoTaLT;
        public ImageView imgLT;
    }
}
