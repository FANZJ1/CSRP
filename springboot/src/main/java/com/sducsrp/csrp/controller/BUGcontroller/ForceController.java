package com.sducsrp.csrp.controller.BUGcontroller;

import com.sducsrp.csrp.common.Constants;
import com.sducsrp.csrp.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//@Controller
@RestController
@RequestMapping("/force/") // 接口注解
public class ForceController {

    @Autowired
    DataSource dataSource;
    public String sql;
    public String message;
    public Object value1;
    public String token1;


    @RequestMapping("client")
//    value = "username",required = false
//    value = "password",required = false
    public Result sqli_char(@RequestParam(value = "username") String username,
                            @RequestParam(value = "password") String password,
                            Model model) throws SQLException {

        System.out.println(username);
        Connection connection = dataSource.getConnection();
        Statement stmt = connection.createStatement();

        sql="select password from usersqli where name='"+username+"'";
        System.out.println(sql);

        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            //获取列对应的值。
            Object value = rs.getObject("password");
            value1 = value;
            System.out.println(value1);
        }
        rs.close();
        connection.close();

        System.out.println(username);
        System.out.println(password);
        System.out.println("model:"+model);

        if(password.equals(value1)){
            message ="login success";
            model.addAttribute("message",message);
        }
        else{
            message ="username or password is not exits~";
            model.addAttribute("message",message);
        }


        Result res=new Result(Constants.CODE_200,null,message);
        return res;
    }


    @RequestMapping("form")
    public Result force(@RequestParam(value = "username",required = false) String name,
                            @RequestParam(value = "password",required = false) String password,
                            Model model) throws SQLException {

        Connection connection = dataSource.getConnection();
        Statement stmt = connection.createStatement();

        sql="select password from usersqli where name='"+name+"'";
        System.out.println(sql);

        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            //获取列对应的值。
            Object value = rs.getObject("password");
            value1 = value;
            System.out.println(value1);
        }
        rs.close();
        connection.close();

        System.out.println(name);
        System.out.println(password);
        System.out.println("model:"+model);

        if(password.equals(value1)){
            message ="login success";
            model.addAttribute("message",message);
        }
        else{
            message ="username or password is not exits~";
            model.addAttribute("message",message);
        }


        Result res=new Result(Constants.CODE_200,null,message);
        return res;

    }


    @RequestMapping("token")
    public String forcetoken(@RequestParam(value = "username",required = false) String name,
                             @RequestParam(value = "password",required = false) String password,
                             @RequestParam(value = "token",required = false) String token,
                             Model model) throws SQLException {

        System.out.println(token);

        Connection connection = dataSource.getConnection();
        Statement stmt = connection.createStatement();

        sql="select password from users where username='"+name+"'";
        System.out.println(sql);

        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            //获取列对应的值。
            Object value = rs.getObject("password");
            value1 = value;
            System.out.println(value1);
        }
        rs.close();
        connection.close();

        System.out.println(name);
        System.out.println(password);
        System.out.println("model:"+model);

        if(password.equals(value1)){

            token1=
            message ="login success";
            model.addAttribute("message",message);
            return "force_form";
        }
        else{
            message ="username or password is not exits~";
            model.addAttribute("message",message);
            return "force_form";
        }



    }
}
