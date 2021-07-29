package com.wuyiming.asyncchain;

public class ProcessorUtils {

    private static FirstProcessor firstProcessor;

    public static void startProcess() {
        SyncRequestProcessor syncRequestProcessor = new SyncRequestProcessor(null);
        syncRequestProcessor.start();
        firstProcessor = new FirstProcessor(syncRequestProcessor);
        firstProcessor.start();
    }

    public static void submitTask(Request request) {
        firstProcessor.processRequest(request);
    }
}
