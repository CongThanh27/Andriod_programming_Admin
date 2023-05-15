package com.example.appfood.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.appfood.R;
import com.example.appfood.adapter.ProductDamuaAdapter;
import com.example.appfood.api.APIService;
import com.example.appfood.model.ListProductOrrder;
import com.example.appfood.model.ProductOrderModel;
import com.example.appfood.model.listDoanhThu;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListProductDaMua extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private APIService apiService = APIService.apiService;

    private ProductDamuaAdapter mAdapter;
    private List<ProductOrderModel> list= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product_da_mua);
        mRecyclerView=findViewById(R.id.recycler_view1);
        Bundle bundle = getIntent().getExtras();

        mRecyclerView.setLayoutManager(new GridLayoutManager(ListProductDaMua.this, 1));
        mRecyclerView.setHasFixedSize(true);
        if (bundle == null) {
            Intent intent = new Intent(ListProductDaMua.this, ThongKe.class);
            startActivity(intent);
        }
        else {
            int key=Integer.parseInt(bundle.get("key").toString());
            int ngay=Integer.parseInt(bundle.get("day").toString());
            int thang=Integer.parseInt(bundle.get("month").toString());
            int nam=Integer.parseInt(bundle.get("year").toString());
            getProductdamua(key,ngay,thang,nam);


        }


    }
    private void getProductdamua(int flag,int ngay,int thang,int nam)
    {
        Call<ListProductOrrder> call=apiService.getproductdamuatheongaythangnam(ngay,thang,nam,flag);
        call.enqueue(new Callback<ListProductOrrder>() {
            @Override
            public void onResponse(Call<ListProductOrrder> call, Response<ListProductOrrder> response) {
                if(response.isSuccessful()){
                    list=response.body().getList();
                    if(list==null)
                    {
                        Toast.makeText(getApplicationContext(), "Không có dữ liệu", Toast.LENGTH_SHORT).show();

                    }

                    else
                    {

                        mAdapter=new ProductDamuaAdapter(list,ListProductDaMua.this);
                        mRecyclerView.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    }
                }
                else {
                    Toast.makeText(ListProductDaMua.this, "Không có ", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ListProductOrrder> call, Throwable t) {
                Toast.makeText(ListProductDaMua.this, "Error"+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}