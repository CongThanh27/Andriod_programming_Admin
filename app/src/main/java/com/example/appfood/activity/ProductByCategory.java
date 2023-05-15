package com.example.appfood.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import android.widget.Toast;

import com.example.appfood.R;
import com.example.appfood.adapter.CategoryAdapter;

import com.example.appfood.adapter.ProductAdapter;
import com.example.appfood.adapter.ProductbyCategoryAdapter;
import com.example.appfood.api.APIService;
import com.example.appfood.model.ProductModel;
import com.example.appfood.model.Products;

import com.example.appfood.model.listcategory;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductByCategory extends AppCompatActivity {

    private RecyclerView mRecyclerViewcategory;
    private RecyclerView mRecyclerViewproduct;

    private CategoryAdapter categoryAdapter;
    private List<ProductModel> productModels =new ArrayList<>();
    private ProductbyCategoryAdapter productbyCategoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_by_category);
        mRecyclerViewcategory=findViewById(R.id.recycler_view1);
        mRecyclerViewproduct=findViewById(R.id.recycler_view2);

        LinearLayoutManager layoutManager = new LinearLayoutManager(ProductByCategory.this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerViewcategory.setLayoutManager(layoutManager);
        mRecyclerViewcategory.setHasFixedSize(true);

        mRecyclerViewproduct.setLayoutManager(new GridLayoutManager(ProductByCategory.this, 2));
        mRecyclerViewproduct.setHasFixedSize(true);



        APIService productService = APIService.apiService;
        Call<listcategory> call=productService.getCategories();
        call.enqueue(new Callback<listcategory>() {
            @Override
            public void onResponse(Call<listcategory> call, Response<listcategory> response) {
                if (response.isSuccessful()) {
                    if((response.body()!=null))
                    {
                        categoryAdapter = new CategoryAdapter(response.body().getListCategory(),mRecyclerViewproduct,ProductByCategory.this);
                        mRecyclerViewcategory.setAdapter(categoryAdapter);
                        categoryAdapter.notifyDataSetChanged();

                    }


                }
                else {
                    Toast.makeText(ProductByCategory.this, "Failed to get category", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<listcategory>call, Throwable t) {
                Toast.makeText(ProductByCategory.this, "error"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        Call<Products> call1 = productService.getAllProducts();
        call1.enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products> response) {
                if (response.isSuccessful()) {
                    if((response.body())!=null){
                        productModels = (response.body().getProducts());


                       ProductAdapter mAdapter= new ProductAdapter(productModels,ProductByCategory.this);
                       productbyCategoryAdapter = new ProductbyCategoryAdapter(productModels,ProductByCategory.this);
                        mRecyclerViewproduct.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();


                    }
                }

                else {
                    Toast.makeText(ProductByCategory.this, "Failed to get products", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Products> call, Throwable t) {

            }
        });


    }
}