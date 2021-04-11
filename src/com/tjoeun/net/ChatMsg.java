package com.tjoeun.net;

import java.io.Serializable;

/*
 * ��ü�� ����ȭ�Ͽ� stream���� �����ϱ� ���� ���� Ŭ����
 * �۽���, ������, �޽���, ���Ϸ��� ���� ���¸� ������ ����
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