package com.qst.chat01;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Send implements Runnable{

   private BufferedReader console;
   private DataOutputStream dos;
   private Socket client;
   private  boolean isRunning;

    public Send(Socket client) {
        this.client=client;
            console  = new BufferedReader(new InputStreamReader(System.in));
        try {
            dos = new DataOutputStream(client.getOutputStream());
            isRunning =true;
        } catch (IOException e) {
            release();

        }
    }
    @Override
    public void run() {
        while (isRunning){
            try {
                String msg = console.readLine();
                if (!msg.equals("")){
                    send(msg);
                }
            } catch (IOException e) {
                release();
            }
        }

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
        SxtUtils.close(dos,client);
    }

}
