package com.example.betsapi.repository;

import com.example.betsapi.kafka.model.BetData;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class BetRepositoryImpl implements BetRepository {
    private final Map<String, BetData> betDataMap = new ConcurrentHashMap<>();

    @Override
    public void save(BetData betData) {
        betDataMap.put(betData.betId(), betData);
    }

    @Override
    public void delete(String betId) {
        betDataMap.remove(betId);
    }

    @Override
    public BetData findById(String betId) {
        return betDataMap.get(betId);
    }

    @Override
    public List<BetData> findAll() {
        return new ArrayList<>(betDataMap.values());
    }
}
