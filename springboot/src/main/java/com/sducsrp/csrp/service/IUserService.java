package com.sducsrp.csrp.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sducsrp.csrp.controller.dto.UserDTO;
import com.sducsrp.csrp.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 */
public interface IUserService extends IService<User> {

    // 登录
    UserDTO login(UserDTO userDTO);

    // 注册
    User register(UserDTO userDTO);

//    User register(UserDTO userDTO);

//    void updatePassword(UserPasswordDTO userPasswordDTO);

//    Page<User> findPage(Page<User> objectPage, String username, String email, String address);
}
