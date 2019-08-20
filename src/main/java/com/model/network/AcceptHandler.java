package com.model.network;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class AcceptHandler implements Runnable {
    final ServerSocketChannel serverSocketChannel;
    final Selector selector;

    public AcceptHandler(ServerSocketChannel ssc,Selector sr){
        serverSocketChannel = ssc;
        selector = sr;
    }


    @Override
    public void run() {
        try {
            SocketChannel  sc = serverSocketChannel.accept();
            System.out.println("----accept--11111--");
            if (null!=sc){
                new ReadHandler(sc,selector);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
