package com.cybersentinel.cyberthreatplatform.controller;

import com.cybersentinel.cyberthreatplatform.dto.DashboardResponse;
import com.cybersentinel.cyberthreatplatform.repository.ScanHistoryRepository;
import com.cybersentinel.cyberthreatplatform.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardController {

    private final UserRepository userRepository;
    private final ScanHistoryRepository scanHistoryRepository;

    public DashboardController(UserRepository userRepository,
                               ScanHistoryRepository scanHistoryRepository) {

        this.userRepository = userRepository;
        this.scanHistoryRepository = scanHistoryRepository;
    }

    @GetMapping("/api/dashboard")
    public DashboardResponse getDashboard() {

        long totalUsers = userRepository.count();

        long totalScans = scanHistoryRepository.count();

        long fakeJobScans = scanHistoryRepository.countByScanType("Fake Job Scan");

        long urlScans = scanHistoryRepository.countByScanType("URL Scan");

        long highRisk = scanHistoryRepository.countByRiskLevel("HIGH");

        long mediumRisk = scanHistoryRepository.countByRiskLevel("MEDIUM");

        long lowRisk = scanHistoryRepository.countByRiskLevel("LOW");

        return new DashboardResponse(
                totalUsers,
                totalScans,
                fakeJobScans,
                urlScans,
                highRisk,
                mediumRisk,
                lowRisk
        );
    }
}