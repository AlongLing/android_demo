package com.along.permissiontest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.permissionx.guolindev.PermissionX;
import com.permissionx.guolindev.callback.ExplainReasonCallbackWithBeforeParam;
import com.permissionx.guolindev.callback.ForwardToSettingsCallback;
import com.permissionx.guolindev.callback.RequestCallback;
import com.permissionx.guolindev.request.ExplainScope;
import com.permissionx.guolindev.request.ForwardScope;

import java.util.ArrayList;
import java.util.List;

public class JavaActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mJavaBtnOne;
    private Button mJavaBtnTwo;
    private AlertDialog mPermissionDialog;

    private String[] mPermissions = new String[]{Manifest.permission.READ_CONTACTS,
                            Manifest.permission.CAMERA, Manifest.permission.CALL_PHONE};

    private List<String> mDeniedPermissions = new ArrayList<>();  // 用于存储未被授予的权限

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java);
        initView();
        initListener();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.javaBtnOne:
                requestPermissionByAndroid();
                break;
            case R.id.javaBtnTwo:
                requestPermissionByPermissionX();
                break;
            default:
                // do nothing.
                break;
        }
    }

    /*@Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean hasPermissionDismiss = false;//有权限没有通过
        switch (requestCode) {
            case 1:
                for (int result : grantResults) {
                    if (result == PackageManager.PERMISSION_DENIED) {
                        hasPermissionDismiss = true;  // 表示有权限被拒绝了
                        break;
                    }
                }
                break;
            default:
                break;
        }
        if (hasPermissionDismiss) {
            showPermissionDialog();
        } else {
            Toast.makeText(this, "恭喜你，已获得所有权限", Toast.LENGTH_SHORT).show();
        }
    }*/

    private void initView() {
        mJavaBtnOne = findViewById(R.id.javaBtnOne);
        mJavaBtnTwo = findViewById(R.id.javaBtnTwo);
    }

    private void initListener() {
        mJavaBtnOne.setOnClickListener(this);
        mJavaBtnTwo.setOnClickListener(this);
    }

    // 使用 android 原生写法去动态请求权限
    private void requestPermissionByAndroid() {
        mDeniedPermissions.clear();  // 首先清空未被授予的权限
        for (int i = 0; i < mPermissions.length; i++) {
            if (ContextCompat.checkSelfPermission(JavaActivity.this, mPermissions[i]) != PackageManager.PERMISSION_GRANTED) {
                mDeniedPermissions.add(mPermissions[i]);
            }
        }
        // 开始申请权限
        if (mDeniedPermissions.size() > 0) {
            ActivityCompat.requestPermissions(JavaActivity.this, mDeniedPermissions.toArray(new String[mDeniedPermissions.size()]), 1);
        } else {
            Toast.makeText(this, "恭喜你，已获得所有权限", Toast.LENGTH_SHORT).show();
        }
    }

    // 使用 PermissionX 库去动态请求权限
    private void requestPermissionByPermissionX() {
        PermissionX.init(this)
                .permissions(mPermissions)
                .explainReasonBeforeRequest()
                .onExplainRequestReason(new ExplainReasonCallbackWithBeforeParam() {
                    @Override
                    public void onExplainReason(ExplainScope scope, List<String> deniedList, boolean beforeRequest) {
                        if (beforeRequest) {
                            scope.showRequestReasonDialog(deniedList, "应用需要使用以下权限:\n" + deniedList, "我已明白");
                        } else {
                            scope.showRequestReasonDialog(deniedList, "即将申请的权限是应用必须要使用的", "确定");
                        }
                    }
                })
                .onForwardToSettings(new ForwardToSettingsCallback() {
                    @Override
                    public void onForwardToSettings(ForwardScope scope, List<String> deniedList) {
                        scope.showForwardToSettingsDialog(deniedList, "需要去应用程序设置当中手动开启权限", "确定");
                    }
                })
                .request(new RequestCallback() {
                    @Override
                    public void onResult(boolean allGranted, List<String> grantedList, List<String> deniedList) {
                        if (allGranted) {
                            Toast.makeText(JavaActivity.this, "恭喜你，已获得所有权限", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(JavaActivity.this, "你拒绝了如下权限： " + deniedList, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /*// 当权限被拒绝的时候，显示对话框提示用户打开系统设置手动开启权限
    private void showPermissionDialog() {
        mPermissionDialog = new AlertDialog.Builder(this)
                .setMessage("已禁用权限，请手动授予")
                .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cancelPermissionDialog();
                        Uri packageURI = Uri.parse("package:" + "com.along.permissiontest");
                        Intent intent = new Intent(Settings.
                                ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cancelPermissionDialog();
                    }
                }).create();
        mPermissionDialog.show();
    }

    // 隐藏对话框
    private void cancelPermissionDialog() {
        mPermissionDialog.cancel();
    }*/
}