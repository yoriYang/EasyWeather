package receiver;

import activity.AlarmActivity;
import activity.Setting;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent arg1) {
		// TODO Auto-generated method stub
		// Log.d(Setting.TAG, "the time is up,start the alarm...");
		Toast.makeText(context, "看看天气呗", Toast.LENGTH_LONG).show();
		Intent i = new Intent(context, AlarmActivity.class);
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		// City.swipeLayout.setRefreshing(ture, true);
		// City.mDrawerLayout.closeDrawers();
		context.startActivity(i);
	}

}
