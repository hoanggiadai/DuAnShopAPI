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

public class DienThoaiAdapter extends BaseAdapter {
    ArrayList<SanPham> sanPhamArrayList;
    Context context;
    public DienThoaiAdapter(Context context, ArrayList<SanPham> sanPhamArrayList) {
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
        ViewHolderDT viewHolderDT=null;
        if (convertView==null){
            viewHolderDT=new ViewHolderDT();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.item_dienthoai,null);
            viewHolderDT.txtTenDT=convertView.findViewById(R.id.textviewdienthoai);
            viewHolderDT.txtGiaDT=convertView.findViewById(R.id.textviewgiadienthoai);
            viewHolderDT.txtMoTaDT=convertView.findViewById(R.id.textviewmotadienthoai);
            viewHolderDT.imgDT=convertView.findViewById(R.id.imageviewdienthoai);
            convertView.setTag(viewHolderDT);
        }else {
            viewHolderDT=(ViewHolderDT) convertView.getTag();
        }
        SanPham sanPham=(SanPham) getItem(position);
        viewHolderDT.txtTenDT.setText(sanPham.getTenSanPham());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        viewHolderDT.txtGiaDT.setText("Giá: "+decimalFormat.format(sanPham.getGiaSanPham())+" Đ");
        viewHolderDT.txtMoTaDT.setMaxLines(2);
        viewHolderDT.txtMoTaDT.setText(sanPham.getMoTaSanPham());
        Picasso.get().load(sanPham.getHinhAnhSanPham())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.erro)
                .into(viewHolderDT.imgDT);
        return convertView;
    }

    public class ViewHolderDT{
        public TextView txtTenDT, txtGiaDT, txtMoTaDT;
        public ImageView imgDT;
    }
}
