package com.nhnacademy.nhnboard.user.servlce;

import com.nhnacademy.nhnboard.common.pagenation.Page;
import com.nhnacademy.nhnboard.config.CommonPropertiesConfig;
import com.nhnacademy.nhnboard.exception.IdAlreadyExistException;
import com.nhnacademy.nhnboard.exception.UserNotFoundException;
import com.nhnacademy.nhnboard.user.domain.User;
import com.nhnacademy.nhnboard.user.domain.UserRequest;
import com.nhnacademy.nhnboard.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;
    private final CommonPropertiesConfig commonPropertiesConfig;

    public UserService(UserRepository userRepository, CommonPropertiesConfig commonPropertiesConfig) {
        this.userRepository = userRepository;
        this.commonPropertiesConfig = commonPropertiesConfig;
    }

    public User getUser(String id){
        User user = userRepository.getUser(id);
        if(Objects.isNull(user)){
            throw new UserNotFoundException(id);
        }
        return user;
    }

    public Page<User> getUserList(int page, int size){
        return userRepository.getPagedList(page, size);
    }

    public String getProfileImagePath(String id){
        User user = getUser(id);
        if(Objects.nonNull(user)){
            if(StringUtils.hasText(user.getProfileFileName())){
                return  user.getProfileFileName();
            }
        }
        return "/resources/no-image.png";
    }

    public void register(UserRequest userRequest) {

        MultipartFile file = userRequest.getProfileFile();
        if( !file.isEmpty() ){
            try {
                file.transferTo(Paths.get(commonPropertiesConfig.getUploadPath() + File.separator + file.getOriginalFilename()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if(userRepository.existById(userRequest.getId())){
            throw new IdAlreadyExistException(userRequest.getId());
        }
        log.info("fileName:{}",userRequest.getProfileFile().getOriginalFilename());
        User user = User.createUser(userRequest.getId(),userRequest.getPassword(), userRequest.getName(), userRequest.getProfileFile().getOriginalFilename());
        userRepository.add(user);

    }

    public void update(UserRequest userRequest){
        User user = userRepository.getUser(userRequest.getId());
        String uploadPath =commonPropertiesConfig.getUploadPath();
        MultipartFile file = userRequest.getProfileFile();
        if( !file.isEmpty() ){
            try {
                file.transferTo(Paths.get(uploadPath + File.separator + file.getOriginalFilename()));
                user.updateProfileName(file.getOriginalFilename());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        user.update(userRequest.getPassword(),userRequest.getName(),userRequest.getRole());
    }

    public void delete(String id){
        userRepository.remove(id);
    }

}
