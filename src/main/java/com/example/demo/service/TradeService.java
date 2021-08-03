package com.example.demo.service;

import com.example.demo.entity.Trades;
import com.example.demo.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class TradeService {

    @Autowired
    TradeRepository tradeRepository;

    public Trades getTradeById(String id){
       Optional<Trades> tradeOption = tradeRepository.findById(id);
       if(tradeOption.isPresent()){
           return tradeOption.get();
       }
       return null;
    }

    public Trades getTradeByIsin(String isin){
         return tradeRepository.findByISIN(isin);
    }

    public List<Trades> getTradeByDate(String date){

        LocalDate dateOFTrade = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        return tradeRepository.findByTradeDateGreatherThan(dateOFTrade).collect(toList());
    }

}
