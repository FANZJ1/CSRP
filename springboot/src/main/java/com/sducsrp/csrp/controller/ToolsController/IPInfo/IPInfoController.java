package com.sducsrp.csrp.controller.ToolsController.IPInfo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sducsrp.csrp.common.Constants;
import com.sducsrp.csrp.common.Result;
import com.sducsrp.csrp.controller.dto.IPDTO;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
public class IPInfoController {
    @RequestMapping("tools/IPInfo")
    public @ResponseBody
    Result getIPInfo(@RequestParam("ip1") String ip1){
        String url = "https://api.kinh.cc/IP/Speed/GetIPInfo.php?Address="+ip1;
        String return_json = sendRequest(url,"GET");
        JSONObject jo = JSONObject.parseObject(return_json);
        //for ip
//        String status = jo.get("status").toString();
        String Info = jo.get("Info").toString();
        // 解码（将Unicode还原为ASCII） org.apache.commons.lang.StringEscapeUtils#unescapeJava(String)
        Info = StringEscapeUtils.unescapeJava(Info);
        System.out.println("info："+Info);

        String[] info1 = Info.split(",");

        String ip = info1[0];
        int indexs = ip.indexOf(":")+2;
        int indexe = ip.length()-1;
        ip = ip.substring(indexs,indexe);
        if(ip.equals("ul")){
            ip = "";
        }

        String country = info1[2];
        indexs = country.indexOf(":")+2;
        indexe = country.length()-1;
        country = country.substring(indexs,indexe);
        if(country.equals("ul")){
            country = "";
        }

        String country_code = info1[3];
        indexs = country_code.indexOf(":")+2;
        indexe = country_code.length()-1;
        country_code = country_code.substring(indexs,indexe);
        if(country_code.equals("ul")){
            country_code = "";
        }

        String province = info1[4];
        indexs = province.indexOf(":")+2;
        indexe = province.length()-1;
        province = province.substring(indexs,indexe);
        if(province.equals("ul")){
            province = "";
        }

        String city = info1[5];
        indexs = city.indexOf(":")+2;
        indexe = city.length()-1;
        city = city.substring(indexs,indexe);
        if(province.equals("ul")){
            province = "";
        }

        String distinct = info1[6];
        indexs = distinct.indexOf(":")+2;
        indexe = distinct.length()-1;
        distinct = distinct.substring(indexs,indexe);
        if(distinct.equals("ul")){
            distinct = "";
        }

        String isp = info1[7];
        indexs = isp.indexOf(":")+2;
        indexe = isp.length()-1;
        isp = isp.substring(indexs,indexe);
        if(isp.equals("ul")){
            isp = "";
        }

        String operator = info1[8];
        indexs = operator.indexOf(":")+2;
        indexe = operator.length()-1;
        operator = operator.substring(indexs,indexe);
        if(operator.equals("ul")){
            operator = "";
        }

        String lon = info1[9];
        indexs = lon.indexOf(":")+2;
        indexe = lon.length()-1;
        lon = lon.substring(indexs,indexe);
        if(lon.equals("ul")){
            lon = "";
        }

        String lat = info1[10];
        indexs = lat.indexOf(":")+2;
        indexe = lat.length()-1;
        lat = lat.substring(indexs,indexe);
        if(lat.equals("ul")){
            lat = "";
        }

        String net_str = info1[11];
        indexs = net_str.indexOf(":")+2;
        indexe = net_str.length()-2;
        net_str = net_str.substring(indexs,indexe);
        if(net_str.equals("ul")){
            net_str = "";
        }

        String resdata = ip+";"+country+";"+country_code+";"+province+";"+city+";"+distinct+";"+isp+";"+operator+";"+lon+";"+lat+";"+net_str;

        IPDTO ipdto = new IPDTO();
        ipdto.setIp(ip);
        ipdto.setCountry(country);
        ipdto.setCountry_code(country_code);
        ipdto.setProvince(province);
        ipdto.setCity(city);
        ipdto.setDistinct(distinct);
        ipdto.setIsp(isp);
        ipdto.setOperator(operator);
        ipdto.setLon(lon);
        ipdto.setLat(lat);
        ipdto.setNet_str(net_str);

        Result res=new Result(Constants.CODE_200,null,ipdto);
        return res;
    }

    public static String sendRequest(String urlParam, String requestType) {
        HttpURLConnection con = null;
        BufferedReader buffer = null;
        StringBuffer resultBuffer = null;

        try {
            StringBuilder json = new StringBuilder();
            URL url = new URL(urlParam);
            //得到连接对象
            con = (HttpURLConnection) url.openConnection();
            //设置请求类型
            con.setRequestMethod(requestType);
            //设置请求需要返回的数据类型和字符集类型
            //con.setRequestProperty("Content-Type", "text/plain; charset=utf-8");
            con.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.41 Safari/537.36 Edg/101.0.1210.32");
            //允许写出
            con.setDoOutput(true);
            //允许读入
            con.setDoInput(true);
            //不使用缓存
            con.setUseCaches(false);
            //得到响应码
            int responseCode = con.getResponseCode();
            System.out.println(responseCode);
            if(responseCode == HttpURLConnection.HTTP_OK){
                //得到响应流
                InputStream inputStream = con.getInputStream();
                //将响应流转换成字符串
                resultBuffer = new StringBuffer();
                String line;
                buffer = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
                while ((line = buffer.readLine()) != null) {
                    //resultBuffer.append(line);
                    json.append(line);
                }
                return json.toString();
            }

        }catch(Exception e) {
            e.printStackTrace();
        }
        return "{\"count\":0,\"data\":\"fail\n";
    }
    public static void main(String[] args){
        String url = "https://api.kinh.cc/IP/Speed/GetIPInfo.php?Address=82.157.124.157";
        String return_json = sendRequest(url,"GET");
        JSONObject jo = JSONObject.parseObject(return_json);
        //for ip
//        String status = jo.get("status").toString();
        String Info = jo.get("Info").toString();
        // 解码（将Unicode还原为ASCII） org.apache.commons.lang.StringEscapeUtils#unescapeJava(String)
        Info = StringEscapeUtils.unescapeJava(Info);
        System.out.println("info："+Info);

        String[] info1 = Info.split(",");

        String ip = info1[0];
        int indexs = ip.indexOf(":")+2;
        int indexe = ip.length()-1;
        ip = ip.substring(indexs,indexe);

        String country = info1[1];
        indexs = country.indexOf(":")+2;
        indexe = country.length()-1;
        country = country.substring(indexs,indexe);

        String country_code = info1[2];
        indexs = country_code.indexOf(":")+2;
        indexe = country_code.length()-1;
        country_code = country_code.substring(indexs,indexe);

        String province = info1[3];
        indexs = province.indexOf(":")+2;
        indexe = province.length()-1;
        province = province.substring(indexs,indexe);

        String city = info1[4];
        indexs = city.indexOf(":")+2;
        indexe = city.length()-1;
        city = city.substring(indexs,indexe);

        String distinct = info1[5];
        indexs = distinct.indexOf(":")+2;
        indexe = distinct.length()-1;
        distinct = distinct.substring(indexs,indexe);

        String isp = info1[6];
        indexs = isp.indexOf(":")+2;
        indexe = isp.length()-1;
        isp = isp.substring(indexs,indexe);

        String operator = info1[7];
        indexs = operator.indexOf(":")+2;
        indexe = operator.length()-1;
        operator = operator.substring(indexs,indexe);

        String lon = info1[8];
        indexs = lon.indexOf(":")+2;
        indexe = lon.length()-1;
        lon = lon.substring(indexs,indexe);

        String lat = info1[9];
        indexs = lat.indexOf(":")+2;
        indexe = lat.length()-1;
        lat = lat.substring(indexs,indexe);

        String net_str = info1[11];
        indexs = net_str.indexOf(":")+2;
        indexe = net_str.length()-2;
        net_str = net_str.substring(indexs,indexe);

        String res = ip+";"+country+";"+country_code+";"+province+";"+city+";"+distinct+";"+isp+";"+operator+";"+lon+";"+lat+";"+net_str;

    }
}
