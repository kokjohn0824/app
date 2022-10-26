package com.finalpretty.app.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.finalpretty.app.Response.UploadResponse;
import com.finalpretty.app.model.Photo;
import com.finalpretty.app.repositories.PhotoRepository;

@RestController
@RequestMapping("/api/image")
public class PhotoController {

    @Autowired
    PhotoRepository photoRepository;

    /**
     * @param upload
     * @return
     * 
     *         此方法為接受CKeditor 上傳圖片所需api
     *         此方法回傳物件須為以下兩種：
     *         1.圖片上傳成功： 回傳
     *         {
     *         "url": "https://example.com/images/foo.jpg"
     *         }
     *         ` 2.圖片上傳失敗： 回傳
     *         {
     *         "error": {no
     *         "message": "自定義訊息"
     *         }
     *         }
     *         回傳物件類別定義在UploadResponse.java
     * 
     */
    @PostMapping("/upload")
    public UploadResponse upload(@RequestPart MultipartFile upload) {

        Photo p = new Photo();

        try {
            p.setFilebyte(upload.getBytes());
            photoRepository.save(p);
            return new UploadResponse("http://localhost:8082/api/image/" + p.getPhoto_id(), null);

        } catch (IOException e) {
            e.getMessage();
            return new UploadResponse(null, new Error("oh no!"));
        }
    }

    @GetMapping("/{photoId}")
    public byte[] showImage(@PathVariable("photoId") String photoId) {
        Optional<Photo> p = photoRepository.findById(Integer.parseInt(photoId));
        return p.orElseGet(null).getFilebyte();
    }

}
