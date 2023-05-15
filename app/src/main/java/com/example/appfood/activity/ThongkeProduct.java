package com.example.appfood.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.appfood.R;
import com.example.appfood.adapter.ProductDamuaAdapter;
import com.example.appfood.adapter.ProductFullAdapter;
import com.example.appfood.api.APIService;
import com.example.appfood.model.ProductModel;
import com.example.appfood.model.ProductOrderModel;
import com.example.appfood.model.Products;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThongkeProduct extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private APIService apiService = APIService.apiService;

    private ProductFullAdapter mAdapter;
    private List<ProductModel> list= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongke_product);
        mRecyclerView=findViewById(R.id.recycler_view1);
        Bundle bundle = getIntent().getExtras();
        mRecyclerView.setLayoutManager(new GridLayoutManager(ThongkeProduct.this, 1));
        mRecyclerView.setHasFixedSize(true);
        if (bundle == null) {
            Intent intent = new Intent(ThongkeProduct.this, ThongKe.class);
            startActivity(intent);
        }
        else{
            int key=Integer.parseInt(bundle.get("top").toString());
            if(key==1){
                Call<Products> call=apiService.getTop10BanChay();
                call.enqueue(new Callback<Products>() {
                    @Override
                    public void onResponse(Call<Products> call, Response<Products> response) {
                        if(response.isSuccessful()){
                            list=response.body().getProducts();
                            if(list==null){
                                Toast.makeText(getApplicationContext(), "Không có dữ liệu", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                mAdapter= new ProductFullAdapter(list,ThongkeProduct.this);
                                mRecyclerView.setAdapter(mAdapter);
                                mAdapter.notifyDataSetChanged();
                            }
                        }
                        else {
                            Toast.makeText(ThongkeProduct.this, response.body().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Products> call, Throwable t) {
                        Toast.makeText(ThongkeProduct.this, "Error"+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else if(key==2){
                Call<Products> call=apiService.getTop10yeuThich();
                call.enqueue(new Callback<Products>() {
                    @Override
                    public void onResponse(Call<Products> call, Response<Products> response) {
                        if(response.isSuccessful()){
                            list=response.body().getProducts();
                            if(list==null){
                                Toast.makeText(getApplicationContext(), "Không có dữ liệu", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                mAdapter= new ProductFullAdapter(list,ThongkeProduct.this);
                                mRecyclerView.setAdapter(mAdapter);
                                mAdapter.notifyDataSetChanged();
                            }
                        }
                        else {
                            Toast.makeText(ThongkeProduct.this, "Không có ", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Products> call, Throwable t) {
                        Toast.makeText(ThongkeProduct.this, "Error"+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else if(key==3){
                Call<Products> call=apiService.getTop10ChiaSe();
                call.enqueue(new Callback<Products>() {
                    @Override
                    public void onResponse(Call<Products> call, Response<Products> response) {
                        if(response.isSuccessful()){
                            list=response.body().getProducts();
                            if(list==null){
                                Toast.makeText(getApplicationContext(), "Không có dữ liệu", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                mAdapter= new ProductFullAdapter(list,ThongkeProduct.this);
                                mRecyclerView.setAdapter(mAdapter);
                                mAdapter.notifyDataSetChanged();
                            }
                        }
                        else {
                            Toast.makeText(ThongkeProduct.this, "Không có ", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Products> call, Throwable t) {
                        Toast.makeText(ThongkeProduct.this, "Error"+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else if(key==4){
                Call<Products> call=apiService.top10danhgia();
                call.enqueue(new Callback<Products>() {
                    @Override
                    public void onResponse(Call<Products> call, Response<Products> response) {
                        if(response.isSuccessful()){
                            list=response.body().getProducts();
                            if(list==null){
                                Toast.makeText(getApplicationContext(), "Không có dữ liệu", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                mAdapter= new ProductFullAdapter(list,ThongkeProduct.this);
                                mRecyclerView.setAdapter(mAdapter);
                                mAdapter.notifyDataSetChanged();
                            }
                        }
                        else {
                            Toast.makeText(ThongkeProduct.this, "Không có ", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Products> call, Throwable t) {
                        Toast.makeText(ThongkeProduct.this, "Error"+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }


    }
}