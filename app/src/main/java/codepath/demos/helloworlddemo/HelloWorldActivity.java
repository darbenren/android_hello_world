package com.example.rotatecam;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.FrameLayout;

public class MainActivity extends Activity implements SurfaceHolder.Callback {
    private Camera mCamera;
    private SurfaceView mSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSurfaceView = new SurfaceView(this);
        setContentView(mSurfaceView);
        mSurfaceView.getHolder().addCallback(this);
    }

    public void surfaceCreated(SurfaceHolder holder) {
        try {
            mCamera = Camera.open();
            // 核心功能：旋转预览画面 180 度
            mCamera.setDisplayOrientation(180); 
            mCamera.setPreviewDisplay(holder);
            mCamera.startPreview();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
        }
    }
    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {}
}
