package adapter;

import java.util.ArrayList;

import com.example.juhedemo.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class gvadapter extends BaseAdapter {

	private Context context;
	private ArrayList<String> gvlist;
	private LayoutInflater listContainer;
	private ImageView ImageView;
	private TextView text;
	int[] imageId = new int[] { R.drawable.washcar, R.drawable.ill,
			R.drawable.sun, R.drawable.sport, R.drawable.wuran,
			R.drawable.kongtiao };

	public gvadapter(Context context, ArrayList<String> gvlist) {
		super();
		this.gvlist = gvlist;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return imageId.length;
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
			convertView = listContainer.inflate(R.layout.my_gitem, null);
			ImageView = (ImageView) convertView.findViewById(R.id.image);
			text = (TextView) convertView.findViewById(R.id.text);
			text.setText(gvlist.get(position));
			ImageView.setImageResource(imageId[position]);

		}

		return convertView;
	}

}
