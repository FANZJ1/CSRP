package com.sducsrp.csrp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sducsrp.csrp.controller.dto.UserDTO;
import com.sducsrp.csrp.controller.dto.UserPasswordDTO;
import com.sducsrp.csrp.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 */
@Mapper
public interface UserMapper extends BaseMapper<User>  {

    // 更新密码
    @Update("update user set password = #{newpassword} where username = #{username} and password = #{password}")
    int updatePassword(UserPasswordDTO userPasswordDTO);

    // 更新电话
    @Update("update user set phone = #{phone} where username = #{username} and password = #{password}")
    int updatePhone(UserDTO userDTO);

    // 分页查找
    @Select("select username,realname,phone,password,role from  user where username like \"%${search}%\" limit #{beginPage},#{pageSize}")
    List<User> getpage(Integer beginPage,Integer pageSize,String search);

    // 查询总数
    @Select("select count(*) from user where username like \"%${search}%\"")
    Integer gettotalpage(String search);
//    and username like "%${search}%"

    // 根据id查姓名
    @Select("select realname from user where userid = #{userid}")
    String getname(String userid);

    // 根据学号查电话
    @Select("select phone from user where username=#{username}")
    String selectbyun(String username);

    // 更新靶场完成数
    @Update("update user set finished = finished+1 where userid = #{userid}")
    int updateFinished(Integer userid);

    // 根据学号查询
    @Select("select userid from user where username=#{username}")
    Integer seleteIdByName(String username);

    // 插入
    @Update("insert into user (username,password,realname,phone,role) values (#{username},#{password},#{realname},#{phone},#{role})")
    Integer insertuser(String username,String password, String realname,String phone,String role);

    // 删除
    @Update("DELETE FROM user WHERE username = #{username}")
    Integer deleteeuser(String username);

    // 更新
    @Update("UPDATE user set password =#{password}, realname=#{realname},phone=#{phone},role=#{role} where username =#{username}")
    Integer updateuser(String username,String password, String realname,String phone,String role);

}
