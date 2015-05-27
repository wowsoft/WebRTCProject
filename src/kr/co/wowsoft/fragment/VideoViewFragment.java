package kr.co.wowsoft.fragment;

import kr.co.wowsoft.R;
import kr.co.wowsoft.component.VideoPreview;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class VideoViewFragment extends Fragment {
	private final String TAG = "VideoViewFragment";
	
	private VideoPreview mPreview;
	private FrameLayout mLayer;
	
	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_surfaceview, container, false);

		mPreview = new VideoPreview(getActivity());
		mLayer = (FrameLayout) rootView.findViewById(R.id.lay_main_camera_preview);
		mLayer.addView(mPreview);
		
    	return rootView;
	}
	
	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
	}
}
