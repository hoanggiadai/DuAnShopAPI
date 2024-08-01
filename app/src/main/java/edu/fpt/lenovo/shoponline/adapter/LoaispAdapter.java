package edu.fpt.lenovo.shoponline.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import edu.fpt.lenovo.shoponline.R;
import edu.fpt.lenovo.shoponline.model.LoaiSP;

public class LoaispAdapter extends BaseAdapter {
    private ArrayList<LoaiSP> loaiSPArrayList;
    private Context context;

    public LoaispAdapter(ArrayList<LoaiSP> loaiSPArrayList, Context context) {
        this.loaiSPArrayList = loaiSPArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return loaiSPArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return loaiSPArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (view==null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_listview_loaisp, null);
            viewHolder.txttenloaisp=(TextView) view.findViewById(R.id.textviewloaisp);
            viewHolder.imgloaisp=(ImageView) view.findViewById(R.id.imageviewloaisp);
            view.setTag(viewHolder);
        }
        else {
            viewHolder=(LoaispAdapter.ViewHolder)view.getTag();
        }

        // Lấy dữ liệu
        LoaiSP loaiSP = (LoaiSP) getItem(position);
        viewHolder.txttenloaisp.setText(loaiSP.getTenLoaiSP());
        Picasso.get().load(loaiSP.getHinhLoaiSP())
                .placeholder(R.drawable.home)
                .error(R.drawable.erro)
                .into(viewHolder.imgloaisp);

        return view;
    }

    public class ViewHolder{
        ImageView imgloaisp;
        TextView txttenloaisp;

    }
}
