package com.example.dbz.okhttputils.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/8/9 0009.
 */

public class ImageBitmap {

    private static ImageBitmap imageBitmap;

    public static ImageBitmap getInstance() {
        if (imageBitmap == null) {
            imageBitmap = new ImageBitmap();
        }
        return imageBitmap;
    }

    // 根据url获取图片(获取bitmap图片)
    public Bitmap getRequestCodeBitmap(String urlPath) {
        InputStream inputStream = null;
        try {
            URL url = new URL(urlPath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);

            inputStream = conn.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

            return bitmap;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    // 根据url返回byte[]
    public byte[] get(final String urlString) {
        InputStream in = null;
        try {
            OkHttpClient client = new OkHttpClient();
            final String decodedUrl = URLDecoder.decode(urlString, "UTF-8");
            final URL url = new URL(decodedUrl);
            final Request request = new Request.Builder().url(url).build();
            final Response response = client.newCall(request).execute();
            in = response.body().byteStream();
            return IOUtils.toByteArray(in);

        } catch (final MalformedURLException e) {
        } catch (final OutOfMemoryError e) {
        } catch (final UnsupportedEncodingException e) {
        } catch (final IOException e) {
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
