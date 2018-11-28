package com.maxcar.core.entity.mqtt;

public class passRequest {
	private Head head;
	private passBody body;
	
	public passRequest() {
	}
	public passRequest(Head head, passBody body) {
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
	public passBody getBody() {
		return body;
	}
	public void setBody(passBody body) {
		this.body = body;
	}
	
}
