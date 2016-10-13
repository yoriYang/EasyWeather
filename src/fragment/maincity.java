package fragment;

import com.example.juhedemo.R;

import adapter.CCadapter;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;

public class maincity extends Fragment {
	private GridView maincity;
	private String[] maincitylist = { "北京", "天津", "上海", "广州", "重庆", "深圳", "杭州",
			"南京", "青岛", "武汉", "宁波", "大连", "沈阳", "厦门", "成都", "济南", "哈尔滨", "西安",
			"长春", "南昌", "重庆" };

	private TextView citytext;
	private Context context;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.choosecity, container, false);
		maincity = (GridView) view.findViewById(R.id.maincityGV);
		citytext = (TextView) view.findViewById(R.id.citytext);
		CCadapter ccadapter = new CCadapter(this.getActivity(), maincitylist);
		maincity.setAdapter(ccadapter);
		maincity.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				if (getActivity() instanceof FOneBtnClickListener) {
					((FOneBtnClickListener) getActivity()).onFOneBtnClick(arg2);
				}
			}
		});

		return view;
	}

	public interface FOneBtnClickListener {
		void onFOneBtnClick(int a);

	}

}
