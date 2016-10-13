package utils;

import java.util.ArrayList;

import jsonBean.JsonBean;

import org.json.JSONObject;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import fragment.City;

public class Utility {
	public static ArrayList<String> dayTemperatureList1 = new ArrayList<String>();
	public static ArrayList<String> nightTemperatureList1 = new ArrayList<String>();
	public static ArrayList<String> weeklist1 = new ArrayList<String>();
	public static ArrayList<String> weatherIdList1 = new ArrayList<String>();
	public static ArrayList<String> citylist1 = new ArrayList<String>();
	public static ArrayList<String> weatherinfo1 = new ArrayList<String>();
	public static ArrayList<String> wind1 = new ArrayList<String>();

	// 缓存json文件
	public static void saveJson(Context context, String cityname,
			String jsonData) {
		// MainActivity.editor.putString(cityname, jsonData);
		// MainActivity.editor.commit();
		SharedPreferences.Editor editor = context.getSharedPreferences(
				cityname, context.MODE_PRIVATE).edit();
		editor.putString("city", jsonData);
		editor.commit();
	}

	// 建立city数据库
	public static void createDatabase(String city) {
		SQLiteDatabase db = City.dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		// 开始组装数据
		if (judgeCityData(city) == 0) {
			values.put("cityname", city);
			db.insert("Book", null, values);
			values.clear();
			Log.d("MainActivity", "建立数据库 ");
		}
	}

	// 判断Book表中有无城市
	public static int judgeCityData(String city) {
		int j = 0;
		ArrayList<String> citylist1 = new ArrayList<String>();
		SQLiteDatabase db = City.dbHelper.getWritableDatabase();
		Cursor cursor = db.query("Book", null, null, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				// 遍历Cursor对象
				String cityname = cursor.getString(cursor
						.getColumnIndex("cityname"));

				citylist1.add(cityname);
			} while (cursor.moveToNext());
		}

		for (int i = 0; i < citylist1.size(); i++) {

			if (citylist1.get(i).equals(city)) {
				j++;
			}
		}
		cursor.close();
		return j;
	}

	// 查询Book表中所有的数据
	public void queryCityData() {
		SQLiteDatabase db = City.dbHelper.getWritableDatabase();
		Cursor cursor = db.query("Book", null, null, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				// 遍历Cursor对象，取出数据并打印
				String cityname = cursor.getString(cursor
						.getColumnIndex("cityname"));
				Log.d("queryCityData", "db name: " + cityname);
			} while (cursor.moveToNext());
		}
		cursor.close();

	}

	// 解析
	public static Bundle parseJSONWithGSON(String jsonData) {
		// TODO Auto-generated method stub
		Bundle bundleData = new Bundle();
		try {

			// jsonobject解析pm2.5
			JSONObject jsonObject = new JSONObject(jsonData);
			String result = jsonObject.getString("result");
			JSONObject jsonObject1 = new JSONObject(result);
			String data = jsonObject1.getString("data");
			JSONObject jsonObject2 = new JSONObject(data);
			String pm25 = jsonObject2.getString("pm25");
			JSONObject jsonObject3 = new JSONObject(pm25);
			String pm251 = jsonObject3.getString("pm25");
			JSONObject jsonObject4 = new JSONObject(pm251);
			String pm252 = jsonObject4.getString("quality");
			int curPm = jsonObject4.getInt("curPm");
			int pm25index = jsonObject4.getInt("pm25");
			int level = jsonObject4.getInt("level");

			Gson gson = new Gson();
			JsonBean jsonbean = gson.fromJson(jsonData, JsonBean.class);

			int temperature = jsonbean.getResult().getData().getRealtime()
					.getWeather().getTemperature();
			String t = Integer.toString(temperature);
			String i = jsonbean.getResult().getData().getRealtime()
					.getWeather().getInfo();
			String city = jsonbean.getResult().getData().getRealtime()
					.getCity_name();
			String time = jsonbean.getResult().getData().getRealtime()
					.getTime();

			for (int m = 0; m <= 4; m++) {
				String dayTemperature = jsonbean.getResult().getData()
						.getWeather().get(m).getInfo().getDay().get(2)
						.toString();
				String nightTemperature = jsonbean.getResult().getData()
						.getWeather().get(m).getInfo().getNight().get(2)
						.toString();
				String week = jsonbean.getResult().getData().getWeather()
						.get(m).getWeek().toString();
				String weatherId = jsonbean.getResult().getData().getWeather()
						.get(m).getInfo().getDay().get(0).toString();
				String weatherinfo = jsonbean.getResult().getData()
						.getWeather().get(m).getInfo().getDay().get(1)
						.toString();
				String wind = jsonbean.getResult().getData().getWeather()
						.get(m).getInfo().getDay().get(4).toString();
				dayTemperatureList1.add(dayTemperature);
				nightTemperatureList1.add(nightTemperature);
				weeklist1.add(week);
				weatherIdList1.add(weatherId);
				weatherinfo1.add(weatherinfo);
				wind1.add(wind);

			}

			// 解析生活提示
			String ganmao = jsonbean.getResult().getData().getLife().getInfo()
					.getGanmao().get(0);
			String yundong = jsonbean.getResult().getData().getLife().getInfo()
					.getYundong().get(0);
			String wuran = jsonbean.getResult().getData().getLife().getInfo()
					.getChuanyi().get(0);
			String ziwaixian = jsonbean.getResult().getData().getLife()
					.getInfo().getZiwaixian().get(0);
			String xiche = jsonbean.getResult().getData().getLife().getInfo()
					.getXiche().get(0);
			String kongtiao = jsonbean.getResult().getData().getLife()
					.getInfo().getKongtiao().get(0);
			ArrayList<String> gridviewlist = new ArrayList<String>();
			gridviewlist.add(xiche + "洗车");
			gridviewlist.add("感冒" + ganmao);
			gridviewlist.add("紫外线强度" + ziwaixian);
			gridviewlist.add(yundong + "运动");
			gridviewlist.add("污染" + wuran);
			gridviewlist.add("空调" + kongtiao);
			// 组装数据
			bundleData.putString("Temperature", t);
			bundleData.putString("info", i);
			bundleData.putString("city_name", city);
			bundleData.putString("pm25", pm252);
			bundleData.putInt("curPm", curPm);
			bundleData.putInt("pm25index", pm25index);
			bundleData.putInt("level", level);
			bundleData.putString("time", time);
			bundleData.putStringArrayList("dayt", dayTemperatureList1);
			bundleData.putStringArrayList("nightt", nightTemperatureList1);
			bundleData.putStringArrayList("gvlist", gridviewlist);
			bundleData.putStringArrayList("week", weeklist1);
			bundleData.putStringArrayList("weatherid", weatherIdList1);
			bundleData.putStringArrayList("weatherinfo", weatherinfo1);
			bundleData.putStringArrayList("wind", wind1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bundleData;
	}

}
