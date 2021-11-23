package com.mbappsoftware.mobiledevtest.data.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GitHubOwner {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("avatar_url")
    @Expose
    private String avatarUrl;
    @SerializedName("login")
    @Expose
    private String userName;

    public String getId() {
        return id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getUserName() {
        return userName;
    }
}
