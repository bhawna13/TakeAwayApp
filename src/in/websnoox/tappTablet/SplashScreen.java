package in.websnoox.tappTablet;

import in.websnoox.tappTablet.R;
import in.websnoox.tappTablet.util.TimerHandler;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class SplashScreen extends Activity{
	private boolean mTimerExpired = false;

	long WATING_TIME = 2000;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		setContentView(R.layout.splash_screen);
		TimerHandler mTimerHandler = new TimerHandler(mTimerTask);
		mTimerHandler.sleep(WATING_TIME);
	}
	
	private Runnable mTimerTask = new Runnable() {
		@Override
		public void run() {
			mTimerExpired = true;
			closeSplash();
		}
	};
	
	protected void closeSplash() {
		runOnUiThread(mCloseSplashRunnable);
	}
	
	private Runnable mCloseSplashRunnable = new Runnable() {
		@Override
		public void run() {
			if (!mTimerExpired) {
				return;
			}

			Intent i = new Intent(SplashScreen.this, LoginScreen.class);
			SplashScreen.this.startActivity(i);
			SplashScreen.this.finish();
		}
	};

}
