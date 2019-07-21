# HandLibrary
这是一个手势密码库，可直接依赖，替换MainActivity和strings文件，里面有相关字符，也可以自定义进入手势页面，通过onActivityResult返回主界面。

步骤如下：
1.allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
2.implementation 'com.github.GitHubwbb123:HandLibrary:v1.0.0'
3.MainActivity.java
  package com.wxb.handlock;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.wxb.handlibrary.PatternLockActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.patternlock_setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,PatternLockActivity.class);
                intent.putExtra("type","setting");
                startActivityForResult(intent,100);//设置的回调,目标活动只能有一个请求码，请求码是活动的标志
            }
        });
        findViewById(R.id.patternlock_open).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,PatternLockActivity.class);
                intent.putExtra("type","open");
                startActivityForResult(intent,100);//验证回调
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case 100://PatternLockActivity界面数据返回，result=2，设置成功。result=1，验证成功。
                if(resultCode==RESULT_OK){
                    Integer result=data.getIntExtra("result",-1);
                    if(result==1)
                    {
                        Toast.makeText(MainActivity.this,result.toString(),Toast.LENGTH_SHORT).show();
                    }
                    else if(result==2)
                    {
                        Toast.makeText(MainActivity.this,result.toString(),Toast.LENGTH_SHORT).show();
                    }
                }
                break;

        }
    }
}
4.activity_main.xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.wxb.handlock.MainActivity">

    <TextView
        android:id="@+id/patternlock_setting"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:gravity="center"
        android:padding="20dp"
        android:text="@string/setting"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        android:layout_marginLeft="8dp"
        android:layout_marginRight="8dp"
        app:layout_constraintVertical_bias="0.0"
        android:layout_marginTop="8dp"
        app:layout_constraintHorizontal_bias="0.0" />
    <TextView
        android:id="@+id/patternlock_open"
        android:layout_width="0dp"
        android:layout_height="57dp"
        android:gravity="center"
        android:padding="20dp"
        android:text="@string/open"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        android:layout_marginLeft="8dp"
        android:layout_marginRight="8dp"
        app:layout_constraintVertical_bias="0.0"
        app:layout_constraintHorizontal_bias="0.0"
        android:layout_marginTop="0dp"
        app:layout_constraintTop_toBottomOf="@+id/patternlock_setting" />

</android.support.constraint.ConstraintLayout>
5.strings.xml
<resources>
    <string name="app_name">HandLock</string>
    <string name="setting">设置</string>
    <string name="open">解锁</string>
</resources>
搞定！！！

注意修改包名！！切记
