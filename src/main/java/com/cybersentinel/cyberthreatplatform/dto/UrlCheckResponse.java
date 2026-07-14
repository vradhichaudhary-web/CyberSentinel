package com.cybersentinel.cyberthreatplatform.dto;

public class UrlCheckResponse {

    private String riskLevel;
    private int threatScore;
    private String result;

    public UrlCheckResponse(String riskLevel, int threatScore, String result) {
        this.riskLevel = riskLevel;
        this.threatScore = threatScore;
        this.result = result;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public int getThreatScore() {
        return threatScore;
    }

    public String getResult() {
        return result;
    }
}