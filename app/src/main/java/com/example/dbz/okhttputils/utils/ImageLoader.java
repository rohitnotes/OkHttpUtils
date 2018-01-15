package com.example.dbz.okhttputils.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.util.LruCache;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2017/9/25 0025.
 */
// 图片加载及缓存的工具类
//  url----图片 ====存到内存，存到文件
public class ImageLoader {
    //key：根据什么去取存的东西,  value：要存的东西
    private static LruCache<String, Bitmap> lruCache;
    private File path;

    public ImageLoader(Context context) {
        int maxSize = (int) (Runtime.getRuntime().maxMemory() / 8);
        lruCache = new LruCache<String, Bitmap>(4 * 1024 * 1024) {
            //计算每张图片的大小
            @Override
            protected int sizeOf(String key, Bitmap value) {
//                return value.getByteCount();
                // 获取一行的字节数 * 高
                // value.getRowBytes() * value.getHeight();
                return value.getRowBytes() * value.getHeight();
            }
        };

        try {
            path = context.getExternalCacheDir();
            if (!path.exists()) {
                path.mkdirs();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface OnImageLoadListener {
        void onImageLoadOk(String url, Bitmap bitmap);

        void onImageLoadError(String url);
    }

    public Bitmap getImageLoadBitmap(String url, OnImageLoadListener l) {

        Bitmap bitmap;
        //内存取
        bitmap = getFromCache(url);
//        LogUtil.e("------------内存取bitmap = " + bitmap);
        if (bitmap != null) {
            return bitmap;
        }

        //文件取
        bitmap = getFromFile(url);
//        LogUtil.e("------------文件取bitmap = " + bitmap);
        if (bitmap != null) {
            return bitmap;
        }
        //网络下载
        getFromNet(url, l);
//        LogUtil.e("------------网络下载bitmap = " + bitmap);
        return null;
    }

    //保存到缓存
    private void saveToCache(String url, Bitmap bitmap) {
        lruCache.put(url, bitmap);
    }

    //获取缓存
    private Bitmap getFromCache(String url) {
        return lruCache.get(url);
    }

    // 保存到文件
    private void saveToFile(String url, Bitmap bitmap) {
        String fileName = url.substring(url.lastIndexOf('/') + 1);
        OutputStream os = null;
        try {
            File file = new File(path, fileName);
            os = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //获取文件
    private Bitmap getFromFile(String url) {
        String fileName = url.substring(url.lastIndexOf('/') + 1);
        File file = new File(path, fileName);
        if (file.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            if (bitmap != null) {
                saveToCache(url, bitmap);
                return bitmap;
            }
        }
        return null;
    }

    private void getFromNet(String url, OnImageLoadListener l) {
        MyAsyncTask task = new MyAsyncTask(l);
        task.execute(url);
    }

    //参数1：执行该任务需要传入什么参数
    //参数2：执行该任务是否需要在界面显示进度，如果需要，传入进度的数据类型
    //参数3：执行该任务需要返回的数据
    private class MyAsyncTask extends AsyncTask<String, Void, Bitmap> {
        private String newsUrl;
        private OnImageLoadListener l;

        public MyAsyncTask(OnImageLoadListener l) {
            this.l = l;
        }

        //默认在子线程执行，用于执行后台任务
        @Override
        protected Bitmap doInBackground(String... params) {
            newsUrl = params[0];
            Bitmap bitmap = doNetwork();
            return bitmap;//该返回值被传递到了onPostExecute()方法内
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap != null) {
                saveToCache(newsUrl, bitmap);
                saveToFile(newsUrl, bitmap);
                l.onImageLoadOk(newsUrl, bitmap);
            } else {
                l.onImageLoadError(newsUrl);
            }
        }

        private Bitmap doNetwork() {
            InputStream is = null;
            try {
                URL url = new URL(newsUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(20 * 1000);
                conn.setReadTimeout(20 * 1000);
                is = conn.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                if (bitmap != null) {
                    return bitmap;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (is != null) {
                        is.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
}
