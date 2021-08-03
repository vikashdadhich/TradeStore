package com.example.demo.repository;

import com.example.demo.entity.Trades;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.stream.Stream;

@Repository
public interface TradeRepository extends MongoRepository<Trades, String> {
    @Query("{iSIN : ?0}")
   public Trades findByISIN(String isin);
    //@Query("{tradeDate : {$gt : ?0}}")
    public Stream<Trades> findByTradeDateGreatherThan(LocalDate date);

}