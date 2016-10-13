package adapter;

import com.example.juhedemo.R;

import activity.ChooseCity;
import activity.MainActivity;
import activity.Setting;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class setcolor_adapter extends BaseAdapter {

	private String[] colorlist;
	private Context context;
	private LayoutInflater listContainer;
	private ImageButton color_IB;

	int[] imageId = new int[] { R.drawable.color_blue, R.drawable.color_green,
			R.drawable.color_grey, R.drawable.color_pink, R.drawable.color_red,
			R.drawable.color_violet };
	String[] colorId = new String[] { "#6699FF", "#FF7256", "#555555",
			"#FF83FA", "#B452CD", "#00EE00" };

	public setcolor_adapter(Context context, String[] colorlist) {
		super();
		this.colorlist = colorlist;
		this.context = context;
	}

	public setcolor_adapter(Context context) {
		super();
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 6;
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
	public View getView(final int position, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			listContainer = LayoutInflater.from(context);
			convertView = listContainer.inflate(R.layout.setcolor_item, null);
			color_IB = (ImageButton) convertView.findViewById(R.id.color_IV);
			// color_IB.setBackgroundResource(imageId[position]);
			color_IB.setBackgroundColor(Color.parseColor(colorId[position]));

		}
		color_IB.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				SharedPreferences.Editor editor = context.getSharedPreferences(
						"data", context.MODE_PRIVATE).edit();
				editor.putInt("colorId", position);
				editor.commit();
				Intent intent = ((Activity) context).getIntent();
				((Activity) context).overridePendingTransition(0, 0);// 不设置进入退出动画
				intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				((Activity) context).finish();
				((Activity) context).overridePendingTransition(0, 0);
				context.startActivity(intent);

				Intent intent1 = new Intent(context, ChooseCity.class);
				context.startActivity(intent1);

				Intent intent2 = new Intent(context, MainActivity.class);
				intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
						| Intent.FLAG_ACTIVITY_CLEAR_TASK);
				context.startActivity(intent2);
			}

		});
		return convertView;
	}

}
