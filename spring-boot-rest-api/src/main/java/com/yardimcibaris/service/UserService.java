package com.yardimcibaris.service;

import com.util.CustomPage;
import com.yardimcibaris.dto.UserDto;
import com.yardimcibaris.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user);
    List<UserDto> getUser();
     UserDto getUserById(long id);
    UserDto updateUser(long id, UserDto user);
    Boolean deleteUser(long id);

    Page<User> pagination(int currentPage, int pageSize);
    Page<User> pagination(Pageable pageable);
    Slice<User> slice(Pageable pageable);

    CustomPage<UserDto> customPagination(Pageable pageable);

    CustomPage<UserDto> customPage(Pageable pageable);
}
