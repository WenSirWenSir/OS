package os.zbwlkj.com.os.Utils;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import os.zbwlkj.com.os.Dialog.WaitDialog;


/**
 * 网络访问模块  有关于网络访问的监听和提交事件都在这里
 * <p>
 * 该模块属于左边远景软件开发工作室公司服务器内模块 任何开发个体的android project都可以使用
 * 该套模块
 */
@SuppressLint("LongLogTag")
public class Net {

    private String MSG = "Net.java[+]";
    private String tUrl = "";
    private StringBuffer mKvsBuffer = new StringBuffer();
    private Net.onVisitInterServiceListener mOnVisitInterServiceListener;
    private int VisitInterMethod;

    /**
     * 用GET方式获取数据信息
     *
     * @param tUrl                         地址
     * @param mOnVisitInterServiceListener 监听回调
     * @param kvs                          参数对,没有就直接用NULL
     */
    public static void doGet(final Context context, String tUrl, final Net
            .onVisitInterServiceListener mOnVisitInterServiceListener, String... kvs) {
        final WaitDialog.RefreshDialog refreshDialog = mOnVisitInterServiceListener.onStartLoad();
        OkhttpUtil.okHttpGet(tUrl, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
            }

            @Override
            public void onResponse(String response) {
                mOnVisitInterServiceListener.onSucess(response, refreshDialog);
            }
        });
    }

    public interface onVisitInterServiceListener {
        WaitDialog.RefreshDialog onStartLoad();/*开始数据访问 并且添加一个等候的DIALOG*/

        void onSucess(String tOrgin, final WaitDialog.RefreshDialog _rfreshdialog);//成功的监听

        void onNotConnect();//网络断开连接

        void onFail(String tOrgin);//失败的监听
    }


}