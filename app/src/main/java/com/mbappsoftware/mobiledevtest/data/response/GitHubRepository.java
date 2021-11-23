package com.mbappsoftware.mobiledevtest.data.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GitHubRepository {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("stargazers_count")
    @Expose
    private String stars;
    @SerializedName("forks_count")
    @Expose
    private String forks;
    @SerializedName("owner")
    @Expose
    private GitHubOwner owner;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getStars() {
        return stars;
    }

    public String getForks() {
        return forks;
    }

    public GitHubOwner getOwner() {
        return owner;
    }
}
