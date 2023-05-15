package com.example.appfood.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfood.R;
import com.example.appfood.model.MuaHuyModel;

import org.w3c.dom.Text;

import java.util.List;

public class TopMuaHuyAdapter extends RecyclerView.Adapter<TopMuaHuyAdapter.TopMuaHuyHolder> {

    private List<MuaHuyModel> mlist;
    private Context mcontext;

    public TopMuaHuyAdapter(List<MuaHuyModel> mlist, Context mcontext) {
        this.mlist = mlist;
        this.mcontext = mcontext;
    }

    public List<MuaHuyModel> getMlist() {
        return mlist;
    }

    public Context getMcontext() {
        return mcontext;
    }

    public TopMuaHuyAdapter() {
    }

    public void setMlist(List<MuaHuyModel> mlist) {
        this.mlist = mlist;
    }

    public void setMcontext(Context mcontext) {
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public TopMuaHuyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mua_huy_item, null);
        return new TopMuaHuyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopMuaHuyHolder holder, int position) {

        MuaHuyModel muahuy= mlist.get(position);
        if(muahuy==null)
            return;
        else {
            holder.id.setText("Mã khách hàng: "+ muahuy.getId());
            holder.name.setText("Tên khách hàng: "+muahuy.getName());
            holder.email.setText("email: "+muahuy.getEmail());
            holder.sl.setText("số lượng: "+muahuy.getSl());
        }
    }

    @Override
    public int getItemCount() {
        if(mlist!=null)
            return  mlist.size();
        return 0;
    }

    public  class  TopMuaHuyHolder extends RecyclerView.ViewHolder{

        private TextView id;
        private TextView name;
        private TextView email;
        private TextView sl;

        public TopMuaHuyHolder(@NonNull View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.id);
            name=itemView.findViewById(R.id.name);
            email=itemView.findViewById(R.id.email);
            sl=itemView.findViewById(R.id.sl);
        }
    }
}
