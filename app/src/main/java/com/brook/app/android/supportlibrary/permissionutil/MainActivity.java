package com.brook.app.android.supportlibrary.permissionutil;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.brook.app.android.permissionutil.PermissionUtil;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        PermissionUtil.with(this).permission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .request(new PermissionUtil.PermissionCallback() {
                    @Override
                    public void onGranted() {
                        Toast.makeText(MainActivity.this, "同意权限", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onDenied(List<String> permissionList) {
                        Toast.makeText(MainActivity.this, "拒绝权限", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
