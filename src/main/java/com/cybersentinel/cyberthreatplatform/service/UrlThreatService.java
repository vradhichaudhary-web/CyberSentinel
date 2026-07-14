 package com.cybersentinel.cyberthreatplatform.service;

import com.cybersentinel.cyberthreatplatform.dto.UrlCheckRequest;
import com.cybersentinel.cyberthreatplatform.dto.UrlCheckResponse;
import com.cybersentinel.cyberthreatplatform.entity.ScanHistory;
import com.cybersentinel.cyberthreatplatform.repository.ScanHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlThreatService {

    private final ScanHistoryRepository scanHistoryRepository;

    UrlThreatService(ScanHistoryRepository scanHistoryRepository) {
        this.scanHistoryRepository = scanHistoryRepository;
    }

    public UrlCheckResponse checkUrl(UrlCheckRequest request) {

        String url = request.getUrl().toLowerCase();

        int score = 0;
        StringBuilder result = new StringBuilder();

        if (url.contains("login")) {
            score += 25;
            result.append("Fake login page keyword detected. ");
        }

        if (url.contains("verify")) {
            score += 20;
            result.append("Verification scam keyword detected. ");
        }

        if (url.contains("bank")) {
            score += 25;
            result.append("Bank related suspicious keyword detected. ");
        }

        if (url.contains("free")) {
            score += 15;
            result.append("Free offer keyword detected. ");
        }

        if (url.contains("bit.ly") || url.contains("tinyurl")) {
            score += 20;
            result.append("Shortened suspicious URL detected. ");
        }

        String riskLevel;

        if (score >= 60) {
            riskLevel = "HIGH";
        } else if (score >= 30) {
            riskLevel = "MEDIUM";
        } else {
            riskLevel = "LOW";
        }

        if (result.length() == 0) {
            result.append("No suspicious patterns found.");
        }

        ScanHistory history = new ScanHistory(
                "URL",
                request.getUrl(),
                riskLevel,
                score
        );

        scanHistoryRepository.save(history);

        return new UrlCheckResponse(
                riskLevel,
                score,
                result.toString()
        );
    }
}