package com.nyt.demo.breakpoint;

import java.io.*;

/**
 * 文件断点续传模拟
 */
public class FileBreakPoint {

    private static int position=-1;


    public static void main(String[] args){

        String basepath="/Users/nongyongtao/Documents/myproject/filebreakpoint/src/doc";


        FileInputStream fis=null;
        FileOutputStream fos=null;

        File source = new File(basepath,"test.txt");

        File target = new File(basepath,"test_copy2.txt");


        try {
            fis = new FileInputStream(source);

            fos = new FileOutputStream(target);

            byte[] buffer=new byte[1];
            while (fis.read(buffer)!=-1){
                if(target.length()==4){
                    position=4;
                    throw new NetWorkException("模拟网络中断");
                }
                fos.write(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NetWorkException e) {
            e.printStackTrace();

            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.out.println("===开始续传===");
            keepgoing(source,target,position);

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


    /**
     * 断点续传
     *
     * 敲重点了：RandomAccessFile
     * 敲重点了：RandomAccessFile
     * 敲重点了：RandomAccessFile
     */
    public static void keepgoing(File source,File target,int position)   {


        RandomAccessFile sourceFile=null;
        RandomAccessFile targetFile=null;

        try {
            sourceFile= new RandomAccessFile(source, "rw");
            targetFile= new RandomAccessFile(target, "rw");

            sourceFile.seek(position);
            targetFile.seek(position);

            byte[] buffer = new byte[1];
            while (sourceFile.read(buffer)!=-1){
                targetFile.write(buffer);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (sourceFile!=null){
                try {
                    sourceFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (targetFile!=null){
                try {
                    targetFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
