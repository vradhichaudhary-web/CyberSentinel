package com.cybersentinel.cyberthreatplatform.controller;

import com.cybersentinel.cyberthreatplatform.entity.ScanHistory;
import com.cybersentinel.cyberthreatplatform.service.ScanHistoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/history")
@CrossOrigin(origins = "*")
public class ScanHistoryController {

    private final ScanHistoryService scanHistoryService;

    public ScanHistoryController(ScanHistoryService scanHistoryService) {
        this.scanHistoryService = scanHistoryService;
    }

    // Get All Scan History
    @GetMapping
    public List<ScanHistory> getAllHistory() {
        return scanHistoryService.getAllHistory();
    }

    // Get History By Id
    @GetMapping("/{id}")
    public ScanHistory getHistoryById(@PathVariable Long id) {
        return scanHistoryService.getHistoryById(id);
    }

    // Delete History
    @DeleteMapping("/{id}")
    public String deleteHistory(@PathVariable Long id) {
        scanHistoryService.deleteHistory(id);
        return "Scan History Deleted Successfully";
    }
}