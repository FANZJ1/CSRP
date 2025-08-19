package com.sducsrp.csrp.controller.BUGcontroller;

import com.sducsrp.csrp.common.Constants;
import com.sducsrp.csrp.common.Result;
import com.sducsrp.csrp.entity.FlagMake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.sql.DataSource;
import java.io.*;
import java.sql.*;

// 任意文件读取
@RestController
public class FileReadController {

    @Autowired
    DataSource dataSource;
    public String sql;

    @RequestMapping("/Read")
    public Result fileDownload(@RequestParam("filename") String filename,
                               @RequestParam("username") String username)
            throws IOException {

        System.out.println(filename+":ok");
        String text;
        Result res;

        //for flag
        //生成flag
        String problemid = "pro025";
        String flag = null;
        String flagMD5 = null;
        String flagBase64 = null;
        //引导的文件
        String flagPath = "/test/fileread/flag/";
        //真正的flag
        String flagPath1 = "/test/fileread/flag.txt";

//        //引导的文件
//        String flagPath = "D://test/fileread/flag/";
//        //真正的flag
//        String flagPath1 = "D://test/fileread/flag.txt";

        try {
            String sql = "";
            Connection connection;
            PreparedStatement st;
            ResultSet rs;
            int row;

            connection = dataSource.getConnection();

            // 预编译
            sql = "select flag from flag where username = ? and problemid = ?";
            st = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            st.setString(1, username);
            st.setString(2, problemid);
            System.out.println(sql);
            rs = st.executeQuery();

            // 通过此对象可以得到表的结构，包括，列名，列的个数，列数据类型
            rs.last();
            row = rs.getRow();

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
                //将flag MD5编码后存入数据库
                flagMD5 = MD5.getMD5String(flag);
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
            System.out.println("输入内容错误，数据库查询错误 in flag");
            System.out.println(e.toString());
            //return "输入内容错误，数据库查询错误";
        }

        saveAsFileWriter(flag,flagPath1);

        //读取前端传入的文件内容
        text = txt2String(flagPath+filename);
        System.out.println(text);

        res = new Result(Constants.CODE_200,null,text);
        return res;
    }

    /**
     * 读取文件内容
     * @param path 想要读取的文件路径
     * @return 返回文件内容
     */
    public static String txt2String(String path)
            throws IOException{

        File file;
        StringBuilder result;
        boolean flag = true;

        file = new File(path);/*文件名*/
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

    /**
     * 写数据到txt文件
     * @param filePath 写入文件的路径
     * @param content 写入文件的数据
     */
    public static void saveAsFileWriter(String content,String filePath)
            throws IOException{

        FileWriter fwriter = null;
        try {
            // true表示不覆盖原来的内容，而是加到文件的后面。若要覆盖原来的内容，直接省略这个参数就好
            fwriter = new FileWriter(filePath);
            fwriter.write(content);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                fwriter.flush();
                fwriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}