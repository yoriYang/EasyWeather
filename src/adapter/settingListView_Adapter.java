package adapter;

import java.util.ArrayList;

import activity.Setting;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juhedemo.R;

public class settingListView_Adapter extends BaseAdapter {

	private Context context;
	private ArrayList<String> settinglist;
	private TextView settingItem_text;
	private LayoutInflater layoutInflater;
	private ImageButton settingItem_button;

	public settingListView_Adapter(Context context,
			ArrayList<String> settinglist) {
		this.context = context;
		this.settinglist = settinglist;
		this.layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return settinglist.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			if (position == 4) {
				convertView = layoutInflater.inflate(
						R.layout.settinglistview_item2, null);
				settingItem_text = (TextView) convertView
						.findViewById(R.id.settingItem_text);
			} else {
				convertView = layoutInflater.inflate(
						R.layout.settinglistview_item, null);
				settingItem_text = (TextView) convertView
						.findViewById(R.id.settingItem_text);
				settingItem_button = (ImageButton) convertView
						.findViewById(R.id.settingItem_button);
			}
		}

		settingItem_text.setText(settinglist.get(position).toString());

		return convertView;
	}

}