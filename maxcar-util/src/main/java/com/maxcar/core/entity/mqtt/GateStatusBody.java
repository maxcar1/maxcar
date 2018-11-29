package com.maxcar.core.entity.mqtt;
/**
 * 道闸开关信息类
 * @author Administrator
 *
 */
public class GateStatusBody {
	
	
	private String park;
	private String channel;
	private Integer channel_type;//道闸类别
	private String device_number;//设备编号
	private Integer status;//0 closed 1 opened
	
	public GateStatusBody() {
	}
	public GateStatusBody(String park, String channel, Integer channel_type,
			String device_number, Integer status) {
		super();
		this.park = park;
		this.channel = channel;
		this.channel_type = channel_type;
		this.device_number = device_number;
		this.status = status;
	}
	public Integer getChannel_type() {
		return channel_type;
	}

	public void setChannel_type(Integer channel_type) {
		this.channel_type = channel_type;
	}

	public String getPark() {
		return park;
	}
	public void setPark(String park) {
		this.park = park;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getDevice_number() {
		return device_number;
	}
	public void setDevice_number(String device_number) {
		this.device_number = device_number;
	}
	
}
