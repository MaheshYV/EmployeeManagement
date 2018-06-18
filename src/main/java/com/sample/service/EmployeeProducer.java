package com.sample.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.sample.domain.Employee;

@Service
public class EmployeeProducer {

    private static Logger logger = LoggerFactory.getLogger(EmployeeProducer.class);

    @Autowired
    private JmsTemplate jmsTemplate;
    
    public void sendToQueue(String queueName, Employee employee) {
    	logger.info("Start of [EmployeeProducer:sendToQueue] method ");
        logger.info("[EmployeeProducer:sendToQueue] sending " + employee + " with convertAndSend() to queue <" + queueName + ">");
        jmsTemplate.convertAndSend(queueName, employee);
    	logger.info("End of [EmployeeProducer:sendToQueue] method ");
    } // end sendToQueue method
} // end EmployeeProducer class