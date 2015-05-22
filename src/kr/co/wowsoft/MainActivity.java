package kr.co.wowsoft;

import kr.co.wowsoft.fragment.SurfaceViewFragment;
import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.Window;


public class MainActivity extends FragmentActivity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        ActionBar actionBar = getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000ff")));
        actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#550000ff")));
        
        setContentView(R.layout.activity_main);
        
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.lay_main_fragement);
        if(fragment == null) {
        	SurfaceViewFragment surface = new SurfaceViewFragment();
        	getSupportFragmentManager().beginTransaction()
        		.add(R.id.lay_main_fragement, surface)
        		.commit();
        }
    }
}
