package com.xianguoliang.tools;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class DowloadImage {

    public static Bitmap dowload(final String myurl) {
        Bitmap bitmap = null;
        //下载图片的路径
        String iPath = myurl;
        try {
            //对资源链接
            URL url = new URL(iPath);
            //打开输入流
            InputStream inputStream = url.openStream();
            //对网上资源进行下载转换位图图片
            bitmap = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

}
