package kr.co.wowsoft;

import kr.co.wowsoft.config.WebRTCConfig;
import kr.co.wowsoft.fragment.MainMenuFragment;
import kr.co.wowsoft.fragment.VideoViewFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;

public class VideoCallActvity extends FragmentActivity {

	private ImageView mImgSwipe;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Fragment  fragment = getSupportFragmentManager().findFragmentById(R.id.lay_main_fragement);
		if(fragment == null) {
			VideoViewFragment video = new VideoViewFragment();
			getSupportFragmentManager().beginTransaction()
			.add(R.id.lay_main_fragement, video)
			.commit();
		}
		
		fragment = getSupportFragmentManager().findFragmentById(R.id.lay_main_menu_fragment);
		if (fragment == null) {
			MainMenuFragment menu = new MainMenuFragment(WebRTCConfig.VIEW_TYPE_VIDO_CALL);
			getSupportFragmentManager().beginTransaction()
				.add(R.id.lay_main_menu_fragment, menu)
				.commit();
		}
		
		mImgSwipe = (ImageView)findViewById(R.id.img_main_swipe);
		mImgSwipe.setVisibility(View.GONE);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent intent = new Intent(VideoCallActvity.this, MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP);
		startActivity(intent);
		overridePendingTransition(R.anim.animation_left_in, R.anim.animation_right_out);
	}

}
