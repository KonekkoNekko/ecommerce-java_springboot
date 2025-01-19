package com.kiatkoding.ecommerce.service;

import com.kiatkoding.ecommerce.model.entity.UserEntity;
import com.kiatkoding.ecommerce.model.request.RegisterRequest;
import com.kiatkoding.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Boolean register(RegisterRequest registerRequest){
        Optional<UserEntity> userEntity = userRepository.findByPhoneNumber(registerRequest.getPhoneNumber());


        if (userEntity.isPresent()) {
            return false;
        } else {
            try {
                UserEntity newUserEntity = new UserEntity();
                newUserEntity.name = registerRequest.getName();
                newUserEntity.password = registerRequest.getPassword();
                newUserEntity.phoneNumber = registerRequest.getPhoneNumber();

                userRepository.save(newUserEntity);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }
}
