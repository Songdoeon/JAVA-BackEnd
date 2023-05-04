package org.example;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    public static void main(String[] args){
        while(!Thread.interrupted()){
            try(ServerSocket serverSocket = new ServerSocket(8080)){
                //홈페이지 주소창으로 접속
                //ex) 127.0.0.1:8080
                Handler handler = new Handler(serverSocket.accept());
                handler.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
