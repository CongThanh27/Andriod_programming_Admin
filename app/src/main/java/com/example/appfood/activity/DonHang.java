package com.example.appfood.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.appfood.R;
import com.example.appfood.adapter.OrderbyStatusAdapter;
import com.example.appfood.adapter.StatusAdapter;
import com.example.appfood.api.APIService;
import com.example.appfood.model.OrderModel;
import com.example.appfood.model.listOrder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DonHang extends AppCompatActivity {
    LinearLayout nguoidung;
    LinearLayout sanpham;
    LinearLayout thongke;
    LinearLayout tkcuatoi;

    private RecyclerView mRecyclerViewstatus;
    private RecyclerView mRecyclerVieworder;
    private StatusAdapter statusAdapter;
    private List<OrderModel> orderModelList= new ArrayList<>();
    private OrderbyStatusAdapter orderbyStatusAdapter;
    private List<OrderModel> orderbyStatusAdapterfull=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_hang);

        nguoidung=(LinearLayout)findViewById(R.id.nguoidung);
        sanpham=(LinearLayout)findViewById(R.id.sanpham);
        thongke=(LinearLayout)findViewById(R.id.thongke);
        tkcuatoi=(LinearLayout)findViewById(R.id.tkcuatoi);

        mRecyclerVieworder=findViewById(R.id.recycler_view2);
        mRecyclerViewstatus=findViewById(R.id.recycler_view1);
        // xét trạng thái cho danh mục statsus
        LinearLayoutManager layoutManager = new LinearLayoutManager(DonHang.this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerViewstatus.setLayoutManager(layoutManager);
        mRecyclerViewstatus.setHasFixedSize(true);

        mRecyclerVieworder.setLayoutManager(new GridLayoutManager(DonHang.this, 1));
        mRecyclerVieworder.setHasFixedSize(true);
        List<String>stt= new ArrayList<>();
        stt.add("Chờ xác nhận");
        stt.add("Đã xác nhận");
        stt.add("Đang vận chuyển");
        stt.add("Đã nhận");
        stt.add("Đã hủy");

        APIService productService = APIService.apiService;
        Call<listOrder> call =productService.getOrders();
        call.enqueue(new Callback<listOrder>() {
            @Override
            public void onResponse(Call<listOrder> call, Response<listOrder> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null) {
                        orderbyStatusAdapterfull=response.body().getOrders();
                        for (OrderModel ord : response.body().getOrders()) {
                            if (ord.getStatus().toLowerCase().contains("1"))
                                orderModelList.add(ord);

                        }
                        if (orderModelList != null){

                            orderbyStatusAdapter = new OrderbyStatusAdapter(orderModelList, DonHang.this);
                        mRecyclerVieworder.setAdapter(orderbyStatusAdapter);
                        orderbyStatusAdapter.notifyDataSetChanged();
                    }

                    }
                }
                else {
                    Toast.makeText(DonHang.this,"Lấy sản phẩm không thành công",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<listOrder> call, Throwable t) {
                Toast.makeText(DonHang.this,"EROR"+t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });

        statusAdapter= new StatusAdapter(stt,mRecyclerVieworder,DonHang.this,orderbyStatusAdapterfull);
        mRecyclerViewstatus.setAdapter(statusAdapter);
        statusAdapter.notifyDataSetChanged();


        nguoidung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DonHang.this, NguoiDung.class);
                startActivity(intent);
            }
        });

        sanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DonHang.this, ProductManagerment.class);
                startActivity(intent);
            }
        });
        thongke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DonHang.this, ThongKe.class);
                startActivity(intent);
            }
        });
        tkcuatoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DonHang.this, ProfileActivity.class);
                startActivity(intent);
            }
        });


    }
}