package kr.co.wowsoft.component;

import kr.co.wowsoft.R;
import kr.co.wowsoft.VideoCallActvity;
import kr.co.wowsoft.config.WebRTCConfig;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;

public class ViewGestureListener implements OnGestureListener {
	private final String TAG = "ViewGestureListener";
	private static final int SWIPE_MIN_DISTANCE = 120;
	private static final int SWIPE_MAX_OFF_PATH = 250;
	private static final int SWIPE_THRESHOLD_VELOCITY = 200;
	private Activity mActivity;

	public ViewGestureListener(Activity activity) {
		this.mActivity = activity;
	}
	
	@Override
	public boolean onDown(MotionEvent e) {
//		Log.i(TAG, "onDown");
		return true;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
		try {
			if(Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
				return  false;
			
			 // right to left swipe
            if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
            	Intent intent = new Intent(mActivity, VideoCallActvity.class);
            	intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT|Intent.FLAG_ACTIVITY_SINGLE_TOP);
            	mActivity.startActivity(intent);
            	mActivity.overridePendingTransition(R.anim.animation_right_in, R.anim.animation_left_out);
            }
			
		} catch (Exception e) {
			Log.e(TAG, "onFling", e);
		}
		return true;
	}

	@Override
	public void onLongPress(MotionEvent e) {
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return true;
	}

}
