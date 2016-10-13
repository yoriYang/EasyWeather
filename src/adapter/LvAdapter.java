package adapter;

import java.util.ArrayList;

import com.example.juhedemo.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LvAdapter extends BaseAdapter {

	private LayoutInflater listContainer;
	private Context context;
	private TextView textview1;
	private TextView textview2;
	private TextView textview0;
	private TextView textview3;
	private TextView textview4;
	private ArrayList<String> daydata;
	private ArrayList<String> nightdata;
	private ArrayList<String> week;
	private ArrayList<String> weatherlist;
	private ArrayList<String> weatherinfo;
	private ArrayList<String> wind;
	private ImageView weatherIcon;
	int[] imageId = new int[] { R.drawable.w00, R.drawable.w01, R.drawable.w02,
			R.drawable.w03, R.drawable.w04, R.drawable.w05, R.drawable.w06,
			R.drawable.w07, R.drawable.w08, R.drawable.w09, R.drawable.w10,
			R.drawable.w11, R.drawable.w12, R.drawable.w13, R.drawable.w14,
			R.drawable.w15, R.drawable.w16, R.drawable.w17, R.drawable.w18,
			R.drawable.w19, R.drawable.w20, R.drawable.w21, R.drawable.w22,
			R.drawable.w23, R.drawable.w24, R.drawable.w25, R.drawable.w26,
			R.drawable.w27, R.drawable.w28, R.drawable.w29, R.drawable.w30,
			R.drawable.w31, R.drawable.w53 };

	public LvAdapter(Context context, ArrayList<String> daydata,
			ArrayList<String> nightdata, ArrayList<String> week,
			ArrayList<String> weatherlist, ArrayList<String> weatherinfo,
			ArrayList<String> wind) {
		super();
		this.daydata = daydata;
		this.nightdata = nightdata;
		this.context = context;
		this.week = week;
		this.weatherlist = weatherlist;
		this.weatherinfo = weatherinfo;
		this.wind = wind;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return nightdata.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		String[] a = { "今天 ", "明天 " };
		if (convertView == null) {
			listContainer = LayoutInflater.from(context);
			convertView = listContainer.inflate(R.layout.my_item, null);
			textview1 = (TextView) convertView.findViewById(R.id.textView11);
			textview2 = (TextView) convertView.findViewById(R.id.textView22);
			textview0 = (TextView) convertView.findViewById(R.id.textView00);
			textview3 = (TextView) convertView.findViewById(R.id.textView33);
			textview4 = (TextView) convertView.findViewById(R.id.textView44);
			weatherIcon = (ImageView) convertView
					.findViewById(R.id.weatherIcon);
			textview1.setText(daydata.get(position) + "° / ");
			textview2.setText(nightdata.get(position) + "°");
			textview3.setText(weatherinfo.get(position));
			textview4.setText(wind.get(position));
			if (Integer.parseInt(weatherlist.get(position)) > 0) {
				weatherIcon.setImageResource(imageId[Integer
						.parseInt(weatherlist.get(position)) - 1]);
			} else {
				weatherIcon.setImageResource(imageId[Integer
						.parseInt(weatherlist.get(position))]);
			}
			if (position < 2) {
				textview0.setText(a[position]);
			} else {
				textview0.setText("星期" + week.get(position));
			}
		}
		return convertView;
	}

}
