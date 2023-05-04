package org.example;

import org.json.Cookie;
import org.json.JSONObject;

import java.io.*;
import java.net.*;
import java.util.Map;
import java.util.Set;

public class address {
        URL url;
        HttpURLConnection method ;

        public void setMethod(String method) throws ProtocolException {
            this.method.setRequestMethod(method);
            if(method =="POST"){
                this.method.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            }
        }
        public address(URL url){
            this.url = url;
            try {
                method = (HttpURLConnection) this.url.openConnection();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        public void verbose(){
            System.out.println("request");

//            Map requestHeaders = method.getRequestProperties();;
//            Set<String> requestkeys = requestHeaders.keySet();
            System.out.println("host : "+url.getHost());
//            for( String key : requestkeys ){
//                String val = method.getHeaderField(key);
//                System.out.println(key+"    "+val);
//            }
            System.out.println(url.getPath().trim() + " HTTP/1.1");

            System.out.println("\n\nresponse");
            Map responseHeaders = method.getHeaderFields();
            Set<String> keys = responseHeaders.keySet();

            for( String key : keys ){
                String val = method.getHeaderField(key);
                System.out.println(key+"    "+val);
            }
        }
        public void addRequest(String key,String value){
            method.setRequestProperty(key, value);
        }
        public void redirect(){
//            boolean redirect = false;
//            try {
//                int status = method.getResponseCode();
//                if(status != method.HTTP_OK){
//                    if(status ==301||status ==302||status ==307||status ==308){
//                        redirect = true;
//                    }
//                }
//                while(redirect){
//                    url =
//                }
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
        }
        public void addBody(String message){
            try {
                method.setDoOutput(true);
                DataOutputStream writer = new DataOutputStream(method.getOutputStream());
                System.out.println(message);
                JSONObject object = new JSONObject(message);
                writer.writeBytes(object.toString());
                writer.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        public void file(String path){
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("./"+path)));
                OutputStream output = method.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
                method.setRequestProperty("Content-Type","multipart/form-data;charset=utf-8;");
                String line;
                StringBuilder info = new StringBuilder();
                while(((line = reader.readLine())!=null)){
//                  info.append(line);
                    writer.write(line);
                    writer.newLine();
                    writer.flush();
                }

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
         public void print(){
            try{
                BufferedReader in = new BufferedReader(new InputStreamReader(method.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) //한 행씩 읽기
                    System.out.println(inputLine);
                    in.close();
                } catch (IOException e) {
                System.out.println("URL에서 데이터를 읽는 중 오류가 발생 했습니다.");
            }
         }
        }