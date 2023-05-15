package com.example.appfood.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfood.R;
import com.example.appfood.activity.ProductByCategory;
import com.example.appfood.activity.ProductManagerment;
import com.example.appfood.api.APIService;
import com.example.appfood.model.ProductModel;
import com.example.appfood.model.Products;
import com.example.appfood.model.category;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {
    private List<category> buttonContentList;
    private ProductbyCategoryAdapter recyclerView2Adapter;
    private RecyclerView recyclerView2;
    private List<ProductModel> productModels =new ArrayList<>();
    private Context mContext;
    private  List<Boolean> buttonStates;

    public CategoryAdapter(List<category> buttonContentList, ProductbyCategoryAdapter recyclerView2Adapter, RecyclerView recyclerView2, Context mContext) {
        this.buttonContentList = buttonContentList;
        this.recyclerView2Adapter = recyclerView2Adapter;
        this.recyclerView2 = recyclerView2;
        this.mContext = mContext;
        this.buttonStates = new ArrayList<>(Collections.nCopies(buttonContentList.size(), false)); // Khởi tạo trạng thái mặc định cho các button
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public CategoryAdapter(List<category> buttonContentList, ProductbyCategoryAdapter recyclerView2Adapter, RecyclerView recyclerView2) {
        this.buttonContentList = buttonContentList;
        this.recyclerView2Adapter = recyclerView2Adapter;
        this.recyclerView2 = recyclerView2;
        this.buttonStates = new ArrayList<>(Collections.nCopies(buttonContentList.size(), false)); // Khởi tạo trạng th
    }

    public CategoryAdapter(List<category> buttonContentList, RecyclerView recyclerView2, Context mContext) {
        this.buttonContentList = buttonContentList;
        this.recyclerView2 = recyclerView2;
        this.mContext = mContext;
        this.buttonStates = new ArrayList<>(Collections.nCopies(buttonContentList.size(), false)); // Khởi tạo trạng th
    }

    public CategoryAdapter(List<category> buttonContentList, Context mContext) {
        this.buttonContentList = buttonContentList;
        this.mContext = mContext;
        this.buttonStates = new ArrayList<>(Collections.nCopies(buttonContentList.size(), false)); // Khởi tạo trạng th
    }


    public CategoryAdapter() {
    }

    public List<category> getButtonContentList() {
        return buttonContentList;
    }

    public void setButtonContentList(List<category> buttonContentList) {
        this.buttonContentList = buttonContentList;
    }

    public void setRecyclerView2Adapter(ProductbyCategoryAdapter recyclerView2Adapter) {
        this.recyclerView2Adapter = recyclerView2Adapter;
    }

    public void setRecyclerView2(RecyclerView recyclerView2) {
        this.recyclerView2 = recyclerView2;
    }

    public ProductbyCategoryAdapter getRecyclerView2Adapter() {
        return recyclerView2Adapter;
    }

    public RecyclerView getRecyclerView2() {
        return recyclerView2;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.button, null);

        return new CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        String buttonContent = buttonContentList.get(position).getCategory();
        holder.button.setText(buttonContent);
        if (buttonStates.get(position)) {
            holder.button.setBackgroundColor(Color.GREEN); // Đặt màu cho button được chọn
        } else {
            int color = ContextCompat.getColor(mContext.getApplicationContext(), R.color.primary);
            holder.button.setBackgroundColor(color); // Đặt màu mặc định cho các button khác
        }
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int clickedPosition = holder.getAdapterPosition();
                for (int i = 0; i < buttonStates.size(); i++) {
                    if (i == clickedPosition) {
                        buttonStates.set(i, true);

                    }
                    else {
                        buttonStates.set(i, false);
                    }
                }
                notifyDataSetChanged();


                String selectedButtonContent = holder.button.getText().toString();
                APIService productService = APIService.apiService;


                Call<Products> call1 = productService.getAllProducts();
                call1.enqueue(new Callback<Products>() {
                    @Override
                    public void onResponse(Call<Products> call1, Response<Products> response) {
                        if (response.isSuccessful()) {
                            if((response.body())!=null){
                               productModels=response.body().getProducts();
                                List<ProductModel> list = new ArrayList<>();
                                for (ProductModel pro : productModels) {
                                    if (pro.getCategory().toLowerCase().contains(holder.button.getText().toString().toLowerCase())) {
                                        list.add(pro);
                                    }
                                }
                                if (list.isEmpty()) {
                                    Toast toast=Toast.makeText(mContext, "Không tìm thấy", Toast.LENGTH_SHORT);
                                    toast.show();
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            toast.cancel();
                                        }
                                    }, 3000);
                                } else {

                                    recyclerView2Adapter =new ProductbyCategoryAdapter(list,mContext);
                                    recyclerView2.setAdapter(recyclerView2Adapter);
                                    recyclerView2Adapter.notifyDataSetChanged();

                                }
                            }
                        }

                        else {
                            Toast toast=Toast.makeText(mContext, "Không tìm thấy sản phẩm tương tự", Toast.LENGTH_SHORT);
                            toast.show();
                        }

                    }

                    @Override
                    public void onFailure(Call<Products> call, Throwable t) {

                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        if (buttonContentList != null) {
            return buttonContentList.size();
        }
        return 0;
    }

    public  class CategoryHolder extends RecyclerView.ViewHolder{
        private Button button;

        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            button=(Button)  itemView.findViewById(R.id.button);
        }
    }
}
