package kr.co.wowsoft.component;

import java.io.IOException;

import kr.co.wowsoft.R;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class VideoPreview extends SurfaceView implements SurfaceHolder.Callback {

	private SurfaceHolder mHolder;
	private MediaPlayer mMediaPlayer;
	private Context mContext;
	
	public VideoPreview(Context context, MediaPlayer media) {
		super(context);
		
		this.mContext = context;
		this.mMediaPlayer = media;
		
		mHolder = getHolder();
		mHolder.addCallback(this);
		mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		mMediaPlayer.setDisplay(mHolder);
		try {
			mMediaPlayer.setDataSource("android.resource://kr.co.wowsoft/" + R.raw.sample_mpeg4);
			mMediaPlayer.prepare();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mMediaPlayer.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}

}
