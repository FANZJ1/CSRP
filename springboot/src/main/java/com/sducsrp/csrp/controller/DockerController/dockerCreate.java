package com.sducsrp.csrp.controller.DockerController;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.sducsrp.csrp.common.Constants;
import com.sducsrp.csrp.common.Result;
import com.sducsrp.csrp.service.DockerClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.*;

@RestController
@RequestMapping("/docker/")//接口注解
public class dockerCreate {

//    public static int port_vue=8080;
//    public static int port_springboot=9090;
//    public static int containerNum = 0;
//    DockerClient client;
//    public String containerIdVue;
//    public String containerIdSpringboot;

    //与service层进行交互
    @Autowired
    DataSource dataSource;
    @Autowired
    DockerClientUtils dockerClientUtils;

    //String sql;
    public static int port_vue=8080;
    public static int port_springboot=20000;
    public static int containerNum = 0;


    //创建容器，返回结果分为三种情况
    /*
    * 1、返回“1;containerName”
    * 2、返回“2;dockerUrl”
    * 3、返回“3;数据库错误”
    * */
    @RequestMapping("create")
    public Result docker_create(@RequestParam("problemName") String problemName,
                                @RequestParam("username") String username){

//        DockerClientUtils dockerClientUtils;
        DockerClient client;


        String containerIdVue;
        String containerIdSpringboot;

        System.out.println("problemName:"+problemName+";username:"+username);
        String ret = null;

        //判断该用户是否有正在使用的容器
        try {
            Connection connection = dataSource.getConnection();

            // 预编译
            String sql = "select containerId from stucontainer where username = ?";
            PreparedStatement st = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();

            // 通过此对象可以得到表的结构，包括，列名，列的个数，列数据类型
            rs.last();
            int row = rs.getRow();

            rs.beforeFirst();
            System.out.println("查找到行数为"+row);
            if(row==1){
                rs.next();
                String problemName1 = rs.getString("problemName");
                System.out.println("用户"+username+"已创建容器:"+problemName1);
                ret = "1;"+problemName1;
            }
            else {
                System.out.println("为用户"+username+"创建容器中");
                //连接Docker服务器
                client = dockerClientUtils.connectDocker("tcp://82.157.124.157:2375");
                System.out.println("连接docker成功");
                port_vue+=1;
                port_springboot+=1;
                containerNum+=1;
//================================================================
                System.out.println("创建后端项目中");
                dockerClientUtils = new DockerClientUtils();

                //创建容器
                CreateContainerResponse container_springboot = dockerClientUtils.createContainer(
                        client,
                        "csrp_"+problemName+"_springboot_"+containerNum,
//                        "csrp_"+problemName+"_springboot",
                        "csrp_csrf",
                        10000,port_springboot);
                System.out.println("创建springboot容器成功");

                //启动容器
                dockerClientUtils.startContainer(client, container_springboot.getId());
                System.out.println("启动springboot容器成功");
                containerIdSpringboot = container_springboot.getId();

//================================================================
//                System.out.println("创建前端项目中");
//                dockerClientUtils = new DockerClientUtils();
//                //创建容器
//                CreateContainerResponse container_vue = dockerClientUtils.createContainer(
//                        client,
//                        "csrp_"+problemName+"_vue_"+containerNum, "csrp_"+problemName+"_vue",
//                        80,port_vue);
//                System.out.println("创建vue容器成功");
//                //启动容器
//                dockerClientUtils.startContainer(client, container_vue.getId());
//                System.out.println("启动vue容器成功");
//                containerIdVue = container_vue.getId();
//
//                //把容器ID存入数据库
//                sql = "insert into stucontainer (`username`, `problemName`, `containerId`) values ('"+username+
//                        "', '"+problemName+"', '"+containerIdVue+"');";
//                System.out.println(sql);
//                //PreparedStatement st1 = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
//                Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
//                        ResultSet.CONCUR_UPDATABLE);
//                stmt.executeUpdate(sql);

                String dockerUrl = "http://82.157.124.157:"+port_springboot;
//                        +";"+port_springboot;
                ret = "标识号：2；靶机地址："+dockerUrl;
            }
            rs.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("输入内容错误，数据库查询错误");
            System.out.println(e.toString());
            ret = "3;数据库错误";
        }
//        Result res=new Result(Constants.CODE_200,null,ret);
        return Result.success(port_springboot);
    }

//    @RequestMapping("destroy")
//    public Result docker_destroy() throws SQLException {
//        dockerClientUtils.stopContainer(client, containerIdVue);
//        System.out.println("关闭vue容器");
//        try{
//            //删除数据库中容器记录
//            sql = "DELETE FROM stucontainer WHERE username = "+containerIdVue;
//            Connection connection = dataSource.getConnection();
//            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
//                    ResultSet.CONCUR_UPDATABLE);
//            stmt.executeUpdate(sql);
//            ret = "1;success";
//        }catch (SQLException e) {
//            System.out.println("数据库删除错误");
//            System.out.println(e.toString());
//            ret = "2;数据库错误";
//        }
//
//        Result res=new Result(Constants.CODE_200,null,ret);
//        return res;
//    }
}
