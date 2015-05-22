package kr.co.wowsoft;

import kr.co.wowsoft.fragment.SurfaceViewFragment;
import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.ContextMenu.ContextMenuInfo;


public class MainActivity extends FragmentActivity {
	
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
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	getMenuInflater().inflate(R.menu.main, menu);
    	return true;
    }
    
}
