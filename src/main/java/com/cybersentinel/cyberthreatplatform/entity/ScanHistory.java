package com.cybersentinel.cyberthreatplatform.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "scan_history")
public class ScanHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String scanType;
    private String inputData;
    private String riskLevel;
    private int score;

    public ScanHistory() {
    }

    public ScanHistory(String scanType, String inputData, String riskLevel, int score) {
        this.scanType = scanType;
        this.inputData = inputData;
        this.riskLevel = riskLevel;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public String getScanType() {
        return scanType;
    }

    public void setScanType(String scanType) {
        this.scanType = scanType;
    }

    public String getInputData() {
        return inputData;
    }

    public void setInputData(String inputData) {
        this.inputData = inputData;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}