package com.nyt.demo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopy {

    public static void main(String[] args){

        String basepath="/Users/nongyongtao/Documents/myproject/filebreakpoint/src/doc/";

        String filepath=basepath+"test.txt";

        String targetpath=basepath+"test_copy.txt";

        FileInputStream fis=null;
        FileOutputStream fos=null;

        try {
             fis = new FileInputStream(filepath);

             fos = new FileOutputStream(targetpath);

             byte[] buffer=new byte[1];
             while (fis.read(buffer)!=-1){
                 System.out.println(buffer[0]);
                 fos.write(buffer);
             }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
