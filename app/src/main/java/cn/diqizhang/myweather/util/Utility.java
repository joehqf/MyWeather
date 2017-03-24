package cn.diqizhang.myweather.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;

import cn.diqizhang.myweather.db.City;
import cn.diqizhang.myweather.db.County;
import cn.diqizhang.myweather.db.Province;

public class Utility {

	/**
	 * 解析和处理服务器返回的省级数据
	 */
	public static boolean handleProvincesResponse(String response) {
		if (!TextUtils.isEmpty(response)) {
			try {
				JSONArray allProvinces = new JSONArray(response);
				for(int i = 0; i<allProvinces.length(); i++){
					JSONObject provinceObject = allProvinces.getJSONObject(i);

					Province province = new Province();
					province.setProvinceName(provinceObject.getString("name"));
					province.setProvinceCode(provinceObject.getInt("id"));
					province.save();
				}
				return true;
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 解析和处理服务器返回的市级数据
	 */
	public static boolean handleCitiesResponse(String response, int provinceId) {
		if (!TextUtils.isEmpty(response)) {
			try {
				JSONArray allCities = new JSONArray(response);
				for(int i = 0; i<allCities.length(); i++){
					JSONObject cityObject = allCities.getJSONObject(i);

					City city = new City();
					city.setCityName(cityObject.getString("name"));
					city.setCityCode(cityObject.getInt("id"));
					city.setProvinceId(provinceId);
					city.save();
				}
				return true;
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 解析和处理服务器返回的县级数据
	 */
	public static boolean handleCountiesResponse(String response, int cityId) {
		if (!TextUtils.isEmpty(response)) {
			try {
				JSONArray allCounties = new JSONArray(response);
				for(int i = 0; i<allCounties.length(); i++){
					JSONObject cityObject = allCounties.getJSONObject(i);

					County county = new County();
					county.setCountyName(cityObject.getString("name"));
					county.setCountyCode(cityObject.getInt("id"));
					county.setCityId(cityId);
					county.save();
				}
				return true;
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}