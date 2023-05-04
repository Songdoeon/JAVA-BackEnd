package org.example;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Scanner;

@SuppressWarnings({"checkstyle:WhitespaceAround", "checkstyle:RightCurly", "checkstyle:MissingJavadocType"})
public class Handler implements Runnable{
    Socket socket;

    @SuppressWarnings("checkstyle:WhitespaceAround")
    public Handler(Socket socket){
        this.socket = socket;
    }

    @SuppressWarnings({"checkstyle:WhitespaceAround", "checkstyle:WhitespaceAfter", "checkstyle:LeftCurly"})
    public void start(){this.run();}

    @SuppressWarnings({"checkstyle:Indentation", "checkstyle:WhitespaceAfter", "checkstyle:LeftCurly"})
    @Override
    public void run() {
        long time = System.currentTimeMillis();
        StringBuilder list = new StringBuilder();
        String requestData = null;
        String[] filePath = new String[0];
        String[] fileName = new String[0];
        String[] story = new String[0];
        File[] files = new File[0];
        File dir = null;
        Response response = new Response(socket);
        InputStream in;
        Scanner s;
            try{
                in = socket.getInputStream();
                s = new Scanner(in,"UTF_8");
                if(s.hasNextLine())
                    requestData = s.nextLine();

                filePath = requestData.split(" ");
                //GET인지 확인
                if(filePath[0].equals("GET")){
                    dir = new File("/Users/songdo-eon/IdeaProjects/Simple_httpd/src/main/java/org/example" + filePath[1]);

                    // ?로 multipart/form-data, Delete 실행
                    // ex)127.0.0.1:8080/folder1?filename=story
                    // ex)127.0.0.1:8080/folder1?Delete=filename
                    if(filePath[1].contains("?")){
                        fileName = filePath[1].split("\\?");
                        story = fileName[1].split("=");
                        dir = new File("/Users/songdo-eon/IdeaProjects/Simple_httpd/src/main/java/org/example" + fileName[0]);
                    }
                    if(!dir.exists()){
                        response.empty();
                        socket.close();
                    }
                    files = dir.listFiles();

                    //읽을 수 없는 경우 onlyMe
                    //ex)127.0.0.1:8080/onlyMe chmod 000
                    if(dir.canRead()){
                        if(files.length==0){
                            response.empty();
                        }
                        else{
                            for (int i = 0; i < files.length; i++) {
                                list.append(files[i]);
                                list.append("<br>");
                            }
                            response.res200(list);
                        }
                    }
                    else{
                        response.forbidden();
                    }
                    //multipart/form-data 와 Delete
                    if(story.length!=0){
                        if(story[0].equals("DELETE")){
                            File deletefile = new File(dir+"/"+story[1]);
                            if((deletefile).exists()){
                                if(deletefile.delete()){
                                    response.delete();
                                }else{
                                    response.forbidden();
                                }
                            }else {
                                response.delete();
                            }
                        }
                        else{
                            File newFile = new File(dir+"/"+story[0]+".txt");
                            if(newFile.exists()){
                                response.conflict();
                                socket.close();
                            }
                                BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile)));
                                fileWriter.write(story[1]);
                                fileWriter.flush();
                                response.fileSuccess();
                        }
                    }
                }
                else{
                    //GET이 아닌경우
                    response.notAllowed();
                }
            }catch (Exception ignored) {
                ignored.printStackTrace();
            }
            finally {
                long end = System.currentTimeMillis();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                System.out.println("\n시간 : "+sdf.format(time));
                System.out.println("요청 메소드 : "+filePath[0]);
                System.out.println("경로 : "+dir);
                System.out.println("응답 코드 : "+response.code);
                System.out.println("수행시간: " + (end - time) + " ms");
                try {
                    socket.close();
                } catch (Exception ignored) {
                    ignored.printStackTrace();
                }
            }
        }
}
