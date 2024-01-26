package com.cloud.voiture.controllers;

import org.springframework.web.multipart.MultipartFile;

public class TestUpload {
    MultipartFile[] blobs;

    public MultipartFile[] getBlob() {
        return blobs;
    }

    public void setBlob(MultipartFile[] blobs) {
        this.blobs = blobs;
    }

}
