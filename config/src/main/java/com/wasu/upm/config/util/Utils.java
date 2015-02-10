package com.wasu.upm.config.util;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Created by zhengchao on 14-10-16.
 */
public class Utils {

    public static  final String SYSTEMFILESPLITSYMBOL =   java.io.File.separator; ;

    public static final String  FILECHANGELINESYMBOL ="/r/n";

    public static final String STORESPLITSYMBOL = ";;";


    /**
     * 首字母大小
     * @param str
     * @return
     */
    public static String firstletterUpperCase(String str) {
        /*if (str == null || "".equals(str)) {
            return null;
        }
        String firstLetter = str.substring(0, 1);
        int length = str.length();
        if (length == 1) {
            return firstLetter.toUpperCase();
        }
        return firstLetter.toUpperCase()+str.substring(1, length);*/
        byte[] bytes = str.getBytes();
        bytes[0]  = (byte)((char)(bytes[0] - 'a' + 'A'));
        return new String(bytes);
    }

    /**
     * byte[]集合变成 byte[]
     * @param byteList
     * @return
     */
    public static byte[] union(List<byte[]> byteList) {
        int size = 0;
        for ( byte[] bs : byteList ) {
            size += bs.length;
        }
        byte[] ret = new byte[size];
        int pos = 0;
        for ( byte[] bs : byteList ) {
            System.arraycopy(bs, 0, ret, pos, bs.length);
            pos += bs.length;
        }
        return	ret;
    }

    /**
     * 向指定目录下的文件写入字符
     * @param filedir
     * @param fileName
     * @param needWriteStrs
     * @throws Exception
     */
    public static  void writeFile(String filedir, String fileName, String needWriteStrs) throws Exception {
        if(!judgeObjsNotEmpty(filedir,needWriteStrs))
            throw new Exception("filedir: "+filedir +"and needWriteStrs: "+needWriteStrs +" must be not null ");
        File file = newFile(filedir, fileName);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(needWriteStrs);
        fileWriter.close();
    }


    /**
     * 向指定目录下的文件写入 字符集合，集合的每隔字符串已一行形式写入
     * @param filedir
     * @param fileName
     * @param cols
     * @throws Exception
     */
    public static  void writeFile(String filedir, String fileName, Collection<String> cols) throws Exception {
        if(!judgeObjsNotEmpty(filedir,cols))
            throw new Exception("filedir: "+filedir +" and  fileName: "+fileName+" and cols: "+cols +" must be not null ");
        File file = newFile(filedir, fileName);
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fileWriter);
        for (String str : cols) {
            bw.write(str);
            bw.newLine();
        }
        bw.flush();
        bw.close();
        fileWriter.close();
    }


    /**
     * 读取指定目录下的文件
     * @param filedir
     * @param fileName
     * @return
     * @throws Exception
     */
    public static ArrayList<String> readFile(String filedir,String fileName) throws Exception {
        if(!judgeObjsNotEmpty(filedir,fileName)){
            throw new Exception("filedir: "+filedir +"and fileName: "+fileName +" must be not null ");
        }
        File file = newFile(filedir, fileName);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        ArrayList<String> arrayList = new ArrayList<String>();
        String temp = null;
        while ((temp = reader.readLine()) != null) {
           arrayList.add(temp);
        }
        reader.close();
        return arrayList;
    }

    public static File newFile(String filePath,String fileName) throws IOException {
        File dir = new File(filePath);
        if (!dir.exists()){
            dir.mkdirs();
        }
        File file = new File(filePath+SYSTEMFILESPLITSYMBOL+fileName);
        if(!file.exists()){
            file.createNewFile();
        }
        return file;
    }

    /**
     * 判断对象集合中是否有空值
     * @param objs
     * @return
     */
    public static Boolean judgeObjsNotEmpty(Object ... objs){
        for(Object obj :objs){
            if(!judgeObjsNotEmpty(obj)){
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    public static Boolean judgeObjsNotEmpty(Object obj){
        if(obj == null){
            return Boolean.FALSE;
        }
        if(obj instanceof String && "".equals((String)obj)){
            return Boolean.FALSE;
        }
        if(obj instanceof Boolean){
            return (Boolean)obj;
        }
        return Boolean.TRUE;
    }

    /**
     * 产生32位的uuid
     * @return
     */
    public static String newGUID()
    {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }
}
