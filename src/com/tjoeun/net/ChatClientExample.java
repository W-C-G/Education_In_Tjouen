package com.tjoeun.net;

import java.net.*;
import java.util.*;
import java.io.*;

public class ChatClientExample 
{
   // 클라이언트
   //static BufferedReader br;
   //static PrintWriter pw;
   static ObjectOutputStream oos;
   static ObjectInputStream oin;
   static Scanner kbd;
   static String authId;
   
   public static void main(String[] args) {
      try {
         Socket soc = new Socket("localhost", 1234);
         /*
         //서버로부터 오는 데이터 스트림 구성
         var is = soc.getInputStream();
         var isr = new InputStreamReader(is);
         br = new BufferedReader(isr);
         String svrMsg = br.readLine();
         System.out.println(svrMsg); // 로그인해주세요
         
         //서버로 가는 데이터 스트림 구성
         var os = soc.getOutputStream();
         var osw = new OutputStreamWriter(os);
         pw = new PrintWriter(osw);
         */
         oin = new ObjectInputStream(soc.getInputStream());
         String svrMsg = oin.readUTF();
         System.out.println(svrMsg);
         
         oos = new ObjectOutputStream(soc.getOutputStream());
         
         //서버에 로그인
         kbd = new Scanner(System.in);
         if(!login()) {
            System.err.println("로그인 실패");
            return;
         }
         
         new Thread() { // 메시지 수신 쓰레드
            @Override
            public void run() {
               String msg;
               try {
                  while(true) {
                     ChatMsg msgObj = (ChatMsg)oin.readObject();
                     msg = msgObj.getMsg();
                     System.out.println("\n"+msg);
                     System.out.print("msg:");
                  }
               } catch (Exception e) {
                  e.printStackTrace();
               }
               System.out.println("클라이언트 쓰레드 종료");
            }
         }.start();
         
         // 메시지를 서버로 전송
         String line;
         while(true) {
            System.out.print("msg:");
            line = kbd.nextLine();
            ChatMsg msgObj = new ChatMsg(authId, null, authId+":"+line);
            oos.writeObject(msgObj);
            oos.flush();
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

      try {
         oos.writeUTF(uid+":"+upass);
         oos.flush();
         String loginRes = oin.readUTF();
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