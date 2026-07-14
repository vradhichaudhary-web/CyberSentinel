package com.cybersentinel.cyberthreatplatform.service;

import com.cybersentinel.cyberthreatplatform.entity.Threat;
import com.cybersentinel.cyberthreatplatform.repository.ThreatRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThreatService {

    private final ThreatRepository threatRepository;

    public ThreatService(ThreatRepository threatRepository) {
        this.threatRepository = threatRepository;
    }

    public Threat saveThreat(@NonNull Threat threat) {
        return threatRepository.save(threat);
    }

    public List<Threat> getAllThreats() {
        return threatRepository.findAll();
    }

    public List<Threat> getThreatsByRiskLevel(String riskLevel) {
        return threatRepository.findByRiskLevel(riskLevel);
    }

    public void deleteThreat(Long id) {
        threatRepository.deleteById(id);
    }

}