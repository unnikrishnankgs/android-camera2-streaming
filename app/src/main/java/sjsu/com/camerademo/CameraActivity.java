package sjsu.com.camerademo;

/**
 * Created by gotham on 08/04/17.
 */

import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

public class CameraActivity extends Activity{

    private static String TAG_CA = "TAG_CameraActivity";
    private static Camera mCamera;
    CameraPreview mPreview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        if(false) {
            Log.v(TAG_CA, "Opening camera");
            mCamera = getCameraInstance();
            mPreview = new CameraPreview(this, mCamera);

            FrameLayout fl = (FrameLayout) findViewById(R.id.camera_preview);
            fl.addView(mPreview);
        }

        CameraManager manager = (CameraManager)getSystemService(Context.CAMERA_SERVICE);

    }

    private static Camera getCameraInstance() {
        Camera c = null;
        try {
            c = Camera.open();
        } catch (Exception e) {
            Log.v(TAG_CA, "Exception in opening Camera instance " + e);
        }

        return c;
    }
}
