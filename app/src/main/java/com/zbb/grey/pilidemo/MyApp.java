package com.zbb.grey.pilidemo;

import android.view.Gravity;
import android.widget.Toast;

import com.jstudio.base.CommonApplication;
import com.jstudio.widget.toast.SnackToast;
import com.zbb.grey.pilidemo.base.GlobalVar;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by jumook on 2016/10/24.
 */

public class MyApp extends CommonApplication {

    private static MyApp INSTANCE;
    private static SnackToast mSnackToast;

    /**
     * 实现此方法返回BuildConfig.DEBUG的值，用于打印日志和创建crash log
     *
     * @return 返回true表示在debug环境，否则是正式环境
     */
    @Override
    protected boolean isInDebugMode() {
        return BuildConfig.DEBUG;
    }

    /**
     * 方便外部访问
     *
     * @return 本实例
     */
    public static MyApp getInstance() {
        return INSTANCE;
    }

    /**
     * 父类调用onCreate方法时调用
     */
    @Override
    protected void onApplicationCreate() {
        super.onApplicationCreate();
        INSTANCE = this;
        mSnackToast = new SnackToast(this);
        mSnackToast.setDuration(Toast.LENGTH_SHORT).setGravity(Gravity.TOP).setText("")
                .setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
    }

    /**
     * 在主进程创建时调用一次，用于处理初始化的一些操作，如创建文件夹，赋值mGlobalObject
     */
    @Override
    protected void init() {
        //初始化全局常用变量容器GlobalObject
        mGlobalObject = GlobalVar.getInstance();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("font.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }

    public static SnackToast getSnackToast() {
        return mSnackToast;
    }


    /**
     * 扫描到日志文件执行
     *
     * @param crashFilePath 日志文件路径
     * @param crashInfo     错误日志内容
     */
    @Override
    protected void onDiscoverCrashLog(String crashFilePath, String crashInfo) {

    }


}
