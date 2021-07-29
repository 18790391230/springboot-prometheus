package com.wuyiming.asyncchain;

public interface RequestProcessor {

    void processRequest(Request request);

    void shutdown();

}
