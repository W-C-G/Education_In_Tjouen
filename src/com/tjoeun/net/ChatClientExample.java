package com.tjoeun.net;

import java.net.*;
import java.util.*;
import java.io.*;

public class ChatClientExample 
{
   // Ŭ���̾�Ʈ
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
         //�����κ��� ���� ������ ��Ʈ�� ����
         var is = soc.getInputStream();
         var isr = new InputStreamReader(is);
         br = new BufferedReader(isr);
         String svrMsg = br.readLine();
         System.out.println(svrMsg); // �α������ּ���
         
         //������ ���� ������ ��Ʈ�� ����
         var os = soc.getOutputStream();
         var osw = new OutputStreamWriter(os);
         pw = new PrintWriter(osw);
         */
         oin = new ObjectInputStream(soc.getInputStream());
         String svrMsg = oin.readUTF();
         System.out.println(svrMsg);
         
         oos = new ObjectOutputStream(soc.getOutputStream());
         
         //������ �α���
         kbd = new Scanner(System.in);
         if(!login()) {
            System.err.println("�α��� ����");
            return;
         }
         
         new Thread() { // �޽��� ���� ������
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
               System.out.println("Ŭ���̾�Ʈ ������ ����");
            }
         }.start();
         
         // �޽����� ������ ����
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
      System.out.println("Ŭ���̾�Ʈ ����...");
   }
   
   private static boolean login() {
      System.out.print("���̵�:");
      String uid = kbd.nextLine();
      System.out.print("�� ȣ:");
      String upass = kbd.nextLine();

      try {
         oos.writeUTF(uid+":"+upass);
         oos.flush();
         String loginRes = oin.readUTF();
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