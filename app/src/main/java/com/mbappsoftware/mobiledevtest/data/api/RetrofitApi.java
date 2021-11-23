package com.mbappsoftware.mobiledevtest.data.api;

import com.mbappsoftware.mobiledevtest.data.response.GitHubBody;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitApi {
    @GET("search/repositories")
    Call<GitHubBody> getGitHubRepositories(
            @Query("q") String q,
            @Query("sort") String sort,
            @Query("page") String page
    );
}
