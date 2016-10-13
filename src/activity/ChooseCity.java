package activity;

import com.example.juhedemo.R;

import fragment.maincity;
import fragment.maincity.FOneBtnClickListener;
import fragment.querycity;
import fragment.querycity.FTwoBtnClickListener;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

public class ChooseCity extends AppCompatActivity implements
		FOneBtnClickListener, FTwoBtnClickListener {
	private maincity Maincity;
	private querycity Querycity;
	private GridView maincity;
	private EditText importCity;
	private TextView querycity_result;
	private String[] citylist = { "北京", "天津", "上海", "重庆", "哈尔滨市 ", "齐齐哈尔",
			"鸡西市", "鹤岗市", "双鸭山 ", "大庆市", "伊春市", "佳木斯", "七台河 ", "牡丹江", "黑河",
			"绥化", "长春市", "吉林市", "四平市", "辽源市", "通化市", "白山市", "松原市", "白城市",
			"延边朝鲜族自治州", "沈阳市", "大连市", "鞍山市", "抚顺市", "本溪市", "丹东市", "锦州市", "营口市",
			"阜新市", "辽阳市", "盘锦市", "铁岭市", "朝阳市", "葫芦岛市", "济南市", "青岛市", "淄博市",
			"枣庄市", "东营市", " 烟台市", " 潍坊市 ", " 济宁市", " 泰安市", " 威海市", " 日照市 ",
			"莱芜市", " 临沂市 ", "德州市 ", "聊城市 ", "滨州市 ", "菏泽市", "太原市 ", "大同市",
			" 阳泉市", " 长治市", " 晋城市", " 朔州市", " 晋中市 ", "运城市 ", "忻州市", " 临汾市",
			" 吕梁市", "西安市", "铜川市 ", "宝鸡市", " 咸阳市", " 渭南市 ", "延安市 ", "汉中市",
			" 榆林市", " 安康市", " 商洛市", "石家庄市", " 唐山市", " 秦皇岛市", " 邯郸市 ", "邢台市",
			" 保定市", " 张家口市 ", "承德市 ", "沧州市 ", "廊坊市", " 衡水市", "郑州市", " 洛阳市",
			" 平顶山市 ", "安阳市", " 鹤壁市", " 新乡市 ", "焦作市", " 济源市", " 濮阳市", "许昌市",
			" 漯河市 ", "三门峡市 ", "南阳市", " 商丘市", " 信阳市 ", "周口市 ", "驻马店市", "武汉市 ",
			"黄石市 ", "十堰市 ", "宜昌市 ", "襄樊市 ", "鄂州市 ", "荆门市 ", "孝感市 ", "荆州市 ",
			"黄冈市  ", "咸宁市 ", "随州市 ", "恩施土家族苗族自治州", " 仙桃市 ", "潜江市", " 天门市",
			"神农架林区" };

	private String[] maincitylist = { "北京", "天津", "上海", "广州", "重庆", "深圳", "杭州",
			"南京", "青岛", "武汉", "宁波", "大连", "沈阳", "厦门", "成都", "济南", "哈尔滨", "西安",
			"长春", "南昌", "重庆" };
	private Toolbar mToolbar1;
	private ImageButton button1;
	private int theme = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (savedInstanceState == null) {

			SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);

			int colorID = pref.getInt("colorId", 0);
			switch (colorID) {
			case 0:
				setTheme(R.style.AppTheme);
				break;
			case 1:
				setTheme(R.style.AppTheme2);
				break;
			case 2:
				setTheme(R.style.AppTheme3);
				break;
			case 3:
				setTheme(R.style.AppTheme4);
				break;
			case 4:
				setTheme(R.style.AppTheme5);
				break;
			case 5:
				setTheme(R.style.AppTheme6);
				break;
			}
		} else {
			theme = savedInstanceState.getInt("theme");
		}
		super.onCreate(savedInstanceState);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.maincity);
		importCity = (EditText) findViewById(R.id.import_city);
		mToolbar1 = (Toolbar) findViewById(R.id.toolbar1);
		// mToolbar1.setTitle("");// 标题的文字需在setSupportActionBar之前，不然会无效
		this.setSupportActionBar(mToolbar1);
		getSupportActionBar().setHomeButtonEnabled(true); // 设置返回键可用
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		mToolbar1.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
				Intent intent = new Intent(ChooseCity.this, MainActivity.class);
				startActivity(intent);
			}
		});
		Drawable drawable1 = ContextCompat.getDrawable(this, R.drawable.query);
		drawable1.setBounds(0, 0, 40, 40);// 第一0是距左边距离，第二0是距上边距离，40分别是长宽
		importCity.setCompoundDrawables(drawable1, null, null, null);// 只放左边

		setDefaultFragment();
		SharedPreferences.Editor editor = getSharedPreferences("data",
				MODE_PRIVATE).edit();
		for (int i = 0; i < citylist.length; i++) {
			editor.putString("citlist", citylist[i]);
		}

		importCity.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				FragmentManager fm1 = getFragmentManager();
				FragmentTransaction transaction = fm1.beginTransaction();
				Querycity = new querycity(importCity.getText());
				transaction.replace(R.id.id_content, Querycity);
				transaction.commit();

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}

		});

		if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
			// 透明状态栏
			getWindow().addFlags(
					WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			// 透明导航栏
			getWindow().addFlags(
					WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
		}

	}

	public void setDefaultFragment() {
		// TODO Auto-generated method stub
		FragmentManager fm = getFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		Maincity = new maincity();
		transaction.replace(R.id.id_content, Maincity);
		transaction.commit();
	}

	@Override
	public void onFOneBtnClick(int arg2) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, MainActivity.class);
		intent.putExtra("citydata", maincitylist[arg2]);
		setResult(this.RESULT_OK, intent);
		finish();
	}

	@Override
	public void onFTwoBtnClick(String arg1) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, MainActivity.class);
		intent.putExtra("citydata", arg1);
		setResult(this.RESULT_OK, intent);
		finish();
	}

}
