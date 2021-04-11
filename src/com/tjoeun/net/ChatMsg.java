package com.tjoeun.net;

import java.io.Serializable;

/*
 * 객체를 직렬화하여 stream으로 전달하기 위해 만든 클래스
 * 송신자, 수신자, 메시지, 전하려는 파일 형태를 가지고 있음
 */
public class ChatMsg implements Serializable{
	private String sender;
	private String receiver;
	private String msg;
	private byte[] filedata;
	
	// Constructor Overloading
	ChatMsg(){}
	ChatMsg(String sender, String receiver, String msg){
		setSender(sender);
		setReceiver(receiver);
		setMsg(msg);
	}
	ChatMsg(String sender, String receiver, byte[] filedata){
		setSender(sender);
		setReceiver(receiver);
		setFiledata(filedata);
	}
	
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public byte[] getFiledata() {
		return filedata;
	}
	public void setFiledata(byte[] filedata) {
		this.filedata = filedata;
	}	
}