package com.tjoeun.net;

import java.net.*;
import java.io.*;

public class ChatServerExample 
{
   public static void main(String[] args) {
      // 서버
      try {
         ServerSocket svrSoc = new ServerSocket(1234);// 1024~65535
         
         while(true) {
            System.out.println("서버 대기중...");
            Socket soc = svrSoc.accept(); // 대기
            System.out.println("클라이언트 접속");
            //통신용 쓰레드를 생성하고 통신기능을 실행하게 한다(로그인)
            new CommThread(soc).start();
         }
      } catch (IOException e) {
         e.printStackTrace();
      }
      System.out.println("서버 종료...");
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
         pw.println("로그인해주세요");
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
         System.err.println("클라이언트 퇴장");
      }
      System.out.println("서버 통신쓰레드 종료");
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