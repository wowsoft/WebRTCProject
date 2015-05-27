package kr.co.wowsoft.component;

import java.io.IOException;

import kr.co.wowsoft.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class VideoPreview extends SurfaceView implements SurfaceHolder.Callback {

	public VideoPreview(Context context) {
		super(context);
		getHolder().addCallback(this);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap icon = BitmapFactory.decodeResource(getResources(),R.drawable.bg);
        canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(icon, 10, 10, new Paint());
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		
	}

	@SuppressLint("WrongCall") @Override
	public void surfaceCreated(SurfaceHolder holder) {
		Canvas canvas = null;
        try {
            canvas = holder.lockCanvas(null);
            synchronized (holder) {
                onDraw(canvas);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (canvas != null) {
                holder.unlockCanvasAndPost(canvas);
            }
        }
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}

}
