package com.nhnacademy.nhnboard.admin.users.controller;

import com.nhnacademy.nhnboard.common.pagenation.Page;
import com.nhnacademy.nhnboard.user.domain.User;
import com.nhnacademy.nhnboard.user.domain.UserRequest;
import com.nhnacademy.nhnboard.user.servlce.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/users")
@Slf4j
public class AdminUsersController {
    private final UserService userService;

    public AdminUsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = {"/list", "/",""})
    public String users(Model model, @RequestParam(name = "page", defaultValue = "1") int page, @RequestParam(name = "size", defaultValue = "10")int size){
        Page<User> userPage =  userService.getUserList(page,size);
        for(int i=0; i< userPage.getList().size(); i++){
            log.info("user:{}",userPage.getList().get(i));
        }
        model.addAttribute("userPage", userPage);
        return "admin/users/list";
    }

    @GetMapping("/view")
    public String users(Model model, @RequestParam(name = "page", defaultValue = "1") int page, @RequestParam(name = "size", defaultValue = "10")int size, String id){
        User user = userService.getUser(id);
        UserRequest userRequest = new UserRequest(user.getId(),user.getPassword(),user.getName(),user.getProfileFileName(),user.getRole());
        model.addAttribute("userRequest", userRequest);
        model.addAttribute("page",page);
        model.addAttribute("size",size);
        return "admin/users/view";
    }

    @GetMapping("/modify")
    public String modify(Model model,@RequestParam(name = "page", defaultValue = "1") int page, @RequestParam(name = "size", defaultValue = "10")int size , String id){

        User user = userService.getUser(id);
        UserRequest userRequest = new UserRequest(user.getId(),user.getPassword(),user.getName(),user.getProfileFileName(),user.getRole());
        model.addAttribute("userRequest",userRequest);
        model.addAttribute("action","/admin/users/modify");
        model.addAttribute("page",page);
        model.addAttribute("size",size);
        return "admin/users/registerForm";
    }

    @PostMapping("/modify")
    public String modifyAction(@Valid @ModelAttribute UserRequest userRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "admin/users/registerForm";
        }
        userService.update(userRequest);
        return "redirect:/admin/users/view?id=" + userRequest.getId();
    }

    @GetMapping("/register")
    public String register(Model model, @RequestParam(name = "page", defaultValue = "1") int page, @RequestParam(name = "size", defaultValue = "10")int size){
       model.addAttribute("userRequest", new UserRequest());
       model.addAttribute("action","/admin/users/register");
       return "admin/users/registerForm";

    }


    @PostMapping(value = "/register")
    public String registerAction(@Valid @ModelAttribute UserRequest userRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return "admin/users/registerForm";
        }
        userService.register(userRequest);
        return "redirect:/admin/users/";
    }

    @DeleteMapping(value="/")
    public String deleteStudent(String id){
        userService.delete(id);
        return "redirect:/admin/users/";
    }

}
