package activity;

import com.thinkland.sdk.android.JuheSDKInitializer;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
	private static Context ctx;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		JuheSDKInitializer.initialize(getApplicationContext());
	}

	public static Context getCtx() {
		return ctx;
	}

}
