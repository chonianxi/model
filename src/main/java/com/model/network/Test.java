package com.model.network;

import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {
        new Reactor(8888).start();
    }


}
