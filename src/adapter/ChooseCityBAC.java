package adapter;

import java.util.ArrayList;

import com.example.juhedemo.R;

import db.CityListDatabase;
import fragment.City;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ChooseCityBAC extends BaseAdapter {
	private Context context;
	private ArrayList<String> data;
	private LayoutInflater listContainer;
	private TextView text;
	private ImageView ImageView;
	private ImageButton deletebutton;
	private CityListDatabase dbHelper;

	public ChooseCityBAC(Context context, ArrayList<String> data) {
		super();
		this.context = context;
		this.data = data;

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

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			listContainer = LayoutInflater.from(context);
			convertView = listContainer.inflate(R.layout.choose_city_list_item,
					null);
			ImageView = (ImageView) convertView.findViewById(R.id.dingwei);
			text = (TextView) convertView.findViewById(R.id.cityname);
			text.setText(data.get(position));
			deletebutton = (ImageButton) convertView
					.findViewById(R.id.deletebutton);
			Log.d("data.get(position)", data.get(position));
			deletebutton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

					dbHelper = new CityListDatabase(context, "CityList.db",
							null, 2);
					SQLiteDatabase db = dbHelper.getWritableDatabase();
					// 查询Book表中所有的数据
					Cursor cursor = db.query("Book", null, null, null, null,
							null, null);
					if (cursor.moveToFirst()) {
						do {
							// 遍历Cursor对象，取出数据并打印
							String cityname = cursor.getString(cursor
									.getColumnIndex("cityname"));
							Log.d("queryCityData", "db name: " + cityname);
						} while (cursor.moveToNext());
					}
					db.delete("Book", "cityname = ?",
							new String[] { data.get(position - 1) });
					data.remove(position);
					notifyDataSetChanged();
					cursor.close();

				}

			});
			text.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					City city = new City();

					// city.getWeather(text.getText().toString());
					Log.d("queryCityData", text.getText().toString()
							+ "============================");
				}

			});
		}
		return convertView;
	}

}
