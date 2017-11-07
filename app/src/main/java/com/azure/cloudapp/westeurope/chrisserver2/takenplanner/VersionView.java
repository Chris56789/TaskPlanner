package com.azure.cloudapp.westeurope.chrisserver2.takenplanner;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Chris van der Werf on 11/4/2017.
 */

public class VersionView extends android.support.v7.widget.AppCompatTextView {
    public VersionView(Context context) {
        super(context);
        setContent();
    }

    public VersionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setContent();
    }

    public VersionView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setContent();
    }

    public void setContent() {
        try {
            PackageInfo info = getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0);
            this.setText("Version: " + info.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

}
