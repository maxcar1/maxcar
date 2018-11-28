package com.maxcar.core.entity.mqtt;

public class GateStatus {
	private Head head;
	private GateStatusBody body;
	
	public GateStatus() {
	}
	
	public GateStatus(Head head, GateStatusBody body) {
		super();
		this.head = head;
		this.body = body;
	}
	public Head getHead() {
		return head;
	}
	public void setHead(Head head) {
		this.head = head;
	}
	public GateStatusBody getBody() {
		return body;
	}
	public void setBody(GateStatusBody body) {
		this.body = body;
	}
	
}
