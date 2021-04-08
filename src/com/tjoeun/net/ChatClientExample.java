package com.tjoeun.net;

import java.net.*;
import java.util.*;
import java.io.*;

public class ChatClientExample 
{
   // Ŭ���̾�Ʈ
   static BufferedReader br;
   static PrintWriter pw;
   static Scanner kbd;
   static String authId;
   
   public static void main(String[] args) {
      try {
         Socket soc = new Socket("localhost", 1234);
         
         //�����κ��� ���� ������ ��Ʈ�� ����
         InputStream is = soc.getInputStream();
         InputStreamReader isr = new InputStreamReader(is);
         br = new BufferedReader(isr);
         String svrMsg = br.readLine();
         System.out.println(svrMsg); // �α������ּ���
         
         //������ ���� ������ ��Ʈ�� ����
         OutputStream os = soc.getOutputStream();
         OutputStreamWriter osw = new OutputStreamWriter(os);
         pw = new PrintWriter(osw);
         
         //������ �α���
         kbd = new Scanner(System.in);
         if(!login()) {
            System.err.println("�α��� ����");
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
               System.out.println("Ŭ���̾�Ʈ ������ ����");
            }
         }.start();
         
         // �޽����� ������ �����Ѵ�
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
      System.out.println("Ŭ���̾�Ʈ ����...");
   }
   
   private static boolean login() {
      System.out.print("���̵�:");
      String uid = kbd.nextLine();
      System.out.print("�� ȣ:");
      String upass = kbd.nextLine();
      pw.println(uid+":"+upass);
      pw.flush();

      try {
         String loginRes = br.readLine();
         if(loginRes.equals("1")) {
            authId = uid;
            System.out.println("�α��� ����");
            return true;
         }
      } catch (IOException e) {
         e.printStackTrace();
      }
      return false;
   }
}