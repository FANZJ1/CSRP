package com.example.demo.Service;

import java.io.*;

public class enc1Demo {

    private static final int Key=0x99;
    private static int dataOfFile=0;

    /**
     * 文件加密
     * @param srcfile
     * @param encfile
     * @throws IOException
     */
    private static void EncFile(File srcfile,File encfile) throws IOException
    {
        if(!srcfile.exists())
        {
            System.out.println("file not exits");
        }
        if (!encfile.exists())
        {
            System.out.println("encrypt file created");
            encfile.createNewFile();
        }

        InputStream fis=new FileInputStream(srcfile);
        OutputStream fos=new FileOutputStream(encfile);

        while((dataOfFile=fis.read())>-1)
        {
            fos.write(dataOfFile^ Key);
        }
        fis.close();
        fos.flush();
        fos.close();
    }

    /**
     * 文件解密
     * @param encFile
     * @param decFile
     * @throws IOException
     */
    private static void DecFile(File encFile,File decFile)throws IOException{
        if(!encFile.exists())
        {
            System.out.println("file not exits");
        }
        if (!decFile.exists())
        {
            System.out.println("encrypt file created");
            decFile.createNewFile();
        }

        InputStream fis=new FileInputStream(encFile);
        OutputStream fos=new FileOutputStream(decFile);

        while((dataOfFile=fis.read())>-1)
        {
            fos.write(dataOfFile^ Key);
        }
        fis.close();
        fos.flush();
        fos.close();
    }
}
