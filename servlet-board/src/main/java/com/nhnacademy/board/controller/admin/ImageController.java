package com.nhnacademy.board.controller.admin;

import com.nhnacademy.board.controller.Command;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Slf4j
public class ImageController implements Command {
    private static String IMAGE_PATH = System.getProperty("user.dir") + "/images";
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String fileName = req.getQueryString().split("=")[1];

        log.info("Image Controller " + fileName);
        resp.setContentType("multipart/form-data");
        resp.setHeader("Content-Disposition", "attachment;filename=" + fileName);

        File file = new File(IMAGE_PATH + "/" + fileName);

        byte fileByteArray[] = new byte[(int)file.length()];

        try {
            if (file.isFile()) {
                BufferedInputStream fin = new BufferedInputStream(new FileInputStream(file));
                BufferedOutputStream outs = new BufferedOutputStream(resp.getOutputStream());
                int read = 0;
                while ((read=fin.read(fileByteArray))!=-1){
                    outs.write(fileByteArray,0,read);
                }
                outs.close();
                fin.close();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "";
    }
}
