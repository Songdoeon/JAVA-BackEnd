package com.nhnacademy.board.controller.user;

import com.nhnacademy.board.domain.Post;
import com.nhnacademy.board.domain.User;
import com.nhnacademy.board.request.PostRegisterRequest;
import com.nhnacademy.board.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController {

    PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public String list(@RequestParam(name = "page")int page,Model model){
        List<Post> postList = postService.getPostList();
        List<Post> postPartList = postService.getPartList(page);
        int last = postList.size()/10;
        model.addAttribute("postList",postPartList);
        if(last == 0){
            return "post/postList";
        }
        if(page==1){
            model.addAttribute("nextPage",2);
        }
        else if(page==last){
            model.addAttribute("prePage",page-1);
        }
        else{
            model.addAttribute("prePage",page-1);
            model.addAttribute("nextPage",page+1);
        }
        return "post/postList";
    }

    @PostMapping("/register")
    public String addRegister(PostRegisterRequest postRegisterRequest, HttpServletRequest req){
        Long id = postService.getId();
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        postService.register(new Post(id,postRegisterRequest.getTitle(),postRegisterRequest.getContent(),user.getUserId()));

        return "redirect:/post?page=1";
    }
    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute(new Post());
        return "post/postRegister";
    }
    @GetMapping("/view")
    public String view(@RequestParam(name="id")Long id, Model model, HttpServletRequest req, HttpServletResponse rep){
        Post post = postService.getPost(id);
        HttpSession session = req.getSession(true);
        Cookie cookie = new Cookie("VIEW"+id,"view");
        cookie.setMaxAge(60*60*24);
        cookie.setPath("/");
        rep.addCookie(cookie);
        model.addAttribute("post",post);
        return "post/postView";
    }
    @PostMapping("/delete")
    public String view(@RequestParam(name="id")Long id,HttpServletRequest req){
        postService.delete(id,req);
        return "redirect:/post?page=1";
    }
    @GetMapping("/update")
    public String getUpdate(@RequestParam(name = "id")Long id,Model model){
        Post post = postService.getPost(id);
        model.addAttribute("post",post);
        return "post/postRegister";
    }

    @PostMapping("/update")
    public String update(@RequestParam(name="id")Long id,PostRegisterRequest postRegisterRequest,HttpServletRequest req){
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        postService.modify(id,new Post(id,postRegisterRequest.getTitle(),postRegisterRequest.getContent(),user.getUserId()));

        return "redirect:/post?page=1";
    }

}
