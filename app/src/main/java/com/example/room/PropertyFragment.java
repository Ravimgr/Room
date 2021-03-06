package com.example.room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.room.Adapter.AllPropertyAdapter;
import com.example.room.Adapter.PropertyAdapter;
import com.example.room.Model.AllPropertyModel;
import com.example.room.Model.PropertyModel;
import com.example.room.Remote.ApiInterface;
import com.example.room.Remote.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PropertyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PropertyFragment extends Fragment {
    RecyclerView recyclerView;
    List<PropertyModel> propertyList;
    AllPropertyAdapter propertyAdapter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PropertyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PropertyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PropertyFragment newInstance(String param1, String param2) {
        PropertyFragment fragment = new PropertyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_property, container, false);
//        AllPropertyModel[] myListData = new AllPropertyModel[]{
//                new AllPropertyModel(R.drawable.room,
//                        "For Sale",
//                        "Chitwan",
//                        "100000"),
//                new AllPropertyModel(R.drawable.room,"For Rent","Kathmandu","5000"),
//                new AllPropertyModel(R.drawable.room,"For Rent","Kathmandu","5000"),
//                new AllPropertyModel(R.drawable.room,"For Rent","Kathmandu","5000"),
//                new AllPropertyModel(R.drawable.room,"For Rent","Kathmandu","5000"),
//                new AllPropertyModel(R.drawable.room,"For Rent","Kathmandu","5000"),
//                new AllPropertyModel(R.drawable.room,"For Rent","Kathmandu","5000"),
//                new AllPropertyModel(R.drawable.room,"For Rent","Kathmandu","5000"),
//                new AllPropertyModel(R.drawable.room,"For Rent","Kathmandu","5000"),
//                new AllPropertyModel(R.drawable.room,"For Rent","Kathmandu","5000"),
//                new AllPropertyModel(R.drawable.room,"For Rent","Kathmandu","5000")
//        };
//        recyclerView= view.findViewById(R.id.rvAllProperty);
//        AllPropertyAdapter adapter=new AllPropertyAdapter(myListData);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
//        recyclerView.setAdapter(adapter);
        recyclerView = (RecyclerView)view.findViewById(R.id.rvAllProperty);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        propertyAdapter = new AllPropertyAdapter(view.getContext(),propertyList);
        recyclerView.setAdapter(propertyAdapter);

        availableProperty();
        return  view;
    }

    private void availableProperty() {
        ApiInterface apiInterface= RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
        Call<List<PropertyModel>> call=apiInterface.getProperty();
        call.enqueue(new Callback<List<PropertyModel>>() {
            @Override
            public void onResponse(Call<List<PropertyModel>> call, Response<List<PropertyModel>> response) {
                propertyList=response.body();
                Log.d("TAG","Response = "+propertyList);
                propertyAdapter.setPropertyList(propertyList);
                propertyAdapter.setOnItemClickListener(onItemClickListener);

            }
            @Override
            public void onFailure(Call<List<PropertyModel>> call, Throwable t) {

            }
        });
    }
    private final View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            //PropertyModel thisItem = propertyList.get(position);
            startActivity(new Intent(getContext(),DetailActivity.class).putExtra("data",propertyList.get(position)));
        }
    };
}