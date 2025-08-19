package com.sducsrp.csrp.controller;

import com.sducsrp.csrp.common.Result;
import com.sducsrp.csrp.entity.CourseInfo;
import com.sducsrp.csrp.entity.Page;
import com.sducsrp.csrp.entity.User;
import com.sducsrp.csrp.mapper.CourseMapper;
import com.sducsrp.csrp.mapper.StuCourseMapper;
import com.sducsrp.csrp.mapper.UserMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/StuCourse")
public class StuCourseController {

    @Resource
    StuCourseMapper stuCourseMapper;

    @Resource
    UserMapper userMapper;

    //根据课程id和学生id，将其存入数据库
    @GetMapping("/saveinfo")
    public Result saveinfo(@RequestParam() String  userid,@RequestParam() String courseid){
        stuCourseMapper.saveinfo(userid,courseid);
        return Result.success("success");
//        return
    }

    // 删除
    @GetMapping("/delete")
    public Result del(@RequestParam() String courseid,@RequestParam() String  username){
        Integer userid=userMapper.seleteIdByName(username);
        int a=stuCourseMapper.del(courseid,userid);
        if(a==1)
            return Result.success();
        else
            return Result.error();
    }

    // 分页
    @GetMapping("/page")
    public Result findpage(@RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "5")  Integer pageSize,
                           @RequestParam(defaultValue = "")  String search,
                           @RequestParam Integer courseid){

        Integer beginPage=(pageNum-1)*pageSize;
        System.out.println("select user.userid ,username,realname,phone from  stucourse, user where stucourse.userid =user.userid and" +
                " courseid ="+courseid+" limit "+beginPage+","+pageSize);

        List<User> userPage=stuCourseMapper.getpage(beginPage,pageSize,search,courseid);
        Integer totalpage=stuCourseMapper.gettotalpage(search,courseid);

        System.out.println(totalpage);
        Page page=new Page(userPage,totalpage);
        return Result.success(page);
    }

    // 根据userid查找
    @GetMapping("/mycourse")
    public Result findcourse(@RequestParam Integer userid){
        List<CourseInfo> info=stuCourseMapper.getcourseinfo(userid);
        return Result.success(info);
    }
}
