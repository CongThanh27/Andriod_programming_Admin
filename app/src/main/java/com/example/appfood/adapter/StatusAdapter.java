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
import com.example.appfood.activity.DonHang;
import com.example.appfood.api.APIService;
import com.example.appfood.model.OrderModel;
import com.example.appfood.model.ProductModel;
import com.example.appfood.model.Products;
import com.example.appfood.model.listOrder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.OrderHolder> {
    private List<String> dstrangthai;

    private  List<Boolean> buttonStates; // Danh sách trạng thái của các button

    private OrderbyStatusAdapter recyclerView2Adapter;
    private RecyclerView recyclerView2;
    private Context mContext;

    private List<OrderModel> dsorder = new ArrayList<>();

    public StatusAdapter(List<String> dstrangthai, RecyclerView recyclerView2, Context mContext, List<OrderModel> dsorder) {
        this.dstrangthai = dstrangthai;
        this.recyclerView2 = recyclerView2;
        this.mContext = mContext;
        this.dsorder = dsorder;
        this.buttonStates = new ArrayList<>(Collections.nCopies(dstrangthai.size(), false)); // Khởi tạo trạng thái mặc định cho các button
        this.buttonStates.set(0, true);
    }

    public List<OrderModel> getDsorder() {
        return dsorder;
    }

    public void setDsorder(List<OrderModel> dsorder) {
        this.dsorder = dsorder;
    }

    public StatusAdapter(List<String> dstrangthai, RecyclerView recyclerView2, Context mContext) {
        this.dstrangthai = dstrangthai;
        this.recyclerView2 = recyclerView2;
        this.mContext = mContext;
        this.buttonStates = new ArrayList<>(Collections.nCopies(dstrangthai.size(), false)); // Khởi tạo trạng thái mặc định cho các button
    }

    public List<String> getDstrangthai() {
        return dstrangthai;
    }

    public OrderbyStatusAdapter getRecyclerView2Adapter() {
        return recyclerView2Adapter;
    }

    public RecyclerView getRecyclerView2() {
        return recyclerView2;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setDstrangthai(List<String> dstrangthai) {
        this.dstrangthai = dstrangthai;
    }

    public void setRecyclerView2Adapter(OrderbyStatusAdapter recyclerView2Adapter) {
        this.recyclerView2Adapter = recyclerView2Adapter;
    }

    public void setRecyclerView2(RecyclerView recyclerView2) {
        this.recyclerView2 = recyclerView2;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public OrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.button, null);
        return new OrderHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHolder holder, int position) {
   String buttonContent= dstrangthai.get(position);
    //String buttonContent = dstrangthai.get(holder.getAdapterPosition());
        if (buttonStates.get(position)) {
            holder.button.setBackgroundColor(Color.GREEN); // Đặt màu cho button được chọn
        } else {
            int color = ContextCompat.getColor(mContext.getApplicationContext(), R.color.primary);
            holder.button.setBackgroundColor(color); // Đặt màu mặc định cho các button khác
        }

    holder.button.setText(buttonContent);
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

            if(holder.button.getText().toString().contains("Chờ xác nhận")){
                fillter("1");
            }
            else if(holder.button.getText().toString().contains("Đã xác nhận")){
                fillter("2");
            }
            else if(holder.button.getText().toString().contains("Đang vận chuyển")){
                fillter("3");
            }
            else if(holder.button.getText().toString().contains("Đã nhận")){
                fillter("4");
            }
            else if(holder.button.getText().toString().contains("Đã hủy")){
                fillter("0");
            }

        }
    });
    }
    private void fillter(String s)
    {
        APIService productService = APIService.apiService;
        Call<listOrder> call =productService.getOrders();
        call.enqueue(new Callback<listOrder>() {
            @Override
            public void onResponse(Call<listOrder> call, Response<listOrder> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null) {
                        List<OrderModel>orderModelList= new ArrayList<>();
                        for (OrderModel ord : response.body().getOrders()) {
                            if (ord.getStatus().toLowerCase().toString().contains(s)) {
                                orderModelList.add(ord);
                            }

                        }
                        if (orderModelList != null){
                            Toast.makeText(mContext,String.valueOf(orderModelList.size()),Toast.LENGTH_SHORT);

                            recyclerView2Adapter = new OrderbyStatusAdapter(orderModelList, mContext);
                            recyclerView2.setAdapter(recyclerView2Adapter);
                            recyclerView2Adapter.notifyDataSetChanged();
                        }

                    }
                }
                else {
                    Toast.makeText(mContext,"Lấy sản phẩm không thành công",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<listOrder> call, Throwable t) {
                Toast.makeText(mContext,"EROR"+t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    public int getItemCount() {
       if(dstrangthai!=null)
       {
           return dstrangthai.size();
       }
       return 0;
    }

    public  class OrderHolder extends RecyclerView.ViewHolder{
        private Button button;

        public OrderHolder(@NonNull View itemView) {
            super(itemView);
            button= itemView.findViewById(R.id.button);

        }
    }
}
