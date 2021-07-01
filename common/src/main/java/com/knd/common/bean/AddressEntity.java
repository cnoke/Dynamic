package com.knd.common.bean;

import android.text.TextUtils;

import java.io.Serializable;

/**
 * @date on 2021/2/8
 * @author huanghui
 * @title 会员收货地址
 * @describe
 */
public class AddressEntity implements Serializable {

	private String id;
	/**
	 * userId
	 */
	private String userId;
	/**
	 * 收货人姓名
	 */
	private String name;
	/**
	 * 电话
	 */
	private String phone;
	/**
	 * 邮政编码
	 */
	private String postCode;
	/**
	 * 省份/直辖市
	 */
	private String province;
	/**
	 * 城市
	 */
	private String city;
	/**
	 * 区
	 */
	private String region;
	/**
	 * 详细地址(街道)
	 */
	private String detailAddress;
	/**
	 * 门牌号
	 */
	private String  roomNo;
	/**
	 * 省市区代码
	 */
	private String areacode;
	/**
	 * 是否默认
	 */
	private int defaultStatus;
	/**
	 * 经度
	 */
	private double longitude;
	/**
	 * 维度
	 */
	private double latitude;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getAreacode() {
		return areacode;
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	public int getDefaultStatus() {
		return defaultStatus;
	}

	public void setDefaultStatus(int defaultStatus) {
		this.defaultStatus = defaultStatus;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public String getFormat() {
		StringBuilder builder = new StringBuilder();
		isNo(builder,getProvince());
		isNo(builder,getCity());
		isNo(builder,getRegion());
		isNo(builder,getDetailAddress());
		isNo(builder,getRoomNo());
		return builder.toString();
	}

	public String getFormatNoRoomNo() {
		StringBuilder builder = new StringBuilder();
		isNo(builder,getProvince());
		isNo(builder,getCity());
		isNo(builder,getRegion());
		isNo(builder,getDetailAddress());
		return builder.toString();
	}

	private void isNo(StringBuilder builder,String s){
		if(TextUtils.isEmpty(s)){
			return;
		}
		builder.append(s);
	}

}
