package com.yardimcibaris.service.impl;

import com.util.CustomPage;
import com.yardimcibaris.advice.UserNotFound;
import com.yardimcibaris.dto.UserDto;
import com.yardimcibaris.entity.User;
import com.yardimcibaris.repository.UserRepository;
import com.yardimcibaris.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor//otomatik yapıcı metodu oluşturuyor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    @Override
    public UserDto createUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        user.setCreatedDate(new Date());
        user.setCreatedBy("Admin");
        return modelMapper.map(userRepository.save(user),UserDto.class);
    }

    @Override
    public List<UserDto> getUser()   {
        List<User> users = userRepository.findAll();
        List<UserDto> dtos = users.stream().map(user -> modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public UserDto getUserById(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            return modelMapper.map(user.get(),UserDto.class);
        }
        else{
            throw new RuntimeException("Kullanıcı Bulunamadı");
        }

    }

    @Override
    public UserDto updateUser(long id, UserDto user) {
        Optional<User> resultUser = userRepository.findById(id);
        if (resultUser.isPresent()){
            resultUser.get().setFirstName(user.getFirstName());
            resultUser.get().setLastName(user.getLastName());
            resultUser.get().setUpdatedAt(new Date());
            resultUser.get().setUpdatedBy("Admin");

            return modelMapper.map(userRepository.save(resultUser.get()),UserDto.class);
        }
        return null;
    }

    @Override
    public Boolean deleteUser(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Page<User> pagination(int currentPage, int pageSize) {

        Pageable pageable = PageRequest.of(currentPage,pageSize);
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<User> pagination(Pageable pageable){
        return userRepository.findAll(pageable);
    }

    @Override
    public Slice<User> slice(Pageable pageable){//tercih edilen yapı slice yapısıdır. Son çıkan ve en performanslı olandır.
        return userRepository.findAll(pageable);
    }

    @Override
    public CustomPage<UserDto> customPagination(Pageable pageable) {
        Page<User> data = userRepository.findAll(pageable);
        UserDto[] dtos = modelMapper.map(data.getContent(), UserDto[].class);
        return new CustomPage<UserDto>(data,Arrays.asList(dtos));
    }

    @Override
    public CustomPage<UserDto> customPage(Pageable pageable) {
        return null;
    }


}

