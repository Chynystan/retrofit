package com.geektech.retrofit.adapter;

import androidx.annotation.NonNull;

import com.geektech.retrofit.model.Hit;
import com.geektech.retrofit.model.PixaModel;
import com.geektech.retrofit.retrofit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ImageStorage {

    public static void getList(String title, int perPage, int page, Result result) {
        RetrofitService.getPixabayApi().getImage(title, perPage, page).enqueue(new Callback<PixaModel>() {
            @Override
            public void onResponse(@NonNull Call<PixaModel> call, @NonNull Response<PixaModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    result.onSuccess(response.body().getHits());
                }
            }

            @Override
            public void onFailure(@NonNull Call<PixaModel> call, @NonNull Throwable t) {
                result.onError(t.getMessage());
            }
        });
    }

    public interface Result {
        void onSuccess(List<Hit> list);

        void onError(String msg);
    }
}
