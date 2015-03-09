package in.websnoox.tappTablet.util;

import android.os.Handler;
import android.os.Message;

public class TimerHandler extends Handler 
{  
	Runnable mRunnable;
	
	public TimerHandler(Runnable aRunnable)
	{
		mRunnable = aRunnable;
	}
	
	@Override  
	public void handleMessage(Message msg) 
	{  
		mRunnable.run();
	}  

	public void sleep(long delayMillis) 
	{  
		this.removeMessages(0);  
		sendMessageDelayed(obtainMessage(0), delayMillis);  
	}  
};
