package com.sducsrp.csrp.controller.BUGcontroller;

import com.sducsrp.csrp.common.Constants;
import com.sducsrp.csrp.common.Result;
import com.sducsrp.csrp.entity.FlagMake;
import com.sducsrp.csrp.mapper.FlagMapper;
import com.sducsrp.csrp.mapper.ForumMapper;
import com.sducsrp.csrp.mapper.XssstoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.sql.*;


/**
 * XSS漏洞
 */
@RestController
@RequestMapping("/xss")
public class XssController {

    @Autowired
    DataSource dataSource;
    public String sql;

    @Resource
    XssstoreMapper xssstoreMapper;

    @Resource
    FlagMapper flagMapper;

    /**
     * Vuln Code.
     * ReflectXSS
     * http://localhost:8080/xss/reflect?xss=<script>alert(xxx)</script>
     *
     * @param xss unescape string
     */
    @GetMapping("/reflect")
    public @ResponseBody  Result reflect(@RequestParam(value = "xss") String xss,
                                         @RequestParam(value = "username") String username) {
        System.out.println(xss+":ok1");

        String problemid = "pro012";
        String flag = null;
        String flagMD5 = null;
        try {
            Connection connection = dataSource.getConnection();

            // 预编译
            String sql = "select flag from flag where username = ? and problemid = ?";
            String sql_flag = null;
            PreparedStatement st = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            st.setString(1, username);
            st.setString(2, problemid);
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            // 通过此对象可以得到表的结构，包括，列名，列的个数，列数据类型
            rs.last();
            int row = rs.getRow();

            rs.beforeFirst();
            System.out.println("查找到行数为"+row);
            if(row==1){

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

                //将flag存入数据库(插入)，提供给用户使用SQL注入获得flag
                sql_flag = "insert into sqlifl4g (`username`, `problemid`, `fl4g`) values ('"+username+
                        "', '"+problemid+"', '"+flag+"');";
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

//        System.out.println(xss);
//        System.out.println("<script>alert(" + username + ")</script>");
        if(xss.equals("<script>alert(" + username + ")</script>"))
//            return Result.success("<script>alert(" +"弹窗正确！\nflag为："+ flag + ")</script>");
            return Result.success("<script>alert(\"" +"弹窗正确！flag为："+ flag + "\")</script>");
        else
            return Result.error();
    }

    /**
     * Vul Code.
     * StoredXSS Step1
     * http://localhost:8080/xss/stored/store?xss=<script>alert(1)</script>
     *
     * @param xss unescape string
     */
    @GetMapping("/stored/store")
    public @ResponseBody Result store(@RequestParam(value = "xss") String xss,
                                      @RequestParam(value = "username") String username) {

//        Cookie cookie = new Cookie("xss", xss);
//        response.addCookie(cookie);
//        Result res=new Result(Constants.CODE_200,null,"Set param into cookie,访问/stored/show验证一下");

        if(xssstoreMapper.sel(username)==0){
            xssstoreMapper.add(username,xss);
        }else{
            xssstoreMapper.refresh(username,xss);
        }

        String problemid = "pro011";
        String flag = null;
        String flagMD5 = null;
        try {
            Connection connection = dataSource.getConnection();

            // 预编译
            String sql = "select flag from flag where username = ? and problemid = ?";
            String sql_flag = null;
            PreparedStatement st = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            st.setString(1, username);
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
                //将flag存入数据库(插入)，提供给用户使用SQL注入获得flag
                sql_flag = "insert into sqlifl4g (`username`, `problemid`, `fl4g`) values ('"+username+
                        "', '"+problemid+"', '"+flag+"');";
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

        return Result.success();
    }

    /**
     * Vul Code.
     * StoredXSS Step2
     * http://localhost:8080/xss/stored/show
     *
     * @param "xss" unescape string
     */
    @GetMapping("/stored/show")
    public @ResponseBody Result show(@RequestParam(value = "username") String username) {

//        System.out.println(xss);
//        Result res=new Result(Constants.CODE_200,null,xss);
//        return res;

        String problemid="pro011";
        String xss=xssstoreMapper.getContent(username);
        String flag=flagMapper.getFlag(username,problemid);
        System.out.println(xss);
        System.out.println(flag);

        if(xss.equals("<script>console.log(\"" + username + "\")</script>"))
//            return Result.success("<script>alert(" +"弹窗正确！\nflag为："+ flag + ")</script>");
            return Result.success(xss,"flag为："+ flag);
        else
            return Result.error(Constants.CODE_500,xss);
    }



    /**
     * safe Code.
     * http://localhost:8080/xss/safe
     */

    @RequestMapping("/safe")
    @ResponseBody
    public static String safe(String xss) {
        return encode(xss);
    }

    private static String encode(String origin) {
        origin = StringUtils.replace(origin, "&", "&amp;");
        origin = StringUtils.replace(origin, "<", "&lt;");
        origin = StringUtils.replace(origin, ">", "&gt;");
        origin = StringUtils.replace(origin, "\"", "&quot;");
        origin = StringUtils.replace(origin, "'", "&#x27;");
        origin = StringUtils.replace(origin, "/", "&#x2F;");
        return origin;
    }
}
