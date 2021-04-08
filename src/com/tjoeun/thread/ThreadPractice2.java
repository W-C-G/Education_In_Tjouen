package com.tjoeun.thread;
import java.io.*;
import java.util.*;

public class ThreadPractice2 
{
   static ByteArrayOutputStream bout;
   static PrintWriter pw;

   static{ 
      bout = new ByteArrayOutputStream();
      pw = new PrintWriter(bout);
   }
   
   public static void main(String[] args) {
      new Thread() {
         @Override
         public void run() {
            while(true) {
               date_store(); // 
               try {
                  Thread.sleep(1000);
               } catch (InterruptedException e) {
                  e.printStackTrace();
               }
            }
         }
      }.start();
      
      try {
         Thread.sleep(1000);
      } catch (InterruptedException e1) {
         e1.printStackTrace();
      }
      
      new Thread() {
         @Override
         public void run() {
            while(true) {
               date_get();
               try {
                  Thread.sleep(1000);
               } catch (InterruptedException e) {
                  e.printStackTrace();
               }
            }
         }
      }.start();
   }
   
   private static void date_store() {
      pw.println(new Date());
      pw.flush();
   }

   private static void date_get() {
      try {
    	 ByteArrayInputStream bain = new ByteArrayInputStream(bout.toByteArray());
    	 InputStreamReader isr = new InputStreamReader(bain);
         BufferedReader br = new BufferedReader(isr);

         String line = null;
         String last = null;
         while((line=br.readLine())!=null){
            last = line; // 버퍼에 저장된 마지막 행 추출
         }
         System.out.println(last);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}