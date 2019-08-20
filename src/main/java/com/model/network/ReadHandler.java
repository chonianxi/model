package com.model.network;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class ReadHandler implements Runnable {
    final SocketChannel socketChannel;
    final SelectionKey selectionKey;
    ByteBuffer input = ByteBuffer.allocate(1024);

    public ReadHandler(SocketChannel sc,Selector selector) throws IOException {
        socketChannel = sc;
        socketChannel.configureBlocking(false);
        socketChannel.read(input);
        selectionKey = socketChannel.register(selector,0);
        selectionKey.interestOps(SelectionKey.OP_READ);
        selectionKey.attach(this);
        selector.wakeup();
    }

    boolean isReadComplete(){return true;}

    void process(){}

    @Override
    public void run() {
        try {
            socketChannel.read(input);
            if (isReadComplete()){
                System.out.println("----read----");
                process();
                new WriteHandler(socketChannel,selectionKey.selector());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
