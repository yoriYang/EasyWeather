package fragment;

import com.example.juhedemo.R;

import fragment.maincity.FOneBtnClickListener;
import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class querycity extends Fragment {
	private Editable importcity;
	private TextView querycity_result;

	private String[] citylist = { "北京", "天津", "上海", "重庆", "哈尔滨市 ", "齐齐哈尔",
			"鸡西市", "鹤岗市", "双鸭山 ", "大庆市", "伊春市", "佳木斯", "七台河 ", "牡丹江", "黑河",
			"绥化", "长春", "吉林", "四平", "辽源", "通化", "白山", "松原", "白城", "延边朝鲜族自治",
			"沈阳", "大连", "鞍山", "抚顺", "本溪", "丹东", "锦州", "营口", "阜新", "辽阳", "盘锦",
			"铁岭", "朝阳", "葫芦岛", "济南", "青岛", "淄博", "枣庄", "东营市", "烟台市", "潍坊",
			"济宁市", "泰安", "威海", "日照", "莱芜", "临沂 ", "德州 ", "聊城 ", "滨州 ", "菏泽",
			"太原", "大同", "阳泉", "长治", "晋城", "朔州", "晋中 ", "运城 ", "忻州", "临汾", "吕梁",
			"西安", "铜川 ", "宝鸡", " 咸阳", " 渭南 ", "延安 ", "汉中", "榆林", "安康", "商洛",
			"石家庄", "唐山", "秦皇岛", "邯郸 ", "邢台", "保定", "张家口 ", "承德 ", "沧州 ", "廊坊",
			"衡水", "郑州", "洛阳", "平顶山 ", "安阳", "鹤壁", "新乡 ", "焦作", "济源", " 濮阳",
			"许昌", "漯河 ", "三门峡 ", "南阳", "商丘", "信阳 ", "周口 ", "驻马店", "武汉 ", "黄石 ",
			"十堰 ", "宜昌", "襄樊 ", "鄂州 ", "荆门 ", "孝感 ", "荆州 ", "黄冈 ", "咸宁 ",
			"随州 ", "恩施", "仙桃市 ", "潜江", "天门", "神农架" };

	public querycity(Editable editable) {
		super();
		this.importcity = editable;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.querycity, container, false);
		querycity_result = (TextView) view.findViewById(R.id.querycity_result);
		for (int i = 0; i < citylist.length; i++) {

			if (importcity.toString().equals(citylist[i])) {

				querycity_result.setText(importcity);
			}
		}
		querycity_result.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (getActivity() instanceof FOneBtnClickListener) {
					((FTwoBtnClickListener) getActivity())
							.onFTwoBtnClick(importcity.toString());
				}
			}
		});
		return view;
	}

	public interface FTwoBtnClickListener {
		void onFTwoBtnClick(String improtcity1);
	}
}
