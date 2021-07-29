package com.wuyiming.asyncchain;

import java.util.concurrent.LinkedBlockingQueue;

public class FirstProcessor extends Thread implements RequestProcessor{

    LinkedBlockingQueue<Request> submittedRequests = new LinkedBlockingQueue<Request>();

    private RequestProcessor nextProcessor = null;

    public FirstProcessor(RequestProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Request r = submittedRequests.take();
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
        System.out.println("处理请求");
        nextProcessor.processRequest(r);
    }

    @Override
    public void processRequest(Request request) {
        submittedRequests.add(request);
    }

    @Override
    public void shutdown() {
        submittedRequests.clear();
        submittedRequests.add(Request.requestOfDeath);
        nextProcessor.shutdown();

    }
}
