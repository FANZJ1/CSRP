package com.sducsrp.csrp.controller;

import com.sducsrp.csrp.common.Constants;
import com.sducsrp.csrp.common.Result;
import com.sducsrp.csrp.controller.BUGcontroller.MD5;
import com.sducsrp.csrp.entity.DownLoad;
import com.sducsrp.csrp.entity.FlagMake;
import com.sducsrp.csrp.mapper.DownLoadMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.List;

// 文件下载
@RestController
@RequestMapping("/download")
public class DownLoadController {

    @Resource
    DownLoadMapper downLoadMapper;

    // 导出课程情况
    @GetMapping("/load")
    public Result download(@RequestParam() Integer courseid){
        List<DownLoad> downLoads=downLoadMapper.sel(courseid);
        System.out.println(111);
        return Result.success(downLoads);
    }

    @RequestMapping("/txt")
    public Result fileDownload(@RequestParam("filename") String filename) throws IOException {
        System.out.println(filename+":ok");
        String text;
        Result res;

        //这写文件都是代码审计部分的
        //要下载的文件放在了vue/public/file下面，部署服务器的时候可以把拿出来
        //本地D盘
        String Path = "/test/code/";
//        String Path = "D://test/code/";

        //读取前端传入的文件内容
        text = txt2String(Path+filename);
        System.out.println(text);

        res = new Result(Constants.CODE_200,null,text);
        return res;
    }

    public static String txt2String(String path)
            throws IOException {
        File file;
        StringBuilder result;
        boolean flag = true;

        /*文件名*/
        file = new File(path);
        result = new StringBuilder();
        try{
            //构造一个BufferedReader类来读取文件
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;

            //使用readLine方法，一次读一行
            while((s = br.readLine())!=null){
                result.append(System.lineSeparator()+s);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }
}
