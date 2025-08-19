package com.sducsrp.csrp.controller;

import com.sducsrp.csrp.common.Result;
import com.sducsrp.csrp.entity.Problem;
import com.sducsrp.csrp.entity.User;
import com.sducsrp.csrp.mapper.ProblemMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/problem")
public class ProblemController {

    @Resource
    ProblemMapper problemMapper;

    // 根据id获取
    @GetMapping("/getinfobyid")
    public Result getinfobyid(@RequestParam() String problemid){
        Problem a= problemMapper.SearchProbleminfo_byid(problemid);
        return Result.success(a);
    }

    // 根据类型获取
    @GetMapping("/getinfobytype")
    public Result getinfobytype(@RequestParam() String problemtype){
        System.out.println(problemtype);
        List<Problem> a= problemMapper.SearchProbleminfo_bytype(problemtype);
        return Result.success(a);
    }
}
