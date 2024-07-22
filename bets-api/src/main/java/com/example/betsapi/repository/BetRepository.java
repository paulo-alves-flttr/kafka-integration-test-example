package com.example.betsapi.repository;

import com.example.betsapi.kafka.model.BetData;

import java.util.List;

public interface BetRepository {
    void save(BetData betData);

    void delete(String betId);

    BetData findById(String betId);

    List<BetData> findAll();
}
