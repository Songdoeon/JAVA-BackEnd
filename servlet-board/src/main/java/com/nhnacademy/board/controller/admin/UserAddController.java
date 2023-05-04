package com.nhnacademy.board.controller.admin;

import com.nhnacademy.board.User;
import com.nhnacademy.board.UserRepository;
import com.nhnacademy.board.controller.Command;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Objects;
@MultipartConfig(
        fileSizeThreshold   = 1024 * 1024 * 1,  // 1 MB
        maxFileSize         = 1024 * 1024 * 10, // 10 MB
        maxRequestSize      = 1024 * 1024 * 100, // 100 MB
        location = "/Users/songdo-eon/IdeaProjects/board/src/main/upload"
)
@Slf4j
public class UserAddController implements Command {
    private static final String CONTENT_DISPOSITION = "Content-Disposition";
    private static final String UPLOAD_DIR = "/Users/songdo-eon/IdeaProjects/board/src/main/upload";
    private String extractFileName(String contentDisposition) {
        log.error("contentDisposition:{}",contentDisposition);
        for (String token : contentDisposition.split(";")) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=")+2, token.length()-1);
            }
        }
        return null;
    }
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        UserRepository userRepository = (UserRepository) req.getServletContext().getAttribute("userRepository");
        String id = req.getParameter("id");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String profileName = "";
        File file = null;
        if(Objects.isNull(id)||Objects.isNull(password)||Objects.isNull(name)){
            return "redirect:/error.do";
        }
        if(Objects.isNull(profileName)){
            userRepository.add(new User(id,password,name));
        }
//        for(Part part : req.getParts()){
//            String contentDisposition = part.getHeader(CONTENT_DISPOSITION);
//
//            if (contentDisposition.contains("filename=")) {
//                String fileName = extractFileName(contentDisposition);
//                if (part.getSize() > 0) {
//                    part.write(UPLOAD_DIR + File.separator + fileName);
//                    part.delete();
//                }
//            } else {
//                String formValue = req.getParameter(part.getName());
//                log.error("{}={}", part.getName(), formValue);
//            }
//        }
//        ServletContext servletContext = req.getServletContext();
//
//        URL resource = null;
//        try {
//            resource = servletContext.getResource("/WEB-INF/classes/users.json");
//            file = Paths.get(resource.toURI()).toFile();
//
//        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        } catch (URISyntaxException e) {
//            throw new RuntimeException(e);
//        }
        userRepository.add(new User(id,password,name,file));
        return "redirect/admin.do";
    }
}
