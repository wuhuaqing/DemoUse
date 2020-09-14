package com.innotek.demotestproject.activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.innotek.demotestproject.R;
import com.innotek.demotestproject.activity_sdw.SPWDemoActivity;
import com.innotek.demotestproject.activity_view.activity.ShowViewActivity;
import com.innotek.demotestproject.activity_view.activity.ViewMainActivity;
import com.innotek.demotestproject.jetpack.JetpackMainActivity;
import com.weexcontainmoudle.baseview.BaseWeexActivity;

public class MainActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolBar();
        clickMethod();
    }
    public void setToolBar(){
        toolbar.setTitle("Main");
    }

    public void clickMethod(){
        findViewById(R.id.pickview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,PickViewActivity.class));
            }
        });
        findViewById(R.id.snowview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SnowActivity.class));
            }
        });
        findViewById(R.id.changeview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,GaintViewActivity.class));
            }
        });
      /*  findViewById(R.id.payboard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,GaintViewActivity.class));
            }
        });
        findViewById(R.id.payboard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,GaintViewActivity.class));
            }
        });*/

        /*findViewById(R.id.btn_kotlin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, KotlinMainActivity.class));
            }
        });
*/
        findViewById(R.id.btn_floatmenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FloatMenuActivity.class));
            }
        });
        findViewById(R.id.btn_imgloade).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ImageLoadeActivity.class));
            }
        });
        findViewById(R.id.btn_editsearch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SearchEditTextActivity2.class));
            }
        });
        findViewById(R.id.btn_zhihu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ZhihuDataActivity.class));
            }
        });
        findViewById(R.id.btn_greendao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, GreenDaoTestActivity.class));
            }
        });
        findViewById(R.id.btn_valueanimtor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ValueAnimotorActivity.class));
            }
        });
        findViewById(R.id.btn_viewdraghelper).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ViewDragHelperActivity.class));
            }
        });
        findViewById(R.id.viewshow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ShowViewActivity.class));
            }
        });
        findViewById(R.id.numberkeyboard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NumberKeyBoardActivity.class));
            }
        });
        findViewById(R.id.viewmain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ViewMainActivity.class));
            }
        });
        findViewById(R.id.sdw).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SPWDemoActivity.class));
            }
        });
        findViewById(R.id.carviewanimator).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CarAnimatorActivity.class));
            }
        });
        findViewById(R.id.cameraopen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ShanGActivity.class));
            }
        });
        findViewById(R.id.weex).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, BaseWeexActivity.class));
            }
        });
        findViewById(R.id.bottombar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, BottombarActivity.class));
            }
        });

        findViewById(R.id.camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CameraActivity.class));
            }
        });
        findViewById(R.id.jetpack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, JetpackMainActivity.class));
            }
        });


    }

}
