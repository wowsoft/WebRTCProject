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
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainMenuFragment extends Fragment {
	private final String TAG = "MainMenuFragment";
	
	private Button mBtnDeclaration;
	private Button mBtnStop;
	private FrameLayout mLayMySurface;
	
	private int mViewType;
	
	private Camera mCamera;
	private static int mCameraId;
	private int mRotation;
	private CameraPreview mPreview;
	
	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	public MainMenuFragment(int viewType) {
		this.mViewType = viewType;
	}
	
	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_mainmenu, container, false);
	        
	        mBtnDeclaration = (Button)rootView.findViewById(R.id.btn_main_declaration);
	        mBtnDeclaration.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Toast.makeText(getActivity(), "mBtnDeclaration click", Toast.LENGTH_SHORT).show();
				}
			});
	        
	        mBtnStop = (Button)rootView.findViewById(R.id.btn_main_stop);
	        mLayMySurface = (FrameLayout)rootView.findViewById(R.id.lay_main_my_surface);
	        
	        if(mViewType == 0) {
		        mBtnStop.setVisibility(View.GONE);
		        mLayMySurface.setVisibility(View.GONE);
	        } else if(mViewType == 1) {
	        	mBtnStop.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						getActivity().onBackPressed();
					}
				});
	        	
	        	// 카메라 설정
	        	mCamera = getCameraInstance();
	        	mRotation = getActivity().getWindowManager()
	    				.getDefaultDisplay()
	    				.getRotation();
	        	mPreview = new CameraPreview(getActivity(), mCamera, mCameraId, mRotation);
	        	mPreview.setZOrderMediaOverlay(true);
	        	mLayMySurface.addView(mPreview);
	        }
	    
		
		return rootView;
	}
	
	
	
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
			mLayMySurface.addView(mPreview);	
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
