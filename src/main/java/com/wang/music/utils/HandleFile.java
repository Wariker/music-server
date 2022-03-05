package com.wang.music.utils;

import java.io.File;

/**
 * 操作文件
 */
public class HandleFile {
    /**
     * 将文件中存储的实体音乐文件删除
     * @param url
     * @return
     */
    public static boolean deleteSongFile(String url){
        //文件路径
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"song";
        String[] songUrlSplit = url.split("\\/");
        String srcFileName = songUrlSplit[songUrlSplit.length-1];
        //拼出原本歌曲文件存储的路径
        File srcFile = new File(filePath+System.getProperty("file.separator")+srcFileName);
        return srcFile.delete();
    }

    public static boolean deleteSongPic(String url){
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                +System.getProperty("file.separator")+"songPic";
        String[] songUrlSplit = url.split("\\/");
        String srcFileName = songUrlSplit[songUrlSplit.length-1];
        //判断是不是初始图片
        if(srcFileName.equals("tubiao.jpg")){
            return true;
        }
        //拼出原本歌曲图片文件存储的路径
        File srcFile = new File(filePath+System.getProperty("file.separator")+srcFileName);
        return srcFile.delete();
    }
}
