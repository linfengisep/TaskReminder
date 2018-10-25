package com.example.remote.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ProductInterface {
    @GET("Products/")
    Call<List<Product>> getProduct();

    @GET("photos/{id}")
    Call<Product> getProductById(@Path("id") int productId);

    @POST("Products/new")
    Call<Product> saveProduct(@Body Product product);
}
