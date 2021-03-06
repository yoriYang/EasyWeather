package utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;

public class HttpUtil {

	public static final int SHOW_RESPONSE = 0;

	public static final String DEF_CHATSET = "UTF-8";
	public static final int DEF_CONN_TIMEOUT = 30000;
	public static final int DEF_READ_TIMEOUT = 30000;

	public static String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
	// 配置您申请的KEY
	public static final String APPKEY = "8cbfb673eaf00f29fdbd669821d8fe36";

	ArrayList<String> dayTemperatureList = new ArrayList<String>();
	ArrayList<String> nightTemperatureList = new ArrayList<String>();
	ArrayList<String> weeklist = new ArrayList<String>();
	ArrayList<String> weatherIdList = new ArrayList<String>();
	ArrayList<String> citylist = new ArrayList<String>();

	public interface HttpCallbackListener {
		void onFinish(String repsonse);

		void onError(Exception e);
	}

	public static void getWeather(final Context context, final String city,
			final HttpCallbackListener listener) {
		// TODO Auto-generated method stub

		new Thread(new Runnable() {
			@Override
			public void run() {

				String result = null;
				String url = "http://op.juhe.cn/onebox/weather/query";// 请求接口地址

				Map<String, String> params = new HashMap<String, String>();// 请求参数
				params.put("cityname", city);// 要查询的城市，如：温州、上海、北京
				params.put("key", APPKEY);// 应用APPKEY(应用详细页查询)
				params.put("dtype", "json");// 返回数据的格式,xml或json，默认json

				try {
					result = net(city, url, params, "GET", context);
					if (listener != null) {
						listener.onFinish(result.toString());
					}

				} catch (Exception e) {
					e.printStackTrace();
					if (listener != null) {
						listener.onError(e);
					}
				}

			}
		}).start();
	}

	public static String net(String city, String strUrl,
			Map<String, String> params, String method, Context context)
			throws Exception {
		HttpURLConnection conn = null;
		BufferedReader reader = null;
		String rs = null;

		try {

			StringBuffer sb = new StringBuffer();
			if (method == null || method.equals("GET")) {
				strUrl = strUrl + "?" + urlencode(params);
			}
			URL url = new URL(strUrl);
			conn = (HttpURLConnection) url.openConnection();
			if (method == null || method.equals("GET")) {
				conn.setRequestMethod("GET");
			} else {
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
			}
			conn.setRequestProperty("User-agent", userAgent);
			conn.setUseCaches(false);
			conn.setConnectTimeout(DEF_CONN_TIMEOUT);
			conn.setReadTimeout(DEF_READ_TIMEOUT);
			conn.setInstanceFollowRedirects(false);
			conn.connect();
			if (params != null && method.equals("POST")) {
				try {
					DataOutputStream out = new DataOutputStream(
							conn.getOutputStream());
					out.writeBytes(urlencode(params));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			InputStream is = conn.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				sb.append(strRead);
			}
			rs = sb.toString();
			// 缓存json文件
			Utility.saveJson(context, city, rs);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (conn != null) {
				conn.disconnect();
			}
		}
		return rs;
	}

	// 将map型转为请求参数型
	public static String urlencode(Map<String, String> data) {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry i : data.entrySet()) {
			try {
				sb.append(i.getKey()).append("=")
						.append(URLEncoder.encode(i.getValue() + "", "UTF-8"))
						.append("&");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}
