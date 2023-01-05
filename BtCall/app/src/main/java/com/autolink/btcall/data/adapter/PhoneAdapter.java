package com.autolink.btcall.data.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.autolink.btcall.R;
import com.autolink.btcall.data.bean.PhoneData;

import java.util.List;

public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.PhoneHolder> {


    private List<PhoneData> myPhoneList;
    public Context mContext;

    public PhoneAdapter(List<PhoneData> phoneList, Context context) {
        this.myPhoneList = phoneList;
        this.mContext = context;
    }


    class PhoneHolder extends RecyclerView.ViewHolder {

        public PhoneHolder(@NonNull View itemView) {
            super(itemView);


        }
    }


    @NonNull
    @Override
    public PhoneHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.layout_phone_item, parent,false);
        PhoneHolder phoneHolder = new PhoneHolder(itemView);
        return phoneHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneHolder holder, int position) {
        Log.v("onBindViewHolder", "position  " + position);
    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
