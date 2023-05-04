package org.example;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Handler implements Runnable {

    Thread thread;
    List userList = new ArrayList();
    List blackList = new ArrayList();
    private String id;
    private Socket socket;
    private BufferedReader inputsocket;
    private BufferedWriter outputsocket;
    private static List<Handler> handlers = new LinkedList<>();
    public Handler(Socket socket){
        thread = new Thread(this);
        this.socket= socket;
    }

    public void start() {
        thread.start();
        try {
            inputsocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outputsocket = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }
    public void stop(){thread.interrupt();}

    public void run(){
        synchronized (this){
            Handler.handlers.add(this);
        }
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date accesstime = new Date(System.currentTimeMillis());
        try{
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            Thread inputThread = new Thread(() -> {
                try {
                    while (!Thread.interrupted()) {
                        String line = inputsocket.readLine();
                        line.trim();
                        String[] argu = line.split(" ");
                        if (argu[0].equals("@@")) {
                            if (argu[1].equals("connect")) {
                                setId(argu[2]);
                                System.out.println(getId() + "등록");
                            } else if (argu[1].equals("userlist")) {
                                for (Handler handler : handlers) {
                                    System.out.println(handler.getId());
                                    directMessage(getId(), handler.getId());
                                }
                            } else if (argu[1].equals("time")) {
                                Date now = new Date(System.currentTimeMillis());
                                System.out.println(formatter.format(now));
                                directMessage(getId(), formatter.format(now));
                            } else if (argu[1].equals("accesstime")) {
                                System.out.println(formatter.format(accesstime));
                                directMessage(getId(), formatter.format(accesstime));
                            }
                        } else if (argu[0].contains("#")) {
                            String target = argu[0].replaceAll("#","");
                            directMessage(argu[0], argu[1]);
                        } else if(argu[0].equals("@all")){
                            broadcast(line);
                        }
                        else broadcast(line);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            inputThread.start();


            // #@ 명령어는 두번 입력하면 결과값이 나옴
            // #@ 명령어는 두번 입력하면 결과값이 나옴
            while(!Thread.interrupted()){
                String line2 = console.readLine();
                line2.trim();
                String[] argu = line2.split(" ");
                if(argu[0].equals("#@")){
                    if(argu[1].equals("userlist")){
                        for(Handler handler : handlers){
                            System.out.println(handler.getId());
                        }
                    }
                    else if(argu[1].equals("time")){
                        Date now = new Date(System.currentTimeMillis());
                        System.out.println(formatter.format(now));
                    }
                    else if(argu[1].equals("accesstime")){
                        System.out.println(formatter.format(accesstime));
                    }
                }

            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void join(){
        try{
            thread.join();
        }catch(InterruptedException ignore){
            Thread.currentThread().interrupt();
        }
    }
    public void directMessage(String id, String message) throws IOException{
        synchronized (Handler.handlers){
            for(Handler handler : Handler.handlers){
                if(handler.getId().equals(id)){
                    handler.outputsocket.write("#"+getId()+" "+message);
                    handler.outputsocket.newLine();
                    handler.outputsocket.flush();
                }
            }
        }
    }
    public void broadcast(String message) throws IOException{
        synchronized (Handler.handlers){
            for(Handler handler : Handler.handlers){
                handler.outputsocket.write("#"+getId()+" "+message);
                handler.outputsocket.newLine();
                handler.outputsocket.flush();
            }
        }
    }
}
