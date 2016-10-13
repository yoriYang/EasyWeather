package adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class LinearLayoutForListView extends LinearLayout {
	public LinearLayoutForListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	private LvAdapter lvadapter;

	/**
	 * 绑定布局
	 */
	public void bindLinearLayout() {
		int count = lvadapter.getCount();
		removeAllViews();
		for (int i = 0; i < count; i++) {
			View v = lvadapter.getView(i, null, null);
			addView(v, i);
		}
	}

	public void setAdapter(LvAdapter adapter) {
		this.lvadapter = adapter;
	}
}
