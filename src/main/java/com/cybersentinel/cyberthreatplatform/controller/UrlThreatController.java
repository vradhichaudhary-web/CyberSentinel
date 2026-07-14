package com.cybersentinel.cyberthreatplatform.controller;

import com.cybersentinel.cyberthreatplatform.dto.UrlCheckRequest;
import com.cybersentinel.cyberthreatplatform.dto.UrlCheckResponse;
import com.cybersentinel.cyberthreatplatform.entity.ScanHistory;
import com.cybersentinel.cyberthreatplatform.service.ScanHistoryService;
import com.cybersentinel.cyberthreatplatform.service.UrlThreatService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/url")
@CrossOrigin
public class UrlThreatController {

    private final UrlThreatService urlThreatService;
    private final ScanHistoryService scanHistoryService;


    public UrlThreatController(UrlThreatService urlThreatService,
                               ScanHistoryService scanHistoryService) {
        this.urlThreatService = urlThreatService;
        this.scanHistoryService = scanHistoryService;
    }


    @PostMapping("/check")
    public UrlCheckResponse checkUrl(@RequestBody UrlCheckRequest request) {

        UrlCheckResponse response = urlThreatService.checkUrl(request);


        ScanHistory history = new ScanHistory(
                "URL Scan",
                request.getUrl(),
                response.getRiskLevel(),
                response.getThreatScore()
        );


        scanHistoryService.saveHistory(history);


        return response;
    }
}