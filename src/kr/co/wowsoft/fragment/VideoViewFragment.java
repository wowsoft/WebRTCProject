package kr.co.wowsoft.fragment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import kr.co.wowsoft.R;
import kr.co.wowsoft.component.CameraPreview;
import kr.co.wowsoft.component.VideoPreview;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.FrameLayout;

public class VideoViewFragment extends Fragment {
	private final String TAG = "VideoViewFragment";
	
	private Camera mCamera;
	private CameraPreview mPreview;
	private FrameLayout mLayer;
	private static int mCameraId;
	private int mRotation;
	
	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_surfaceview, container, false);

		mCamera = getCameraInstance();

		mRotation = getActivity().getWindowManager()
				.getDefaultDisplay()
				.getRotation();
		
		mPreview = new CameraPreview(getActivity(), mCamera, mCameraId, mRotation);
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
			
			mPreview = new CameraPreview(getActivity(), mCamera, mCameraId, mRotation);
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
				if (info.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
					mCameraId = idx;
					c = Camera.open(idx);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// Camera is not available (in use or does not exist)
		}
		return c;
	}

}
