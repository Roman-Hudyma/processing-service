package com.currency.consumer.api;


import com.currency.consumer.api.dto.CurrencyConversationRateDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Instant;

@FeignClient(
        name = "currency-rate-provider",
        url = "localhost:8080")
public interface CurrencyRateApi {
    @GetMapping("/currency/rate/{src}/{dst}")
    CurrencyConversationRateDto getCurrencyRate(
            @PathVariable("src") String src,
            @PathVariable("dst") String dst,
            @RequestParam("at") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant at);
}
