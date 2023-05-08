package com.nhnacademy.board.controller.login;

import com.nhnacademy.board.controller.BaseController;
import com.nhnacademy.board.domain.User;
import com.nhnacademy.board.repository.UserRepository;
import com.nhnacademy.board.request.LoginRequest;
import com.nhnacademy.board.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.EntityManager;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Objects;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController implements BaseController {
    private final LoginService loginService;
    private final EntityManager entityManager;
    @GetMapping("/login")
    public String getLogin(Model model,User user){
        if(Objects.nonNull(user)){
            return "redirect:/user";
        }
        log.info("message:{}", model.getAttribute("message"));
        model.addAttribute("loginRequest",new LoginRequest());
        return "login/loginForm";
    }
    @PostMapping("/login")
    public String loginForm(@Valid LoginRequest loginRequest, BindingResult bindingResult, Model model, RedirectAttributes red, HttpServletRequest req,HttpServletResponse rep){
        User user = entityManager.find(User.class,loginRequest.getUserId());
        if(bindingResult.hasFieldErrors()){
            model.addAttribute("loginRequest", loginRequest);
            return "login/loginForm";
        }
        if(Objects.isNull(user)){
            red.addFlashAttribute("message", "로그인 실패");
            return "redirect:/login";
        }
        if(loginService.adminMatch(user)){
            HttpSession session = req.getSession(true);
            session.setAttribute("user", user);

            return "redirect:/user?page=1";
        }
        if(loginService.match(user,loginRequest) ){
            HttpSession session = req.getSession(true);
            session.setAttribute("user", user);
            return "redirect:/post?page=1";
        }
        return "redirect:/login";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest req, HttpServletResponse rep){
        HttpSession session = req.getSession(false);
        if(Objects.nonNull(session)){
            session.invalidate();
            Cookie cookie = new Cookie("JSESSIONID","");
            cookie.setMaxAge(0);
            rep.addCookie(cookie);
        }
        return "redirect:/login";
    }

}
