package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "trade_store")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Trades{
    @JsonProperty("TradeId")
    @Id
    public String tradeId;
    @JsonProperty("TradeIdVersion")
    public String tradeIdVersion;
    @JsonProperty("CreDate")
    public String creDate;
    @JsonProperty("ExpireDate")
    public String expireDate;
    @JsonProperty("AccountId")
    public String accountId;
    @JsonProperty("TradeDate")
    public String tradeDate;
    @JsonProperty("SettleDate")
    public String settleDate;
    @JsonProperty("Numbers")
    public String numbers;
    @JsonProperty("ISIN")
    public String iSIN;
}