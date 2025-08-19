package com.sducsrp.csrp.controller;

import cn.hutool.core.util.StrUtil;
import com.sducsrp.csrp.common.Constants;
import com.sducsrp.csrp.common.Result;
import com.sducsrp.csrp.controller.dto.UserDTO;
import com.sducsrp.csrp.controller.dto.UserPasswordDTO;
import com.sducsrp.csrp.entity.Page;
import com.sducsrp.csrp.entity.StuProblem;
import com.sducsrp.csrp.entity.User;
import com.sducsrp.csrp.mapper.StuCourseMapper;
import com.sducsrp.csrp.mapper.StuProblemMapper;
import com.sducsrp.csrp.mapper.UserMapper;
import com.sducsrp.csrp.service.IUserService;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserMapper userMapper;

    @Resource
    private IUserService userService;

    @Resource
    StuProblemMapper stuProblemMapper;

    @Resource
    StuCourseMapper stuCourseMapper;

    // 新增用户，注册
    @GetMapping("/save")
    public Result save(@RequestParam() String username,@RequestParam() String password,
                       @RequestParam() String realname,@RequestParam() String phone,@RequestParam() String role){

        System.out.println(username);
        System.out.println(userMapper.selectbyun(username));
        if (userMapper.selectbyun(username)==null){
            userMapper.insertuser(username,password,realname,phone,role);
            System.out.println("不存在该用户");
            return Result.success("success");
        }else{
            System.out.println("存在该用户");
            return Result.error();
        }

    }

    @GetMapping("/page")
    public Result findpage(@RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "5")  Integer pageSize,
                           @RequestParam(defaultValue = "")  String search){
        Integer beginPage=(pageNum-1)*pageSize;

        List<User> userPage=userMapper.getpage(beginPage,pageSize,search);
        Integer totalpage=userMapper.gettotalpage(search);

        System.out.println(totalpage);
        Page page=new Page(userPage,totalpage);
        return Result.success(page);
    }

    //根据id获取到姓名
    @GetMapping("/getname")
    public Result getname(@RequestParam() String  userid){
        String username=userMapper.getname(userid);
        return Result.success(username);
    }

    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDTO){
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.error(Constants.CODE_400, "参数错误");
        }
        UserDTO dto = userService.login(userDTO);
        return Result.success(dto);
    }

    @PostMapping("/register")
    public Result register(@RequestBody UserDTO userDTO) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.error(Constants.CODE_400, "参数错误");
        }
        return Result.success(userService.register(userDTO));
    }

    @PostMapping("/repw")
    public Result repw(@RequestBody UserPasswordDTO userPasswordDTO) {
        String username = userPasswordDTO.getUsername();
        String password = userPasswordDTO.getPassword();
        String newpassword = userPasswordDTO.getNewpassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password) || StrUtil.isBlank(newpassword)) {
            return Result.error(Constants.CODE_400, "参数错误");
        }
        userMapper.updatePassword(userPasswordDTO);
        return Result.success();
    }

    @PostMapping("/rephone")
    public Result rephone(@RequestBody UserDTO userDTO) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        String phone = userDTO.getPhone();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)||StrUtil.isBlank(phone)) {
            return Result.error(Constants.CODE_400, "参数错误");
        }
        userMapper.updatePhone(userDTO);
        return Result.success();
    }

    @PostMapping("/selbyun")
    public Result selectbyname(@RequestBody UserDTO userDTO){
        String username = userDTO.getUsername();
        String phone=userMapper.selectbyun(username);
        System.out.println(phone);
        userDTO.setPhone(phone);
        return Result.success(userDTO);
    }

    @GetMapping("/delete")
    public Result selectbyname(@RequestParam() String  username){
        int id=userMapper.seleteIdByName(username);
        userMapper.deleteeuser(username);
        stuProblemMapper.deletee(id);
        stuCourseMapper.deletee(id);


        return Result.success();
    }

    @GetMapping("/update")
    public Result updatebyname(@RequestParam() String username,@RequestParam() String password,
                               @RequestParam() String realname,@RequestParam() String phone,@RequestParam() String role){
        userMapper.updateuser(username,password,realname,phone,role);
        return Result.success("success");
    }

}
