package com.tjoeun.net;

import java.net.*;
import java.util.*;
import java.io.*;

public class ChatServerExample 
{
   static HashMap<String, UserIO> userMap = new HashMap<>();
   
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
   //PrintWriter pw;
   //BufferedReader br;
   ObjectInputStream oin;
   ObjectOutputStream oos;
   
   CommThread(Socket soc){
      this.soc = soc;
      try {
         //var os = soc.getOutputStream();
         //var osw = new OutputStreamWriter(os);
         //pw = new PrintWriter(osw);
         oos = new ObjectOutputStream(soc.getOutputStream());
         oos.writeUTF("�α������ּ���");
         oos.flush();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   @Override
   public void run() {
      login();
      if(!signed) return;
      ChatMsg msgObj;
      String sMsg;
      try {
         while(true) {
            //String line = br.readLine();
            msgObj = (ChatMsg)oin.readObject();
            /* �ӼӸ� ����
            String to = msgObj.getTo();
            if(to!=null) {
               UserIO u = ChatServer.userMap.get(to);
               var oos = u.getOos();
               oos.writeObject(msgObj);
               oos.flush();
            }*/
            //sMsg = msgObj.getMsg();
            //��� �������� ��½�Ʈ���� �޽����� �����Ѵ�
            Set<String> keys = ChatServer.userMap.keySet();
            Iterator<String> it = keys.iterator();
            ObjectOutputStream uoos = null;
            while(it.hasNext()) {
               String uid = it.next();
               UserIO uio = ChatServer.userMap.get(uid);
               uoos = uio.getOos();
               if(uoos==this.oos) continue;
               uoos.writeObject(msgObj);
               uoos.flush();
            }
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
         //var isr = new InputStreamReader(is);
         //br = new BufferedReader(isr);
         //String idpwd = br.readLine();
         oin = new ObjectInputStream(is);
         String idpwd = oin.readUTF();
         System.out.println(idpwd);
         String[] loginData = idpwd.split(":");
         if(loginData[0].startsWith("u") &&
               loginData[1].startsWith("1")) {
            signed = true;
            //pw.println("1");
            //ChatServer.userMap.put(loginData[0], 
            //      new UserIO(loginData[0],br,pw));
            oos.writeUTF("1");
            oos.flush();
            ChatServer.userMap.put(loginData[0], 
                  new UserIO(loginData[0],oin,oos));
         }else {
            //pw.println("0");
            oos.writeUTF("0");
            oos.flush();
         }
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}

/*
class UserIO
{
   private String uid;
   //private BufferedReader br;
   //private PrintWriter pw;
   private ObjectInputStream oin;
   private ObjectOutputStream oos;
   
   UserIO(){}
   
   UserIO(String uid, ObjectInputStream oin, 
         ObjectOutputStream oos){
      setUid(uid);
      setOin(oin);
      setOos(oos);
   }

   public String getUid() {
      return uid;
   }

   public void setUid(String uid) {
      this.uid = uid;
   }

   public ObjectInputStream getOin() {
      return oin;
   }

   public void setOin(ObjectInputStream oin) {
      this.oin = oin;
   }

   public ObjectOutputStream getOos() {
      return oos;
   }

   public void setOos(ObjectOutputStream oos) {
      this.oos = oos;
   }
}*/