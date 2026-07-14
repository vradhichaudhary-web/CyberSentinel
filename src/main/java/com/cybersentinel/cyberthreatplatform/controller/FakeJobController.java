package com.cybersentinel.cyberthreatplatform.controller;

import com.cybersentinel.cyberthreatplatform.dto.JobCheckRequest;
import com.cybersentinel.cyberthreatplatform.dto.JobCheckResponse;
import com.cybersentinel.cyberthreatplatform.entity.ScanHistory;
import com.cybersentinel.cyberthreatplatform.service.FakeJobService;
import com.cybersentinel.cyberthreatplatform.service.ScanHistoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/job")
@CrossOrigin
public class FakeJobController {

    private final FakeJobService fakeJobService;
    private final ScanHistoryService scanHistoryService;


    public FakeJobController(FakeJobService fakeJobService,
                             ScanHistoryService scanHistoryService) {
        this.fakeJobService = fakeJobService;
        this.scanHistoryService = scanHistoryService;
    }


    @PostMapping("/check")
    public JobCheckResponse checkJob(@RequestBody JobCheckRequest request) {

        JobCheckResponse response = fakeJobService.checkJob(request);


        ScanHistory history = new ScanHistory(
                "Fake Job Scan",
                request.getJobDescription(),
                response.getRiskLevel(),
                response.getScamScore()
        );


        scanHistoryService.saveHistory(history);


        return response;
    }
}
