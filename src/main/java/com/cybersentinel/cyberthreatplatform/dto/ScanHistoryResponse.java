package com.cybersentinel.cyberthreatplatform.dto;

public class ScanHistoryResponse {

    private Long id;
    private String scanType;
    private String inputData;
    private String riskLevel;
    private int score;

    public ScanHistoryResponse(Long id,
                               String scanType,
                               String inputData,
                               String riskLevel,
                               int score) {

        this.id = id;
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

    public String getInputData() {
        return inputData;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public int getScore() {
        return score;
    }
}