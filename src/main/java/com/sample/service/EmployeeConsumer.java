package com.sample.service;

import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.sample.domain.Employee;

@Component
public class EmployeeConsumer {

    private static Logger logger = LoggerFactory.getLogger(EmployeeConsumer.class);

    @JmsListener(destination = "${queue.employee}")
    public void receiveEmployee(@Payload Employee employee,
                                    @Headers MessageHeaders headers,
                                    Message message,
                                    Session session) {
        logger.info("Start of [EmployeeConsumer:receiveEmployee] method");
        logger.info("[EmployeeConsumer:receiveEmployee] info received <" + employee + ">");
        logger.info("End of [EmployeeConsumer:receiveEmployee] method");

    } // end receiveEmployee method

} // end EmployeeConsumer class