package com.sducsrp.csrp.controller.BUGcontroller;

import com.sducsrp.csrp.common.Constants;
import com.sducsrp.csrp.common.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class UrlRedirectController {
    @RequestMapping("/url_redirect")
    public @ResponseBody Result
    sendRedirect(@RequestParam("url") String url, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //String url = request.getParameter("url");
        System.out.println(url+":ok");
        Result res=new Result(Constants.CODE_200,null,url);
        return res;
//        response.sendRedirect(url); // 302 redirect
//        Result res=new Result(Constants.CODE_200,null,"result:success");
//        return res;实在不行模仿文件下载，返回baidu。com
    }
}
