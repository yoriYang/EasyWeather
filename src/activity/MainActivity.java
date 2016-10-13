package activity;

import java.util.ArrayList;

import view.myScrollView;
import com.example.juhedemo.R;

import db.CityListDatabase;
import fragment.City;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

	public static final int SHOW_RESPONSE = 0;
	public static final String DEF_CHATSET = "UTF-8";
	public static final int DEF_CONN_TIMEOUT = 30000;
	public static final int DEF_READ_TIMEOUT = 30000;
	public static String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
	// 配置您申请的KEY
	public static final String APPKEY = "8cbfb673eaf00f29fdbd669821d8fe36";

	ArrayList<String> dayTemperatureList = new ArrayList<String>();
	ArrayList<String> nightTemperatureList = new ArrayList<String>();
	ArrayList<String> weeklist = new ArrayList<String>();
	ArrayList<String> citylist = new ArrayList<String>();
	private CityListDatabase dbHelper;
	public static Toolbar mToolbar;
	private ActionBarDrawerToggle mDrawerToggle;
	public static String name;
	public static SharedPreferences.Editor editor;
	private int theme = 0;
	private myScrollView scrollView1;
	private TextView toptextview;
	private int fadingHeight = 600;
	private Drawable drawable;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
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
		// this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		dbHelper = new CityListDatabase(this, "CityList.db", null, 2);
		dbHelper.getWritableDatabase();

		mToolbar = (Toolbar) findViewById(R.id.toolbar);
		mToolbar.setTitle("");// 标题的文字需在setSupportActionBar之前，不然会无效
		mToolbar.getBackground().setAlpha(255);
		this.setSupportActionBar(mToolbar);
		getSupportActionBar().setHomeButtonEnabled(true); // 设置返回键可用
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		mDrawerToggle = new ActionBarDrawerToggle(this, City.mDrawerLayout,
				mToolbar, R.string.drawer_open, R.string.drawer_close) {
			@Override
			public void onDrawerSlide(View drawerView, float slideOffset) {
				// TODO Auto-generated method stub
				super.onDrawerSlide(drawerView, slideOffset);
				toptextview.setVisibility(View.GONE);
			}
		};
		mDrawerToggle.syncState();
		City.mDrawerLayout.addDrawerListener(mDrawerToggle);

		if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
			// 透明状态栏
			getWindow().addFlags(
					WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			// 透明导航栏
			getWindow().addFlags(
					WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
		}
		editor = getSharedPreferences("JsonData", MODE_PRIVATE).edit();
		SharedPreferences pref = getSharedPreferences("JsonData", MODE_PRIVATE);
		name = pref.getString("jsonData", "");

		toptextview = (TextView) findViewById(R.id.toptextview);
		drawable = getResources().getDrawable(R.color.button_material_light);
		drawable.setAlpha(0);
		toptextview.setBackgroundDrawable(drawable);
		scrollView1 = (myScrollView) findViewById(R.id.scrollView1);
		// toptextview.setTextColor(drawable);

		scrollView1
				.setScrollViewListener(new myScrollView.ScrollViewListener() {
					@Override
					public void onScrollChanged(myScrollView scrollView, int x,
							int y, int oldx, int oldy) {
						if (y > 0) {
							toptextview.setVisibility(View.VISIBLE);
						}

						if (y > fadingHeight) {
							y = fadingHeight; // 当滑动到指定位置之后设置颜色为纯色，之前的话要渐变---实现下面的公式即可
						}
						drawable.setAlpha(y * (255) / fadingHeight);
						toptextview.setTextColor(Color.argb(y * (255)
								/ fadingHeight, 40, 40, 40));
						// 通知标题栏刷新显示
						toptextview.invalidate();
					}

				});

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			moveTaskToBack(false);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
