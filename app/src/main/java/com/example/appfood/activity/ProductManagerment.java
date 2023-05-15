package com.example.appfood.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.appfood.R;
import com.example.appfood.adapter.ProductAdapter;
import com.example.appfood.api.APIService;
import com.example.appfood.model.MessagerProductAll;
import com.example.appfood.model.ProductModel;
import com.example.appfood.model.Products;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductManagerment extends AppCompatActivity {



    private RecyclerView mRecyclerView;
    private ProductAdapter mAdapter;
    private SearchView serchview;
    private List<ProductModel> productModels =new ArrayList<>();
    private Button btnThem;

    private Button btndangban;
    private Button ngungban;
    private Button danhmuc;


    LinearLayout nguoidung;
    LinearLayout donhang;
    LinearLayout thongke;
    LinearLayout tkcuatoi;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_managerment);
        btnThem=findViewById(R.id.btn_add_product);
        nguoidung=(LinearLayout)findViewById(R.id.nguoidung);
        donhang=(LinearLayout)findViewById(R.id.donhang);
        thongke=(LinearLayout)findViewById(R.id.thongke);
        tkcuatoi=(LinearLayout)findViewById(R.id.tkcuatoi);
        danhmuc=findViewById(R.id.danhmuc);
        danhmuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductManagerment.this, ProductByCategory.class);
                startActivity(intent);
            }
        });

        btndangban =findViewById(R.id.dangban);
        ngungban = findViewById(R.id.ngungban);


      //  page=findViewById(R.id.tvspage);
     //   sp=findViewById(R.id.tv_sp);

      //  page.setText("1");
      //  sp.setText("10");

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(ProductManagerment.this, 2));
        mRecyclerView.setHasFixedSize(true);

        ListProduct("dangban");
        btndangban.setBackgroundColor(Color.GREEN);


        serchview = findViewById(R.id.search_view);
        serchview.clearFocus();
        serchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filterListener(s);

                return true;
            }
        });
        ngungban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ngungban.setBackgroundColor(Color.GREEN);
                int color = ContextCompat.getColor(getApplicationContext(), R.color.primary);
                btndangban.setBackgroundColor(color);

                    ListProduct("ngungban");

            }
        });

        btndangban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btndangban.setBackgroundColor(Color.GREEN);
                int color = ContextCompat.getColor(getApplicationContext(), R.color.primary);
                ngungban.setBackgroundColor(color);
                ListProduct("dangban");

            }
        });




        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductManagerment.this, AddProduct.class);
                startActivity(intent);
            }
        });
        nguoidung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductManagerment.this, NguoiDung.class);
                startActivity(intent);
            }
        });

        donhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductManagerment.this, DonHang.class);
                startActivity(intent);
            }
        });
        thongke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductManagerment.this, ThongKe.class);
                startActivity(intent);
            }
        });
        tkcuatoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductManagerment.this, ProfileActivity.class);
                startActivity(intent);
            }
        });




    }

    private void ListProduct(String S) {
        // gọi API và hiển thị danh sách sản phẩm
        APIService productService = APIService.apiService;
         // lấy trang 1 với kích thước trang 10

        Call<Products> call = productService.getAllProducts();
        call.enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products> response) {
                if (response.isSuccessful()) {
                    if((response.body())!=null){
                        productModels = (response.body().getProducts());
                        List<ProductModel> list = new ArrayList<>();
                        if (S.toLowerCase().contains("all")) {
                            list=productModels;
                        }
                        else if(S.toLowerCase().contains("ngungban"))
                        {
                            for (ProductModel pro : productModels) {
                                if(pro.getActive()==0)
                                    list.add(pro);

                            }
                        }
                        else if(S.toLowerCase().contains("dangban"))
                        {
                            for (ProductModel pro : productModels) {
                                if(pro.getActive()==1)
                                    list.add(pro);

                            }
                        }

                        if (list.isEmpty()) {
                            Toast toast=Toast.makeText(ProductManagerment.this, "Không tìm thấy sản phẩm tương tự", Toast.LENGTH_SHORT);
                            toast.show();
                            Handler handler = new Handler();
                            mAdapter = new ProductAdapter(list, ProductManagerment.this);
                            mRecyclerView.setAdapter(mAdapter);
                            mAdapter.notifyDataSetChanged();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    toast.cancel();
                                }
                            }, 3000);
                        } else {

                            mAdapter = new ProductAdapter(list, ProductManagerment.this);
                            mRecyclerView.setAdapter(mAdapter);
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                }

                else {
                    Toast.makeText(ProductManagerment.this, "Failed to get products", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Products> call, Throwable t) {

            }
        });
    }

    private void filterListener(String S)
    {
        APIService productService = APIService.apiService;
        Call<Products> call = productService.getAllProducts();
        call.enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products> response) {
                if (response.isSuccessful()) {
                    if((response.body())!=null){
                        productModels = (response.body().getProducts());


                        List<ProductModel> list = new ArrayList<>();
                        for (ProductModel pro : productModels) {
                            if (pro.getProduct_name().toLowerCase().contains(S.toLowerCase())) {
                                list.add(pro);
                            }
                        }
                        if (list.isEmpty()) {
                            Toast toast=Toast.makeText(ProductManagerment.this, "Không tìm thấy sản phẩm tương tự", Toast.LENGTH_SHORT);
                            toast.show();
                            Handler handler = new Handler();
                            mAdapter = new ProductAdapter(list, ProductManagerment.this);
                            mRecyclerView.setAdapter(mAdapter);
                            mAdapter.notifyDataSetChanged();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    toast.cancel();
                                }
                            }, 3000);
                        } else {

                            mAdapter = new ProductAdapter(list, ProductManagerment.this);
                            mRecyclerView.setAdapter(mAdapter);
                            mAdapter.notifyDataSetChanged();
                        }
                }
                }

                else {
                    Toast.makeText(ProductManagerment.this, "Failed to get products", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Products> call, Throwable t) {

            }
        });







    }

    }