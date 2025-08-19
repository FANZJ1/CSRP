package com.example.demo.Service;

import java.io.*;

public class enc2Demo {
    public static void enc(String str)throws IOException
    {
        BufferedInputStream bis= new BufferedInputStream(new FileInputStream(str));
        String[] s= str.split("\\.");
        BufferedOutputStream bos= new BufferedOutputStream(new
                FileOutputStream("C:\\Users\\86156\\Desktop\\CipherText2.txt"));
        int n;
        long a=System.currentTimeMillis();
        while((n=bis.read())!=-1)
        {bos.write(n+1);}

        long b=System.currentTimeMillis();
        bis.close();
        bos.close();
        System.out.println("success!");
    }

    public static void dec(String str) throws IOException
    {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(str));
        String [] s=str.split("\\.");
        BufferedOutputStream bos = new BufferedOutputStream(new
                FileOutputStream("C:\\Users\\86156\\Desktop\\dec.txt"));
        int n;
        long a=System.currentTimeMillis();
        while((n=bis.read())!=-1){
            bos.write(n-1);
        }
        long b=System.currentTimeMillis();
        bis.close();
        bos.close();
        System.out.println("success！");
        System.out.println("解密用时："+(b-a)+"ms");
    }
}
