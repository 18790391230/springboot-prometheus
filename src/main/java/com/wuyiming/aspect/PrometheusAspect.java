package com.wuyiming.aspect;


import io.micrometer.core.instrument.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;

@Component
@Aspect
public class PrometheusAspect {


    @Autowired
    private MeterRegistry meterRegistry;

    private Counter requests;

    private DistributionSummary helloLatencySeconds;

    @PostConstruct
    public void init() {
        requests = meterRegistry.counter("requests_total", "status", "success");
//        helloLatencySeconds = meterRegistry.summary("hello_latency_seconds");
    }

//    static final Counter requests = Counter.build()
//            .name("requests_total").help("Total requests.").register();
//        Counter.builder("metrics.request.count").tags("appMark", appId, "apiCode", "product").register(meterRegistry).increment();

    @Pointcut("execution(* com.wuyiming.controller.*.*(..))")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Exception {

        Object proceed = null;

        Signature signature = pjp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        Timer timer = Metrics.timer("requests_cost_time", "method_name", method.getName());

        proceed = timer.recordCallable(() -> {
            try {
                return pjp.proceed();
            } catch (Throwable throwable) {
                throw new RuntimeException(throwable);
            }
        });

        requests.increment();
        return proceed;
    }
}
