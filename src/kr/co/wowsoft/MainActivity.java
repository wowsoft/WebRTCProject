package kr.co.wowsoft;

import kr.co.wowsoft.component.ViewGestureListener;
import kr.co.wowsoft.config.WebRTCConfig;
import kr.co.wowsoft.fragment.MainMenuFragment;
import kr.co.wowsoft.fragment.SurfaceViewFragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends FragmentActivity {
	private final String TAG = "MainActivity";
	private GestureDetector mGesture;
	private boolean mDoubleBackToExitPressedOnce;
	private Handler mHandler = new Handler();
	

	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        
        setContentView(R.layout.activity_main);
        
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.lay_main_fragement);
        if(fragment == null) {
        	SurfaceViewFragment surface = new SurfaceViewFragment();
        	getSupportFragmentManager().beginTransaction()
        		.add(R.id.lay_main_fragement, surface)
        		.commit();
        }
        
        fragment = getSupportFragmentManager().findFragmentById(R.id.lay_main_menu_fragment);
        if(fragment == null) {
        	MainMenuFragment menu = new MainMenuFragment(WebRTCConfig.VIEW_TYPE_MAIN);
        	getSupportFragmentManager().beginTransaction()
    		.add(R.id.lay_main_menu_fragment, menu)
    		.commit();
        }
        
        mGesture = new GestureDetector(MainActivity.this, new ViewGestureListener(this));
       
    }
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return mGesture.onTouchEvent(event);
	}
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	getMenuInflater().inflate(R.menu.main, menu);
    	return true;
    }
    
    @Override
    public void onBackPressed() {
    	if(mDoubleBackToExitPressedOnce) {
    		super.onBackPressed();
    		return;
    	}
    	
    	mDoubleBackToExitPressedOnce = true;
    	Toast.makeText(this, "나가려면 BACK 버튼을 눌려주세요", Toast.LENGTH_SHORT).show();
    	mHandler.postDelayed(mRunnable, 2000);
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	if(mHandler != null) {
    		mHandler.removeCallbacks(mRunnable);
    	}
    }
    
    private final Runnable mRunnable = new Runnable() {
		@Override
		public void run() {
			mDoubleBackToExitPressedOnce = false;
		}
	};
    
}
