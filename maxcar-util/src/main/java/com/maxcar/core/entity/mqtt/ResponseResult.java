package com.maxcar.core.entity.mqtt;

public class ResponseResult {
	
	private Integer result;
	private String display;
	private String voice;
	
	public ResponseResult() {
	}
	
	public ResponseResult(Integer result, String display, String voice) {
		super();
		this.result = result;
		this.display = display;
		this.voice = voice;
	}
	public Integer getResult() {
		return result;
	}
	public void setResult(Integer result) {
		this.result = result;
	}
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	public String getVoice() {
		return voice;
	}
	public void setVoice(String voice) {
		this.voice = voice;
	}
	
	

}
