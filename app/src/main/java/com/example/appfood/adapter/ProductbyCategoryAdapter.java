package com.example.appfood.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appfood.R;
import com.example.appfood.activity.Edit_Product;
import com.example.appfood.api.APIService;
import com.example.appfood.model.ProductModel;
import com.example.appfood.util.Constants;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductbyCategoryAdapter extends RecyclerView.Adapter<ProductbyCategoryAdapter.ProductbyCategoryViewHolder>{

    private List<ProductModel> mProductModel;
    private Context mContext;

    public ProductbyCategoryAdapter(List<ProductModel> mProductModel, Context mContext) {
        this.mProductModel = mProductModel;
        this.mContext = mContext;
    }

    public List<ProductModel> getmProductModel() {
        return mProductModel;
    }

    public Context getmContext() {
        return mContext;
    }

    public ProductbyCategoryAdapter() {
    }

    public void setmProductModel(List<ProductModel> mProductModel) {
        this.mProductModel = mProductModel;
    }
    public void setListenerList(List<ProductModel> product){
        this.mProductModel=product;
        notifyDataSetChanged();
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ProductbyCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, null);
        return new ProductbyCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductbyCategoryViewHolder holder, int position) {
        ProductModel product = mProductModel.get(position);
        if (product == null) {
            return;
        }
        else {

            String correctedImagePath = product.getImage().replace("\\", "/");
            Glide.with(mContext)
                    .load(Constants.url+"/"+correctedImagePath)
                    .into(holder.imgProductImages);

            holder.category.setText("Danh mục: "+product.getCategory());
            holder.describe.setText("Mô tả: "+product.getDescription());
            holder.price.setText("Giá: "+ Double.toString(product.getPrice()));
            if(product.getActive()==0)
            {
                holder.active.setText("Không còn bán");
            }
            else {
                holder.active.setText("Đang bán");
            }
            holder.sex.setText("Giới tính: "+product.getSex());
            holder.textviewname.setText("Tên sản phẩm: "+product.getProduct_name());
            holder.buttonxoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RequestBody id = RequestBody.create(MediaType.parse("multipart/form-data"),String.valueOf(product.getId()));

                    RequestBody product_name = RequestBody.create(MediaType.parse("multipart/form-data"), product.getProduct_name());
                    RequestBody priceold = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(product.getPriceold()));
                    RequestBody price = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(product.getPrice()));
                    RequestBody quantity = RequestBody.create(MediaType.parse("multipart/form-data"),String.valueOf(product.getQuantity()));
                    RequestBody supplier = RequestBody.create(MediaType.parse("multipart/form-data"),product.getSupplier());
                    RequestBody category = RequestBody.create(MediaType.parse("multipart/form-data"), product.getCategory());
                    RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"), product.getDescription());
                    RequestBody trademark = RequestBody.create(MediaType.parse("multipart/form-data"), product.getTrademark());
                    RequestBody origin = RequestBody.create(MediaType.parse("multipart/form-data"), product.getOrigin());
                    RequestBody sex = RequestBody.create(MediaType.parse("multipart/form-data"), product.getSex());
                    RequestBody skinproblems = RequestBody.create(MediaType.parse("multipart/form-data"),product.getSkinproblems());
                    RequestBody active = RequestBody.create(MediaType.parse("multipart/form-data"),"0");
                    APIService apiService;
                    apiService = APIService.apiService;
                    Call<Void> call = apiService.updateAd(
                            id,
                            product_name,
                            price,
                            priceold,
                            quantity,
                            null,
                            supplier,
                            category,
                            description,
                            trademark,
                            origin,
                            sex,
                            skinproblems,
                            null,
                            null,
                            null,
                            null,
                            active,
                            null
                    );

                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(mContext, "Product update successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(mContext, "Failed to update product", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(mContext, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
            holder.buttoncapnhat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, Edit_Product.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("product", product);
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);
                }
            });
        }

    }



    @Override
    public int getItemCount() {
        if (mProductModel != null) {
            return mProductModel.size();
        }
        return 0;
    }

    public static class ProductbyCategoryViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgProductImages;
        private TextView textviewname;
        private TextView price;
        private TextView category;
        private TextView describe;
        private TextView sex;
        private TextView active;

        private Button buttonxoa;

        private Button buttoncapnhat;


        public ProductbyCategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProductImages = (ImageView) itemView.findViewById(R.id.imageViewProduct);
            textviewname = itemView.findViewById(R.id.textviewname);
            price = itemView.findViewById(R.id.price);
            category = itemView.findViewById(R.id.category);
            describe = itemView.findViewById(R.id.describe);
            sex = itemView.findViewById(R.id.sex);
            active = itemView.findViewById(R.id.active);
            buttonxoa=(Button)  itemView.findViewById(R.id.btnxoa);
            buttoncapnhat=(Button) itemView.findViewById(R.id.btncapnhat);


        }
    }
}
