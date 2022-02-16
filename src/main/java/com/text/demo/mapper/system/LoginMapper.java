package com.text.demo.mapper.system;

import com.text.demo.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginMapper {
    Boolean getUser(@Param("username") String user,@Param("password") String password);

    UserEntity getUserMsg(@Param("username") String username,@Param("password") String password);

    int delUser(@Param("id") int id);
}
