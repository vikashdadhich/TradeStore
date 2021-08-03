package com.example.demo.kafka;

import com.example.demo.entity.Trades;
import com.example.demo.repository.TradeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public final class ConsumerService {
    private static final Logger logger = LoggerFactory.getLogger(ConsumerService.class);

    @Autowired
    TradeRepository repository;
    @KafkaListener(topics = "trade", groupId = "group_id")
    public void consume(String message) throws JsonProcessingException {
        logger.info(String.format("$$$$ => Consumed message: %s", message));
        ObjectMapper mapper = new ObjectMapper();
        Trades trade = mapper.readValue(message, Trades
                .class);
        repository.save(trade);
    }
}
