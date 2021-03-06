package com.example.room.Adapter;

import static android.os.Build.VERSION_CODES.R;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.room.DetailActivity;
import com.example.room.LogInActivity;
import com.example.room.Model.PropertyModel;
import com.example.room.R;
import com.example.room.RegisterActivity;
import com.example.room.SelectedAreaActivity;

import java.util.List;

public class PropertyAdapter extends RecyclerView.Adapter<PropertyAdapter.ViewHolder> {
    Context context;
    List<PropertyModel> propertyList;
    private View.OnClickListener mOnItemClickListener;

    public PropertyAdapter(Context context, List<PropertyModel> propertyList) {
        this.context = context;
        this.propertyList = propertyList;
    }

    public void setPropertyList(List<PropertyModel> propertyList) {
        this.propertyList = propertyList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(com.example.room.R.layout.property_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.propertyType.setText(propertyList.get(position).getPropertyType());
        holder.propertyArea.setText(propertyList.get(position).getPropertySize());
        holder.propertyLocation.setText(propertyList.get(position).getPropertyLocation());
        holder.propertyAmount.setText(propertyList.get(position).getPropertyRent());
        holder.propertyName.setText(propertyList.get(position).getPropertyLocation());

        String propertyImage = propertyList.get(position).getPropertyImage();
        String img = "http://room.oxfordcollege.edu.np/storage";
        //get index of c from public of public/documents/sXyBPygG0YNIfRraVGeUcFQyURcwoKVE928sw7kW.jpg
        int index = propertyImage.indexOf("c");
        //remove string before index of /
        String result = propertyImage.substring(index+1);
        //create new image link joining server url
        String newImageUrl = img.concat(result);
        Glide.with(context).asBitmap().load(newImageUrl).into(holder.propertyImage);


    }

    @Override
    public int getItemCount() {
        if(propertyList != null){
            return propertyList.size();
        }
        return 0;
    }
    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView propertyImage;
        TextView propertyType,propertyArea,propertyLocation,propertyAmount,propertyName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            propertyImage=itemView.findViewById(com.example.room.R.id.image);
            propertyType=itemView.findViewById(com.example.room.R.id.subtitle);
            propertyArea=itemView.findViewById(com.example.room.R.id.area);
            propertyName=itemView.findViewById(com.example.room.R.id.propertyName);
            propertyLocation=itemView.findViewById(com.example.room.R.id.tvLocation);
            propertyAmount=itemView.findViewById(com.example.room.R.id.Amount);



            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }
    }
}
