package com.example.trnews.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Legacy {

    @SerializedName("xlarge")
    @Expose
    private String xlarge;
    @SerializedName("xlargewidth")
    @Expose
    private Long xlargewidth;
    @SerializedName("xlargeheight")
    @Expose
    private Long xlargeheight;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("thumbnailwidth")
    @Expose
    private Long thumbnailwidth;
    @SerializedName("thumbnailheight")
    @Expose
    private Long thumbnailheight;

    public String getXlarge() {
        return xlarge;
    }

    public void setXlarge(String xlarge) {
        this.xlarge = xlarge;
    }

    public Long getXlargewidth() {
        return xlargewidth;
    }

    public void setXlargewidth(Long xlargewidth) {
        this.xlargewidth = xlargewidth;
    }

    public Long getXlargeheight() {
        return xlargeheight;
    }

    public void setXlargeheight(Long xlargeheight) {
        this.xlargeheight = xlargeheight;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Long getThumbnailwidth() {
        return thumbnailwidth;
    }

    public void setThumbnailwidth(Long thumbnailwidth) {
        this.thumbnailwidth = thumbnailwidth;
    }

    public Long getThumbnailheight() {
        return thumbnailheight;
    }

    public void setThumbnailheight(Long thumbnailheight) {
        this.thumbnailheight = thumbnailheight;
    }

}