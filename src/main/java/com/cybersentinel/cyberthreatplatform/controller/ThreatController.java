package com.cybersentinel.cyberthreatplatform.controller;

import com.cybersentinel.cyberthreatplatform.entity.Threat;
import com.cybersentinel.cyberthreatplatform.service.ThreatService;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/threats")
@CrossOrigin
public class ThreatController {

    private final ThreatService threatService;

    public ThreatController(ThreatService threatService) {
        this.threatService = threatService;
    }

    @PostMapping
    public Threat saveThreat(@RequestBody @NonNull Threat threat) {
        return threatService.saveThreat(threat);
    }

    @GetMapping
    public List<Threat> getAllThreats() {
        return threatService.getAllThreats();
    }

    @GetMapping("/risk/{riskLevel}")
    public List<Threat> getThreatsByRiskLevel(@PathVariable String riskLevel) {
        return threatService.getThreatsByRiskLevel(riskLevel);
    }

    @DeleteMapping("/{id}")
    public void deleteThreat(@PathVariable Long id) {
        threatService.deleteThreat(id);
    }

}