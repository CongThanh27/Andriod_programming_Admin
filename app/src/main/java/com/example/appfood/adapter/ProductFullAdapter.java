package com.example.appfood.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appfood.R;
import com.example.appfood.model.ProductModel;
import com.example.appfood.util.Constants;

import java.util.List;

public class ProductFullAdapter extends  RecyclerView.Adapter<ProductFullAdapter.ProProductFullHolder>{

    private List<ProductModel> listproduct;
    private Context mContext;

    public ProductFullAdapter(List<ProductModel> listproduct, Context mContext) {
        this.listproduct = listproduct;
        this.mContext = mContext;
    }

    public List<ProductModel> getListproduct() {
        return listproduct;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setListproduct(List<ProductModel> listproduct) {
        this.listproduct = listproduct;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ProProductFullHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item_full, null);
        return new ProProductFullHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProProductFullHolder holder, int position) {
    ProductModel pro=listproduct.get(position);
    if(listproduct==null)
        Toast.makeText(mContext,"Không có sản phẩm nào",Toast.LENGTH_SHORT).show();
    else {
        holder.addtofavorite.setText("Số lượng được yêu thích: "+String.valueOf(pro.getAddtofavorite()));
        holder.addtocart.setText("Số lượng được thêm vào giỏ hàng: "+String.valueOf(pro.getAddtocart()));
        holder.skinproblems.setText("Vấn đề về da: "+pro.getSkinproblems());
        holder.origin.setText("Xuất xứ: "+pro.getOrigin());
        holder.product_priceold.setText("Giá gốc: "+String.valueOf(pro.getPriceold()));
        holder.trademark.setText("Nhãn hiệu: "+pro.getTrademark());
        holder.description.setText("Mô tả: "+pro.getDescription());
        holder.category.setText("Danh mục: "+pro.getCategory());
        holder.quantity.setText("Số lượng: "+pro.getQuantity());
        holder.product_name.setText("Tên sản phẩm: "+pro.getProduct_name());
        holder.rain.setText("Đánh giá: "+String.valueOf(pro.getRain()));
        holder.sex.setText("Giới tính: "+pro.getSex());
        holder.share.setText("Số lượng chia sẽ: "+ String.valueOf(pro.getShare()));
        holder.sold.setText("Đã bán: "+String.valueOf(pro.getSold()));
        holder.supplier.setText("Đơn vị vận chuyển: "+pro.getSupplier());
        // lấy ảnh
        String correctedImagePath = pro.getImage().replace("\\", "/");
        Glide.with(mContext)
                .load(Constants.url+"/"+correctedImagePath)
                .into(holder.image);
    }

    }

    @Override
    public int getItemCount() {
        if(listproduct!=null)
            return listproduct.size();
        return 0;
    }

    public class ProProductFullHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView product_name;
        private TextView product_price;
        private TextView product_priceold;
        private TextView quantity;
        private TextView sold;
        private TextView supplier;

        private TextView category;
        private TextView description;
        private TextView trademark;
        private TextView origin;
        private TextView sex;
        private TextView skinproblems;
        private TextView addtocart;
        private TextView addtofavorite;
        private TextView share;
        private TextView rain;

        public ProProductFullHolder(@NonNull View itemView) {
            super(itemView);
            product_name=itemView.findViewById(R.id.product_name);
            product_price=itemView.findViewById(R.id.product_price);
            product_priceold=itemView.findViewById(R.id.product_priceold);
            quantity=itemView.findViewById(R.id.product_quantity);
            sold=itemView.findViewById(R.id.product_sold);
            supplier=itemView.findViewById(R.id.product_supplier);
            category=itemView.findViewById(R.id.product_category);
            description=itemView.findViewById(R.id.product_description);
            trademark=itemView.findViewById(R.id.product_trademark);
            origin=itemView.findViewById(R.id.product_origin);
            sex=itemView.findViewById(R.id.product_sex);
            skinproblems=itemView.findViewById(R.id.product_skinproblem);
            addtocart=itemView.findViewById(R.id.product_addtocart);
            addtofavorite=itemView.findViewById(R.id.product_addtofavorite);
            share=itemView.findViewById(R.id.product_share);
            rain=itemView.findViewById(R.id.product_rain);
            image=itemView.findViewById(R.id.imageView);

        }
    }
}
