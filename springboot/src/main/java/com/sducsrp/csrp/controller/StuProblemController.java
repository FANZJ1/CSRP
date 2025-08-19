package com.sducsrp.csrp.controller;

import com.sducsrp.csrp.common.Result;
import com.sducsrp.csrp.entity.StuProblem;
import com.sducsrp.csrp.mapper.DetailMapper;
import com.sducsrp.csrp.mapper.StuProblemMapper;
import com.sducsrp.csrp.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/stuproblem")
public class StuProblemController {

    @Resource
    StuProblemMapper stuProblemMapper;

    @Resource
    DetailMapper detailMapper;

    @Resource
    UserMapper userMapper;

    // 插入、更新
    @GetMapping("/recordinfo")
    public Result save(@RequestParam() Integer userid, @RequestParam() String problemid){

        stuProblemMapper.insertinfo(userid,problemid);
        userMapper.updateFinished(userid);
        return Result.success("success");
    }

    // 获取
    @GetMapping("/getinfo")
    public Result get(@RequestParam() Integer userid, @RequestParam() String problemid){

        StuProblem stuProblem;
        System.out.println("select * from stuproblem where userid="+userid+" and problemid=\""+problemid+"\"");
//        stuProblem=stuProblemMapper.selectinfo(userid,problemid);
        return Result.success(stuProblemMapper.selectinfo(userid,problemid));

    }

    // 做题详情
    @GetMapping("/details")
    public Result details(@RequestParam() Integer courseid,@RequestParam() String username){

        return Result.success(detailMapper.sel(courseid,username));

    }

}
