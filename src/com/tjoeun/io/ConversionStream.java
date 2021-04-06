package com.tjoeun.io;

import java.io.*;
import java.util.*;

public class ConversionStream 
{
   public static void main(String[] args) {
      // 문자스트림 <-> 바이트 스트림 상호 변환
      String fpath = "C:/labs/sample.txt";
      try {
         //바이트스트림을 사용하여 텍스트를 읽어오기
         FileInputStream fin = new FileInputStream(fpath);
         InputStreamReader isr = new InputStreamReader(fin);
         BufferedReader br = new BufferedReader(isr);
         
         String line = null;
         while(true) {
            line = br.readLine();
            if(line==null) break;
            System.out.println(line);
         }
         br.close();
         
         /*
         Scanner f = new Scanner(isr);
         try {
            while(true) {
               String line = f.nextLine();
               System.out.println(line);
            }
         }catch(NoSuchElementException nse) {
            System.err.println("파일 끝~");
         }*/
         
         //바이트스트림을 사용하여 텍스트 출력하기
         FileOutputStream fout = new FileOutputStream(fpath);
         OutputStreamWriter osw = new OutputStreamWriter(fout);
         PrintWriter pw = new PrintWriter(osw);
         pw.println("바이트스트림을 통한 텍스트 쓰기");
         pw.close();
         
      } catch (Exception e) {
         e.printStackTrace();
      }
      System.out.println("프로그램 종료...");
   }
}