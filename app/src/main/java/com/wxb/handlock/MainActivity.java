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
