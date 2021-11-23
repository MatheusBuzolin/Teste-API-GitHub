package com.mbappsoftware.mobiledevtest.data.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class GitHubBody {
    @SerializedName("items")
    @Expose
    private List<GitHubRepository> gitHubRepositories;

    public List<GitHubRepository> getGitHubRepositories() {
        return gitHubRepositories;
    }

}
