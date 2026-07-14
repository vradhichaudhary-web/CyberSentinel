package com.cybersentinel.cyberthreatplatform.repository;

import com.cybersentinel.cyberthreatplatform.entity.ScanHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScanHistoryRepository extends JpaRepository<ScanHistory, Long> {

    long countByScanType(String scanType);

    long countByRiskLevel(String riskLevel);
}