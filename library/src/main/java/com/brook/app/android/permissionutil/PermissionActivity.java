/*
 * Copyright (c) 2016-present, Brook007 Contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.brook.app.android.permissionutil;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Brook
 * @time 2017年11月15日
 */
public class PermissionActivity extends AppCompatActivity {

    private static volatile WeakReference<PermissionUtil.PermissionCallback> sCallback;

    private final int REQUEST_CODE = 6529;

    public static void setCallBack(PermissionUtil.PermissionCallback callBack) {
        sCallback = new WeakReference<>(callBack);
    }


    @Override
    public void overridePendingTransition(int enterAnim, int exitAnim) {
        super.overridePendingTransition(enterAnim, exitAnim);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, android.R.color.transparent)));
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        // 得到Intent数据
        Intent intent = getIntent();

        String[] permissions = intent.getStringArrayExtra("permission");

        // 开始请求权限
        ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE);

        View view = new View(this);

        view.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));

        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        view.setClickable(true);
        view.setFocusable(true);
        view.setFocusableInTouchMode(false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setContentView(view);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            List<String> denied = new ArrayList<>();
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    denied.add(permissions[i]);
                }
            }

            if (sCallback != null && sCallback.get() != null) {
                if (denied.size() > 0) {
                    sCallback.get().onDenied(denied);
                } else {
                    sCallback.get().onGranted();
                }
            }
            finish();
        }
    }
}
