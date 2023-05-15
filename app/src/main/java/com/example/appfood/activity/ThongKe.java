package com.example.appfood.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appfood.R;
import com.example.appfood.adapter.TopMuaHuyAdapter;
import com.example.appfood.api.APIService;
import com.example.appfood.model.DoanhThu;
import com.example.appfood.model.ListMuaHuy;
import com.example.appfood.model.ListProductOrrder;
import com.example.appfood.model.MuaHuyModel;
import com.example.appfood.model.ProductOrderModel;
import com.example.appfood.model.listDoanhThu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThongKe extends AppCompatActivity {

    private Button tracuu;
    private EditText ed_ngay;
    private EditText ed_thang;
    private EditText ed_nam;
    private TextView doanhthu_ngay;
    private TextView doanhthu_thang;
    private TextView doanhthu_nam;
    private TextView doanhthu_tong;

    private TextView doanhang_ngay;
    private TextView donhang_thang;
    private TextView donhang_nam;
    private TextView donhang_tong;


    private TextView choxacnhan_ngay;
    private TextView choxacnhan_thang;
    private TextView choxacnhan_nam;

    private TextView choxacnhan_tong;

    private TextView daxacnhan_nam;
    private TextView daxacnhan_thang;
    private TextView daxacnhan_ngay;
    private TextView daxacnhan_tong;

    private TextView dagiavc_nam;
    private TextView dagiavc_thang;
    private TextView dagiavc_ngay;
    private TextView dagiavc_tong;

    private TextView danhan_nam;
    private TextView danhan_thang;
    private TextView danhan_ngay;

    private  TextView danhan_tong;

    private TextView dahuy_nam;
    private TextView dahuy_ngay;
    private TextView dahuy_thang;

    private TextView dahuy_tong;

    private TextView soluongsp_ngay;
    private TextView chitiet_ngay;

    private TextView soluong_thang;
    private TextView chitiet_thang;
    private RecyclerView mRecyclerViewmua;
    private RecyclerView mRecyclerViewhuy;
    private TopMuaHuyAdapter topMuaHuyAdapter;
    private TextView soluong_nam;
    private TextView chitiet_nam;
    private TextView banchay;
    private TextView yeuthich;
    private TextView chiase;
    private TextView danhgia;

    private Button pchart;
    private Button cbchart;
    private Button barchart;


    LinearLayout sanpham;
    LinearLayout don;
    LinearLayout nguoidung;
    LinearLayout tkcuatoi;
    private List<ProductOrderModel>list= new ArrayList<>();


    private String donhang=new String();

    private String day;
    private String month;
    private String year;
    private Date currentDate = new Date();

    private List<MuaHuyModel>listmuahuy=new ArrayList<>();

    private APIService apiService = APIService.apiService;

    private SimpleDateFormat dateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);
        AnhXa();
        mRecyclerViewmua.setLayoutManager(new GridLayoutManager(ThongKe.this, 1));
        mRecyclerViewmua.setHasFixedSize(true);

        mRecyclerViewhuy.setLayoutManager(new GridLayoutManager(ThongKe.this, 1));
        mRecyclerViewhuy.setHasFixedSize(true);


        dateFormat = new SimpleDateFormat("dd");
        day = dateFormat.format(currentDate);

        dateFormat = new SimpleDateFormat("MM");
        month = dateFormat.format(currentDate);

        dateFormat = new SimpleDateFormat("yyyy");
        year = dateFormat.format(currentDate);

        ed_thang.setText(month);
        ed_ngay.setText(day);
        ed_nam.setText(year);
        Thongke();


        tracuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thongke();
            }
        });

        sanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThongKe.this, ProductManagerment.class);
                startActivity(intent);
            }
        });

        don.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThongKe.this, DonHang.class);
                startActivity(intent);
            }
        });
        nguoidung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThongKe.this, NguoiDung.class);
                startActivity(intent);
            }
        });
        tkcuatoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThongKe.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
        chitiet_ngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThongKe.this, ListProductDaMua.class);


                Bundle bundle = new Bundle();
                bundle.putSerializable("key", "1");
                bundle.putSerializable("day",ed_ngay.getText().toString() );
                bundle.putSerializable("month", ed_thang.getText().toString());
                bundle.putSerializable("year", ed_nam.getText().toString());

                intent.putExtras(bundle);
               startActivity(intent);
            }
        });
        chitiet_thang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThongKe.this, ListProductDaMua.class);


                Bundle bundle = new Bundle();
                bundle.putSerializable("key", "2");
                bundle.putSerializable("day",ed_ngay.getText().toString() );
                bundle.putSerializable("month", ed_thang.getText().toString());
                bundle.putSerializable("year", ed_nam.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        chitiet_nam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThongKe.this, ListProductDaMua.class);


                Bundle bundle = new Bundle();
                bundle.putSerializable("key", "3");
                bundle.putSerializable("day",ed_ngay.getText().toString() );
                bundle.putSerializable("month", ed_thang.getText().toString());
                bundle.putSerializable("year", ed_nam.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        banchay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThongKe.this, ThongkeProduct.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("top", "1");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        yeuthich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThongKe.this, ThongkeProduct.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("top", "2");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        chiase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThongKe.this, ThongkeProduct.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("top", "3");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        danhgia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThongKe.this, ThongkeProduct.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("top", "4");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        pchart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThongKe.this, PChartActivity.class);
                startActivity(intent);
            }
        });
        cbchart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThongKe.this, CbChartActivity.class);
                startActivity(intent);
            }
        });
        barchart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThongKe.this, BarChartActivity.class);
                startActivity(intent);
            }
        });
    }


    private void Thongke()
    {
        if(ed_thang.getText().toString().isEmpty())
        {
            dateFormat = new SimpleDateFormat("MM");
            month = dateFormat.format(currentDate);
            ed_thang.setText(month);
        }
        if(ed_ngay.getText().toString().isEmpty())
        {
            dateFormat = new SimpleDateFormat("dd");
            day = dateFormat.format(currentDate);
            ed_ngay.setText(day);
        }
        if(ed_nam.getText().toString().isEmpty())
        {
            dateFormat = new SimpleDateFormat("yyyy");
            year = dateFormat.format(currentDate);
            ed_nam.setText(year);
        }



        // doanh thu ngày
        Call<listDoanhThu> call =apiService.getdoanhthungay(Integer.parseInt(ed_ngay.getText().toString()),Integer.parseInt(ed_thang.getText().toString()),Integer.parseInt(ed_nam.getText().toString()));
        call.enqueue(new Callback<listDoanhThu>() {
            @Override
            public void onResponse(Call<listDoanhThu> call, Response<listDoanhThu> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null)
                    {
                        if(response.body().getDoanhthu().get(0).getPrice()==null){
                            doanhthu_ngay.setText("0 VND");
                        }
                        else {
                            doanhthu_ngay.setText(response.body().getDoanhthu().get(0).getPrice()+" VND");
                        }
                    }

                }

            }

            @Override
            public void onFailure(Call<listDoanhThu> call, Throwable t) {
            Toast.makeText(ThongKe.this,"Err:"+ t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        /// doanh thu tháng

        Call<listDoanhThu> callthang =apiService.getdoanhthuthang(Integer.parseInt(ed_thang.getText().toString()),Integer.parseInt(ed_nam.getText().toString()));
        callthang.enqueue(new Callback<listDoanhThu>() {
            @Override
            public void onResponse(Call<listDoanhThu> call, Response<listDoanhThu> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null)
                    {

                        if(response.body().getDoanhthu().get(0).getPrice()==null){
                           doanhthu_thang.setText("0"+" VND");
                        }
                        else {
                            doanhthu_thang.setText(response.body().getDoanhthu().get(0).getPrice()+" VND");
                        }
                    }

                }

            }

            @Override
            public void onFailure(Call<listDoanhThu> call, Throwable t) {
                Toast.makeText(ThongKe.this,"Err:"+ t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        /// doanh thu năm
       Call<listDoanhThu> calldoanhthunam =apiService.getdoanhthunam(Integer.parseInt(ed_nam.getText().toString()));
        calldoanhthunam.enqueue(new Callback<listDoanhThu>() {
            @Override
            public void onResponse(Call<listDoanhThu> call, Response<listDoanhThu> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null)
                    {
                        if(response.body().getDoanhthu().get(0).getPrice()==null){
                            doanhthu_nam.setText("0 "+"VND");
                        }
                        else {
                            doanhthu_nam.setText(response.body().getDoanhthu().get(0).getPrice()+" VND");
                            doanhthu_tong.setText(doanhthu_nam.getText());
                        }


                    }

                }

            }

            @Override
            public void onFailure(Call<listDoanhThu> call, Throwable t) {
                Toast.makeText(ThongKe.this,"Err:"+ t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        // đon hàng theo ngay
        Call<listDoanhThu> calldonhangngay =apiService.getdonhangngay(Integer.parseInt(ed_ngay.getText().toString()),Integer.parseInt(ed_thang.getText().toString()),Integer.parseInt(ed_nam.getText().toString()));
        calldonhangngay.enqueue(new Callback<listDoanhThu>() {
            @Override
            public void onResponse(Call<listDoanhThu> calldonhangngay, Response<listDoanhThu> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null)
                    {
                        if(response.body().getDoanhthu().get(0).getPrice()==null){
                            doanhang_ngay.setText("Tổng: 0");
                        }
                        else {
                            doanhang_ngay.setText("Tổng: "+response.body().getDoanhthu().get(0).getPrice());
                        }
                    }

                }

            }

            @Override
            public void onFailure(Call<listDoanhThu> calldonhangngay, Throwable t) {
                Toast.makeText(ThongKe.this,"Err:"+ t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        //đơn hàng tháng

        Call<listDoanhThu> calldonhangthang =apiService.getdonhangthang(Integer.parseInt(ed_thang.getText().toString()),Integer.parseInt(ed_nam.getText().toString()));
        calldonhangthang.enqueue(new Callback<listDoanhThu>() {
            @Override
            public void onResponse(Call<listDoanhThu> calldonhangthang, Response<listDoanhThu> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null)
                    {

                        if(response.body().getDoanhthu().get(0).getPrice()==null){
                            donhang_thang.setText("Tổng: 0");
                        }
                        else {
                            donhang_thang.setText("Tổng: "+response.body().getDoanhthu().get(0).getPrice());
                        }
                    }

                }

            }

            @Override
            public void onFailure(Call<listDoanhThu> calldonhangthang, Throwable t) {
                Toast.makeText(ThongKe.this,"Err:"+ t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        //đơn hàng năm
        Call<listDoanhThu> calldonhangnam =apiService.getdonhangnam(Integer.parseInt(ed_nam.getText().toString()));
        calldonhangnam.enqueue(new Callback<listDoanhThu>() {
            @Override
            public void onResponse(Call<listDoanhThu> calldonhangnam, Response<listDoanhThu> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null)
                    {
                        if(response.body().getDoanhthu().get(0).getPrice()==null){
                            donhang_nam.setText("Tổng: 0");
                        }
                        else {
                            donhang_nam.setText("Tổng: "+response.body().getDoanhthu().get(0).getPrice());
                            donhang_tong.setText(donhang_nam.getText());
                        }


                    }

                }

            }

            @Override
            public void onFailure(Call<listDoanhThu> calldonhangnam, Throwable t) {
                Toast.makeText(ThongKe.this,"Err:"+ t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        getDonHangbySatusthang("1");
        getDonHangbySatusthang("2");
        getDonHangbySatusthang("3");
        getDonHangbySatusthang("4");
        getDonHangbySatusthang("0");

        getDonHangbyStatusngay("0");
        getDonHangbyStatusngay("1");
        getDonHangbyStatusngay("2");
        getDonHangbyStatusngay("3");
        getDonHangbyStatusngay("4");

        getDonhangbyStatusnam("0");
        getDonhangbyStatusnam("1");
        getDonhangbyStatusnam("2");
        getDonhangbyStatusnam("3");
        getDonhangbyStatusnam("4");

        getProductdamua(1);
        getProductdamua(2);
        getProductdamua(3);

        Call<ListMuaHuy>callmua=apiService.getTopMua();
        callmua.enqueue(new Callback<ListMuaHuy>() {
            @Override
            public void onResponse(Call<ListMuaHuy> call, Response<ListMuaHuy> response) {
                if(response.isSuccessful())
                {
                    listmuahuy=response.body().getThongke();
                    if(listmuahuy==null)
                        Toast.makeText(getApplicationContext(), "Không có dữ liệu", Toast.LENGTH_SHORT).show();
                    else {
                        topMuaHuyAdapter=new TopMuaHuyAdapter(listmuahuy,ThongKe.this);
                        mRecyclerViewmua.setAdapter(topMuaHuyAdapter);
                        topMuaHuyAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<ListMuaHuy> call, Throwable t) {
                Toast.makeText(ThongKe.this, "Không có ", Toast.LENGTH_SHORT).show();
            }
        });

        Call<ListMuaHuy>callhuy=apiService.gettop10HuyDon();
        callhuy.enqueue(new Callback<ListMuaHuy>() {
            @Override
            public void onResponse(Call<ListMuaHuy> call, Response<ListMuaHuy> response) {
                if(response.isSuccessful())
                {
                    listmuahuy=response.body().getThongke();
                    if(listmuahuy==null)
                        Toast.makeText(getApplicationContext(), "Không có dữ liệu", Toast.LENGTH_SHORT).show();
                    else {
                        topMuaHuyAdapter=new TopMuaHuyAdapter(listmuahuy,ThongKe.this);
                        mRecyclerViewhuy.setAdapter(topMuaHuyAdapter);
                        topMuaHuyAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<ListMuaHuy> call, Throwable t) {
                Toast.makeText(ThongKe.this, "Không có ", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getDonHangbyStatusngay(String status){


        Call<listDoanhThu> calldonhangstatus =apiService.getdonhangstatusngay(Integer.parseInt(ed_ngay.getText().toString()),Integer.parseInt(ed_thang.getText().toString()),Integer.parseInt(ed_nam.getText().toString()),status);
        calldonhangstatus.enqueue(new Callback<listDoanhThu>() {
            @Override
            public void onResponse(Call<listDoanhThu> calldonhangstatus, Response<listDoanhThu> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null)
                    {
                        if(response.body().getDoanhthu().get(0).getPrice()==null){
                            //doanhang_ngay.setText("0");
                            if(status.contains("1"))
                                choxacnhan_ngay.setText("Chờ xác nhận: 0");
                            else if(status.contains("2"))
                                daxacnhan_ngay.setText("Đã xác nhận: 0");
                            else if(status.contains("3"))
                                dagiavc_ngay.setText("Đã giao vận chuyển: 0");
                            else if(status.contains("4"))
                                danhan_ngay.setText("Đã nhận: 0");
                            else if(status.contains("0"))
                                dahuy_ngay.setText("Đã hủy: 0");

                        }
                        else {
                            if(status.contains("1"))
                                choxacnhan_ngay.setText("Chờ xác nhận: "+response.body().getDoanhthu().get(0).getPrice());
                            else if(status.contains("2"))
                                daxacnhan_ngay.setText("Đã xác nhận: "+response.body().getDoanhthu().get(0).getPrice());
                            else if(status.contains("3"))
                                dagiavc_ngay.setText("Đã giao vận chuyển: "+response.body().getDoanhthu().get(0).getPrice());
                            else if(status.contains("4"))
                                danhan_ngay.setText("Đã nhận: "+response.body().getDoanhthu().get(0).getPrice());
                            else if(status.contains("0"))
                                dahuy_ngay.setText("Đã hủy: "+response.body().getDoanhthu().get(0).getPrice());
                        }
                    }

                }

            }

            @Override
            public void onFailure(Call<listDoanhThu> calldonhangstatus, Throwable t) {
               donhang ="Err"+t.getMessage();
            }
        });


    }

    private void getDonHangbySatusthang(String status){
        Call<listDoanhThu> calldonstatusthang =apiService.getdonhangstatusthang(Integer.parseInt(ed_thang.getText().toString()),Integer.parseInt(ed_nam.getText().toString()),status);
        calldonstatusthang.enqueue(new Callback<listDoanhThu>() {
            @Override
            public void onResponse(Call<listDoanhThu> calldonstatusthang, Response<listDoanhThu> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null)
                    {

                        if(response.body().getDoanhthu().get(0).getPrice()==null){
                            if(status.contains("1"))
                                choxacnhan_thang.setText("Chờ xác nhận: 0");
                            else if(status.contains("2"))
                                daxacnhan_thang.setText("Đã xác nhận: 0");
                            else if(status.contains("3"))
                                dagiavc_thang.setText("Đã giao vận chuyển: 0");
                            else if(status.contains("4"))
                                danhan_thang.setText("Đã nhận: 0");
                            else if(status.contains("0"))
                                dahuy_thang.setText("Đã hủy: 0");
                        }
                        else {
                            if(status.contains("1"))
                                choxacnhan_thang.setText("Chờ xác nhận: "+response.body().getDoanhthu().get(0).getPrice());
                            else if(status.contains("2"))
                                daxacnhan_thang.setText("Đã xác nhận: "+response.body().getDoanhthu().get(0).getPrice());
                            else if(status.contains("3"))
                                dagiavc_thang.setText("Đã giao vận chuyển: "+response.body().getDoanhthu().get(0).getPrice());
                            else if(status.contains("4"))
                                danhan_thang.setText("Đã nhận: "+response.body().getDoanhthu().get(0).getPrice());
                            else if(status.contains("0"))
                                dahuy_thang.setText("Đã hủy: "+response.body().getDoanhthu().get(0).getPrice());

                        }
                    }

                }

            }

            @Override
            public void onFailure(Call<listDoanhThu> calldonstatusthang, Throwable t) {
                donhang=t.getMessage();
            }
        });


    }

    private void getProductdamua(int flag)
    {
        Call<ListProductOrrder> call=apiService.getproductdamuatheongaythangnam(Integer.parseInt(ed_ngay.getText().toString()),Integer.parseInt(ed_thang.getText().toString()),Integer.parseInt(ed_nam.getText().toString()),flag);
        call.enqueue(new Callback<ListProductOrrder>() {
            @Override
            public void onResponse(Call<ListProductOrrder> call, Response<ListProductOrrder> response) {
                if(response.isSuccessful()){
                    list=response.body().getList();
                    if(list==null)
                    {

                    if(flag==1){
                        soluongsp_ngay.setText("0");
                    }
                    else if(flag==2){
                            soluong_thang.setText("0");
                    }
                    else if(flag==3)
                        soluong_nam.setText("0");
                    }

                    else
                    {
                        if(flag==1){
                            soluongsp_ngay.setText(String.valueOf(list.size()));
                        }
                        else if(flag==2){
                            soluong_thang.setText(String.valueOf(list.size()));
                        }
                        else if(flag==3)
                            soluong_nam.setText(String.valueOf(list.size()));
                    }
                }

            }

            @Override
            public void onFailure(Call<ListProductOrrder> call, Throwable t) {

                donhang=t.getMessage();
            }
        });
    }

    private void getDonhangbyStatusnam(String status){
       //7/ /đơn hàng năm
        Call<listDoanhThu> caldonstatusnam =apiService.getdonhangstatusnam(Integer.parseInt(ed_nam.getText().toString()),status);
        caldonstatusnam.enqueue(new Callback<listDoanhThu>() {
            @Override
            public void onResponse(Call<listDoanhThu> caldonstatusnam, Response<listDoanhThu> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null)
                    {
                        if(response.body().getDoanhthu().get(0).getPrice()==null){
                            if(status.contains("1"))
                                choxacnhan_nam.setText("Chờ xác nhận: 0");
                            else if(status.contains("2"))
                                daxacnhan_nam.setText("Đã xác nhận: 0");
                            else if(status.contains("3"))
                                dagiavc_nam.setText("Đã giao vận chuyển: 0");
                            else if(status.contains("4"))
                                danhan_nam.setText("Đã nhận: 0");
                            else if(status.contains("0"))
                                dahuy_nam.setText("Đã hủy: 0");
                        }
                        else {
                            if(status.contains("1")){
                                choxacnhan_nam.setText("Chờ xác nhận: "+response.body().getDoanhthu().get(0).getPrice());
                                choxacnhan_tong.setText(choxacnhan_nam.getText());
                            }

                            else if(status.contains("2")){
                                daxacnhan_nam.setText("Đã xác nhận: "+response.body().getDoanhthu().get(0).getPrice());
                                daxacnhan_tong.setText(daxacnhan_nam.getText());
                            }
                            else if(status.contains("3")){
                                dagiavc_nam.setText("Đã giao vận chuyển: "+response.body().getDoanhthu().get(0).getPrice());
                                dagiavc_tong.setText(dagiavc_nam.getText());
                            }

                            else if(status.contains("4")) {
                                danhan_nam.setText("Đã nhận: " + response.body().getDoanhthu().get(0).getPrice());
                                danhan_tong.setText(danhan_nam.getText());
                            }
                            else if(status.contains("0")) {
                                dahuy_nam.setText("Đã hủy: " + response.body().getDoanhthu().get(0).getPrice());
                                dahuy_tong.setText(dahuy_nam.getText());
                            }


                        }


                    }

                }

            }

            @Override
            public void onFailure(Call<listDoanhThu> caldonstatusnam, Throwable t) {
                donhang=t.getMessage();
            }
        });

    }


    private void AnhXa()
    {
        tracuu=findViewById(R.id.tracuu);
        ed_ngay=findViewById(R.id.editTextNgay);
        ed_thang=findViewById(R.id.editTextThang);
        ed_nam=findViewById(R.id.editTextNam);
        doanhthu_ngay=findViewById(R.id.doanhthu_ngay);
        doanhthu_thang=findViewById(R.id.doanhthu_thang);
        doanhthu_nam=findViewById(R.id.doanhthu_nam);
        doanhthu_tong=findViewById(R.id.doanhthu_tong);
        doanhang_ngay=findViewById(R.id.doanhang_ngay);
        donhang_thang=findViewById(R.id.donhang_thang);
        donhang_nam=findViewById(R.id.donhang_nam);
        donhang_tong=findViewById(R.id.donhang_tong);
        choxacnhan_ngay=findViewById(R.id.choxacnhan_ngay);
        choxacnhan_thang = findViewById(R.id.choxacnhan_thang);
        choxacnhan_nam=findViewById(R.id.choxacnhan_nam);
        daxacnhan_ngay=findViewById(R.id.daxacnhan_ngay);
        daxacnhan_thang=findViewById(R.id.daxacnhan_thang);
        daxacnhan_nam=findViewById(R.id.daxacnhan_nam);
        dagiavc_ngay=findViewById(R.id.dagiavc_ngay);
        dagiavc_thang=findViewById(R.id.dagiavc_thang);
        dagiavc_nam=findViewById(R.id.dagiavc_nam);
        danhan_ngay=findViewById(R.id.danhan_ngay);
        danhan_thang=findViewById(R.id.danhan_thang);
        danhan_nam=findViewById(R.id.danhan_nam);
        dahuy_ngay=findViewById(R.id.dahuy_ngay);
        dahuy_thang=findViewById(R.id.dahuy_thang);
        dahuy_nam=findViewById(R.id.dahuy_nam);

        choxacnhan_tong=findViewById(R.id.choxacnhan_tong);
        daxacnhan_tong=findViewById(R.id.daxacnhan_tong);
        dagiavc_tong=findViewById(R.id.dagiavc_tong);
        danhan_tong=findViewById(R.id.danhan_tong);
        dahuy_tong=findViewById(R.id.dahuy_tong);

        soluongsp_ngay=findViewById(R.id.soluongsp_ngay);
        chitiet_ngay=findViewById(R.id.chitiet_ngay);
        soluong_thang=findViewById(R.id.soluong_thang);
        soluong_nam=findViewById(R.id.soluong_nam);
        chitiet_thang=findViewById(R.id.chitiet_thang);
        chitiet_nam=findViewById(R.id.chitiet_nam);

        banchay=findViewById(R.id.top10banchay);
        yeuthich=findViewById(R.id.top10yeuthich);
        chiase=findViewById(R.id.top10chíase);
        danhgia=findViewById(R.id.top10danhgia);

        mRecyclerViewmua=findViewById(R.id.recycler_view1);
        mRecyclerViewhuy=findViewById(R.id.recycler_view2);

        sanpham=(LinearLayout)findViewById(R.id.sanpham);
        don=(LinearLayout)findViewById(R.id.donhang);
        nguoidung=(LinearLayout)findViewById(R.id.nguoidung);
        tkcuatoi=(LinearLayout)findViewById(R.id.tkcuatoi);

        pchart=findViewById(R.id.pchart);
        cbchart=findViewById(R.id.CbChart);
        barchart=findViewById(R.id.Barchart);




    }
}