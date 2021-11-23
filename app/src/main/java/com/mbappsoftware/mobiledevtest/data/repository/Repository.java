package com.mbappsoftware.mobiledevtest.data.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.mbappsoftware.mobiledevtest.data.client.RetrofitClient;
import com.mbappsoftware.mobiledevtest.data.response.GitHubBody;
import com.mbappsoftware.mobiledevtest.data.response.GitHubRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    public void getGitHubRepositories(String q, String sort, String page, RepositoryCallbacks repositoryCallbacks) {
        RetrofitClient.getInstance()
            .retrofitApi
            .getGitHubRepositories(q, sort, page)
            .enqueue(new Callback<GitHubBody>() {
                    @Override
                    public void onResponse(@NonNull Call<GitHubBody> call,
                                           @NonNull Response<GitHubBody> response) {
                        if(response.isSuccessful()) {
                            assert response.body() != null;
                            Log.i("failure", call.request().url().toString());
                            repositoryCallbacks.onSuccess(response.body().getGitHubRepositories());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<GitHubBody> call,
                                          @NonNull Throwable t) {
                        Log.e("failure", call.request().url().toString());
                    }
                });
    }
}
