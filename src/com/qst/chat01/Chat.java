package com.qst.chat01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Chat {
    public static void main(String[] args) throws Exception {
        System.out.println("===========server=======");
        //1.指定服務器端要暴露的端口，使用ServerSocket創建服務器
        ServerSocket server =new ServerSocket(9999);

        while(true) {

            //2.阻塞式的得等待连接
            Socket client = server.accept();
            System.out.println("一个客户端建立了连接");
            new Thread(new Channel(client)).start();
        }
    }
    static  class Channel implements Runnable{

        private  DataOutputStream dos;
        private  DataInputStream  dis;
        private  Socket client;
        private  boolean isRunning;

        public Channel(Socket client) {
            this.client = client;
            try {
                dos = new DataOutputStream(client.getOutputStream());
                dis = new DataInputStream(client.getInputStream());
                isRunning  = true;
            } catch (IOException e) {
               release();
            }
        }

        //接受消息
        private String receive(){
            String msg ="";
            try {
                msg = dis.readUTF();
            } catch (IOException e) {
                release();
            }
            return  msg;
        }

        //发送消息
        private void send(String msg){
            try {
                dos.writeUTF(msg);
                dos.flush();
            } catch (IOException e) {
                release();
            }
        }

        //关闭资源
        private  void release(){
            this.isRunning =false;
            SxtUtils.close(dis,dos,client);
        }


        @Override
        public void run() {
            while(isRunning){
                String msg = receive();
                if (!msg.equals("")){
                    send(msg);
                }

            }
        }
    }

}


