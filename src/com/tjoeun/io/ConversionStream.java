package com.tjoeun.io;

import java.io.*;
import java.util.*;

public class ConversionStream 
{
   public static void main(String[] args) {
      // ���ڽ�Ʈ�� <-> ����Ʈ ��Ʈ�� ��ȣ ��ȯ
      String fpath = "C:/labs/sample.txt";
      try {
         //����Ʈ��Ʈ���� ����Ͽ� �ؽ�Ʈ�� �о����
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
            System.err.println("���� ��~");
         }*/
         
         //����Ʈ��Ʈ���� ����Ͽ� �ؽ�Ʈ ����ϱ�
         FileOutputStream fout = new FileOutputStream(fpath);
         OutputStreamWriter osw = new OutputStreamWriter(fout);
         PrintWriter pw = new PrintWriter(osw);
         pw.println("����Ʈ��Ʈ���� ���� �ؽ�Ʈ ����");
         pw.close();
         
      } catch (Exception e) {
         e.printStackTrace();
      }
      System.out.println("���α׷� ����...");
   }
}