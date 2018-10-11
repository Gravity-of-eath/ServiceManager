package com.yaokun.test.servicemanager.utils;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;

import com.yaokun.test.servicemanager.interfaces.ResultPoster;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class OkHttpUtils {

    private static OkHttpClient client;
    private static Dialog dialog;

    public static OkHttpClient getClient() {
        if (null == client) {
            client = new OkHttpClient().newBuilder().connectTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS).build();
        }
        return client;
    }

    public static String getSyncRequset(Context context, String url) {
        Log.e("OkHttpUtils", "GET: " + url);
        dismissDialog();
        dialog = DialogsUtils.CreatDialog(context, "加载中。。。");
        Request request = new Request.Builder().get().url(url).build();
        Call call = getClient().newCall(request);
        String result;
        try {
            Response response = call.execute();
            if (response.isSuccessful()) {
                result = response.body().string();
                dismissDialog();
                return result;
            }
        } catch (IOException e) {
            dismissDialog();
            e.printStackTrace();
        }
        dismissDialog();
        return null;
    }

    public static void getAsyncRequest(Context context, final String url, final ResultPoster poster) {
        dismissDialog();
        dialog = DialogsUtils.CreatDialog(context, "加载中。。。");
        Log.e("OkHttpUtils", "AGET: " + url);
        Request request = new Request.Builder().get().url(url).build();
        final Call call = getClient().newCall(request);
        // 同步execute
        // 同步请求
        // 同步是耗时的
        // 同步execute需要开启子线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = call.execute();
                    if (response.isSuccessful()) {
                        String string = response.body().string();
                        Log.e("OkHttpUtils", "AGET: " + url + "   data:" + string);
                        dismissDialog();
                        poster.postResult(string);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    dismissDialog();
                    Log.e("OkHttpUtils", "AGET: ERROR " + url);
                }
            }
        }).start();
    }

    public static void getAsyncRequest(final String url, final ResultPoster poster) {
        Log.e("OkHttpUtils", "AGET: " + url);
        Request request = new Request.Builder().get().url(url).build();
        final Call call = getClient().newCall(request);
        // 同步execute
        // 同步请求
        // 同步是耗时的
        // 同步execute需要开启子线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = call.execute();
                    if (response.isSuccessful()) {
                        String string = response.body().string();
                        Log.e("OkHttpUtils", "AGET: " + url + "   data:" + string);
                        poster.postResult(string);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("OkHttpUtils", "AGET: ERROR " + url);
                }
            }
        }).start();
    }

    public static void postAsyncRequest(Context context, String url, Map<String, Object> map, ResultPoster poster) {
        Log.e("OkHttpUtils", "POST: " + url);
        dismissDialog();
        dialog = DialogsUtils.CreatDialog(context, "加载中。。。");
        RequestBody body;
        if (null != map && !map.isEmpty()) {
            JSONObject jsonObject = new JSONObject();
            for (String key : map.keySet()) {
                Object object = map.get(key);
                try {
                    jsonObject.put(key, object);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            String s = jsonObject.toString();
            Log.e("ooookHttp", "" + s);
            body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s);
        } else {
            body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), "");
        }
        Request request = new Request.Builder().url(url).post(body).build();
        Response response = null;
        try {
            response = getClient().newCall(request).execute();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            dismissDialog();
            e1.printStackTrace();
        } finally {
            dismissDialog();
        }
        if (null != poster) {
            if (null != response && null != response.body()) {
                try {
                    poster.postResult(response.body().string());
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } finally {
                    dismissDialog();
                }
            } else {
                try {
                    poster.postResult(null);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } finally {
                    dismissDialog();
                }
            }
        }
    }

    private static void dismissDialog() {
        if (null != dialog) {
            dialog.dismiss();
        }
    }

}
