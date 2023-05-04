package org.example;


import java.io.*;
import java.net.Socket;

public class Client22 extends Thread{
    public static void main(String[] args) {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter terminal = new BufferedWriter(new OutputStreamWriter(System.out));
        String host = null;
        String port = null;
        String command;
        try {
            command = console.readLine();
            String[] argu = command.split(" ");
            host = argu[1];
            port = argu[2];

        } catch (IOException e) {
            interrupted();
        }
        try(Socket socket = new Socket(host, Integer.parseInt(port))){
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Thread inputThread = new Thread(() -> {
                try {
                    while(!Thread.interrupted()) {
                        terminal.write(input.readLine());
                        terminal.newLine();
                        terminal.flush();
                    }
                } catch (IOException e) {
                    System.err.println("연결이 끊겼습니다.");
                }
            });
            inputThread.start();

            while(!Thread.interrupted()) {
                String line = console.readLine();
                output.write(line);
                output.newLine();
                output.flush();
            }
        } catch (Exception e) {
            interrupted();
        }

    }
}
