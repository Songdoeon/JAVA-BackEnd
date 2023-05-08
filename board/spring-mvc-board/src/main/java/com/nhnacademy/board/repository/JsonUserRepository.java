//package com.nhnacademy.board;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//
//import java.io.*;
//import java.nio.charset.StandardCharsets;
//import java.util.List;
//
//public class JsonUserRepository implements IUserRepository{
//    private final ObjectMapper objectMapper;
//    //json file 저장 경로
//    private static final String JSON_FILE_PATH="/Users/songdo-eon/IdeaProjects/board/src/main/upload/student.json";
//
//    public JsonUserRepository(){
//        objectMapper = new ObjectMapper();
//        //LocalDatetime json 직열화/역직렬화 가능하도록 설정
//        objectMapper.registerModule(new JavaTimeModule());
//        //todo JSON_FILE_PATH 경로에 json 파일이 존재하면 삭제 합니다.
//        if(JSON_FILE_PATH.)
//
//    }
//
//    private synchronized List<User> readJsonFile(){
//        //todo json 파일이 존재하지 않다면 비어있는 List<User> 리턴
//
//        //json read & 역직렬화 ( json string -> Object )
//        try(FileInputStream fileInputStream = new FileInputStream(file);
//            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
//            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//        ) {
//            students = objectMapper.readValue(bufferedReader, new TypeReference<List<User>>() {});
//            return  students;
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//
//    private synchronized void writeJsonFile(List<User> studentList){
//        // List<User> 객체를 -> json 파일로 저장 : 직렬화
//        File file = new File(JSON_FILE_PATH);
//
//        try(
//                FileWriter fileWriter = new FileWriter(file);
//                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
//        ) {
//            objectMapper.writeValue(bufferedWriter,studentList);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public void add(User user) {
//
//    }
//
//    @Override
//    public void modify(User user) {
//
//    }
//
//    @Override
//    public void remove(String id) {
//
//    }
//
//    @Override
//    public User getUser(String id) {
//        return null;
//    }
//
//    @Override
//    public List<User> getUsers() {
//        return null;
//    }
//}
