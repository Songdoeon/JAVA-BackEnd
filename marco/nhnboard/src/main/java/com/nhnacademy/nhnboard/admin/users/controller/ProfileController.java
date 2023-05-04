package com.nhnacademy.nhnboard.admin.users.controller;

import com.nhnacademy.nhnboard.config.CommonPropertiesConfig;
import com.nhnacademy.nhnboard.user.servlce.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.MalformedURLException;


@Slf4j
@Controller
@RequestMapping("/profile")
public class ProfileController {
    private final CommonPropertiesConfig commonPropertiesConfig;

    private final UserService userService;

    public ProfileController(CommonPropertiesConfig commonPropertiesConfig, UserService userService) {
        this.commonPropertiesConfig = commonPropertiesConfig;
        this.userService = userService;
    }

    @ResponseBody
    @GetMapping
    public Resource profile(@RequestParam(name = "id", required = true)String id, HttpServletRequest request) throws MalformedURLException {
        String imagePath = commonPropertiesConfig.getUploadPath() + File.separator + userService.getProfileImagePath(id);
        log.info("image-path:{}",imagePath);
        log.info("image-path:{}",userService.getProfileImagePath(id));
        return new UrlResource("file:" + imagePath);
    }

    @GetMapping("/download")
    @ResponseBody
    public ResponseEntity<Resource> download(@RequestParam(name = "id", required = true)String id, HttpServletRequest request) throws MalformedURLException {

        String imagePath = commonPropertiesConfig.getUploadPath() + File.separator + userService.getProfileImagePath(id);
        log.info("image-path:{}",imagePath);

        UrlResource urlResource = new UrlResource("file:" + imagePath );
        String contentDispostion="attachment; filename=\""+ FilenameUtils.getName(imagePath)+"\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,contentDispostion)
                .body(urlResource);
    }
}
