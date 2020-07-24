package com.btechbuddy.newsindiaexample;

public class ModelOfNews {
    String newsTitle;
    String newsImg;
    String newsUrl;
    String newsDesc;

    public String getNewsTitle() {
        return newsTitle;
    }

    public String getNewsImg() {
        return newsImg;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public String getNewsDesc() {
        return newsDesc;
    }


    public ModelOfNews(String newsTitle, String newsImg, String newsUrl, String newsDesc) {
        this.newsTitle = newsTitle;
        this.newsImg = newsImg;
        this.newsUrl = newsUrl;
        this.newsDesc = newsDesc;
    }
}
