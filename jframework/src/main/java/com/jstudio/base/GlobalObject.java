package com.jstudio.base;

import java.util.Map;

/**
 * 存放常用的变量，在app启动时刻就必须初始化，供后续调用
 * <p/>
 * Created by Jason
 */
@SuppressWarnings("unused")
public abstract class GlobalObject {

    /**
     * AppToken，App唯一标识，请求公参之一
     */
    protected String mAppToken;

    /**
     * Sign，请求公参之一
     */
    protected String mSign;

    /**
     * SessionId，请求公参之一
     */
    protected String mSessionId;

    /**
     * UserId，用户Id
     */
    protected String mUserId;

    /**
     * 外置存储是否就位
     */
    protected boolean mIsExternalAvailable;

    /**
     * 应用主文件夹名字
     */
    protected String mAppFolderName;

    /**
     * 应用主文件夹完整路径
     */
    protected String mAppFolderPath;

    /**
     * 屏幕宽度
     */
    protected int mScreenWidth;

    /**
     * 屏幕高度
     */
    protected int mScreenHeight;

    /**
     * 获取公参的方法，子类实现
     *
     * @return 存有公参的HashMap
     */
    public abstract Map<String, String> getCommonParams();


}
