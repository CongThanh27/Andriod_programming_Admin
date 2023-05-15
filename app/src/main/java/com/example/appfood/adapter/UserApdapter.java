package com.example.appfood.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appfood.R;
import com.example.appfood.model.ProductModel;
import com.example.appfood.model.UserModel;
import com.example.appfood.util.Constants;

import java.util.List;

public class UserApdapter extends RecyclerView.Adapter<UserApdapter.UserViewHolder>{


    private List<UserModel> musermodel;
    private Context mContext;

    public UserApdapter(List<UserModel> musermodel, Context mContext) {
        this.musermodel = musermodel;
        this.mContext = mContext;
    }

    public UserApdapter() {
    }

    @NonNull
    @Override
    public UserApdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, null);
        return new UserViewHolder(view);
    }

    public void setListenerList(List<UserModel> user){
        this.musermodel=user;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        UserModel userModel= musermodel.get(position);
        if(userModel== null)
            return;
        else{
            String correctedImagePath = userModel.getImage().replace("\\", "/");
            Glide.with(mContext)
                    .load(Constants.url+"/"+correctedImagePath)
                    .into(holder.imageViewUser);
            holder.age.setText("Tuổi: "+String.valueOf(userModel.getAge()));
            holder.Email.setText("Email: "+userModel.getEmail());
            holder.textviewname.setText("Tên: "+userModel.getName());
            holder.gender.setText("Phái: "+userModel.getGender());
            holder.phone_number.setText("SDT: "+userModel.getPhoneNumber());

        }

    }



    @Override
    public int getItemCount() {
        if (musermodel != null) {
            return musermodel.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageViewUser;
        private TextView textviewname;
        private TextView Email;
        private TextView gender;
        private TextView age;
        private TextView phone_number;



        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewUser = (ImageView) itemView.findViewById(R.id.imageViewUser);
            textviewname = itemView.findViewById(R.id.textviewname);
            Email = itemView.findViewById(R.id.Email);
            gender = itemView.findViewById(R.id.gender);
            age = itemView.findViewById(R.id.age);
            phone_number = itemView.findViewById(R.id.phone_number);



        }
    }
}
