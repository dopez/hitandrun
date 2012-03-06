package com.bbm.gps.adm.image.service;

import com.bbm.gps.cmm.service.GpsDefaultVO;

@SuppressWarnings("serial")
public class ImageManageVO extends GpsDefaultVO{

	private String imageIdList;
	
    private String imageId;

    private int imageSn;

    private String imageSe;

    private String imageNm;

    private String imageFileNm;

    private String imageFileMask;

    private String imageFileSize;

    private String imageFileMime;

    private int imageWidth;

    private int imageHeight;

    private String imageReflctAt;

    private String imageReflctUrl;

    private String imageDc;

    public String getImageIdList() {
		return imageIdList;
	}

	public void setImageIdList(String imageIdList) {
		this.imageIdList = imageIdList;
	}

	public String getImageSe() {
        return imageSe;
    }

    public void setImageSe(String imageSe) {
        this.imageSe = imageSe;
    }

    public String getImageNm() {
        return imageNm;
    }

    public void setImageNm(String imageNm) {
        this.imageNm = imageNm;
    }

    public String getImageFileNm() {
        return imageFileNm;
    }

    public void setImageFileNm(String imageFileNm) {
        this.imageFileNm = imageFileNm;
    }

    public String getImageFileMask() {
		return imageFileMask;
	}

	public void setImageFileMask(String imageFileMask) {
		this.imageFileMask = imageFileMask;
	}

    public String getImageFileSize() {
        return imageFileSize;
    }

    public void setImageFileSize(String imageFileSize) {
        this.imageFileSize = imageFileSize;
    }

    public String getImageFileMime() {
        return imageFileMime;
    }

    public void setImageFileMime(String imageFileMime) {
        this.imageFileMime = imageFileMime;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public String getImageReflctAt() {
        return imageReflctAt;
    }

    public void setImageReflctAt(String imageReflctAt) {
        this.imageReflctAt = imageReflctAt;
    }

    public String getImageReflctUrl() {
        return imageReflctUrl;
    }

    public void setImageReflctUrl(String imageReflctUrl) {
        this.imageReflctUrl = imageReflctUrl;
    }

    public String getImageDc() {
        return imageDc;
    }

    public void setImageDc(String imageDc) {
        this.imageDc = imageDc;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public int getImageSn() {
        return imageSn;
    }

    public void setImageSn(int imageSn) {
        this.imageSn = imageSn;
    }
}
