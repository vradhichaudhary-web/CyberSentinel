package com.cybersentinel.cyberthreatplatform.service;

import com.cybersentinel.cyberthreatplatform.entity.ScanHistory;
import com.cybersentinel.cyberthreatplatform.repository.ScanHistoryRepository;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScanHistoryService {

    private final ScanHistoryRepository scanHistoryRepository;

    public ScanHistoryService(ScanHistoryRepository scanHistoryRepository) {
        this.scanHistoryRepository = scanHistoryRepository;
    }

    // Get All Scan History
    public List<ScanHistory> getAllHistory() {
        return scanHistoryRepository.findAll();
    }

    // Get History By ID
    public ScanHistory getHistoryById(@NonNull Long id) {
        return scanHistoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Scan History Not Found"));
    }

    // Delete History
    public void deleteHistory(@NonNull Long id) {
        scanHistoryRepository.deleteById(id);
    }

    // Save Scan History
    public ScanHistory saveHistory(@NonNull ScanHistory scanHistory) {
        return scanHistoryRepository.save(scanHistory);
    }
}