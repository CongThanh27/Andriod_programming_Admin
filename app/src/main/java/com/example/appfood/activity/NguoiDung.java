package com.example.appfood.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appfood.R;
import com.example.appfood.adapter.ProductAdapter;
import com.example.appfood.adapter.UserApdapter;
import com.example.appfood.api.APIService;
import com.example.appfood.model.UserAll;
import com.example.appfood.model.UserModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NguoiDung extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private UserApdapter mAdapter;
    private SearchView serchview;
    private List<UserModel> userModelList= new ArrayList<>();


    private Button btnaddpage;
    private Button btnsubpage;
    private Button btnaddsp;
    private Button btnsubsp;
    private TextView page;
    private TextView sp;

    LinearLayout sanpham;
    LinearLayout donhang;
    LinearLayout thongke;
    LinearLayout tkcuatoi;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoi_dung);
        AnhXa();

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(NguoiDung.this, 2));
        mRecyclerView.setHasFixedSize(true);
        ListUser(1,100);

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

        /*btnsubsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Integer.parseInt(sp.getText().toString())>10)
                {
                    sp.setText(String.valueOf(Integer.parseInt(sp.getText().toString())-5));
                    ListUser(Integer.parseInt(page.getText().toString()),Integer.parseInt(sp.getText().toString()));

                }
            }
        });

        btnaddsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.setText(String.valueOf(Integer.parseInt(sp.getText().toString())+5));
                ListUser(Integer.parseInt(page.getText().toString()),Integer.parseInt(sp.getText().toString()));
            }
        });

        btnsubpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Integer.parseInt(page.getText().toString())>1)
                {
                    page.setText(String.valueOf(Integer.parseInt(page.getText().toString())-1));
                    ListUser(Integer.parseInt(page.getText().toString()),Integer.parseInt(sp.getText().toString()));

                }
            }
        });

        btnaddpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page.setText(String.valueOf(Integer.parseInt(page.getText().toString())+1));
                ListUser(Integer.parseInt(page.getText().toString()),Integer.parseInt(sp.getText().toString()));
            }
        });*/

        sanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NguoiDung.this, ProductManagerment.class);
                startActivity(intent);
            }
        });

        donhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NguoiDung.this, DonHang.class);
                startActivity(intent);
            }
        });
        thongke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NguoiDung.this, ThongKe.class);
                startActivity(intent);
            }
        });
        tkcuatoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NguoiDung.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

    }

    private void ListUser(int trang,int sotrang){
        APIService userservice = APIService.apiService;
        Call<UserAll> call=userservice.getAllUser(trang,sotrang);
        call.enqueue(new Callback<UserAll>() {
            @Override
            public void onResponse(Call<UserAll> call, Response<UserAll> response) {
                if (response.isSuccessful()) {
                    userModelList=response.body().getUsers();
                    mAdapter = new UserApdapter(userModelList,NguoiDung.this);
                    mRecyclerView.setAdapter(mAdapter);

                    mAdapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(NguoiDung.this, "Failed to get user", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<UserAll> call, Throwable t) {
                Toast.makeText(NguoiDung.this, "Error"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void AnhXa() {
        mRecyclerView= findViewById(R.id.recycler_view);
        /*page=findViewById(R.id.tvspage);
        sp=findViewById(R.id.tv_sp);*/
        serchview= findViewById(R.id.search_view);
        /*btnaddpage=findViewById(R.id.addpage);
        btnaddsp=findViewById(R.id.addsp);
        btnsubsp=findViewById(R.id.subsp);
        btnsubpage=findViewById(R.id.subpage);*/
        sanpham=(LinearLayout)findViewById(R.id.sanpham);
        donhang=(LinearLayout)findViewById(R.id.donhang);
        thongke=(LinearLayout)findViewById(R.id.thongke);
        tkcuatoi=(LinearLayout)findViewById(R.id.tkcuatoi);

    }

    private void filterListener(String S)
    {

        List<UserModel>list=new ArrayList<>();

        for(UserModel user: userModelList)
        {
            if (user.getName().toLowerCase().contains(S.toLowerCase())) {
                list.add(user);

            }
        }
        if (list.isEmpty())
        {
            Toast toast=Toast.makeText(NguoiDung.this, "Không tìm thấy người dùng tương tự", Toast.LENGTH_SHORT);
            toast.show();
            Handler handler = new Handler();
            mAdapter = new UserApdapter(list,NguoiDung.this);
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    toast.cancel();
                }
            }, 3000);
        }
        else {

            mAdapter = new UserApdapter(list,NguoiDung.this);
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        }
    }
}