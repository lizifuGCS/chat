package com.qst.chat01;


import java.io.*;
import java.net.Socket;

public class Client {


    public static void main(String[] args) throws IOException {
        System.out.println("=============Client=============");

        //1.建立连接：使用Socket创建客户端+服务的地址和端口
        Socket client = new Socket("localhost",9999);

        //2.发送消息
        new Thread(new Send(client)).start();

        //3.接受消息
        new Thread(new Reciver(client)).start();

    }
}
