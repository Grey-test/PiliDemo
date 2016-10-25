package com.jstudio.async;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.jstudio.network.base.AsyncRequestCreator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Jason on 2016/2/22.
 */
public class AsyncHttpUtils {

    public static void uploadFile(String path, String url, String key, final OnContentDeliverListener listener) throws Exception {
        File file = new File(path);
        if (file.exists() && file.length() > 0) {
            AsyncHttpClient client = AsyncRequestCreator.getClient();
            RequestParams params = new RequestParams();
            params.put(key, file);
            // 上传文件
            client.post(url, params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    if (listener != null) {
                        listener.onProgress(100);
                        listener.onFinish();
                    }
                }

                @Override
                public void onStart() {
                    super.onStart();
                    if (listener != null) {
                        listener.onStart();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    if (listener != null) {
                        listener.onFailed();
                    }
                }

                @Override
                public void onProgress(long bytesWritten, long totalSize) {
                    super.onProgress(bytesWritten, totalSize);
                    int count = (int) ((bytesWritten * 1.0 / totalSize) * 100);
                    if (listener != null) {
                        listener.onProgress(count);
                    }
                }
            });
        }
    }


    public static void downloadFile(String url, final String fileNameAbsolutePath, final OnContentDeliverListener listener) throws Exception {
        AsyncHttpClient client = AsyncRequestCreator.getClient();
//        String[] allowedContentTypes = new String[]{"image/png", "image/jpeg"};
        client.get(url, new BinaryHttpResponseHandler(/*allowedContentTypes*/) {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] binaryData) {
//                Bitmap bmp = BitmapFactory.decodeByteArray(binaryData, 0, binaryData.length);
                File file = new File(fileNameAbsolutePath);
//                Bitmap.CompressFormat format = Bitmap.CompressFormat.JPEG;
//                int quality = 100;
                try {
                    if (file.exists()) {
                        file.delete();
                    }
                    file.createNewFile();
                    OutputStream stream = new FileOutputStream(file);
                    stream.write(binaryData);
//                    bmp.compress(format, quality, stream);
                    stream.close();
                    if (listener != null) {
                        listener.onFinish();
                        listener.onProgress(100);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    if (listener != null) {
                        listener.onFailed();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] binaryData, Throwable error) {
                if (listener != null) {
                    listener.onFailed();
                }
            }

            @Override
            public void onProgress(long bytesWritten, long totalSize) {
                super.onProgress(bytesWritten, totalSize);
                int count = (int) ((bytesWritten * 1.0 / totalSize) * 100);
                if (listener != null) {
                    listener.onProgress(count);
                }
            }

        });
    }

    public interface OnContentDeliverListener {
        public void onProgress(int progress);

        public void onStart();

        public void onFinish();

        public void onFailed();
    }
}
