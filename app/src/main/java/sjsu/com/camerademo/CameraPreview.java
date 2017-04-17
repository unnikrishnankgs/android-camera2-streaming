package sjsu.com.camerademo;

import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.hardware.Camera;
import android.content.Context;
import android.util.Log;

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
    private Camera mCamera;
    private SurfaceHolder mSurfaceHolder;

    private String TAG_CP = "TAG_CameraPreview";

    public CameraPreview(Context context, Camera camera) {
        super(context);

        mCamera = camera;
        mSurfaceHolder = getHolder();
        //We get notified when the underlying Surface get altered
        mSurfaceHolder.addCallback(this);

        //mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

    }

    public void surfaceCreated(SurfaceHolder holder) {
        try {
            //set the surface to be used for live display
            mCamera.setPreviewDisplay(holder);
            mCamera.startPreview();
        } catch (Exception e) {
            Log.v(TAG_CP, "Exception " + e);
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        if(mSurfaceHolder.getSurface() == null) {
            Log.e(TAG_CP, "No surface available");
            return;
        }
        //restart the preview
        try {
            mCamera.stopPreview();
            mCamera.setPreviewDisplay(holder);
            mCamera.startPreview();
        } catch (Exception e) {
            Log.d(TAG_CP, "Exception while restarting preview" + e);
        }
    }
}
