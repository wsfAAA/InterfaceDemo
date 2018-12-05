package test.cmcc.com.networkdemo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hjq.permissions.OnPermission;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;

import java.util.List;

import test.cmcc.com.networkdemo.R;
import test.cmcc.com.networkdemo.basevu.testrun.MvpMgBaseVuActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.mvp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MvpMgBaseVuActivity.class));
            }
        });
        findViewById(R.id.retrofit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XXPermissions.with(MainActivity.this)
                        .permission(Permission.READ_EXTERNAL_STORAGE,Permission.WRITE_EXTERNAL_STORAGE)
                        .request(new OnPermission() {

                            @Override
                            public void hasPermission(List<String> granted, boolean isAll) {
                                if (isAll) {
                                    startActivity(new Intent(MainActivity.this, RetrofitActivity.class));
                                }
                            }

                            @Override
                            public void noPermission(List<String> denied, boolean quick) {

                            }
                        });
            }
        });
        findViewById(R.id.retrofitandrxjava).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NetActivity.class));
            }
        });
    }
}
