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

// 文件下载漏洞
@RestController
public class FileDownloadController {

    @Autowired
    DataSource dataSource;

    public String sql;

    @RequestMapping("/Download")
    public Result fileDownload(@RequestParam("filename") String filename,
                               @RequestParam("username") String username)
            throws IOException {
        System.out.println(filename+":ok");
        String text;
        Result res;

        //for flag
        //生成flag
        String problemid = "pro007";
        String flag = null;
        String flagMD5 = null;
        String flagBase64 = null;
        //引导的文件
        String flagPath = "/test/filedownload/exe/";
        //真正的flag
        String flagPath1 = "/test/filedownload/flag.txt";

//        //引导的文件
//        String flagPath = "D://test/filedownload/exe/";
//        //真正的flag
//        String flagPath1 = "D://test/filedownload/flag.txt";

        try {
            String sql = "";
            Connection connection;
            PreparedStatement st;
            ResultSet rs;
            int row;

            //建立连接
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

        //将flag base64编码后写入文件
        flagBase64 = setEncryptionBase64(flag);
        System.out.println("将flagBase64写入文件："+flagBase64);
        saveAsFileWriter(flagBase64,flagPath1);

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

    //编码
    public static String setEncryptionBase64(String str){
        byte[] b = null;
        String s = null;

        try {
            b = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if( b != null){
            s = new BASE64Encoder().encode(b);
        }
        return s;

    }

    //解码
    public static String getDecodeBase64(String str){
        byte[] b = null;
        String result = null;

        if(str != null){
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                b = decoder.decodeBuffer(str);
                result = new String(b, "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}