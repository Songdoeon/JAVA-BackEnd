package com.nhnacademy.board.controller.admin;

import com.nhnacademy.board.domain.User;
import com.nhnacademy.board.request.UserRegisterRequest;
import com.nhnacademy.board.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/user")
public class AdminController{
    UserService userService;

    //올릴 파일 경로
    private static final String UPLOAD_DIR = "**/resources";
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String admin(Pageable pageable, Model model){
        Page page = userService.paging(pageable);
        List<User> userPartList = page.getContent();

        model.addAttribute("userList",userPartList);
        model.addAttribute("size",page.getTotalPages()-1);
        model.addAttribute("page",pageable.getPageNumber());
        return "user/userList";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute(new User());
        return "user/userRegister";
    }

    @PostMapping("/register")
    @Transactional
    public String addRegister(UserRegisterRequest userRegisterRequest) throws IOException {
        MultipartFile file = userRegisterRequest.getProfileFileName();
        String id = userRegisterRequest.getUserId();
        String password = userRegisterRequest.getPassword();
        String name = userRegisterRequest.getName();
        String fileName = null;
        if(!Objects.isNull(userRegisterRequest.getProfileFileName())){
            file.transferTo(Paths.get(UPLOAD_DIR + file.getOriginalFilename()));
            fileName = file.getOriginalFilename();
        }else{
            fileName = "no-image.png";
        }
        userService.register(new User(id,password,name,fileName));
        return "redirect:/user?page=1&size=10";
    }
    @GetMapping("/view")
    public String views(@RequestParam(name = "id") String id, Model model){
        User user = userService.getUser(id);

        model.addAttribute("user",user);
        return "user/userView";
    }
    @PostMapping("/delete")
    public String delete(@RequestParam(name = "id") String id){
        userService.delete(id);
        return "redirect:/user?page=1&size=10";
    }
    @GetMapping("/update")
    public String getUpdate(@RequestParam(name = "id")String id,Model model){
        User user = userService.getUser(id);
        model.addAttribute("user",user);
        return "user/userRegister";
    }
    @PostMapping("/update")
    public String update(UserRegisterRequest userRegisterRequest, Model model) throws IOException {
        MultipartFile file = userRegisterRequest.getProfileFileName();
        String id = userRegisterRequest.getUserId();
        String password = userRegisterRequest.getPassword();
        String name = userRegisterRequest.getName();
        String fileName = null;
        if(!Objects.isNull(userRegisterRequest.getProfileFileName())){
            file.transferTo(Paths.get(UPLOAD_DIR + file.getOriginalFilename()));
            fileName = file.getOriginalFilename();
        }else{
            fileName = "no-image.png";
        }
        userService.modify(new User(id,password,name,fileName));
        return "redirect:/user?page=1&size=10";
    }


}
