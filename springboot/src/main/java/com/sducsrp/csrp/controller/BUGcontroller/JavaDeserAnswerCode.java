package com.sducsrp.csrp.controller.BUGcontroller;

import com.sducsrp.csrp.common.Constants;
import com.sducsrp.csrp.common.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JavaDeserAnswerCode {

    @PostMapping("/java_deser_answer")
    public @ResponseBody Result answer_mathod(@RequestBody String r1){

        System.out.println("ok");
        byte[] r2;
        JavaDeserializationFlag Flag1;
        Result res;

        r2 = JavaDeserByteArrayUtils.toByteArray(r1);
        Flag1 = (JavaDeserializationFlag) JavaDeserByteArrayUtils.bytesToObject(r2).get();
        System.out.println("反序列化后对象："+Flag1.toString());
        //String flag = Flag1.flag;

        res = new Result(Constants.CODE_200,null,"反序列化后对象："+Flag1.toString());
        return res;
    }
}
