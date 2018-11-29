package com.maxcar.core.entity.mqtt;
/**
 * 车辆请求通行信息类
 * @author Administrator
 *
 */
public class passBody {
	
	private String park;
	private String channel;
	private Integer channel_type;
	private String tnx;
	private Car car;
	
	public passBody() {
	}
	
	public passBody(String park, String channel, Integer channel_type,
			String tnx, Car car) {
		super();
		this.park = park;
		this.channel = channel;
		this.channel_type = channel_type;
		this.tnx = tnx;
		this.car = car;
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
	public Integer getChannel_type() {
		return channel_type;
	}
	public void setChannel_type(Integer channel_type) {
		this.channel_type = channel_type;
	}
	public String getTnx() {
		return tnx;
	}
	public void setTnx(String tnx) {
		this.tnx = tnx;
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	
}
