package com.model.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Reactor extends Thread {
    final ServerSocketChannel serverSocketChannel;
    final Selector selector;

    Reactor(int port) throws IOException {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        serverSocketChannel.configureBlocking(false);
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        selectionKey.attach(new AcceptHandler(serverSocketChannel,selector));
    }

    @Override
    public void run() {
        try{
            while(true){
                while (!Thread.interrupted()){
                    selector.select();
                    Set selected = selector.selectedKeys();
                    Iterator it = selected.iterator();
                    while(it.hasNext()){
                        dispatch((SelectionKey) it.next());
                    }
                    selected.clear();
                    System.out.println("----accept----");
                }
            }

        }catch(IOException e){

        }
    }

    void dispatch(SelectionKey sk){
        Runnable runnable = (Runnable) sk.attachment();
        if (null!=runnable){
            runnable.run();
        }
    }
}
