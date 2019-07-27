package com.mossco.za.mvpapp.news;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class NewsArticle implements Serializable {

    @SerializedName("Headline") private String headline;
    @SerializedName("ID") private Integer storyId;
    @SerializedName("Blurb") private String blurb;
    @SerializedName("SiteName") private String siteName;
    @SerializedName("UrlName") private String urlName;
    @SerializedName("IsMainStory") private boolean isMainStory;
    @SerializedName("UrlFriendlyDate") private String urlFriendlyDate;
    @SerializedName("UrlFriendlyHeadline") private String urlFriendlyHeadline;
    @SerializedName("ImageUrlRemote") private String imageUrlRemote;
    @SerializedName("Category") private String category;
    @SerializedName("SmallImageName") private String smallImageName;
    @SerializedName("DateCreated") private String dateCreated;
    @SerializedName("LargeImageAlt") private String largeImageAlt;
    @SerializedName("Body") private String storyBody;

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public Integer getStoryId() {
        return storyId;
    }

    public void setStoryId(Integer storyId) {
        this.storyId = storyId;
    }

    public String getBlurb() {
        return blurb;
    }

    public void setBlurb(String blurb) {
        this.blurb = blurb;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    public boolean isMainStory() {
        return isMainStory;
    }

    public void setMainStory(boolean mainStory) {
        isMainStory = mainStory;
    }

    public String getUrlFriendlyDate() {
        return urlFriendlyDate;
    }

    public void setUrlFriendlyDate(String urlFriendlyDate) {
        this.urlFriendlyDate = urlFriendlyDate;
    }

    public String getUrlFriendlyHeadline() {
        return urlFriendlyHeadline;
    }

    public void setUrlFriendlyHeadline(String urlFriendlyHeadline) {
        this.urlFriendlyHeadline = urlFriendlyHeadline;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSmallImageName() {
        return smallImageName;
    }

    public void setSmallImageName(String smallImageName) {
        this.smallImageName = smallImageName;
    }

    public String getImageUrlRemote() {
        return imageUrlRemote;
    }

    public void setImageUrlRemote(String imageUrlRemote) {
        this.imageUrlRemote = imageUrlRemote;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getLargeImageAlt() {
        return largeImageAlt;
    }

    public void setLargeImageAlt(String largeImageAlt) {
        this.largeImageAlt = largeImageAlt;
    }

    public String getStoryBody() {
        return storyBody;
    }

    public void setStoryBody(String storyBody) {
        this.storyBody = storyBody;
    }
}
