package geekbrains.java3.lesson3;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;


public class Main{
    public static void main (String[] args) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("result.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        CountDownLatch cdnl = new CountDownLatch(15);

        FileOutputStream finalFos = fos;
        Thread tA = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    try {
                        finalFos.write(65);
                        Thread.sleep(20);
                        cdnl.countDown();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread tB = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    try {
                        finalFos.write(66);
                        Thread.sleep(20);
                        cdnl.countDown();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread tC = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    try {
                        finalFos.write(67);
                        Thread.sleep(20);
                        cdnl.countDown();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        tA.start();
        tB.start();
        tC.start();

        try {
            cdnl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            fos.close();
            finalFos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class MainHomework3 {

    public static void mainHomework3(String[] args) throws Exception {
        readFile();
        concatFiles();
        readPage();
    }

    public static void readFile() throws Exception {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("task1.txt"));
        StringBuilder sb = new StringBuilder("");
        int x;
        while((x=bis.read())!=-1){
            sb.append((char)x);
        }

        System.out.println(sb);
        bis.close();
    }

    public static void concatFiles() throws Exception {
        ArrayList<FileInputStream> alis = new ArrayList<>();
        alis.add(new FileInputStream("1.txt"));
        alis.add(new FileInputStream("2.txt"));
        alis.add(new FileInputStream("3.txt"));
        alis.add(new FileInputStream("4.txt"));
        alis.add(new FileInputStream("5.txt"));

        SequenceInputStream sis = new SequenceInputStream(Collections.enumeration(alis));

        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("12345.txt"));
        int x;
        while((x=sis.read())!=-1){
            bos.write(x);
        }
        sis.close();
        bos.close();
    }

    public static void readPage() throws Exception {
        RandomAccessFile raf = new RandomAccessFile("wap.txt","r");
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("Enter page number (0 to exit)");
            int x = sc.nextInt();
            if(x==0)break;
            else {
                raf.seek((Math.abs(x)-1) * 1800);
                StringBuilder sb = new StringBuilder("");
                for (int i = 0; i < 1800; i++) {
                    sb.append((char) raf.read());
                }
                System.out.println(sb);
            }
        }
        raf.close();
    }
}
