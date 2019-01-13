package com.example.android.doordash.data;

import android.text.TextUtils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public final class Restaurant {
    @SerializedName("id")
    @Expose
    public String mId;

    @SerializedName("name")
    @Expose
    public String mTitle;

    @SerializedName("average_rating")
    @Expose
    public String mAvgRating;

    @SerializedName("status")
    @Expose
    public String mStatus;

    @SerializedName("status_type")
    @Expose
    public String mStatusType;

    @SerializedName("tags")
    @Expose
    public String[] mTags;

    @SerializedName("cover_img_url")
    @Expose
    public String mPosterPath;

    public String mLocalTags;

    public Restaurant(String id,
                      String title,
                      String asapTime,
                      String[] tags,
                      String coverImage) {
        mId = id;
        mTitle = title;
        mTags = tags;
        mStatus = asapTime;
        mPosterPath = coverImage;
    }

    public Restaurant(String id,
                      String title,
                      String asapTime,
                      String tags,
                      String coverImage) {
        mId = id;
        mTitle = title;
        mLocalTags = tags;
        mStatus = asapTime;
        mPosterPath = coverImage;
    }

    public String getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getTags() {
        if (!TextUtils.isEmpty(mLocalTags)) {
            return mLocalTags;
        }
        String tags = "";
        String commaSeperator = ", ";
        if (!TextUtils.isEmpty(mLocalTags)) {
            for (int i = 0; i < mTags.length; i++) {
                if (i == 0) {
                    tags.concat(mTags[i]);
                } else {
                    tags.concat(commaSeperator + mTags[i]);
                }
            }
        }
        return tags;
    }

    public String getStatus() {
        return mStatus;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

}
