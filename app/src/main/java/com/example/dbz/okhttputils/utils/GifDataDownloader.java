package com.example.dbz.okhttputils.utils;

import android.os.AsyncTask;

/**
 * Created by Administrator on 2017/8/10 0010.
 */

public class GifDataDownloader extends AsyncTask<String, Void, byte[]> {

    @Override
    protected byte[] doInBackground(final String... params) {
        final String gifUrl = params[0];

        if (gifUrl == null)
            return null;

        try {
            return ImageBitmap.getInstance().get(gifUrl);
        } catch (OutOfMemoryError e) {
            return null;
        }
    }
}
