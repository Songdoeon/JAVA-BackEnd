package org.example;

public class Client {
    private String userId;
    private int port;
    private String ip;



    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        System.out.println(userId);
        this.userId = userId;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        System.out.println(port);
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        System.out.println(ip);
        this.ip = ip;
    }
}
