package com.tjoeun.net;

import java.net.*;
import java.util.*;
import java.io.*;

public class ChatClientExample 
{
   // 클라이언트
   static BufferedReader br;
   static PrintWriter pw;
   static Scanner kbd;
   static String authId;
   
   public static void main(String[] args) {
      try {
         Socket soc = new Socket("localhost", 1234);
         
         //서버로부터 오는 데이터 스트림 구성
         InputStream is = soc.getInputStream();
         InputStreamReader isr = new InputStreamReader(is);
         br = new BufferedReader(isr);
         String svrMsg = br.readLine();
         System.out.println(svrMsg); // 로그인해주세요
         
         //서버로 가는 데이터 스트림 구성
         OutputStream os = soc.getOutputStream();
         OutputStreamWriter osw = new OutputStreamWriter(os);
         pw = new PrintWriter(osw);
         
         //서버에 로그인
         kbd = new Scanner(System.in);
         if(!login()) {
            System.err.println("로그인 실패");
            return;
         }
         
         new Thread() {
            @Override
            public void run() {
               String msg;
               try {
                  while(true) {
                     msg = br.readLine();
                     System.out.println(msg);
                     System.out.println("Msg:");
                  }
               } catch (IOException e) {
                  e.printStackTrace();
               }
               System.out.println("클라이언트 쓰레드 종료");
            }
         }.start();
         
         // 메시지를 서버로 전송한다
         String line;
         while(true) {
            System.out.println("Msg:");
            line = kbd.nextLine();
            pw.println(authId+":"+line);
            pw.flush();
         }
      } catch (UnknownHostException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (Exception e) {
         e.printStackTrace();
      }
      System.out.println("클라이언트 종료...");
   }
   
   private static boolean login() {
      System.out.print("아이디:");
      String uid = kbd.nextLine();
      System.out.print("암 호:");
      String upass = kbd.nextLine();
      pw.println(uid+":"+upass);
      pw.flush();

      try {
         String loginRes = br.readLine();
         if(loginRes.equals("1")) {
            authId = uid;
            System.out.println("로그인 성공");
            return true;
         }
      } catch (IOException e) {
         e.printStackTrace();
      }
      return false;
   }
}