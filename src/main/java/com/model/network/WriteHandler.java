package com.model.network;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class WriteHandler implements Runnable {
    final SocketChannel socketChannel;
    final SelectionKey selectionKey;
    ByteBuffer output = ByteBuffer.allocate(1024);

    public WriteHandler(SocketChannel sc, Selector selector) throws IOException {
        socketChannel = sc;
        socketChannel.configureBlocking(false);
        selectionKey = socketChannel.register(selector,SelectionKey.OP_WRITE);
        selectionKey.interestOps(SelectionKey.OP_WRITE);
        selectionKey.attach(this);
    }

    boolean isWriteComplete(){return true;}

    void process(){}

    @Override
    public void run() {
        try {
            if (isWriteComplete()){
                System.out.println("----write----");
                process();
                socketChannel.write(output);
                selectionKey.cancel();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
