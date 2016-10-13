package adapter;

import com.example.juhedemo.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CCadapter extends BaseAdapter {

	private Context context;
	private LayoutInflater listContainer;
	private TextView text;
	private String[] citylist;

	public CCadapter(Context context, String[] citylist) {
		super();
		this.citylist = citylist;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return citylist.length;
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
	public View getView(int position, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			listContainer = LayoutInflater.from(context);
			convertView = listContainer
					.inflate(R.layout.choose_city_item, null);

			text = (TextView) convertView.findViewById(R.id.text);
			text.setText(citylist[position]);

		}
		return convertView;
	}

}
