package com.tjoeun.net;

import java.io.Serializable;

public class ChatMsg implements Serializable{
	private String sender;
	private String receiver;
	private String msg;
	private byte[] filedata;
	
	ChatMsg(){}
	ChatMsg(String sender, String receiver, String msg, byte[] filedata){
		setSender(sender);
		setReceiver(receiver);
		setMsg(msg);
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