package kr.co.wowsoft.fragment;

import kr.co.wowsoft.R;
import kr.co.wowsoft.component.CameraPreview;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class SurfaceViewFragment extends Fragment {

	private final String TAG = "kr.co.wowsoft.fragment.SurfaceViewFragment";
	private Camera mCamera;
	private CameraPreview mPreview;
	private FrameLayout mLayer;
	private static int mCameraId;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_surfaceview, container, false);

		mCamera = getCameraInstance();

		int rotation = getActivity().getWindowManager().getDefaultDisplay().getRotation();
		mPreview = new CameraPreview(getActivity(), mCamera, mCameraId, rotation);
		mLayer = (FrameLayout) rootView.findViewById(R.id.lay_main_camera_preview);
		mLayer.addView(mPreview);

		return rootView;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
		if(mCamera == null) {
			initializeCamera();
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		releaseCamera();
	}

	private void releaseCamera() {
		if (mCamera != null) {
			mCamera.release();
			mCamera = null;
		}
	}
	
	private void initializeCamera() {
		try {
			mPreview.getHolder().removeCallback(mPreview);
			mCamera = getCameraInstance();
			
			int rotation = getActivity().getWindowManager().getDefaultDisplay().getRotation();
			mPreview = new CameraPreview(getActivity(), mCamera, mCameraId, rotation);
			mLayer.addView(mPreview);	
		} catch (Exception e) {
			Log.d(TAG, "Error onResume camera preview: " + e.getMessage());
		}
	}

	@SuppressWarnings("deprecation")
	public static Camera getCameraInstance() {
		Camera c = null;
		try {
			CameraInfo info = new CameraInfo();
			int cameraCount = Camera.getNumberOfCameras();
			for (int idx = 0; idx < cameraCount; idx++) {
				Camera.getCameraInfo(idx, info);
				if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
					mCameraId = idx;
					c = Camera.open(idx);
					break;
				}
			}
		} catch (Exception e) {
			// Camera is not available (in use or does not exist)
		}
		return c;
	}

}
