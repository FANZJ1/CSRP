package com.sducsrp.csrp.controller.ToolsController.ScanPort;

import com.sducsrp.csrp.common.Constants;
import com.sducsrp.csrp.common.Result;
import com.sducsrp.csrp.controller.dto.PortDTO;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * 扫描器启动类
 */
@RestController
@RequestMapping("/tools/")
public class ScanApp {
//    {ip='127.0.0.1', port=445, service='null', isOpen=true, banner='null'}
    public static String result;
    public int count = 0;

    @RequestMapping("/scan_port")
    public @ResponseBody Result scan(@RequestParam(value = "ips") String ips,
                                     @RequestParam(value = "ports") String ports,
                                     HttpServletResponse response){
        result="";
        String[] results;
        int count;
        PortDTO[] portDTOS;

        // 待扫描的ip地址或ip地址组
        System.out.println(ips+" "+ports);
//        ips = "14.29.192.196,14.116.188.121";
//        ips = "127.0.0.1";
        // 待扫描的port范围
//        ports = "442-449";
        Scanner.start(ips,ports);
        System.out.println("from ScanApp:"+result);

        results = result.split(";");
        count = results.length;
        portDTOS = new PortDTO[count];

        for(int i = 0;i<count;i++){
            String[] ret;

            System.out.println(i);
            portDTOS[i] = new PortDTO();
            ret = results[i].split(",");
            System.out.println(ret[0]);
            portDTOS[i].setIp(ret[0]);
            portDTOS[i].setPort(ret[1]);
            portDTOS[i].setService(ret[2]);

            if(ret[3].equals("true")){
                ret[3] = "open";
            }
            else {
                ret[3] = "close";
            }
            portDTOS[i].setIsOpen(ret[3]);
//            portDTOS[i].setBanner(ret[4]);
//            portDTOS[i].setPort(ret[1]);
        }

        Result res=new Result(Constants.CODE_200,null,portDTOS);
        return res;
    }
}
