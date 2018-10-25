package com.example.remote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.remote.service.Product;
import com.example.remote.service.ProductInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteMainActivity extends AppCompatActivity {
    public static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    public TextView productTestView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_main);
        productTestView = findViewById(R.id.product_id);

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductInterface productDao = retrofit.create(ProductInterface.class);
        Call<Product> productCall = productDao.getProductById(2);

        productCall.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                productTestView.setText(response.body().toString());
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                productTestView.setText(t.getMessage()+",retrofit url:"+retrofit.baseUrl());
            }
        });
    }
}
