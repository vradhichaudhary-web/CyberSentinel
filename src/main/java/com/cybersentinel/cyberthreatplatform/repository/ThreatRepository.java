package com.cybersentinel.cyberthreatplatform.repository;

import com.cybersentinel.cyberthreatplatform.entity.Threat;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ThreatRepository extends JpaRepository<Threat, Long> {

    List<Threat> findByRiskLevel(String riskLevel);

}


