package com.cybersentinel.cyberthreatplatform.service;

import com.cybersentinel.cyberthreatplatform.dto.JobCheckRequest;
import com.cybersentinel.cyberthreatplatform.dto.JobCheckResponse;
import com.cybersentinel.cyberthreatplatform.entity.ScanHistory;
import com.cybersentinel.cyberthreatplatform.repository.ScanHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FakeJobService {

    private final ScanHistoryRepository scanHistoryRepository;

    FakeJobService(ScanHistoryRepository scanHistoryRepository) {
        this.scanHistoryRepository = scanHistoryRepository;
    }

    public JobCheckResponse checkJob(JobCheckRequest request) {

        String job = request.getJobDescription().toLowerCase();

        int score = 0;
        StringBuilder reason = new StringBuilder();

        if (job.contains("registration fee")) {
            score += 30;
            reason.append("Registration fee detected. ");
        }

        if (job.contains("pay")) {
            score += 20;
            reason.append("Payment request detected. ");
        }

        if (job.contains("earn")) {
            score += 15;
            reason.append("Unrealistic earning claim detected. ");
        }

        if (job.contains("whatsapp")) {
            score += 20;
            reason.append("WhatsApp contact detected. ");
        }

        if (job.contains("urgent")) {
            score += 15;
            reason.append("Urgent hiring keyword detected. ");
        }

        String risk;

        if (score >= 60) {
            risk = "HIGH";
        } else if (score >= 30) {
            risk = "MEDIUM";
        } else {
            risk = "LOW";
        }

        if (reason.length() == 0) {
            reason.append("No suspicious keywords found.");
        }

        ScanHistory history = new ScanHistory(
                "FAKE_JOB",
                request.getJobDescription(),
                risk,
                score
        );

        scanHistoryRepository.save(history);

        return new JobCheckResponse(risk, score, reason.toString());
    }
}