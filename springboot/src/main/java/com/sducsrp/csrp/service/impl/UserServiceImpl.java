package com.sducsrp.csrp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sducsrp.csrp.common.Constants;
import com.sducsrp.csrp.controller.dto.UserDTO;
import com.sducsrp.csrp.entity.User;
import com.sducsrp.csrp.exception.ServiceException;
import com.sducsrp.csrp.mapper.UserMapper;
import com.sducsrp.csrp.service.IUserService;
import com.sducsrp.csrp.utils.TokenUtils;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private static final Log LOG = Log.get();

    // 登录
    @Override
    public UserDTO login(UserDTO userDTO) {
        User one = getUserInfo(userDTO);
        if (one != null) {
            BeanUtil.copyProperties(one, userDTO, true);

            //设置token
            String token= TokenUtils.genToken(one.getUserid().toString(),one.getPassword().toString());
            userDTO.setToken(token);

            return userDTO;
        } else {
            throw new ServiceException(Constants.CODE_600, "用户名或密码错误");
        }
    }

    // 注册
    @Override
    public User register(UserDTO userDTO) {
        User one = getUserInfo(userDTO);
        if (one == null) {
            one = new User();
            BeanUtil.copyProperties(userDTO, one, true);
            // 默认学生
            one.setRole(1);
            // 把 copy完之后的用户对象存储到数据库
            save(one);
        } else {
            throw new ServiceException(Constants.CODE_600, "用户已存在");
        }
        return one;
    }

    // 获取用户信息
    private User getUserInfo(UserDTO userDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userDTO.getUsername());
//        queryWrapper.eq("password", userDTO.getPassword());
        User one;
        try {
            // 从数据库查询用户信息
            one = getOne(queryWrapper);
        } catch (Exception e) {
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
        return one;
    }

}
