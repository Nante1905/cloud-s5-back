package com.cloud.voiture.services.media;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.cloud.voiture.types.media.Media;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@Service
public class MediaService {

    public List<String> uploadMultipleFile(Media[] files) throws IOException {
        List<String> urls = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            urls.add(this.uploadFile(files[i]));
        }
        return urls;
    }

    public String uploadFile(Media media) throws IOException {

        String bucketName = "cloud-photo-cab19.appspot.com";
        String uuid = UUID.randomUUID().toString();
        String fileName = uuid + media.getFileName();

        BlobId blobId = BlobId.of(bucketName, fileName); // Replace with your bucker name
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(media.getContentType()).build();
        InputStream inputStream = new FileInputStream("cloud-photo-cab19-firebase-adminsdk-qwi9m-73b563db7b.json");

        Credentials credentials = GoogleCredentials.fromStream(inputStream);
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, Base64.getDecoder().decode(media.getBlob()));

        // storage.create(blobInfo, Files.readAllBytes(file.toPath()));

        String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/" + bucketName + "/o/%s?alt=media";
        return String.format(DOWNLOAD_URL,
                URLEncoder.encode(fileName, StandardCharsets.UTF_8));
    }

    public void deleteMediaFiles(Media media) throws IOException {
        String bucketName = "cloud-photo-cab19.appspot.com";
        BlobId blobId = BlobId.of(bucketName, media.getFileName()); // Replace with your bucker name
        InputStream inputStream = new FileInputStream("cloud-photo-cab19-firebase-adminsdk-qwi9m-73b563db7b.json");

        Credentials credentials = GoogleCredentials.fromStream(inputStream);
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.delete(blobId);
    }

    public void deleteMediaFiles(Media[] medias) throws IOException {
        for (Media media : medias) {
            this.deleteMediaFiles(media);
        }
    }
}
