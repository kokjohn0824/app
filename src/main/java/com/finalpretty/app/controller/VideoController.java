package com.finalpretty.app.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.finalpretty.app.Response.VideoDTO;
import com.finalpretty.app.Response.VideoResponse;
import com.finalpretty.app.model.Member;
import com.finalpretty.app.model.Users;
import com.finalpretty.app.model.Video;
import com.finalpretty.app.repositories.MemberRespository;
import com.finalpretty.app.repositories.VideoRespository;

@Controller
public class VideoController {

    @Autowired
    private VideoRespository videoR;

    @Autowired
    private MemberRespository memberR;

    // 後台
    // 顯示全部影片
    @GetMapping("/video/manage")
    public String manageVideoPage(Model m) {
        List<Video> list = videoR.findAll();
        m.addAttribute("list", list);
        return "/video/backEndManageVideo";
    }

    @GetMapping("/public/api/video/manage")
    @ResponseBody
    public List<VideoDTO> getAllvideos() {

        List<VideoDTO> videolist = new ArrayList<>();
        videoR.findAlloOrderById().forEach((n) -> {
            videolist.add(
                    new VideoDTO(n.getVideo_id(), n.getTitle(), n.getType(), n.getBody_parts(), n.getViews()));
        });
        return videolist;
    }

    // 前往新增影片
    @GetMapping("/video/add")
    public String addArticle() {
        return "/video/backEndAddVideoPage";
    }

    // 新增影片
    @PostMapping("/video/add")
    public String processAction(@RequestParam("myFiles") MultipartFile mf,
            @RequestParam(name = "picture") MultipartFile picture,
            @RequestParam(name = "title") String title,
            @RequestParam(name = "type") String type,
            @RequestParam(name = "body_parts") String body_parts) throws IllegalStateException, IOException {
        String fileName = mf.getOriginalFilename();
        String saveFileDir = System.getProperty("user.dir") + "/src/main/resources/static/public/video";
        File saveFilePath = new File(saveFileDir, fileName);
        mf.transferTo(saveFilePath);
        Video video = new Video();
        video.setPicture(picture.getBytes());
        video.setTitle(title);
        video.setType(type);
        video.setBody_parts(body_parts);
        video.setUrl(fileName);
        video.setViews(0);
        videoR.save(video);
        // if (fileName != null && fileName.length() != 0) {
        // videoR.setUrl(fileName);
        // }
        // return "SaveFilePath:" + saveFilePath;
        // return null;

        System.out.println();
        return "redirect:/video/manage";
    }

    // 刪除影片(by id)
    @GetMapping("/video/delete")
    public String deleteVideo(@RequestParam(name = "video_id") Integer video_id) {
        videoR.deleteById(video_id);
        return "redirect:/video/manage";
    }

    // 修改影片
    @GetMapping("/video/edit")
    public String editVideo(@RequestParam(name = "video_id") Integer id, Model model) {
        Optional<Video> v1 = videoR.findById(id);
        model.addAttribute("video", v1.orElse(null));
        return "video/backEndEditVideo";
    }

    @PostMapping("/video/edit")
    public String editVideoPost(@RequestParam(name = "video_id") Integer video_id,
            @RequestParam(name = "title") String title,
            @RequestParam(name = "type") String type,
            @RequestParam(name = "body_parts") String body_parts,
            @RequestParam(name = "file") MultipartFile file,
            Model model) {
        byte[] picture = null;
        try {
            if (file.getBytes().length == 0) {
                picture = videoR.findById(video_id).get().getPicture();
            } else {
                picture = file.getBytes();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        videoR.updateById(video_id, title, type, body_parts, picture);
        return "redirect:/video/manage";
    }

    // =============================================================================================
    // 前台
    // 顯示影片分類
    @GetMapping("/public/video/categories")
    public String videoCategories() {
        return "/video/frontEndVideoCategories";
    }

    // 選擇分類
    @GetMapping("/public/video/type/{type}")
    public String videoCategoriesByType(@PathVariable String type, Model m) {
        List<Video> list = videoR.findByType(type);
        m.addAttribute("list", list);
        return "/video/frontEndVideoCategoriesByType";
    }

    // 顯示影片的預覽圖
    @GetMapping("/public/showVideoImage/{id}")
    public ResponseEntity<byte[]> showVideoImage(@PathVariable Integer id) {
        System.out.println();
        Video video = videoR.findById(id).get();
        byte[] photoFile = video.getPicture();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<byte[]>(photoFile, headers, HttpStatus.OK);
    }

    // 顯示選取影片
    @GetMapping("/public/video/show")
    public String showVideo(@RequestParam(name = "video_id") Integer video_id, Model m) {
        Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Member member;
        Optional<Video> optional = videoR.findById(video_id);
        Video video = optional.get();
        Set<Member> members = video.getMembers();

        Integer views = video.getViews();
        views++;
        video.setViews(views);
        videoR.save(video);

        if (o instanceof Users) {
            member = ((Users) o).getFkMember();
            Integer member_id = member.getMember_id();
            Member memberFromJpa = memberR.findById(member_id).get();

            if (members.contains(memberFromJpa)) {
                m.addAttribute("bool", "true");
            } else {
                m.addAttribute("bool", "false");
            }

        }
        m.addAttribute("video", video);
        return "/video/frontEndShowVideo";
    }

    // 按讚影片
    @ResponseBody
    @PostMapping("/video/like/{video_id}")
    public VideoResponse likeVideo(
            @PathVariable(name = "video_id") Integer video_id) {
        try {
            Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Member member = ((Users) o).getFkMember();
            Integer member_id = member.getMember_id();
            Member memberFromJpa = memberR.findById(member_id).get();
            Video video = videoR.findById(video_id).get();
            Set<Video> like = memberFromJpa.getVideos();
            like.add(video);
            memberR.save(member);

            VideoResponse ar = new VideoResponse();
            ar.setVideo_id(video_id);
            ar.setMember_id(member_id);

            return ar;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 取消按讚影片
    @ResponseBody
    @PostMapping("/video/delike/{video_id}")
    public VideoResponse delikeVideo(
            @PathVariable(name = "video_id") Integer video_id) {
        try {
            Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Member member = ((Users) o).getFkMember();

            Integer member_id = member.getMember_id();
            Member memberFromJpa = memberR.findById(member_id).get();
            Video video = videoR.findById(video_id).get();
            Set<Video> ss = memberFromJpa.getVideos();
            ss.remove(video);
            VideoResponse ar = new VideoResponse();
            ar.setVideo_id(video_id);
            ar.setMember_id(member_id);
            videoR.save(video);

            return ar;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
