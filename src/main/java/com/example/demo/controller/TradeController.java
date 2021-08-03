package com.example.demo.controller;

import com.example.demo.entity.Trades;
import com.example.demo.kafka.ProducerService;
import com.example.demo.service.TradeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/kafka")
public class TradeController {
    private static final Logger logger = LoggerFactory.getLogger(TradeController.class);
    @Autowired
    private  ProducerService producerService;

    @Autowired
    private TradeService tradeService;

    @PostMapping(value = "/publish")
    public void sendTrade(@RequestBody Trades message) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String trade = mapper.writeValueAsString(message);
            producerService.sendMessage(trade);
        } catch (JsonProcessingException e) {
            logger.error("error in publishing the message-"+ message);
        }
    }
    @GetMapping(value="/{id}/trade")
    public ResponseEntity<Trades> getTrade(@PathVariable(name="id") String id){
        return new ResponseEntity<>(tradeService.getTradeById(id), HttpStatus.OK);
    }

    @GetMapping(value="/isin/{isin}/trade")
    public ResponseEntity<Trades> getTradeByIsin(@PathVariable(name="isin") String isin){
        return new ResponseEntity<>(tradeService.getTradeByIsin(isin), HttpStatus.OK);
    }

    @GetMapping(value="/trade")
    public ResponseEntity<List<Trades>> getTradeByTradeDate(@RequestParam(name="tradeDate") String date){
        return new ResponseEntity<>(tradeService.getTradeByDate(date), HttpStatus.OK);
    }
}
