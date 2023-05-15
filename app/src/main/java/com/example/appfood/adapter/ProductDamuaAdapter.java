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
import com.example.appfood.model.ProductOrderModel;
import com.example.appfood.util.Constants;

import java.util.List;

public class ProductDamuaAdapter extends RecyclerView.Adapter<ProductDamuaAdapter.ProductDamuaHolder>{

    private List<ProductOrderModel> listproduct;
    private Context mContext;

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public ProductDamuaAdapter(List<ProductOrderModel> listproduct, Context mContext) {
        this.listproduct = listproduct;
        this.mContext = mContext;
    }

    public List<ProductOrderModel> getListproduct() {
        return listproduct;
    }

    public void setListproduct(List<ProductOrderModel> listproduct) {
        this.listproduct = listproduct;
    }

    @NonNull
    @Override
    public ProductDamuaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_order_item, null);
        return new ProductDamuaAdapter.ProductDamuaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductDamuaHolder holder, int position) {
        ProductOrderModel pro= listproduct.get(position);
        if(listproduct==null)
            Toast.makeText(mContext,"Không có sản phẩm nào",Toast.LENGTH_SHORT).show();
        else{
            holder.or_date.setText("Ngày đặt: "+ pro.getOrder_date());
            holder.or_num.setText("Mã đơn hàng: "+pro.getOrder_number());
            holder.or_prname.setText("sản phẩm: "+pro.getProduct_name());
            holder.or_quantity.setText("Số lượng mua: "+String.valueOf(pro.getQuantity()));
            holder.price.setText("Giá: "+ String.valueOf(pro.getPrice()));

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

    public  class ProductDamuaHolder extends RecyclerView.ViewHolder{

        private ImageView image;
        private TextView or_num;
        private TextView or_date;
        private TextView or_status;
        private TextView or_quantity;
        private TextView or_prname;
        private TextView price;



        public ProductDamuaHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.imageView);
            or_num=itemView.findViewById(R.id.or_num);
            or_status=itemView.findViewById(R.id.or_status);
            or_quantity=itemView.findViewById(R.id.or_quantity);
            or_prname=itemView.findViewById(R.id.or_prname);
            price=itemView.findViewById(R.id.price);
            or_date=itemView.findViewById(R.id.or_date);


        }

    }


}
