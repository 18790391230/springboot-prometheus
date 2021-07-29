package com.wuyiming.asyncchain;

import java.util.concurrent.LinkedBlockingQueue;

public class SyncRequestProcessor extends Thread implements RequestProcessor {

    LinkedBlockingQueue<Request> queuedRequests = new LinkedBlockingQueue<Request>();

    private RequestProcessor nextProcessor = null;

    public SyncRequestProcessor(RequestProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }


    @Override
    public void run() {
        try {
            while (true) {
                Request r = queuedRequests.take();
                if (r == Request.requestOfDeath) {
                    break;
                }

                pRequest(r);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void pRequest(Request r) {
        System.out.println("SyncRequestProcessor 处理请求");
        if (nextProcessor != null) {
            nextProcessor.processRequest(r);
        }
    }

    @Override
    public void processRequest(Request request) {
        queuedRequests.add(request);
    }

    @Override
    public void shutdown() {
        queuedRequests.add(Request.requestOfDeath);
        if (nextProcessor != null) {
            nextProcessor.shutdown();
        }
    }
}
