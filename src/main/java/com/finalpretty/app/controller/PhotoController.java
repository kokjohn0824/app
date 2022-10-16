package com.finalpretty.app.controller;

import java.io.IOException;

import org.apache.tomcat.util.http.fileupload.UploadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.finalpretty.app.model.Photo;
import com.finalpretty.app.model.UploadResponse;
import com.finalpretty.app.repositories.PhotoRepository;

import lombok.AllArgsConstructor;

@Controller
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
     *         "error": {
     *         "message": "自定義訊息"
     *         }
     *         }
     *         回傳物件類別定義在UploadResponse.java
     * 
     */
    @PostMapping("/upload")
    @ResponseBody
    public UploadResponse upload(@RequestPart MultipartFile upload) {

        Photo p = new Photo();

        try {
            // TODO:製作上傳後產生圖片網址的metod
            p.setPath("aaa");
            p.setFilebyte(upload.getBytes());
            photoRepository.save(p);
            //FIXME:目前會有後續圖片上傳 只會顯示第一張圖片的bug 這是由於路徑目前是寫死的
            return new UploadResponse("http://localhost:8082/api/image/aaa", null);

        } catch (IOException e) {
            e.getMessage();
            return new UploadResponse(null, new Error("oh no!"));
        }
    }

    @GetMapping("/{path}")
    @ResponseBody
    public byte[] showImage(@PathVariable("path") String path) {
        System.out.println(path);
        Photo p = photoRepository.findByPath(path);
        return p.getFilebyte();
    }

}
