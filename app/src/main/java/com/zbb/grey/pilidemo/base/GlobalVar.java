package com.zbb.grey.pilidemo.base;

import android.content.Context;

import com.jstudio.base.GlobalObject;
import com.jstudio.utils.FileUtils;
import com.jstudio.utils.SizeUtils;
import com.zbb.grey.pilidemo.MyApp;
import com.zbb.grey.pilidemo.ui.bean.UserEntity;

import java.util.Map;

/**
 * Created by Jason
 */
public class GlobalVar extends GlobalObject {

    public static final String TAG = GlobalVar.class.getSimpleName();

    private UserEntity mUserEntity;

    public UserEntity getUserEntity() {
        return mUserEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.mUserEntity = userEntity;
    }

    /**
     * GlobalObject实例，有且只有一个
     */
    private static GlobalVar INSTANCE;

    private GlobalVar(Context context) {
        super();
        mScreenWidth = SizeUtils.getScreenWidth(context);
        mScreenHeight = SizeUtils.getScreenHeight(context);
        if (FileUtils.isExternalStorageAvailable()) {
            mAppFolderName = "." + context.getPackageName();
            mIsExternalAvailable = true;
        } else {
            mAppFolderName = context.getPackageName();
            mIsExternalAvailable = false;
        }
        mAppFolderPath = FileUtils.getAppMainFolder(context);

    }

    @Override
    public Map<String, String> getCommonParams() {
        return null;
    }

    public static GlobalVar getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GlobalVar(MyApp.getInstance());
        }
        return INSTANCE;
    }


}
