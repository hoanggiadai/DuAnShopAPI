package edu.fpt.lenovo.shoponline.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import edu.fpt.lenovo.shoponline.R;
import edu.fpt.lenovo.shoponline.model.SanPham;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.ItemViewHolder>{
    Context context;
    ArrayList<SanPham> sanPhamArrayList;

    public SanPhamAdapter(Context context, ArrayList<SanPham> sanPhamArrayList) {
        this.context = context;
        this.sanPhamArrayList = sanPhamArrayList;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sanphammoinhat, null);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        SanPham sanPham = sanPhamArrayList.get(position);
        holder.txtTenSanPham.setText(sanPham.getTenSanPham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtGiaSanPham.setText("Giá: "+decimalFormat.format(sanPham.getGiaSanPham())+" Đ");
        Picasso.get().load(sanPham.getHinhAnhSanPham())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.erro)
                .into(holder.imgSanPham);

    }

    @Override
    public int getItemCount() {
        return sanPhamArrayList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imgSanPham;
        TextView txtTenSanPham, txtGiaSanPham;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imgSanPham = itemView.findViewById(R.id.imageviewsanpham);
            txtTenSanPham = itemView.findViewById(R.id.textviewtensanpham);
            txtGiaSanPham = itemView.findViewById(R.id.textviewgiasanpham);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
