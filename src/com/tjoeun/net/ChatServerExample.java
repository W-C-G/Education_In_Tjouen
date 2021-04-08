package com.tjoeun.net;

import java.net.*;
import java.io.*;

public class ChatServerExample 
{
   public static void main(String[] args) {
      // ����
      try {
         ServerSocket svrSoc = new ServerSocket(1234);// 1024~65535
         
         while(true) {
            System.out.println("���� �����...");
            Socket soc = svrSoc.accept(); // ���
            System.out.println("Ŭ���̾�Ʈ ����");
            //��ſ� �����带 �����ϰ� ��ű���� �����ϰ� �Ѵ�(�α���)
            new CommThread(soc).start();
         }
      } catch (IOException e) {
         e.printStackTrace();
      }
      System.out.println("���� ����...");
   }
}

class CommThread extends Thread
{
   Socket soc;
   boolean signed;
   PrintWriter pw;
   BufferedReader br;
   
   CommThread(Socket soc){
      this.soc = soc;
      try {
         OutputStream os = soc.getOutputStream();
         OutputStreamWriter osw = new OutputStreamWriter(os);
         pw = new PrintWriter(osw);
         pw.println("�α������ּ���");
         pw.flush();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   @Override
   public void run() {
      login();
      if(!signed) return;
      try {
         while(true) {
            String line = br.readLine();
            pw.println(line);
            pw.flush();
         }
      }catch(Exception e) {
         e.printStackTrace();
         System.err.println("Ŭ���̾�Ʈ ����");
      }
      System.out.println("���� ��ž����� ����");
   }
   
   private void login() {
      try {
         InputStream is = soc.getInputStream();
         InputStreamReader isr = new InputStreamReader(is);
         br = new BufferedReader(isr);
         String idpwd = br.readLine();
         //System.out.println(idpwd);
         String[] loginData = idpwd.split(":");
         if(loginData[0].startsWith("u") &&
               loginData[1].startsWith("1")) {
            signed = true;
            pw.println("1");
         }else {
            pw.println("0");
         }
         pw.flush();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}