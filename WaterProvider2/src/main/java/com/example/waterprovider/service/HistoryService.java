package com.example.waterprovider.service;

import com.example.waterprovider.exception.InvalidIdException;
import com.example.waterprovider.model.History;
import com.example.waterprovider.model.User;
import com.example.waterprovider.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRepository historyRepository;

    public List<History> getHistory() {

        return historyRepository.findAll();
    }


}
