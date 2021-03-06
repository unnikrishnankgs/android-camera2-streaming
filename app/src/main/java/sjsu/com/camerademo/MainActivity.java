package sjsu.com.camerademo;

import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.Context;
import android.util.Log;
import android.hardware.Camera;
import android.widget.Button;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    private static String TAG_MA = "TAG_MainActivity";
    private boolean mCameraAvailable;
    private Camera mCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    // Example of a call to a native method
    TextView tv = (TextView) findViewById(R.id.sample_text);
    tv.setText(stringFromJNI());

        if(mCameraAvailable = checkCameraHardware(this.getApplicationContext()))
        {
            Log.v(TAG_MA, "Device has a camera!!");

        }
        else
        {
            Log.v(TAG_MA, "Device does not have a camera!!");
        }

        Button button_open_cam = (Button)findViewById(R.id.button_open_cam);
        button_open_cam.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openCamera();
            }
        });
    }

    public void openCamera()
    {
        Intent intent = new Intent(this, CameraActivity.class);
        startActivity(intent);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    private boolean checkCameraHardware(Context context) {
        if(context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA))
        {
            return true;
        }
        else
        {
            return false;
        }
    }


}
