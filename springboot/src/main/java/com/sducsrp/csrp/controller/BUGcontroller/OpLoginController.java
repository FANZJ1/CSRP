package com.sducsrp.csrp.controller.BUGcontroller;

import com.sducsrp.csrp.common.Constants;
import com.sducsrp.csrp.common.Result;
import com.sducsrp.csrp.entity.FlagMake;
import org.apache.tomcat.jni.Address;
import org.apache.tomcat.jni.User;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.sql.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.yaml.snakeyaml.error.MarkedYAMLException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class OpLoginController {

    @Autowired
    DataSource dataSource;
    public String sql;

    @RequestMapping("/op1/login")
    public @ResponseBody Result login(@RequestParam(value = "username") String username,
                                      @RequestParam(value = "password") String password,
                                      @RequestParam(value = "username1") String username1
                                      , HttpServletResponse response) throws SQLException {
        if(username.equals("lucy")){
            Result res1=new Result(Constants.CODE_200,null,"op");
            return res1;
        }
        if(username.equals("curry")){
            //for flag
            //String username = "moxi";
            String problemid = "pro014";
            String flag = null;
            String flagMD5 = null;
            try {
                Connection connection = dataSource.getConnection();

                // 预编译
                String sql = "select flag from flag where username = ? and problemid = ?";
                String sql_flag = null;
                PreparedStatement st = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                st.setString(1, username1);
                st.setString(2, problemid);
                System.out.println(sql);
                ResultSet rs = st.executeQuery();

                // 通过此对象可以得到表的结构，包括，列名，列的个数，列数据类型
                rs.last();
                int row = rs.getRow();

                rs.beforeFirst();
                System.out.println("查找到行数为"+row);
                if(row==1){
//                rs.next();
//                /*
//                * 再次生成flag
//                */
//                FlagMake flagMake = new FlagMake();
//                flag = flagMake.getFlag();
//                System.out.println("生成flag:"+ flag);
//                //将flag MD5加密后存入数据库
//                flagMD5 = MD5.getMD5String(flag);
//                sql = "update flag set flag='"+flag+"' where username=''"+username+"' and problemid='"+problemid+"'";
//                System.out.println(sql);
//                //将flag存入数据库(更新)，提供给用户使用SQL注入获得flag
//                sql_flag = "update sqlifl4g set fl4g='"+flag+"' where username=''"+username+"' and problemid='"+problemid+"'";
//                Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
//                        ResultSet.CONCUR_UPDATABLE);
//
//                stmt.executeUpdate(sql);
//                stmt.executeUpdate(sql_flag);
                    rs.next();
                    flag = rs.getString("flag");
                    System.out.println("flag已存在:"+ flag);
                }
                else {
                    FlagMake flagMake = new FlagMake();
                    flag = flagMake.getFlag();
                    System.out.println("生成flag:"+ flag);

                    //将flag MD5加密后存入数据库
                    flagMD5 = MD5.getMD5String(flag);
                    sql = "insert into flag (`username`, `problemid`, `flag`) values ('"+username+
                            "', '"+problemid+"', '"+flag+"');";
                    System.out.println(sql);

                    //将flag存入数据库的opuser表中(插入)，作为lucy用户的phone提供给用户使用op获得flag
                    sql_flag = "insert into opuser (`username`, `phone`, `username1`) values ('"+"lucy"+
                            "', '"+flag+"', '"+username1+"');";
                    Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE);

                    stmt.executeUpdate(sql);
                    stmt.executeUpdate(sql_flag);

                }
                rs.close();
                connection.close();

            } catch (SQLException e) {
                System.out.println("输入内容错误，数据库查询错误");
                System.out.println(e.toString());
                //return "输入内容错误，数据库查询错误";
            }


            //for op
            try{
                //System.out.println(param);
                Connection connection = dataSource.getConnection();
                Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);

                //使用预编译防止SQL注入
                sql="select * from opuser where username=? and password= ?";

                PreparedStatement st = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                st.setString(1, username);
                st.setString(2, password);

                System.out.println(sql);
                ResultSet rs = st.executeQuery();

                // 通过此对象可以得到表的结构，包括，列名，列的个数，列数据类型
                rs.last();
                int row = rs.getRow();

                rs.beforeFirst();
                System.out.println("查找到行数为"+row);
                if(row==1){
                    rs.close();
                    connection.close();
                    //生成cookie或session
                    Cookie cookie = new Cookie("username", username);
                    response.addCookie(cookie);

                    Result res=new Result(Constants.CODE_200,null,"success");
                    return res;
                }
                else {
                    rs.close();
                    connection.close();
                    Result res=new Result(Constants.CODE_200,null,"fail");
                    return res;
                }
            } catch (SQLException e) {
                e.printStackTrace();

                Result res=new Result(Constants.CODE_200,null,"result:"+e.toString());
                return res;
            }
        }else {
            Result res2=new Result(Constants.CODE_200,null,"no");
            return res2;
        }

    }
    class Param{
        public User user;
        public Address address;
    }
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/login22")
    public String login() {
        return "login";
    }

    @GetMapping("/logout22")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {

        String username = request.getUserPrincipal().getName();

//        MarkedYAMLException SecurityContextHolder = null;
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null) {
//            new SecurityContextLogoutHandler().logout(request, response, auth);
//        }

        String[] deleteCookieKey = {"JSESSIONID", "remember-me"}; // delete cookie
        for (String key : deleteCookieKey) {
            Cookie cookie = new Cookie(key, null);
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
        }

        if (null == request.getUserPrincipal()) {
            logger.info("USER " + username + " LOGOUT SUCCESS.");
        } else {
            logger.info("User " + username + " logout failed. Please try again.");
        }

        return "redirect:/login?logout";
    }
}
