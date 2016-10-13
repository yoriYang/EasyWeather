package fragment;

import java.io.File;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import utils.HttpUtil;
import utils.HttpUtil.HttpCallbackListener;
import utils.Utility;
import view.air_quality_index_view;
import view.main_pollutants_view;

import com.example.juhedemo.R;

import db.CityListDatabase;
import activity.ChooseCity;
import activity.Setting;
import adapter.LinearLayoutForListView;
import adapter.LvAdapter;
import adapter.gvadapter;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

public class City extends Fragment {

	private Context context;
	private TextView textview1;
	private TextView textview2;
	private TextView textview3;
	private TextView textview4;
	private TextView textview6;
	private TextView setting_tv;
	private TextView toptextview;
	public static TextView about_tv;
	private ListView listview2;
	private View mainLayout;
	private GridView gridview;
	public static DrawerLayout mDrawerLayout;
	private Button button5;
	private ImageView ImageView;
	private ImageButton deletebutton;
	private LinearLayout setting_layout;
	private LinearLayoutForListView mylinearlayout;
	public static SwipeRefreshLayout swipeLayout;
	private air_quality_index_view air_quality_index_view;
	private main_pollutants_view main_pollutants_view;
	public static final int SHOW_RESPONSE = 0;
	private static final int CITYLIST = 1;
	public static final String DEF_CHATSET = "UTF-8";
	public static final int DEF_CONN_TIMEOUT = 30000;
	public static final int DEF_READ_TIMEOUT = 30000;
	public static String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
	// 配置您申请的KEY
	public static final String APPKEY = "8cbfb673eaf00f29fdbd669821d8fe36";

	ArrayList<String> dayTemperatureList = new ArrayList<String>();
	ArrayList<String> nightTemperatureList = new ArrayList<String>();
	ArrayList<String> weeklist = new ArrayList<String>();
	ArrayList<String> weatherIdList = new ArrayList<String>();
	ArrayList<String> citylist = new ArrayList<String>();
	public static CityListDatabase dbHelper;
	private ScrollView scrollView1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		super.onCreate(savedInstanceState);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case 1:
			if (resultCode == Activity.RESULT_OK) {
				String returnedData = data.getStringExtra("citydata");
				dayTemperatureList.clear();
				nightTemperatureList.clear();
				weeklist.clear();
				weatherIdList.clear();

				SharedPreferences pref = getActivity().getSharedPreferences(
						"isdefault", context.MODE_PRIVATE);
				String name1 = pref.getString("yes", "");
				// 初始化侧边栏城市列表

				ArrayList<String> Citylist = new ArrayList<String>();
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				// 查询Book表中所有的数据
				Cursor cursor = db.query("Book", null, null, null, null, null,
						null);
				if (cursor.moveToFirst()) {
					do {
						// 遍历Cursor对象，取出数据并打印
						String cityname = cursor.getString(cursor
								.getColumnIndex("cityname"));
						Citylist.add(cityname);
					} while (cursor.moveToNext());
				}
				cursor.close();

				Bundle bundleCityData = new Bundle();
				if (name1.length() > 0) {
					bundleCityData.putString("isdefault", name1);
				}
				bundleCityData.putStringArrayList("CityList", Citylist);
				Message message = new Message();
				message.what = CITYLIST;
				message.obj = bundleCityData;
				handler.sendMessage(message);

				HttpUtil.getWeather(getActivity(), returnedData,
						new HttpCallbackListener() {

							@Override
							public void onFinish(String repsonse) {
								// TODO Auto-generated method stub
								JSONObject object;
								try {
									object = new JSONObject(repsonse);
									if (object.getInt("error_code") == 0) {
										System.out.println(object.get("result"));
										Utility.dayTemperatureList1.clear();
										Utility.nightTemperatureList1.clear();
										Utility.weeklist1.clear();
										Utility.weatherIdList1.clear();
										Utility.weatherinfo1.clear();
										Utility.wind1.clear();
										Message message = new Message();
										message.what = SHOW_RESPONSE;
										message.obj = Utility
												.parseJSONWithGSON(repsonse);
										handler.sendMessage(message);
									} else {
										System.out.println(object
												.get("error_code")
												+ ":"
												+ object.get("reason"));
									}
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							}

							@Override
							public void onError(Exception e) {
								// TODO Auto-generated method stub

							}

						});
			}
			break;
		default:
		}
	}

	private Handler handler = new Handler() {

		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SHOW_RESPONSE:
				Bundle bundle = (Bundle) msg.obj;

				String temperature = bundle.getString("Temperature") + "℃";
				String info = bundle.getString("info");
				String pm25n = bundle.getString("pm25");
				String city = bundle.getString("city_name");
				String time = bundle.getString("time");
				int pm25index = bundle.getInt("pm25index");
				int level = bundle.getInt("level");
				int curPm = bundle.getInt("curPm");
				ArrayList<String> week = bundle.getStringArrayList("week");
				ArrayList<String> daytt = bundle.getStringArrayList("dayt");
				ArrayList<String> nighttt = bundle.getStringArrayList("nightt");
				ArrayList<String> gvlist = bundle.getStringArrayList("gvlist");
				ArrayList<String> weatheridlist = bundle
						.getStringArrayList("weatherid");
				ArrayList<String> weatherinfolist = bundle
						.getStringArrayList("weatherinfo");
				ArrayList<String> windlist = bundle.getStringArrayList("wind");
				// 在这里进行UI操作，将结果显示到界面上
				toptextview.setText(city);
				textview1.setText(city);
				textview2.setText(temperature);
				textview3.setText(info);
				textview4.setText(pm25n);
				textview6.setText(time);
				LvAdapter lvadapter = new LvAdapter(getActivity(), daytt,
						nighttt, week, weatheridlist, weatherinfolist, windlist);
				mylinearlayout.setAdapter(lvadapter);
				mylinearlayout.bindLinearLayout();
				gvadapter gvAdapter = new gvadapter(getActivity(), gvlist);
				gridview.setAdapter(gvAdapter);
				addcity(city);
				air_quality_index_view.setPm25n(curPm);
				air_quality_index_view.setLevel(level);
				main_pollutants_view.setPmNumber(pm25index);
				main_pollutants_view.setLevel(level);
				break;
			case CITYLIST:
				Bundle bundle1 = (Bundle) msg.obj;
				citylist = bundle1.getStringArrayList("CityList");
				String name = bundle1.getString("isdefault");
				ChooseCityBA choosecityBA = new ChooseCityBA(getActivity(),
						citylist, name);
				listview2.setAdapter(choosecityBA);

				break;
			}
		}
	};

	public void addcity(String city) {
		int j = 0;
		if (citylist.size() == 0) {
			citylist.add(city);
		}
		for (int i = 0; i < citylist.size(); i++) {
			if (city.equals(citylist.get(i))) {
				j++;
			}
		}
		if (j == 0) {
			citylist.add(city);
		}
		ChooseCityBA choosecityBA = new ChooseCityBA(context, citylist);
		listview2.setAdapter(choosecityBA);
		Utility.createDatabase(city);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		mainLayout = inflater.inflate(R.layout.main, container, false);
		init();
		Utility.dayTemperatureList1.clear();
		Utility.nightTemperatureList1.clear();
		Utility.weeklist1.clear();
		Utility.weatherIdList1.clear();
		Utility.weatherinfo1.clear();
		Utility.wind1.clear();
		return mainLayout;
	}

	public void init() {
		// TODO Auto-generated method stub
		toptextview = (TextView) mainLayout.findViewById(R.id.toptextview);
		textview1 = (TextView) mainLayout.findViewById(R.id.textView1);
		textview2 = (TextView) mainLayout.findViewById(R.id.textView2);
		textview3 = (TextView) mainLayout.findViewById(R.id.textView3);
		textview4 = (TextView) mainLayout.findViewById(R.id.textView4);
		textview6 = (TextView) mainLayout.findViewById(R.id.textView6);
		mylinearlayout = (LinearLayoutForListView) mainLayout
				.findViewById(R.id.citylinearlayout);
		listview2 = (ListView) mainLayout.findViewById(R.id.listView2);
		listview2.setVerticalScrollBarEnabled(true);

		air_quality_index_view = (air_quality_index_view) mainLayout
				.findViewById(R.id.air_quality_index_view);
		main_pollutants_view = (main_pollutants_view) mainLayout
				.findViewById(R.id.main_pollutants_view);
		gridview = (GridView) mainLayout.findViewById(R.id.gview);
		mDrawerLayout = (DrawerLayout) mainLayout
				.findViewById(R.id.drawerlayout);
		button5 = (Button) mainLayout.findViewById(R.id.addcity_button);
		deletebutton = (ImageButton) mainLayout.findViewById(R.id.deletebutton);
		setting_layout = (LinearLayout) mainLayout
				.findViewById(R.id.setting_layout);
		setting_tv = (TextView) mainLayout.findViewById(R.id.setting_tv);
		setting_layout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(), Setting.class);
				startActivity(intent);
			}
		});

		about_tv = (TextView) mainLayout.findViewById(R.id.about_tv);
		ImageView = (ImageView) mainLayout.findViewById(R.id.dingwei);
		swipeLayout = (SwipeRefreshLayout) mainLayout
				.findViewById(R.id.swipeLayout);
		ChooseCityBA choosecityBA = new ChooseCityBA(context, citylist);
		listview2.setAdapter(choosecityBA);

		swipeLayout
				.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
					@Override
					public void onRefresh() {
						new Thread(new Runnable() {
							@Override
							public void run() {
								dayTemperatureList.clear();
								nightTemperatureList.clear();
								weeklist.clear();
								weatherIdList.clear();

								HttpUtil.getWeather(getActivity(), textview1
										.getText().toString(),
										new HttpCallbackListener() {

											@Override
											public void onFinish(String repsonse) {
												// TODO Auto-generated method
												// stub
												Utility.dayTemperatureList1
														.clear();
												Utility.nightTemperatureList1
														.clear();
												Utility.weeklist1.clear();
												Utility.weatherIdList1.clear();
												Utility.weatherinfo1.clear();
												Utility.wind1.clear();
												Message message = new Message();
												message.what = SHOW_RESPONSE;
												message.obj = Utility
														.parseJSONWithGSON(repsonse);
												handler.sendMessage(message);
												Utility.saveJson(context,
														textview1.getText()
																.toString(),
														repsonse);
											}

											@Override
											public void onError(Exception e) {
												// TODO Auto-generated method
												// stub

											}

										});

								swipeLayout.setRefreshing(false);
							}
						}).start();
					}
				});

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		super.onActivityCreated(savedInstanceState);
		dbHelper = new CityListDatabase(this.getActivity(), "CityList.db",
				null, 2);

		button5.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(), ChooseCity.class);
				startActivityForResult(intent, 1);

			}
		});

		// 获取首页天气信息

		SharedPreferences pref = getActivity().getSharedPreferences(
				"isdefault", context.MODE_PRIVATE);
		String name1 = pref.getString("yes", "");
		SharedPreferences pref1 = getActivity().getSharedPreferences(
				name1.toString(), context.MODE_PRIVATE);
		String aa = pref1.getString("city", "");

		if (aa.length() > 0) {
			Message message = new Message();
			message.what = SHOW_RESPONSE;
			message.obj = Utility.parseJSONWithGSON(aa);
			handler.sendMessage(message);
		} else {

			HttpUtil.getWeather(getActivity(), "北京",
					new HttpCallbackListener() {

						@Override
						public void onFinish(String repsonse) {
							// TODO Auto-generated method stub
							JSONObject object;
							try {
								object = new JSONObject(repsonse);
								if (object.getInt("error_code") == 0) {
									System.out.println(object.get("result"));
									Message message = new Message();
									message.what = SHOW_RESPONSE;
									message.obj = Utility
											.parseJSONWithGSON(repsonse);
									handler.sendMessage(message);
								} else {
									System.out.println(object.get("error_code")
											+ ":" + object.get("reason"));
								}
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

						@Override
						public void onError(Exception e) {
							// TODO Auto-generated method stub

						}

					});
		}

		// 初始化侧边栏城市列表

		ArrayList<String> Citylist = new ArrayList<String>();
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		// 查询Book表中所有的数据
		Cursor cursor = db.query("Book", null, null, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				// 遍历Cursor对象，取出数据并打印
				String cityname = cursor.getString(cursor
						.getColumnIndex("cityname"));
				Citylist.add(cityname);
			} while (cursor.moveToNext());
		}
		cursor.close();

		Bundle bundleCityData = new Bundle();
		if (name1.length() > 0) {
			bundleCityData.putString("isdefault", name1);
		}
		bundleCityData.putStringArrayList("CityList", Citylist);
		Message message = new Message();
		message.what = CITYLIST;
		message.obj = bundleCityData;
		handler.sendMessage(message);

	}

	// 侧边栏城市列表适配器
	class ChooseCityBA extends BaseAdapter {
		private Context context;
		private ArrayList<String> data;
		private LayoutInflater listContainer;
		private TextView text;
		private ImageView ImageView;
		private ImageButton deletebutton;
		private CityListDatabase dbHelper;
		private ArrayList<String> signlist;
		private String isdefault;

		public ChooseCityBA(Context context, ArrayList<String> data) {
			super();
			this.context = context;
			this.data = data;

		}

		public ChooseCityBA(Context context, ArrayList<String> data,
				String isdefault) {
			super();
			this.context = context;
			this.data = data;
			this.isdefault = isdefault;

		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return data.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return data.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@SuppressWarnings("deprecation")
		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			// TODO Auto-generated method stub
			final ArrayList<String> signlist1 = new ArrayList<String>();
			if (convertView == null) {
				listContainer = LayoutInflater.from(getActivity());
				convertView = listContainer.inflate(
						R.layout.choose_city_list_item, null);

				ImageView = (ImageView) convertView.findViewById(R.id.dingwei);
				text = (TextView) convertView.findViewById(R.id.cityname);
				text.setText(data.get(position));
				Log.d("data.get(position)", data.get(position));

				SharedPreferences pref = getActivity().getSharedPreferences(
						"isdefault", context.MODE_PRIVATE);
				String defaultCity = pref.getString("yes", "");
				if (defaultCity.length() > 0) {
					for (int i = 0; i < data.size(); i++) {
						if (data.get(position).equals(defaultCity)) {

							ImageView.setImageDrawable(getResources()
									.getDrawable(R.drawable.dingwei2));
						}
					}
				}

				ImageView.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						View listview2Item = listview2.getChildAt(position);
						ImageView ImageView = (ImageView) listview2Item
								.findViewById(R.id.dingwei);
						SharedPreferences pref = getActivity()
								.getSharedPreferences("isdefault",
										context.MODE_PRIVATE);
						String defaultCity = pref.getString("yes", "");
						if (defaultCity == null || defaultCity.length() <= 0) {
							ImageView.setImageDrawable(getResources()
									.getDrawable(R.drawable.dingwei2));
							text = (TextView) listview2Item
									.findViewById(R.id.cityname);
							SharedPreferences.Editor editor = getActivity()
									.getSharedPreferences("isdefault",
											context.MODE_PRIVATE).edit();
							editor.putString("yes", text.getText().toString());
							editor.commit();
						}
					}

				});

				if (data.size() > 1) {
					deletebutton = (ImageButton) convertView
							.findViewById(R.id.deletebutton);

					deletebutton.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							// 删除缓存的城市天气信息SP文件
							String DATA_URL = "/data/data/";
							File file = new File(DATA_URL
									+ getActivity().getPackageName().toString()
									+ "/shared_prefs", data.get(position)
									+ ".xml");
							if (file.exists()) {
								file.delete();
							}

							// 删除标记默认城市的SP文件
							SharedPreferences pref = getActivity()
									.getSharedPreferences("isdefault",
											context.MODE_PRIVATE);
							String name = pref.getString("yes", "");
							Log.d("----------------------", name);

							if (data.get(position).toString().equals(name)) {
								SharedPreferences settings = getActivity()
										.getSharedPreferences("isdefault",
												context.MODE_PRIVATE);
								SharedPreferences.Editor editor = settings
										.edit();
								editor.clear();
								editor.commit();
							}

							// 删除城市列表数据库中的城市
							dbHelper = new CityListDatabase(getActivity(),
									"CityList.db", null, 2);
							SQLiteDatabase db = dbHelper.getWritableDatabase();
							db.delete("Book", "cityname = ?",
									new String[] { data.get(position) });
							data.remove(position);

							ArrayList<String> Citylist = new ArrayList<String>();

							// 查询Book表中所有的数据
							SQLiteDatabase db1 = dbHelper.getWritableDatabase();
							Cursor cursor = db1.query("Book", null, null, null,
									null, null, null);
							if (cursor.moveToFirst()) {
								do {
									// 遍历Cursor对象，取出数据并打印
									String cityname = cursor.getString(cursor
											.getColumnIndex("cityname"));
									Citylist.add(cityname);
								} while (cursor.moveToNext());
							}
							cursor.close();
							Bundle bundleCityData = new Bundle();
							bundleCityData.putStringArrayList("CityList",
									Citylist);

							bundleCityData.putString("isdefault", name);
							Message message = new Message();
							message.what = CITYLIST;
							message.obj = bundleCityData;
							handler.sendMessage(message);

						}

					});
				} else {
					deletebutton = (ImageButton) convertView
							.findViewById(R.id.deletebutton);
					deletebutton.setVisibility(View.INVISIBLE);
				}

				text.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						mDrawerLayout.closeDrawers();

						SharedPreferences pref = getActivity()
								.getSharedPreferences(
										data.get(position).toString(),
										context.MODE_PRIVATE);
						Log.d("mDrawerLayout", data.get(position).toString());
						String aa = pref.getString("city", "");
						if (aa != null) {
							Utility.dayTemperatureList1.clear();
							Utility.nightTemperatureList1.clear();
							Utility.weeklist1.clear();
							Utility.weatherIdList1.clear();
							Utility.weatherinfo1.clear();
							Utility.wind1.clear();
							Message message = new Message();
							message.what = SHOW_RESPONSE;
							message.obj = Utility.parseJSONWithGSON(aa);
							handler.sendMessage(message);
						} else {

							HttpUtil.getWeather(getActivity(),
									data.get(position).toString(),
									new HttpCallbackListener() {
										@Override
										public void onFinish(
												final String repsonse) {
											// TODO
											// Auto-generated
											// method stub
											JSONObject object;

											try {
												object = new JSONObject(
														repsonse);

												System.out.println(object
														.get("result"));

												Utility.dayTemperatureList1
														.clear();
												Utility.nightTemperatureList1
														.clear();
												Utility.weeklist1.clear();
												Utility.weatherIdList1.clear();
												Utility.weatherinfo1.clear();
												Utility.wind1.clear();
												Message message = new Message();
												message.what = SHOW_RESPONSE;
												message.obj = Utility
														.parseJSONWithGSON(repsonse
																.toString());

												handler.sendMessage(message);

											} catch (JSONException e) {
												// TODO
												// Auto-generated
												// catch block
												e.printStackTrace();
											}
										}

										@Override
										public void onError(Exception e) {
											// TODO
											// Auto-generated
											// method stub

										}

									});
						}
					}
				});

			}

			return convertView;
		}

	}

}
