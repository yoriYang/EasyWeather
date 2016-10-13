package activity;

import java.util.ArrayList;
import java.util.Calendar;

import receiver.AlarmReceiver;

import com.example.juhedemo.R;

import fragment.City;
import adapter.setcolor_adapter;
import adapter.settingListView_Adapter;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TimePicker;

public class Setting extends AppCompatActivity {
	public static String TAG;
	private Toolbar settingToolbar;
	private ListView setting_list;
	private GridView setcolor_gv;
	private TimePicker timepicker;
	private Button changeTheme;
	private Button set_reminder_time_button;
	private int theme = 0;

	@SuppressLint("InlinedApi")
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		if (savedInstanceState == null) {

			SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);

			int colorID = pref.getInt("colorId", 0);
			switch (colorID) {
			case 0:
				setTheme(R.style.AppTheme);
				break;
			case 1:
				setTheme(R.style.AppTheme2);
				break;
			case 2:
				setTheme(R.style.AppTheme3);
				break;
			case 3:
				setTheme(R.style.AppTheme4);
				break;
			case 4:
				setTheme(R.style.AppTheme5);
				break;
			case 5:
				setTheme(R.style.AppTheme6);
				break;
			}
		} else {
			theme = savedInstanceState.getInt("theme");
		}

		super.onCreate(savedInstanceState);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.setting);
		settingToolbar = (Toolbar) findViewById(R.id.setting_toolbar);
		settingToolbar.setTitle("设置");// 标题的文字需在setSupportActionBar之前，不然会无效
		this.setSupportActionBar(settingToolbar);
		getSupportActionBar().setHomeButtonEnabled(true); // 设置返回键可用
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		ArrayList<String> settinglist = new ArrayList<String>();
		settinglist.add("更新间隔");
		settinglist.add("天气提醒");
		settinglist.add("主题颜色");
		settinglist.add("检查更新");
		settinglist.add("仅WiFi下更新");
		setting_list = (ListView) findViewById(R.id.setting_listview);

		settingListView_Adapter settingadapter = new settingListView_Adapter(
				this, settinglist);
		setting_list.setAdapter(settingadapter);
		if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
			// 透明状态栏
			getWindow().addFlags(
					WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			// 透明导航栏
			getWindow().addFlags(
					WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
		}

		settingToolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
				Intent intent = new Intent(Setting.this, MainActivity.class);
				startActivity(intent);
			}
		});

		setting_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long arg3) {
				// TODO Auto-generated method stub
				switch (position) {
				case 1:
					showSetReminderTime_Pw(view);
					break;
				case 2:
					showSetColor_PW(view);
					break;
				}

			}

		});

	}

	protected void showSetReminderTime_Pw(View view) {
		// TODO Auto-generated method stub

		View contentView = LayoutInflater.from(this).inflate(
				R.layout.set_reminder_time, null);
		final PopupWindow popupWindow = new PopupWindow(contentView,
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
		new ColorDrawable(0x000000);
		// popupWindow.setBackgroundDrawable(R.drawable.ill);
		popupWindow.setTouchable(true);
		popupWindow.setOutsideTouchable(true);
		// 设置好参数之后再show
		popupWindow.showAtLocation(
				Setting.this.findViewById(R.id.setting_listview),
				Gravity.CENTER, 0, 0);
		timepicker = (TimePicker) contentView.findViewById(R.id.timepicker);

		popupWindow.setOnDismissListener(new poponDismissListener());
		backgroundAlpha(0.7f);

		set_reminder_time_button = (Button) contentView
				.findViewById(R.id.set_reminder_time_button);
		set_reminder_time_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				int hourOfDay = timepicker.getCurrentHour();
				int minute = timepicker.getCurrentMinute();// 6.0以下
				// int minute2=timepicker.getMinute();//6.0以上才能用
				Log.d("=================", "你选择的时间是: " + hourOfDay + "时"
						+ minute + "分");
				Intent intent = new Intent(Setting.this, AlarmReceiver.class);
				PendingIntent pendingIntent = PendingIntent.getBroadcast(
						Setting.this, 0, intent, 0);
				// 设置日历的时间，主要是让日历的年月日和当前同步
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(System.currentTimeMillis());
				// 设置日历的小时和分钟
				calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
				calendar.set(Calendar.MINUTE, minute);
				// 将秒和毫秒设置为0
				calendar.set(Calendar.SECOND, 0);
				calendar.set(Calendar.MILLISECOND, 0);
				AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
				// 设置闹钟
				// alarmManager.set(AlarmManager.RTC_WAKEUP,
				// calendar.getTimeInMillis(), pendingIntent);

				alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
						calendar.getTimeInMillis(), 24 * 3600 * 1000,
						pendingIntent);

				popupWindow.dismiss();
			}
		});

	}

	private void showSetColor_PW(View view) {

		// 一个自定义的布局，作为显示的内容
		View contentView = LayoutInflater.from(this).inflate(R.layout.setcolor,
				null);

		setcolor_gv = (GridView) contentView.findViewById(R.id.setcolor_gv);
		setcolor_adapter setcolor_adapter1 = new setcolor_adapter(this);
		setcolor_gv.setAdapter(setcolor_adapter1);

		PopupWindow popupWindow = new PopupWindow(contentView,
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
		popupWindow.setTouchable(true);
		popupWindow.setOutsideTouchable(true);
		// 设置好参数之后再show
		popupWindow.showAtLocation(
				Setting.this.findViewById(R.id.setting_listview),
				Gravity.CENTER, 0, 0);
		popupWindow.setOnDismissListener(new poponDismissListener());
		backgroundAlpha(0.7f);

	}

	public void backgroundAlpha(float bgAlpha) {
		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.alpha = bgAlpha; // 0.0-1.0
		getWindow().setAttributes(lp);
	}

	class poponDismissListener implements PopupWindow.OnDismissListener {
		@Override
		public void onDismiss() {
			// TODO Auto-generated method stub
			backgroundAlpha(1f);
		}
	}
}
