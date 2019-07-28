package com.mossco.za.mvpapp.article.model;

public class ArticleInformation {
    private String siteName;
    private String urlName;
    private String urlFriendlyDate;
    private String urlFriendlyHeadline;

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
}
