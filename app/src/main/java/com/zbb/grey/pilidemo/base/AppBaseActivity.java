package com.zbb.grey.pilidemo.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.jstudio.base.BaseAppCompatActivity;
import com.jstudio.base.CommonApplication;
import com.jstudio.utils.JLog;
import com.jstudio.widget.toast.SnackToast;
import com.zbb.grey.pilidemo.MyApp;
import com.zbb.grey.pilidemo.R;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


/**
 * Created by Jason
 */
public abstract class AppBaseActivity extends BaseAppCompatActivity {

    protected AlertDialog.Builder mDialogBuilder;

    @Override
    protected boolean onRestoreState(Bundle paramSavedState) {
        return false;
    }

    @Override
    protected void findViews() {
    }

    @Override
    protected void doMoreInOnCreate() {
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * In order to customizer font
     *
     * @param newBase Context
     */
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    protected void showSnackToast(String message) {
        SnackToast snackToast = MyApp.getSnackToast();
        if (snackToast == null) {
            JLog.e(TAG, CommonApplication.class.getSimpleName() + "not initialize");
            return;
        }
        snackToast.setText(message);
        snackToast.show();
    }

    protected AlertDialog.Builder getDialogBuilder() {
        if (mDialogBuilder == null) {
            mDialogBuilder = new AlertDialog.Builder(this, R.style.MyDialogTheme);
        }
        return mDialogBuilder;
    }

}
