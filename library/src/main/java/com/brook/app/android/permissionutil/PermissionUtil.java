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

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther Brook
 * @time 2017年11月15日 9:37
 */
public class PermissionUtil {

    private Context context;

    private String[] permission;

    private PermissionUtil(Context context) {
        this.context = context;
    }

    public static PermissionUtil with(Activity activity) {
        return new PermissionUtil(activity);
    }

    public static PermissionUtil with(Fragment fragment) {
        return new PermissionUtil(fragment.getActivity());
    }

    public static PermissionUtil with(android.support.v4.app.Fragment fragment) {
        return new PermissionUtil(fragment.getActivity());
    }

    public static PermissionUtil with(Context context) {
        return new PermissionUtil(context);
    }

    public PermissionUtil permission(String... permission) {
        this.permission = permission;
        return this;
    }


    public void request(PermissionCallback callback) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            if (callback != null) {
                callback.onGranted();
            }
            return;
        }

        PermissionActivity.setCallBack(callback);
        List<String> requestPermission = new ArrayList<>();

        if (permission != null) {
            for (int i = 0; i < permission.length; i++) {
                if (ContextCompat.checkSelfPermission(context, permission[i])
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermission.add(permission[i]);
                }
            }
        }

        if (requestPermission.size() == 0) {
            if (callback != null) {
                callback.onGranted();
            }
            return;
        }
        Intent intent = new Intent();
        intent.setClass(context, PermissionActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("permission", permission);
        context.startActivity(intent);

        context = null;
    }

    public interface PermissionCallback {
        void onGranted();

        void onDenied(List<String> permissionList);
    }


}
