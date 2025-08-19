package com.sducsrp.csrp.controller.BUGcontroller;

import com.sducsrp.csrp.common.Constants;
import com.sducsrp.csrp.common.Result;
import com.sducsrp.csrp.entity.FlagMake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Optional;

@RestController
public class JavaDeserController {

    @Autowired
    DataSource dataSource;
    public String sql;

    @RequestMapping("/java_deser/start")
    public @ResponseBody Result java_derser_start(@RequestParam("username") String username){
        //for flag
        //String username = "moxi";
        String problemid = "pro009";
        String flag = null;
        //String flagMD5 = null;

        try {
            Connection connection = dataSource.getConnection();

            // 预编译
            String sql = "select flag from flag where username = ? and problemid = ?";
            PreparedStatement st = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
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
                //flagMD5 = MD5.getMD5String(flag);
                sql = "insert into flag (`username`, `problemid`, `flag`) values ('"+username+
                        "', '"+problemid+"', '"+flag+"');";
                System.out.println(sql);
                //PreparedStatement st1 = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);

                stmt.executeUpdate(sql);


                // st1.executeUpdate();
            }

            rs.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("输入内容错误，数据库查询错误");
            System.out.println(e.toString());
            //return "输入内容错误，数据库查询错误";
        }



        JavaDeserializationFlag Flag = new JavaDeserializationFlag(flag);//flag
        System.out.println("被序列化对象："+Flag.toString());
        Optional<byte[]> bytes = JavaDeserByteArrayUtils.objectToBytes(Flag);//将对象转换为二进制字节数组(序列化)
        System.out.println(bytes);
        byte[] ret = bytes.get();
        String r1 = JavaDeserByteArrayUtils.toHexString(ret);//序列化后的反序列化是新的对象
        System.out.println("序列化后转换字节数组-16进制表示："+r1);

        Result res=new Result(Constants.CODE_200,null,"flag类序列化后转换字节数组(16进制):"+r1);
        return res;
    }

    //下载部分序列化源码
    @RequestMapping("/java_deser/Download")
    public Result fileDownload(@RequestParam("filename") String filename)
            throws IOException {
        String path = "/test/java_deser/";
//        String path = "D://test/java_deser/";
        System.out.println(filename+":ok");
        String text;
        Result res;

        text = txt2String(path+filename);
        System.out.println(text);

        res = new Result(Constants.CODE_200,null,text);
        return res;
    }

    /**
     * 读取文件内容
     * @param path 想要读取的文件路径
     * @return 返回文件内容
     */
    public static String txt2String(String path){
        File file;
        StringBuilder result;

        file = new File(path);/*文件名*/
        result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result.append(System.lineSeparator()+s);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }
}
