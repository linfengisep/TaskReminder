package com.example.remote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.remote.service.Product;
import com.example.remote.service.ProductInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteMainActivity extends AppCompatActivity {
    public static final String PRODUCT_TAG = "PRODUCT_TAG";
    public static final String BASE_URL = "http://localhost:8080/";

    public TextView productTestView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_main);
        productTestView = findViewById(R.id.product_id);
        //build a gson instance;
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        Product product = new Product(7,"AFD.TECH",2400,2000);

        ProductInterface productDao = retrofit.create(ProductInterface.class);
        Call<Product> productCall = productDao.saveProduct(product);
        //Call<Product> productCall = productDao.getProductById(2);
        productCall.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                productTestView.setText(response.body().toString()+",code"+response.code());
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                productTestView.setText(t.getMessage()+",service is not available");
            }
        });
    }
}
