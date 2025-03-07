package com.ganesh.AOP.services.impl;

import com.ganesh.AOP.aspect.MyLogging;
import com.ganesh.AOP.services.ShipmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.beans.Transient;


@Slf4j
@Service
public class ShipmentServiceImpl implements ShipmentService {

    @Override
    @MyLogging
    public String orderPackage(Long orderId) {
        try{
            log.info("processing the order");
            Thread.sleep(1000);
        }catch(InterruptedException e){
            log.error("error occured while processing the order",e);
        }
        return "Order has been processed successfully,orderId"+orderId;
    }

    @Override
    @Transient
    public String trackPackage(Long orderId) {
        try{
            log.info("tracking the order");
            Thread.sleep(500);
            throw new RuntimeException("Exception occured during the trackpackage");
        }catch(InterruptedException e){
            throw new RuntimeException(e);
        }

    }
}
