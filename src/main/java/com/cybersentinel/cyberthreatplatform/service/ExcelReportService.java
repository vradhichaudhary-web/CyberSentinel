package com.cybersentinel.cyberthreatplatform.service;

import com.cybersentinel.cyberthreatplatform.entity.ScanHistory;
import com.cybersentinel.cyberthreatplatform.repository.ScanHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExcelReportService {

    private final ScanHistoryRepository scanHistoryRepository;

    public ExcelReportService(ScanHistoryRepository scanHistoryRepository) {
        this.scanHistoryRepository = scanHistoryRepository;
    }

    public List<ScanHistory> getAllScans() {
        return scanHistoryRepository.findAll();
    }
}