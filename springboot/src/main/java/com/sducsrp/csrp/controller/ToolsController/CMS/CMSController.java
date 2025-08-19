package com.sducsrp.csrp.controller.ToolsController.CMS;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sducsrp.csrp.common.Constants;
import com.sducsrp.csrp.common.Result;
import com.sducsrp.csrp.controller.BUGcontroller.MD5;
import com.sducsrp.csrp.controller.dto.CMSDTO;
import com.sducsrp.csrp.controller.dto.UserDTO;
import com.sducsrp.csrp.entity.FlagMake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;

@RestController
public class CMSController {

    @Autowired
    DataSource dataSource;
    public String sql;

    @RequestMapping("/tools/cms")
    public @ResponseBody
    Result CMSInfo(@RequestParam("param") String param) {
        param = "target="+param+";verify=1111;s=";
        //cookie
        String cookie = null;
        try {
            Connection connection = dataSource.getConnection();
            // 预编译
            String sql = "select cookie from cookie where tools = ?";
            PreparedStatement st = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            st.setString(1, "cms");
            System.out.println(sql);
            ResultSet rs = st.executeQuery();
            // 通过此对象可以得到表的结构，包括，列名，列的个数，列数据类型
            rs.last();
            int row = rs.getRow();

            rs.beforeFirst();
            System.out.println("查找到行数为"+row);
            if(row==1){
                rs.next();
                cookie = rs.getString("cookie");
            }
            else {
                System.out.println("无cookie信息");
            }
            rs.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("输入内容错误，数据库查询错误");
            System.out.println(e.toString());
            //return "输入内容错误，数据库查询错误";
        }

        //传入的参数
        //String param1="url=www.lhwblogs.cn&location_capcha=no";
//        String param1 = "target=www.lhwblogs.cn";
        String url="http://finger.tidesec.com/home/index/index";
        //post请求，返回json
        String data=sendPostRequest(url,param,cookie);
        //请求回来的数据
        System.out.println("json"+data);
        JSONObject jo = JSONObject.parseObject(data);
        //for ip
        String res_json = jo.get("res").toString();
        System.out.println("res_json:"+res_json);
        JSONObject jo1 = JSONObject.parseObject(res_json);

        String url1 = jo1.get("url").toString();
        System.out.println("url1:"+url1);

        String ip_gps_info = jo1.get("ip_gps_info").toString().replace(" ","");
        System.out.println("ip_gps_info:"+ip_gps_info);
        JSONObject jo_ip_gps_info = JSONObject.parseObject(res_json);

        String[] ip_gsp_info_1 = ip_gps_info.split(",");

        String isp = ip_gsp_info_1[0].substring(ip_gsp_info_1[0].indexOf(':')+2
                ,ip_gsp_info_1[0].length()-1);
        System.out.println("isp:"+isp);

        String area = ip_gsp_info_1[1].substring(ip_gsp_info_1[1].indexOf(':')+2
                ,ip_gsp_info_1[1].length()-1);
        System.out.println("area:"+area);

        String gps = ip_gsp_info_1[2].substring(ip_gsp_info_1[2].indexOf(':')+2
                ,ip_gsp_info_1[2].length()-1)
                +","
                +ip_gsp_info_1[3].substring(0,ip_gsp_info_1[3].length()-2);
        System.out.println("gps:"+gps);

        String cms = jo1.get("cms").toString();
        cms = cms.replace("[\"","");
        cms = cms.replace("\"","");
        cms = cms.replace("]","");
        System.out.println("cms:"+cms);

        String banner = jo1.get("banner").toString();
        System.out.println("banner:"+banner);
        JSONObject json_banner = (JSONObject) JSON.parse(banner);

        String webservice = json_banner.get("WebService").toString();
        System.out.println("webservice:"+webservice);

        String Language = json_banner.get("Language").toString();
        int index_start = Language.indexOf('>');
        int index_end = Language.lastIndexOf('<');
        Language = Language.substring(index_start+1,index_end);
        System.out.println("Language:"+Language);

        String other = json_banner.get("Other").toString();
        System.out.println("Other:"+other);

        index_start = other.indexOf('"');
        index_end = other.lastIndexOf(',');
        String mail = other.substring(index_start+1,index_end-1);
        System.out.println("mail:"+mail);

        String os = jo1.get("os").toString();
        os = os.substring(1,os.length()-1);
        System.out.println("os:"+os);

        String waf = jo1.get("waf").toString();
        System.out.println("waf:"+waf);

        String ip = jo1.get("ip").toString();
        System.out.println("ip:"+ip);

        String domain = jo1.get("domain").toString();
        System.out.println("domain:"+domain);

        String main_domain = jo1.get("main_domain").toString();
        System.out.println("main_domain:"+main_domain);

        String domain_whois = jo1.get("domain_whois").toString();
        System.out.println("domain_whois:"+domain_whois);

        String[] domain_whois_1 = domain_whois.split(",");

        String reg = domain_whois_1[0].substring(domain_whois_1[0].indexOf(':')+3
                ,domain_whois_1[0].length()-1);
        System.out.println("reg:"+reg);

        String creaction_date = domain_whois_1[2].substring(domain_whois_1[2].indexOf(':')+3
                ,domain_whois_1[2].length()-1)
                +"-"
                +domain_whois_1[3].substring(1,domain_whois_1[3].length())
                +"-"
                +domain_whois_1[4].substring(1,domain_whois_1[4].length());
        System.out.println("creaction_date:"+creaction_date);

        String expiration_date = domain_whois_1[8].substring(domain_whois_1[8].indexOf(':')+3
                ,domain_whois_1[8].length()-1)
                +"-"
                +domain_whois_1[9].substring(1,domain_whois_1[9].length())
                +"-"
                +domain_whois_1[10].substring(1,domain_whois_1[10].length());
        System.out.println("expiration_date:"+expiration_date);

        String name_servers = domain_whois_1[14].substring(domain_whois_1[14].indexOf(':')+4
                ,domain_whois_1[14].length()-2);
        System.out.println("name_servers:"+name_servers);

        String finger = jo1.get("finger").toString();
        System.out.println("finger:"+finger);
        JSONObject json_finger = (JSONObject) JSON.parse(finger);

        String title = json_finger.get("title").toString();
        System.out.println("title:"+title);

        String httpserver = json_finger.get("httpserver").toString();
        System.out.println("httpserver:"+httpserver);

        String domain_beian = jo1.get("domain_beian").toString();
        System.out.println("domain_beian:"+domain_beian);
        JSONObject json_domain_beian = (JSONObject) JSON.parse(domain_beian);

        String gongsi = json_domain_beian.get("gongsi").toString();
        System.out.println("gongsi:"+gongsi);

        String xingzhi = json_domain_beian.get("xingzhi").toString();
        System.out.println("xingzhi:"+xingzhi);

        String beian = json_domain_beian.get("beian").toString();
        System.out.println("beian:"+beian);

        String title1 = json_domain_beian.get("title").toString();
        System.out.println("title1:"+title1);

        String time = json_domain_beian.get("time").toString();
        System.out.println("time:"+time);

        CMSDTO cmsdto = new CMSDTO();
        cmsdto.setUrl1(url1);
        cmsdto.setIsp(isp);
        cmsdto.setArea(area);
        cmsdto.setGps(gps);
        cmsdto.setCms(cms);
        cmsdto.setLanguage(Language);
        cmsdto.setMail(mail);
        cmsdto.setOs(os);
        cmsdto.setWaf(waf);
        cmsdto.setIp(ip);
        cmsdto.setDomain(domain);
        cmsdto.setMain_domain(main_domain);
        cmsdto.setReg(reg);
        cmsdto.setCreaction_date(creaction_date);
        cmsdto.setExpiration_date(expiration_date);
        cmsdto.setName_servers(name_servers);
        cmsdto.setTitle(title);
        cmsdto.setHttpserver(httpserver);
        cmsdto.setGongsi(gongsi);
        cmsdto.setXingzhi(xingzhi);
        cmsdto.setTitle1(title1);
        cmsdto.setTime(time);
        cmsdto.setRegister_date(creaction_date+"到"+expiration_date);

        Result res=new Result(Constants.CODE_200,null,cmsdto);
        return res;
    }

    //提交post请求，返回json数据
    public static String sendPostRequest(String url,String param,String cookie){
        HttpURLConnection httpURLConnection = null;
        OutputStream out = null; //写
        InputStream in = null;   //读
        int responseCode = 0;    //远程主机响应的HTTP状态码
        String result="";

        try{
            URL sendUrl = new URL(url);
            httpURLConnection = (HttpURLConnection)sendUrl.openConnection();
            //post方式请求
            httpURLConnection.setRequestMethod("POST");
            //设置头部信息
            //httpURLConnection.setRequestProperty("headerdata", "ceshiyongde");
            //一定要设置 Content-Type 要不然服务端接收不到参数
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            httpURLConnection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.41 Safari/537.36 Edg/101.0.1210.32");
            httpURLConnection.setRequestProperty("Cookie",cookie);
            httpURLConnection.setRequestProperty("Referer","http://finger.tidesec.com/");
            //指示应用程序要将数据写入URL连接,其值默认为false（是否传参）
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            //httpURLConnection.setUseCaches(false);
            //ttpURLConnection.setConnectTimeout(30000); //30秒连接超时
            //httpURLConnection.setReadTimeout(30000);    //30秒读取超时
            //获取输出流
            out = httpURLConnection.getOutputStream();
            //输出流里写入POST参数
            out.write(param.getBytes());
            out.flush();
            out.close();
            responseCode = httpURLConnection.getResponseCode();
            System.out.println(responseCode);
            BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(),"UTF-8"));

            result =br.readLine();

        }catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args){
        //传入的参数
        //String param1="url=www.lhwblogs.cn&location_capcha=no";
        String param1 = "target=https://sspai.com/post/64093";
        String url="http://finger.tidesec.com/home/index/reget";
        String cookie = "PHPSESSID=422md6ifcl7ua58lhmj8ma1jg5; think_var=zh-cn";
        //post请求，返回json
        String data=sendPostRequest(url,param1,cookie);
        //请求回来的数据
        System.out.println("json"+data);
        JSONObject jo = JSONObject.parseObject(data);
        //for ip
        String res_json = jo.get("res").toString();
        System.out.println("res_json:"+res_json);
        JSONObject jo1 = JSONObject.parseObject(res_json);

        String url1 = jo1.get("url").toString();
        System.out.println("url1:"+url1);

        String ip_gps_info = jo1.get("ip_gps_info").toString().replace(" ","");
        System.out.println("ip_gps_info:"+ip_gps_info);
        JSONObject jo_ip_gps_info = JSONObject.parseObject(res_json);

        String[] ip_gsp_info_1 = ip_gps_info.split(",");

        String isp = ip_gsp_info_1[0].substring(ip_gsp_info_1[0].indexOf(':')+2
                ,ip_gsp_info_1[0].length()-1);
        System.out.println("isp:"+isp);

        String area = ip_gsp_info_1[1].substring(ip_gsp_info_1[1].indexOf(':')+2
                ,ip_gsp_info_1[1].length()-1);
        System.out.println("area:"+area);

        String gps = ip_gsp_info_1[2].substring(ip_gsp_info_1[2].indexOf(':')+2
                ,ip_gsp_info_1[2].length()-1)
                +","
                +ip_gsp_info_1[3].substring(0,ip_gsp_info_1[3].length()-2);
        System.out.println("gps:"+gps);

        String cms = jo1.get("cms").toString();
        cms = cms.replace("[\"","");
        cms = cms.replace("\"","");
        cms = cms.replace("]","");
        System.out.println("cms:"+cms);

        String banner = jo1.get("banner").toString();
        System.out.println("banner:"+banner);
        JSONObject json_banner = (JSONObject) JSON.parse(banner);

        String webservice = json_banner.get("WebService").toString();
        System.out.println("webservice:"+webservice);

        String Language = json_banner.get("Language").toString();
        int index_start = Language.indexOf('>');
        int index_end = Language.lastIndexOf('<');
        Language = Language.substring(index_start+1,index_end);
        System.out.println("Language:"+Language);

        String other = json_banner.get("Other").toString();
        System.out.println("Other:"+other);

        index_start = other.indexOf('"');
        index_end = other.lastIndexOf(',');
        String mail = other.substring(index_start+1,index_end-1);
        System.out.println("mail:"+mail);

        String os = jo1.get("os").toString();
        os = os.substring(1,os.length()-1);
        System.out.println("os:"+os);

        String waf = jo1.get("waf").toString();
        System.out.println("waf:"+waf);

        String ip = jo1.get("ip").toString();
        System.out.println("ip:"+ip);

        String domain = jo1.get("domain").toString();
        System.out.println("domain:"+domain);

        String main_domain = jo1.get("main_domain").toString();
        System.out.println("main_domain:"+main_domain);

        String domain_whois = jo1.get("domain_whois").toString();
        System.out.println("domain_whois:"+domain_whois);

        String[] domain_whois_1 = domain_whois.split(",");

        String reg = domain_whois_1[0].substring(domain_whois_1[0].indexOf(':')+3
                ,domain_whois_1[0].length()-1);
        System.out.println("reg:"+reg);

        String creaction_date = domain_whois_1[2].substring(domain_whois_1[2].indexOf(':')+3
                ,domain_whois_1[2].length()-1)
                +"-"
                +domain_whois_1[3].substring(1,domain_whois_1[3].length())
                +"-"
                +domain_whois_1[4].substring(1,domain_whois_1[4].length());
        System.out.println("creaction_date:"+creaction_date);

        String expiration_date = domain_whois_1[8].substring(domain_whois_1[8].indexOf(':')+3
                ,domain_whois_1[8].length()-1)
                +"-"
                +domain_whois_1[9].substring(1,domain_whois_1[9].length())
                +"-"
                +domain_whois_1[10].substring(1,domain_whois_1[10].length());
        System.out.println("expiration_date:"+expiration_date);

        String name_servers = domain_whois_1[14].substring(domain_whois_1[14].indexOf(':')+4
                ,domain_whois_1[14].length()-2);
        System.out.println("name_servers:"+name_servers);

        String finger = jo1.get("finger").toString();
        System.out.println("finger:"+finger);
        JSONObject json_finger = (JSONObject) JSON.parse(finger);

        String title = json_finger.get("title").toString();
        System.out.println("title:"+title);

        String httpserver = json_finger.get("httpserver").toString();
        System.out.println("httpserver:"+httpserver);

        String domain_beian = jo1.get("domain_beian").toString();
        System.out.println("domain_beian:"+domain_beian);
        JSONObject json_domain_beian = (JSONObject) JSON.parse(domain_beian);

        String gongsi = json_domain_beian.get("gongsi").toString();
        System.out.println("gongsi:"+gongsi);

        String xingzhi = json_domain_beian.get("xingzhi").toString();
        System.out.println("xingzhi:"+xingzhi);

        String beian = json_domain_beian.get("beian").toString();
        System.out.println("beian:"+beian);

        String title1 = json_domain_beian.get("title").toString();
        System.out.println("title1:"+title1);

        String time = json_domain_beian.get("time").toString();
        System.out.println("time:"+time);

        CMSDTO cmsdto = new CMSDTO();
        cmsdto.setUrl1(url1);
        cmsdto.setIsp(isp);
        cmsdto.setArea(area);
        cmsdto.setGps(gps);
        cmsdto.setCms(cms);
        cmsdto.setLanguage(Language);
        cmsdto.setMail(mail);
        cmsdto.setOs(os);
        cmsdto.setWaf(waf);
        cmsdto.setIp(ip);
        cmsdto.setDomain(domain);
        cmsdto.setMain_domain(main_domain);
        cmsdto.setReg(reg);
        cmsdto.setCreaction_date(creaction_date);
        cmsdto.setExpiration_date(expiration_date);
        cmsdto.setName_servers(name_servers);
        cmsdto.setTitle(title);
        cmsdto.setHttpserver(httpserver);
        cmsdto.setGongsi(gongsi);
        cmsdto.setXingzhi(xingzhi);
        cmsdto.setTitle1(title1);
        cmsdto.setTime(time);
    }
}
