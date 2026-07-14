package com.cybersentinel.cyberthreatplatform.service;

import com.cybersentinel.cyberthreatplatform.dto.DashboardStatsResponse;
import com.cybersentinel.cyberthreatplatform.repository.ScanHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final ScanHistoryRepository scanHistoryRepository;

    DashboardService(ScanHistoryRepository scanHistoryRepository) {
        this.scanHistoryRepository = scanHistoryRepository;
    }

    public DashboardStatsResponse getDashboardStats() {

        long totalScans = scanHistoryRepository.count();

        long fakeJobScans = scanHistoryRepository.countByScanType("FAKE_JOB");

        long urlScans = scanHistoryRepository.countByScanType("URL");

        long highRisk = scanHistoryRepository.countByRiskLevel("HIGH");

        long mediumRisk = scanHistoryRepository.countByRiskLevel("MEDIUM");

        long lowRisk = scanHistoryRepository.countByRiskLevel("LOW");

        return new DashboardStatsResponse(
                totalScans,
                fakeJobScans,
                urlScans,
                highRisk,
                mediumRisk,
                lowRisk
        );
    }
}