package com.example.trnews.Model;

import android.provider.MediaStore;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("section")
    @Expose
    private String section;

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("abstract")
    @Expose
    private String _abstract;
    @SerializedName("published_date")
    @Expose
    private String publishedDate;

    @SerializedName("id")
    @Expose
    private Double id;

    @SerializedName("adx_keywords")
    @Expose
    private String adxKeywords;

    @SerializedName("media")
    @Expose
    private List<Medium> media;


    @SerializedName("uri")
    @Expose
    private String uri;

    public List<Medium> getMedia() { return media; }
    public void setMedia(List<Medium> media) { this.media = media; }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAdxKeywords() {
        return adxKeywords;
    }

    public void setAdxKeywords(String adxKeywords) {
        this.adxKeywords = adxKeywords;
    }


    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbstract() {
        return _abstract;
    }

    public void setAbstract(String _abstract) {
        this._abstract = _abstract;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }


    public Double getId() {
        return id;
    }

    public void setId(Double id) {
        this.id = id;
    }







    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

}