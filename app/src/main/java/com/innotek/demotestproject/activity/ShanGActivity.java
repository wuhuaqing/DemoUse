package com.innotek.demotestproject.activity;

import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.innotek.demotestproject.R;


/**
 * Created by admin on 2017/12/11.
 */

public class ShanGActivity extends BaseActivity {

    private Button btn_close;
    private Button btn_open;
    private Camera m_Camera;
    private CameraManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shan_gua_d);

        btn_open = (Button) findViewById(R.id.btn_open);
        btn_close = (Button) findViewById(R.id.btn_close);

        manager = (CameraManager) getSystemService(CAMERA_SERVICE);

        btn_open.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {

                lightSwitch(true);

            }
        });
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lightSwitch(false);
            }
        });
    }
    /**
     * 手电筒控制方法
     *
     * @param lightStatus
     * @return
     */
    private void lightSwitch(final boolean lightStatus) {
        if (lightStatus) { // 关闭手电筒
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                try {
                    manager.setTorchMode("0", false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                if (m_Camera != null) {
                    m_Camera.stopPreview();
                    m_Camera.release();
                    m_Camera = null;
                }
            }
        } else { // 打开手电筒
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                try {
                    manager.setTorchMode("0", true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                final PackageManager pm = getPackageManager();
                final FeatureInfo[] features = pm.getSystemAvailableFeatures();
                for (final FeatureInfo f : features) {
                    if (PackageManager.FEATURE_CAMERA_FLASH.equals(f.name)) { // 判断设备是否支持闪光灯
                        if (null == m_Camera) {
                            m_Camera = Camera.open();
                        }
                        final Camera.Parameters parameters = m_Camera.getParameters();
                        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                        m_Camera.setParameters(parameters);
                        m_Camera.startPreview();
                    }
                }
            }
        }
    }

    public void openLi(){
        try {
            m_Camera = Camera.open(0);
            m_Camera.startPreview();
            Camera.Parameters parameters = m_Camera.getParameters();
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            m_Camera.setParameters(parameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeLi(){
        try {
            Camera.Parameters parameters = m_Camera.getParameters();
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            m_Camera.setParameters(parameters);
            m_Camera.release();
            m_Camera = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
