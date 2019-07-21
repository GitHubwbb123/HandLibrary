package com.wxb.handlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 图案解锁
 * Created by sgffsg on 17/4/24.
 */

public class PatternLockActivity extends AppCompatActivity{

    private PatternLockView patternLockView;
    private TextView tvTip;
    private String password;
    private String type;
    private SPUtils spUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pattern_lock);
        patternLockView= (PatternLockView) findViewById(R.id.patternlockview);
        tvTip= (TextView) findViewById(R.id.tv_tip);
        spUtils=new SPUtils(this,"patternPSW");

        type=getIntent().getStringExtra("type");
        if ("open".equals(type)){
            String psw=spUtils.getString("password");
            if (!TextUtils.isEmpty(psw)&&psw.length()>3){
                patternLockView.setPassword(psw);//验证密码
            }else {
                patternLockView.setIsSetting(true);//进入手势设置状态
            }
        }else {
            patternLockView.setIsSetting(true);
        }
        tvTip.setText("请输入手势");//不管是设置还是验证都提示输入手势
        //这是输入完成的回调
        patternLockView.setPatternViewListener(new PatternViewListener() {
            @Override
            public void onSuccess() {//密码正确
                Toast.makeText(PatternLockActivity.this,"成功",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent();
                intent.putExtra("result",1);//密码正确
                setResult(RESULT_OK,intent);
                finish();
            }

            @Override
            public void onDraw(String psw) {
                if (!TextUtils.isEmpty(psw)){//这手是手势弹起后的回调，手离开屏幕就回调
                    if (psw.length()<4){
                        Toast.makeText(PatternLockActivity.this,"最少连接四个点",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(password)){//第一次输入手势
                        password=psw;
                        tvTip.setText("再次输入手势");
                    }else {
                        if (password.equals(psw)){//两次密码一致
                            tvTip.setText("设置成功");
                            spUtils.put("password",password);//写入内存
                            Intent intent=new Intent();
                            intent.putExtra("result",2);//设置成功
                            setResult(RESULT_OK,intent);
                            finish();
                           // onBackPressed();
                        }else {
                            password="";
                            tvTip.setText("两次手势不一致");
                        }
                    }
                }
            }

            @Override
            public void onError() {
                Toast.makeText(PatternLockActivity.this,"手势密码错误，还可以输入四次",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
