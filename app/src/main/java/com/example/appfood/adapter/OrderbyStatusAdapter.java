package com.example.appfood.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfood.R;
import com.example.appfood.activity.DonHang;
import com.example.appfood.activity.Edit_Product;
import com.example.appfood.activity.ProductManagerment;
import com.example.appfood.api.APIService;
import com.example.appfood.model.OrderModel;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderbyStatusAdapter extends RecyclerView.Adapter<OrderbyStatusAdapter.OrderbyOrderNumberHolder> {


    private List<OrderModel> mOrderModel;
    private Context mContext;

    private   RequestBody stt;

    public OrderbyStatusAdapter(List<OrderModel> mOrderModel, Context mContext) {
        this.mOrderModel = mOrderModel;
        this.mContext = mContext;
    }

    public OrderbyStatusAdapter() {
    }

    public List<OrderModel> getmOrderModel() {
        return mOrderModel;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmOrderModel(List<OrderModel> mOrderModel) {
        this.mOrderModel = mOrderModel;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public OrderbyOrderNumberHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, null);
        return new OrderbyOrderNumberHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderbyOrderNumberHolder holder, int position) {
    OrderModel order=mOrderModel.get(position);
    if(order== null){

        return;
    }
    else {
        holder.button.setText("haha");
        holder.address.setText("Địa chỉ: "+order.getAddress());
        holder.order_date.setText("Ngày tạo: "+order.getOrder_date());
        if(order.getStatus().toLowerCase().contains("0"))
        {
            holder.status.setText("Trạng thái đơn: "+" Đã hủy");
            holder.button.setText("");

        }
        else if(order.getStatus().toLowerCase().contains("1"))
        {
            holder.status.setText("Trạng thái đơn: "+" Chờ Xác nhận");
            holder.button.setText("Xác nhận");
        }
        else if(order.getStatus().toLowerCase().contains("2"))
        {
            holder.status.setText("Trạng thái đơn: "+"Đã xác nhận");
            holder.button.setText("Bàn giao cho vận chuyển");
        }
        else if(order.getStatus().toLowerCase().contains("3"))
        {
            holder.status.setText("Trạng thái đơn: "+" Đơn hàng đang vận chuyển");
            holder.button.setText("Đã giao");
        }
        else if(order.getStatus().toLowerCase().contains("4"))
        {
            holder.status.setText("Trạng thái đơn: "+" Người mua đã nhận hàng");
            holder.button.setText("");
        }

        holder.order_number.setText("Mã đơn hàng: "+order.getOrder_number());
        holder.name.setText("Người mua: "+order.getName());
        holder.phone.setText("SĐT: "+order.getPhone());
        holder.priceTotal.setText("Tổng tiền: "+order.getPrice()+"VND");
        holder.product_names.setText("Sản phẩm: "+order.getProduct_names());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(order.getStatus().toLowerCase().contains("1"))
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setTitle("Xác nhận");
                    builder.setMessage("Bạn có muốn Xác nhận");

                    builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Xử lý khi người dùng chọn Yes
                            Toast.makeText(mContext, "Xác nhận đơn hàng", Toast.LENGTH_SHORT).show();
                            // Gọi phương thức xử lý tương ứng ở đây

                            update(order.getOrder_number(),"2");
                        }
                    });

                    builder.setNegativeButton("Hủy đơn", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Xử lý khi người dùng chọn No
                            Toast.makeText(mContext, "Xác nhận Hủy đơn", Toast.LENGTH_SHORT).show();
                            // Gọi phương thức xử lý tương ứng ở đây
                            update(order.getOrder_number(),"0");
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();

                }
                else if(order.getStatus().toLowerCase().contains("2"))
                {

                    update(order.getOrder_number(),"3");
                }
                else if(order.getStatus().toLowerCase().contains("3"))
                {

                    update(order.getOrder_number(),"4");
                }



            }
        });



    }

    }
    private void update(String order_num,String status)
    {

        APIService apiService = APIService.apiService;
        Call<Void> call=apiService.updatestatus(order_num,status);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(mContext,"Update Order is successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext, DonHang.class);
                    mContext.startActivity(intent);
                } else {
                    Toast.makeText(mContext, "Failed to update order", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext, DonHang.class);
                    mContext.startActivity(intent);
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(mContext, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        if(mOrderModel!=null)
        {
            return mOrderModel.size();
        }
        return 0;
    }

    public class OrderbyOrderNumberHolder extends RecyclerView.ViewHolder{

        private Button button;
        private TextView order_number;
        private TextView order_date;
        private TextView status;
        private TextView name;
        private TextView address;
        private TextView phone;
        private TextView priceTotal;
        private TextView product_names;
        public OrderbyOrderNumberHolder(@NonNull View itemView) {
            super(itemView);

            button=itemView.findViewById(R.id.btnxoa);
            order_number=itemView.findViewById(R.id.order_number);
            order_date=itemView.findViewById(R.id.order_date);
            status=itemView.findViewById(R.id.status);
            name=itemView.findViewById(R.id.name);
            address=itemView.findViewById(R.id.address);
            phone=itemView.findViewById(R.id.phone);
            priceTotal=itemView.findViewById(R.id.priceTotal);
            product_names=itemView.findViewById(R.id.product_names);


        }
    }
}
