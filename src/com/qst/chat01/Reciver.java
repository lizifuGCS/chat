package com.qst.chat01;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Reciver implements Runnable{
    private DataInputStream dis;
    private Socket client;
    private  boolean isRunning;

    public Reciver(Socket client) {
        try {
            dis = new DataInputStream(client.getInputStream());
            isRunning = true;
        } catch (IOException e) {
            release();
        }
    }

    @Override
    public void run() {
        while(isRunning){
            String  msg = null;
            try {
                msg = dis.readUTF();
            } catch (IOException e) {
                release();
            }
            System.out.println(msg);
        }

    }

    //关闭资源
    private  void release(){
        this.isRunning =false;
        SxtUtils.close(dis,client);
    }
}
